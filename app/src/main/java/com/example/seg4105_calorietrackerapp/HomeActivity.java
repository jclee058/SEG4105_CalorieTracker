package com.example.seg4105_calorietrackerapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    private Button btnLogMeal;
    private ImageButton navHome, navCalendar, navSettings, btnSearch, btnProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Homepage - Meal App");
        }

        btnLogMeal   = findViewById(R.id.btnLogMeal);
        navHome      = findViewById(R.id.navHome);
        navCalendar  = findViewById(R.id.navCalendar);
        navSettings  = findViewById(R.id.navSettings);
        btnSearch    = findViewById(R.id.btnSearch);
        btnProfile   = findViewById(R.id.btnProfile);

        // Log Meal -> ouvrir ton écran de prise de photo par ex.
        btnLogMeal.setOnClickListener(v -> {
            // Exemple : aller vers TakePhotoActivity
            // Intent intent = new Intent(this, TakePhotoActivity.class);
            // startActivity(intent);
            Toast.makeText(this, "Log meal tapped", Toast.LENGTH_SHORT).show();
        });

        navHome.setOnClickListener(v ->
                Toast.makeText(this, "Already on Home", Toast.LENGTH_SHORT).show()
        );

        navCalendar.setOnClickListener(v ->
                Toast.makeText(this, "Open calendar (TODO)", Toast.LENGTH_SHORT).show()
        );

        navSettings.setOnClickListener(v ->
                Toast.makeText(this, "Open settings (TODO)", Toast.LENGTH_SHORT).show()
        );

        btnSearch.setOnClickListener(v ->
                Toast.makeText(this, "Search tapped (TODO)", Toast.LENGTH_SHORT).show()
        );

        btnProfile.setOnClickListener(v ->
                Toast.makeText(this, "Profile tapped (TODO)", Toast.LENGTH_SHORT).show()
        );
    }
}
