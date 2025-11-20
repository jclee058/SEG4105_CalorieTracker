package com.example.seg4105_calorietrackerapp

import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val tvBack = findViewById<TextView>(R.id.tvBack)
        val tvUser = findViewById<TextView>(R.id.tvUser)
        val etSearch = findViewById<EditText>(R.id.etSearch)
        val btnSearch = findViewById<TextView>(R.id.btnSearch)

        // Return to Home
        tvBack.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
            finish()
        }

        // To Profile
        tvUser.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }

        fun doSearch() {
            val keyword = etSearch.text.toString().trim()

            // Always navigate to results
            val intent = Intent(this, SearchResultsActivity::class.java)

            intent.putExtra("keyword", keyword)

            startActivity(intent)
        }

        // Click the search icon
        btnSearch.setOnClickListener { doSearch() }

        // Press keyboard "Search" action
        etSearch.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                doSearch()
                true
            } else false
        }
    }
}
