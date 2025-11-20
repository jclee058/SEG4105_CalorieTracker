package com.example.seg4105_calorietrackerapp

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SearchResultsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_results)

        // Return to Home page
        findViewById<TextView>(R.id.tvBack).setOnClickListener {
            val i = Intent(this, HomeActivity::class.java)
            i.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(i)
            finish()
        }

        // To Profile page
        findViewById<TextView>(R.id.tvUser).setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }
    }
}
