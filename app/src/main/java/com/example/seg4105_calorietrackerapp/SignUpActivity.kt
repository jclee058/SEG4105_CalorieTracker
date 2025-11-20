package com.example.seg4105_calorietrackerapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

/**
 * SignUpActivity handles the sign-up screen UI.
 * For now, clicking "Sign Up" simply navigates back to LoginActivity.
 */
class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Load the signup layout
        setContentView(R.layout.activity_signup)

        // Bind input fields
        val etFirstName = findViewById<EditText>(R.id.etFirstName)
        val etLastName = findViewById<EditText>(R.id.etLastName)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val etPasswordConfirm = findViewById<EditText>(R.id.etPasswordConfirm)

        // Bind submit button
        val btnSignUp = findViewById<Button>(R.id.btnSignUpSubmit)

        // TODO: Add validation + account creation logic here
        btnSignUp.setOnClickListener {
            // Go to login screen after signup
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
