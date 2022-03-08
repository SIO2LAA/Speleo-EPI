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

        ArrayList<Materiel> listeMateriels = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM materiel WHERE id = 1", null);
        while (cursor.moveToNext()) {
            /*Log.i("sql", cursor.getString(0));
            Toast.makeText(this, cursor.getString(1), Toast.LENGTH_LONG).show();*/
            Date date_ac = new Date(cursor.getString(4));
            Date date_pu = new Date(cursor.getString(5));
            Date date_lr = new Date(cursor.getString(6));
            Date date_f = new Date(cursor.getString(7));
            Materiel materiel = new Materiel(cursor.getInt(0), cursor.getString(1), cursor.getString(2),cursor.getString(3), date_ac, date_pu, date_lr, date_f, cursor.getString(8), cursor.getString(9), cursor.getInt(10), cursor.getInt(11), cursor.getInt(12), cursor.getInt(13));
            listeMateriels.add(materiel);
        }
        MaterielAdaptater materielAdapter = new MaterielAdaptater(this, R.layout.listeview_item, listeMateriels);
        listeMateriel.setAdapter(materielAdapter);

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