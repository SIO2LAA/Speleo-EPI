package fr.sio.app_epi2.controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;

import fr.sio.app_epi2.InfoMateriel;
import fr.sio.app_epi2.R;
import fr.sio.app_epi2.models.Materiel;

public class GestionMateriel extends AppCompatActivity implements View.OnClickListener {

    private ListView listeMateriel;
    private Button boutonInfoMateriel;
    private Intent infoMateriel;
    private CheckBox filtreDate;
    private CheckBox filtreModele;
    private CheckBox filtreMarquage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_materiel);
        listeMateriel = findViewById(R.id.listeMateriel);
        boutonInfoMateriel = findViewById(R.id.boutonInfoMateriel);
        boutonInfoMateriel.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (boutonInfoMateriel.isPressed()) {
            Materiel materiel = (Materiel) listeMateriel.getSelectedItem();
            infoMateriel = new Intent(this, InfoMateriel.class);
            infoMateriel.putExtra("Item", materiel.getIdMateriel());
            startActivity(infoMateriel);
        }
    }

}