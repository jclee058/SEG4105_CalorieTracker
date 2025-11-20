package com.example.seg4105_calorietrackerapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class DayMealAdd : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_day_meal_add)

        NavHelper.setupBottomNav(this, NavHelper.NavItem.CALENDAR)
        NavHelper.setupTopNav(this)
    }
}
