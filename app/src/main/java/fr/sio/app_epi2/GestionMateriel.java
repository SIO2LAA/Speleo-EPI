package fr.sio.app_epi2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

import fr.sio.app_epi2.models.Materiel;
import fr.sio.app_epi2.models.MaterielAdaptater;
//import fr.sio.app_epi2.models.MaterielAdaptater;

public class GestionMateriel extends AppCompatActivity implements View.OnClickListener {

    private ListView listeMateriel;
    private Button boutonInfoMateriel;
    private Intent infoMateriel;
    private CheckBox filtreDate;
    private CheckBox filtreModele;
    private CheckBox filtreMarquage;
    private SQLiteDatabase db = MainActivity.dbOpenHelper.getReadableDatabase();

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
        Materiel materiel = new Materiel(1, "Sangle", "Test","point vert", date_ac, date_pu, date_lr, date_f, "marquage", "toit");
        /*ArrayList<Materiel> listeMateriels = new ArrayList<>();
        listeMateriels.add(materiel);
        MaterielAdaptater<Materiel> materielAdapter = new MaterielAdaptater(this, R.layout.listeview_item, listeMateriels);
        listeMateriel.setAdapter(materielAdapter);*/
        Cursor libelle = db.rawQuery("SELECT libelle FROM materiel WHERE id = 1", null);
        while (libelle.moveToNext()) {
            Log.i("sql", libelle.getString(0));
            Toast.makeText(this, libelle.getString(0), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View view) {
        if (boutonInfoMateriel.isPressed()) {
            Materiel materiel = (Materiel) listeMateriel.getSelectedItem();
            infoMateriel = new Intent(this, InfoMateriel.class);
            infoMateriel.putExtra("Item", materiel.getIdMateriel());
            startActivity(infoMateriel);
        }
        if (listeMateriel.isPressed()) {
            Log.i("info", String.valueOf(listeMateriel.getSelectedItemPosition()));
            Materiel materiel = (Materiel) listeMateriel.getAdapter().getItem(listeMateriel.getSelectedItemPosition());
            infoMateriel = new Intent(this, InfoMateriel.class);
            infoMateriel.putExtra("idMateriel", materiel.getIdMateriel());
            startActivity(infoMateriel);
        }
    }

}