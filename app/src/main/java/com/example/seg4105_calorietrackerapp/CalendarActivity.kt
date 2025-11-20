package com.example.seg4105_calorietrackerapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class CalendarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)

        // Set up Bottom Nav navigation
        NavHelper.setupBottomNav(this, NavHelper.NavItem.CALENDAR)
        // Set up Top Nav actions (Back / Search / User)
        NavHelper.setupTopNav(this)
    }
}
