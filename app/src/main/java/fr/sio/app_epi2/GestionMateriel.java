package fr.sio.app_epi2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;

import java.util.Date;

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
        Date date_ac = new Date();
        Date date_pu = new Date();
        Date date_lr = new Date();
        Date date_f = new Date();
        Materiel materiel = new Materiel(1, "Sangle", "point vert", date_ac, date_pu, date_lr, date_f, "marquage", "toit");
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