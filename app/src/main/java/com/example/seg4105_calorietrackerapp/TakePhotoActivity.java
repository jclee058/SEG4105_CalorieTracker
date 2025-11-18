package com.example.seg4105_calorietrackerapp;  // adapte au tien

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class TakePhotoActivity extends AppCompatActivity {

    private ImageView photoPreview;
    private ImageView thumbnail;
    private TextView thumbnailBadge;
    private View shutterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_photo);  // ⬅️ ton XML avec la vignette + badge

        // Titre dans la barre du haut
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Take Photo");
        }

        // Récupération des vues
        photoPreview   = findViewById(R.id.photoPreview);
        thumbnail      = findViewById(R.id.thumbnail);
        thumbnailBadge = findViewById(R.id.thumbnailBadge);
        shutterButton  = findViewById(R.id.shutterButton);

        // Valeur du badge (ici "03" comme sur Figma)
        int index = 3;
        thumbnailBadge.setText(String.format("%02d", index));  // 03, 04, 05, etc.

        // Clic sur le bouton rond (future prise de photo)
        shutterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TakePhotoActivity.this,
                        "Prendre une photo ici 📸",
                        Toast.LENGTH_SHORT).show();
                // TODO : lancer la caméra
            }
        });

        // Clic sur la vignette (future ouverture de galerie)
        thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TakePhotoActivity.this,
                        "Ouvrir la galerie ici 🖼️",
                        Toast.LENGTH_SHORT).show();
                // TODO : ouvrir la galerie
            }
        });
    }
}

