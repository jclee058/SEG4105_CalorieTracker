package com.example.seg4105_calorietrackerapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {

    // UI references for the three summary cards
    private lateinit var tvMealsLoggedValue: TextView
    private lateinit var tvCaloriesValue: TextView
    private lateinit var tvBreakfastSummary: TextView
    private lateinit var tvLunchSummary: TextView
    private lateinit var tvDinnerSummary: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inflate the Home layout
        setContentView(R.layout.activity_home)

        // Setup navigation bars
        NavHelper.setupBottomNav(this, NavHelper.NavItem.HOME)
        NavHelper.setupTopNav(this)

        // Bind views from XML
        tvMealsLoggedValue = findViewById(R.id.tvMealsLoggedValue)
        tvCaloriesValue = findViewById(R.id.tvCaloriesValue)
        tvBreakfastSummary = findViewById(R.id.tvBreakfastSummary)
        tvLunchSummary = findViewById(R.id.tvLunchSummary)
        tvDinnerSummary = findViewById(R.id.tvDinnerSummary)

        // "Log Meal" opens DayView so user can add breakfast/lunch/dinner
        val btnLogMeal = findViewById<Button>(R.id.btnLogMeal)
        btnLogMeal.setOnClickListener {
            startActivity(Intent(this, DayView::class.java))
        }

        // Initial render when Home first loads
        renderFromStore()
    }

    override fun onResume() {
        super.onResume()
        renderFromStore()
    }

    private fun renderFromStore() {
        val count = MealStore.mealsLoggedCount()
        val totalCals = MealStore.totalCalories()

        // Top cards: progress + total calories
        tvMealsLoggedValue.text = "$count/3"
        tvCaloriesValue.text = "$totalCals/3000"

        // Meal Summary card: show food names, or "..." if not logged yet
        tvBreakfastSummary.text = MealStore.getMeal("breakfast")?.foodName ?: "..."
        tvLunchSummary.text = MealStore.getMeal("lunch")?.foodName ?: "..."
        tvDinnerSummary.text = MealStore.getMeal("dinner")?.foodName ?: "..."
    }
}
