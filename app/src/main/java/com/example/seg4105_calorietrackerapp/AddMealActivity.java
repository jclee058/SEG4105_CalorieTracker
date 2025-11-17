package com.example.seg4105_calorietrackerapp;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddMealActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meal);

        // Récupérer le bouton rond ("shutter")
        View shutterButton = findViewById(R.id.shutterButton);

        shutterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ici tu ouvriras la caméra plus tard
                Toast.makeText(AddMealActivity.this,
                        "Ouvrir la caméra ici 🚀",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
