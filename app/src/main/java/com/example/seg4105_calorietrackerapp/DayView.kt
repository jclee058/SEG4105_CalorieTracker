package com.example.seg4105_calorietrackerapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

/**
 * DayView shows the meals of the day (breakfast/lunch/dinner).
 * Users can add meals via CameraActivity, review saved meals via MealResultActivity,
 * and the UI is always rendered from MealStore (in-memory).
 */
class DayView : AppCompatActivity() {

    // Add Meal buttons
    private lateinit var btnAddBreakfast: Button
    private lateinit var btnAddLunch: Button
    private lateinit var btnAddDinner: Button

    // Detail containers (hidden until a meal is saved)
    private lateinit var layoutBreakfastDetails: LinearLayout
    private lateinit var layoutLunchDetails: LinearLayout
    private lateinit var layoutDinnerDetails: LinearLayout

    // Description text (food name)
    private lateinit var tvBreakfastDesc: TextView
    private lateinit var tvLunchDesc: TextView
    private lateinit var tvDinnerDesc: TextView

    // Calories text
    private lateinit var tvBreakfastCalories: TextView
    private lateinit var tvLunchCalories: TextView
    private lateinit var tvDinnerCalories: TextView

    // "Check" buttons (open saved meal again)
    private lateinit var btnBreakfastCheck: Button
    private lateinit var btnLunchCheck: Button
    private lateinit var btnDinnerCheck: Button

    private val cameraLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { res ->
            if (res.resultCode != RESULT_OK || res.data == null) return@registerForActivityResult

            val data = res.data!!
            val mealType = data.getStringExtra("mealType") ?: return@registerForActivityResult
            val isFood = data.getBooleanExtra("isFood", false)

            // Reject non-food results
            if (!isFood) {
                Toast.makeText(this, "Not food. Please try again.", Toast.LENGTH_SHORT).show()
                return@registerForActivityResult
            }

            // Read returned values from MealResultActivity
            val imageName = data.getStringExtra("imageName") ?: "Unknown"
            val foodName = data.getStringExtra("foodName") ?: imageName.substringBeforeLast(".")
            val calories = data.getIntExtra("calories", 0)
            val carbs = data.getIntExtra("carbs", 0)
            val protein = data.getIntExtra("protein", 0)
            val fat = data.getIntExtra("fat", 0)

            // Save the meal into in-memory store
            MealStore.saveMeal(
                MealStore.MealEntry(
                    mealType = mealType,
                    imageName = imageName,
                    foodName = foodName,
                    calories = calories,
                    carbs = carbs,
                    protein = protein,
                    fat = fat
                )
            )

            // Refresh UI with latest store values
            renderFromStore()
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_day_view)

        // Setup top/bottom navigation
        NavHelper.setupBottomNav(this, NavHelper.NavItem.CALENDAR)
        NavHelper.setupTopNav(this)

        // Bind views
        btnAddBreakfast = findViewById(R.id.btnAddBreakfast)
        btnAddLunch = findViewById(R.id.btnAddLunch)
        btnAddDinner = findViewById(R.id.btnAddDinner)

        layoutBreakfastDetails = findViewById(R.id.layoutBreakfastDetails)
        layoutLunchDetails = findViewById(R.id.layoutLunchDetails)
        layoutDinnerDetails = findViewById(R.id.layoutDinnerDetails)

        tvBreakfastDesc = findViewById(R.id.tvBreakfastDesc)
        tvLunchDesc = findViewById(R.id.tvLunchDesc)
        tvDinnerDesc = findViewById(R.id.tvDinnerDesc)

        tvBreakfastCalories = findViewById(R.id.tvBreakfastCalories)
        tvLunchCalories = findViewById(R.id.tvLunchCalories)
        tvDinnerCalories = findViewById(R.id.tvDinnerCalories)

        btnBreakfastCheck = findViewById(R.id.tvBreakfastCheck)
        btnLunchCheck = findViewById(R.id.tvLunchCheck)
        btnDinnerCheck = findViewById(R.id.tvDinnerCheck)

        // Add Meal -> open CameraActivity for that meal type
        btnAddBreakfast.setOnClickListener { openCameraFor("breakfast") }
        btnAddLunch.setOnClickListener { openCameraFor("lunch") }
        btnAddDinner.setOnClickListener { openCameraFor("dinner") }

        // Check -> open previously saved meal detail page
        btnBreakfastCheck.setOnClickListener { openSavedMeal("breakfast") }
        btnLunchCheck.setOnClickListener { openSavedMeal("lunch") }
        btnDinnerCheck.setOnClickListener { openSavedMeal("dinner") }

        // Initial render
        renderFromStore()
    }

    override fun onResume() {
        super.onResume()
        renderFromStore()
    }

    // Open camera to add/update a meal of given type
    private fun openCameraFor(type: String) {
        val i = Intent(this, CameraActivity::class.java)
        i.putExtra("mealType", type)
        cameraLauncher.launch(i)
    }

    // open MealResultActivity for a saved meal so user can review it
    private fun openSavedMeal(type: String) {
        val entry = MealStore.getMeal(type) ?: return
        val i = Intent(this, MealResultActivity::class.java).apply {
            putExtra("mealType", entry.mealType)
            putExtra("imageName", entry.imageName)
            putExtra("foodName", entry.foodName)
            putExtra("isFood", true)
            putExtra("calories", entry.calories)
            putExtra("carbs", entry.carbs)
            putExtra("protein", entry.protein)
            putExtra("fat", entry.fat)
        }
        startActivity(i)
    }

    // Render all three meals from MealStore into the UI
    private fun renderFromStore() {
        renderMeal(
            "breakfast",
            layoutBreakfastDetails, tvBreakfastDesc, tvBreakfastCalories,
            btnAddBreakfast, btnBreakfastCheck
        )
        renderMeal(
            "lunch",
            layoutLunchDetails, tvLunchDesc, tvLunchCalories,
            btnAddLunch, btnLunchCheck
        )
        renderMeal(
            "dinner",
            layoutDinnerDetails, tvDinnerDesc, tvDinnerCalories,
            btnAddDinner, btnDinnerCheck
        )
    }


    // Helper to render a single meal row
    private fun renderMeal(
        type: String,
        detailsLayout: LinearLayout,
        descView: TextView,
        calView: TextView,
        addBtn: Button,
        checkBtn: Button
    ) {
        val entry = MealStore.getMeal(type)

        if (entry == null) {
            // No meal saved yet
            detailsLayout.visibility = View.GONE
            addBtn.visibility = View.VISIBLE
            checkBtn.visibility = View.GONE
        } else {
            // Meal exists: show summary + Check
            detailsLayout.visibility = View.VISIBLE
            descView.text = entry.foodName
            calView.text = "${entry.calories} Cal"
            addBtn.visibility = View.GONE
            checkBtn.visibility = View.VISIBLE
        }
    }
}
