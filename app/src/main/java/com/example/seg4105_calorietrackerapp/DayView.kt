package com.example.seg4105_calorietrackerapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class DayView : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_day_view)

        // Bottom Nav
        NavHelper.setupBottomNav(this, NavHelper.NavItem.CALENDAR)
        // Top Nav
        NavHelper.setupTopNav(this)

        // Add Meal buttons
        val btnBreakfast = findViewById<Button>(R.id.btnAddBreakfast)
        val btnLunch = findViewById<Button>(R.id.btnAddLunch)
        val btnDinner = findViewById<Button>(R.id.btnAddDinner)

        // Shared click listener to open DayMealAdd page
        val goToAddedListener = View.OnClickListener {
            startActivity(Intent(this, DayMealAdd::class.java))
        }

        btnBreakfast.setOnClickListener(goToAddedListener)
        btnLunch.setOnClickListener(goToAddedListener)
        btnDinner.setOnClickListener(goToAddedListener)
    }
}
