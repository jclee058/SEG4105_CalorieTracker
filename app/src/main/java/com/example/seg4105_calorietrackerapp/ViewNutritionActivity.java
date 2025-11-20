package com.example.seg4105_calorietrackerapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ViewNutritionActivity extends AppCompatActivity {

    private ImageButton btnBack;
    private Button btnSaveMeal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_nutrition);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("View Nutrition");
        }

        btnBack = findViewById(R.id.btnBack);
        btnSaveMeal = findViewById(R.id.btnSaveMeal);

        // Retour en arrière
        btnBack.setOnClickListener(v -> finish());


        // Save
        btnSaveMeal.setOnClickListener(v ->
                Toast.makeText(this, "Meal saved ✅", Toast.LENGTH_SHORT).show()
        );
    }
}
