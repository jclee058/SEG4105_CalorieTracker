package com.example.seg4105_calorietrackerapp

import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MealResultActivity : AppCompatActivity() {

    // UI views
    private lateinit var ivPhoto: ImageView
    private lateinit var btnBack: TextView
    private lateinit var btnSave: Button

    private lateinit var tvMealTitle: TextView
    private lateinit var tvCalories: TextView
    private lateinit var tvCarbs: TextView
    private lateinit var tvProtein: TextView
    private lateinit var tvFat: TextView
    private lateinit var tvNotFoodHint: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inflate Meal Result screen
        setContentView(R.layout.activity_meal_result)

        // Bind views from XML
        ivPhoto = findViewById(R.id.ivPhoto)
        btnBack = findViewById(R.id.btnBackResult)
        btnSave = findViewById(R.id.btnSaveMeal)

        tvMealTitle = findViewById(R.id.tvMealTitle)
        tvCalories = findViewById(R.id.tvCalories)
        tvCarbs = findViewById(R.id.tvCarbs)
        tvProtein = findViewById(R.id.tvProtein)
        tvFat = findViewById(R.id.tvFat)
        tvNotFoodHint = findViewById(R.id.tvNotFoodHint)

        // Read data passed from CameraActivity
        val imageName = intent.getStringExtra("imageName") ?: ""
        val mealType = intent.getStringExtra("mealType") ?: "Meal"
        val isFood = intent.getBooleanExtra("isFood", false)

        val foodName = intent.getStringExtra("foodName") ?: imageName.substringBeforeLast(".")
        val calories = intent.getIntExtra("calories", 0)
        val carbs = intent.getIntExtra("carbs", 0)
        val protein = intent.getIntExtra("protein", 0)
        val fat = intent.getIntExtra("fat", 0)

        // Show the captured/test image as background
        if (imageName.isNotEmpty()) {
            val bitmap: Bitmap = AssetUtils.loadOneTestImage(this, imageName)
            ivPhoto.setImageBitmap(bitmap)
        }

        // Display meal type as title (Breakfast/Lunch/Dinner)
        tvMealTitle.text = mealType.replaceFirstChar { it.uppercase() }

        if (!isFood) {
            // Not food: hide nutrition numbers and show hint
            tvCalories.text = "--"
            tvCarbs.text = "--"
            tvProtein.text = "--"
            tvFat.text = "--"
            tvNotFoodHint.visibility = View.VISIBLE
        } else {
            // Food: populate nutrition values
            tvCalories.text = calories.toString()
            tvCarbs.text = "${carbs} g"
            tvProtein.text = "${protein} g"
            tvFat.text = "${fat} g"
        }

        // Back just closes this screen
        btnBack.setOnClickListener { finish() }

        // Save returns the meal data back to DayView via setResult
        btnSave.setOnClickListener {
            val data = intent.apply {
                putExtra("mealType", mealType)
                putExtra("imageName", imageName)
                putExtra("foodName", foodName)
                putExtra("isFood", isFood)
                putExtra("calories", calories)
                putExtra("carbs", carbs)
                putExtra("protein", protein)
                putExtra("fat", fat)
            }
            setResult(RESULT_OK, data)
            finish()
        }
    }
}
