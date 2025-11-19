package com.example.seg4105_calorietrackerapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ConfirmPhotoActivity extends AppCompatActivity {

    private ImageView confirmedPhoto;
    private Button btnConfirm;
    private Button btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_photo); // ⬅️ le layout corrigé

        // Titre en haut (optionnel)
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Confirm Photo");
        }

        // Références aux vues
        confirmedPhoto = findViewById(R.id.confirmedPhoto);
        btnConfirm     = findViewById(R.id.btnConfirm);
        btnCancel      = findViewById(R.id.btnCancel);

        // --------------------------
        // BOUTON CONFIRM (VERT)
        // --------------------------
        btnConfirm.setOnClickListener(v -> {
            Toast.makeText(this, "Photo confirmée ✔", Toast.LENGTH_SHORT).show();

            // TODO : logique après confirmation (ex. sauvegarder la photo, envoyer à une autre page)
            finish();
        });

        // --------------------------
        // BOUTON CANCEL (ROUGE)
        // --------------------------
        btnCancel.setOnClickListener(v -> {
            Toast.makeText(this, "Annulé ✖", Toast.LENGTH_SHORT).show();

            // Renvoie à la page précédente
            finish();
        });
    }
}
