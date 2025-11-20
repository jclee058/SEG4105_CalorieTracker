package com.example.seg4105_calorietrackerapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SettingsActivity : AppCompatActivity() {

    private var remindersOn = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        // Bottom Nav
        NavHelper.setupBottomNav(this, NavHelper.NavItem.SETTINGS)

        // Top Nav
        NavHelper.setupTopNav(this)

        // Return to Home
        findViewById<TextView>(R.id.tvBack).setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
            finish()
        }

        // Toggle reminders on/off
        val btnToggle = findViewById<Button>(R.id.btnToggleReminders)
        val timesGroup = findViewById<LinearLayout>(R.id.reminderTimes)

        // Initialize UI state
        btnToggle.text = if (remindersOn) "On" else "Off"
        timesGroup.visibility = if (remindersOn) View.VISIBLE else View.GONE

        btnToggle.setOnClickListener {
            remindersOn = !remindersOn
            btnToggle.text = if (remindersOn) "On" else "Off"
            timesGroup.visibility = if (remindersOn) View.VISIBLE else View.GONE
        }
    }
}
