package fr.sio.app_epi2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class InfoMateriel extends AppCompatActivity {
    private TextView libelle;
    private TextView date;
    private TextView modele;
    private TextView marquage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_materiel);
        libelle = findViewById(R.id.nomInfo);
        date = findViewById(R.id.dateMateriel);
        modele = findViewById(R.id.modeleMateriel);
        marquage = findViewById(R.id.marquageMateriel);

        Intent intent = getIntent();
        int id =  Integer.valueOf(intent.getStringExtra("idMateriel"));

    }
}