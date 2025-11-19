package com.example.seg4105_calorietrackerapp;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateFoodActivity extends AppCompatActivity {

    private EditText etItemName;
    private EditText etItemWeight;
    private Button btnSaveFood;
    private Button btnCancelFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_food);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Update Food");
        }

        etItemName    = findViewById(R.id.etItemName);
        etItemWeight  = findViewById(R.id.etItemWeight);
        btnSaveFood   = findViewById(R.id.btnSaveFood);
        btnCancelFood = findViewById(R.id.btnCancelFood);

        // (Optionnel) Pré-remplir si tu passes le nom/poids via Intent
        // String name   = getIntent().getStringExtra("item_name");
        // String weight = getIntent().getStringExtra("item_weight");
        // if (name != null)   etItemName.setText(name);
        // if (weight != null) etItemWeight.setText(weight);

        btnSaveFood.setOnClickListener(v -> {
            String name   = etItemName.getText().toString().trim();
            String weight = etItemWeight.getText().toString().trim();

            if (TextUtils.isEmpty(name) || TextUtils.isEmpty(weight)) {
                Toast.makeText(this,
                        "Please fill all fields",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            // TODO: renvoyer les données à l'activité précédente avec setResult(...)
            Toast.makeText(this,
                    "Food updated: " + name + " (" + weight + " g)",
                    Toast.LENGTH_SHORT).show();

            finish(); // fermer la popup
        });

        btnCancelFood.setOnClickListener(v -> {
            // Rien sauvegarder, juste fermer
            finish();
        });
    }
}