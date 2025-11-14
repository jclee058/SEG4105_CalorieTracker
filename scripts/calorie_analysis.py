import os
import json
import random
from dotenv import load_dotenv
from datetime import datetime
import boto3

"""
calorie_analysis.py

**AWS Credentials are saved in a .env file locally
    * Needed to run script locally

Uses AWS Rekognition Model (Free Version) to analyze a folder of images.
The model gives each image a set of labels.
This script checks to see if the image was labeled with 'Food'.

Fake caloric data is added to each image as well for use within the app.

Analysis results are output to a json file, within this folder.
"""

# Loading AWS credentials from .env
load_dotenv()

# setup AWS Rekognition client
client = boto3.client("rekognition")

# Relative path to images
IMAGE_FOLDER = "test_images"

def generate_output_filename():
    timestamp = datetime.now().strftime("%Y-%m-%d_%H-%M-%S")
    
    return f"analysis_results_{timestamp}.json"

def generate_caloric_data():
    calories = random.randint(50,2000) #cals
    carbs = random.randint(5, 100) #grams
    protein = random.randint(1, 50) #grams
    fat = random.randint(1, 50) #grams

    return{
        "calories": calories,
        "carbs": carbs,
        "protein": protein,
        "fat": fat
    }

def analyze_single_image(image_path):
    
    # Open and read image
    with open(image_path, "rb") as image:
        image_bytes = image.read()

    # Send the image to the model, and have it detect the labels
    model_response = client.detect_labels(
        Image={"Bytes": image_bytes},
        MaxLabels=10,
        MinConfidence=75
    )

    # Check if Food shows up in labels assigned to image
    for label in model_response["Labels"]:
        if label["Name"] == "Food":
            return True
        
    return False

def main():
    analysis_results = {}

    # Check to see if image folder exists
    if not os.path.exists(IMAGE_FOLDER):
        print(f"Folder '{IMAGE_FOLDER}' does not exist!")
        return
    
    # Analyze each image in the folder
    for filename in os.listdir(IMAGE_FOLDER):
        if filename.lower().endswith((".jpg", ".jpeg", ".png")):
            image_path = os.path.join(IMAGE_FOLDER, filename)
            is_food = analyze_single_image(image_path)
            result = {"Food": is_food}

            if is_food:
                result["nutritionData"] = generate_caloric_data()

            analysis_results[filename] = result

    # Get output file name
    output_file = generate_output_filename()

    # Save analysis results to json
    with open(output_file, "w") as file:
        json.dump(analysis_results, file, indent=2)

    print(f"\nAnalysis complete! Results saved to '{output_file}'")

if __name__=="__main__":
    main()