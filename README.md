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
---

## Android App (Setup / Run / Test)

### 1. Prerequisites
Please make sure you have the following installed/configured:

- **Android Studio** (a recent stable version is recommended)
- **JDK 17+** (Android Studio usually ships with a compatible JDK)
- **Android SDK** with a recent API level (e.g., **API 33/34**)
- One of the following:
  - **Android Emulator** (Pixel devices recommended), or
  - **Physical Android phone** with USB debugging enabled

---

### 2. Open the Project
1. Clone or download this repository.
2. Open **Android Studio → Open** and select the project root folder.
3. Wait for **Gradle Sync** to finish.
4. If Android Studio suggests upgrading Gradle or plugins, you may accept the defaults.

---

### 3. Run the App
1. **Start an emulator**
   - In Android Studio, go to **Tools → Device Manager**.
   - If no emulator exists yet, click **Create device**, choose a **Pixel** model, and finish the setup.
   - Click the **Play ▶** button next to the emulator to launch it.
2. In the top toolbar, select the **app** run configuration.
3. Click **Run ▶ (Run app)**.
4. The app should launch directly into the **Login** screen.

---

### 4. Login Credentials (for grading)
To enter the app, please use:

- **Email:** `admin`
- **Password:** `123`

Any other input will be rejected.

---

### 5. Main Functional Test Flow

#### A) Home Page – Live Summary
After logging in, the Home page shows:

- **Meals Logged** (updates from `0/3 → 1/3 → 2/3 → 3/3`)
- **Total calories for the day**
- **Meal Summary placeholders** (Breakfast / Lunch / Dinner names)

These values automatically update after meals are saved in **Day View Page**.

#### B) Go to DayView (Log Meals)
1. Tap **“Log Meal”** on the Home page to open **Day View Page**.
2. Under **Breakfast / Lunch / Dinner**, tap **“Add Meal”**.
3. You will enter **Camera**.

#### C) CameraActivity (Mock Camera)
- Every time you enter CameraActivity, it randomly loads one test image from:
  - `assets/test_image/`

When you press the capture button:

- **If the image is food:**  
  → navigates to **Meal Result**.

- **If the image is NOT food:**  
  → a large centered hint appears:  
  **“Please take a photo of food”**  
  and the page is locked (no switching / no navigation).  
  You must press **←** to return, then re-enter CameraActivity from DayView.

#### D) Meal Result
- Displays the selected photo as the background.
- Shows nutrition info
- Tap **Save** to return to DayView.

#### E) DayView After Saving
The corresponding meal section will update:

- Shows the **food name** (e.g., “Yogurt Bowl”) and calories.
- A **“Check”** button appears.
- Tapping **Check** reopens the saved **Meal Result**.

Meal data is stored in memory so users can revisit logged meals anytime during the session.

---

### 6. Run Unit Tests (if needed)

- **Run in Android Studio:**
1. Open:
   `app > src > test > java > com.example.seg4105_calorietrackerapp`
2. Right-click `ExampleUnitTest` or `MealStoreTest`
3. Click **Run**
4. View results in the **Test Results** panel

- **Run by Gradle (from project root):**
```bash
./gradlew testDebugUnitTest
