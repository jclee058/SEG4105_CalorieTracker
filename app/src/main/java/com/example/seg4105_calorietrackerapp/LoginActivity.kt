package com.example.seg4105_calorietrackerapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Load login layout
        setContentView(R.layout.activity_login)

        // Bind input fields
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)

        // Bind buttons
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val btnSignUp = findViewById<Button>(R.id.btnSignUp)

        // TODO: Add real authentication/validation here
        btnLogin.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }

        // Open the sign-up page
        btnSignUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
}
