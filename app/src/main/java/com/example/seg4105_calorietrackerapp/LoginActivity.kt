package com.example.seg4105_calorietrackerapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Bind input fields
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)

        // Bind buttons
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val btnSignUp = findViewById<Button>(R.id.btnSignUp)

        btnLogin.setOnClickListener {
            val emailInput = etEmail.text.toString().trim()
            val passwordInput = etPassword.text.toString().trim()

            val correctEmail = "admin"
            val correctPassword = "123"

            if (emailInput == correctEmail && passwordInput == correctPassword) {
                // Login success
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                // Login failed
                Toast.makeText(
                    this,
                    "Invalid email or password. Please try again.",
                    Toast.LENGTH_SHORT
                ).show()

                // Optional: clear password for safety
                etPassword.text.clear()
            }
        }

        // Open the sign-up page
        btnSignUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
}
