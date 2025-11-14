# SEG4105_CalorieTracker

Work for SEG4105 Course Project - Calorie Tracker App Prototype


## AWS Rekognition Script
### Script Overview & Project Usage
This project uses the Free version of Amazon's Rekognition Model to analyze an existing folder of images. The model assigns a set of Labels to each image. To mock the app's intended functionality, our script checks to see if 'Food' is one of these labels.

The caloric data for each image is randomly generated in the same script and attached to the model's results. Again, this was done to mock the app's intended functionality. 

The script results are saved in a json file (under /scripts...), read by the Android Studio application and displayed to the user. 

### Credentials
**The following information has been added for documentation purposes**

To run /scripts/calorie_analysis.py you will need to have a set of AWS credentials setup. 

> For privacy purposes, these credentials have not been published to the repository!
> 
> However, it is not necessary to be able to run the script since result data is saved/published to the repo.
>
> The Android Studios application simply reads the json file to display information to the user. This was done so the project could be run/graded without credential sharing.

To run the script locally, add a .env file under .../scripts and add the following (with your key values):

```
AWS_ACCESS_KEY_ID=
AWS_SECRET_ACCESS_KEY=
AWS_DEFAULT_REGION=us-east-1
```

You can then run the script by:
```
python calorie_analysis.py
```
