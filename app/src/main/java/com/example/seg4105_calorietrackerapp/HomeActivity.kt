package com.example.seg4105_calorietrackerapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        NavHelper.setupBottomNav(this, NavHelper.NavItem.HOME)
        NavHelper.setupTopNav(this)

        // Log Meal button
        val btnLogMeal = findViewById<Button>(R.id.btnLogMeal)
        btnLogMeal.setOnClickListener {
            startActivity(Intent(this, DayView::class.java))
        }
    }
}
