package fr.sio.app_epi2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import fr.sio.app_epi2.models.Materiel;
import fr.sio.app_epi2.models.MaterielAdaptater;
//import fr.sio.app_epi2.models.MaterielAdaptater;

public class GestionMateriel extends AppCompatActivity implements AdapterView.OnItemClickListener, CompoundButton.OnCheckedChangeListener, SearchView.OnQueryTextListener, AdapterView.OnItemSelectedListener {

    private ListView listeMateriel;
    private Intent infoMateriel;
    private CheckBox filtreDate;
    private CheckBox filtreModele;
    private CheckBox filtreMarquage;
    private SearchView search;
    private Spinner selection;
    private MaterielAdaptater materielAdapter;
    private ArrayList<Materiel> listeMateriels;
    private SQLiteDatabase db = MainActivity.dbOpenHelper.getReadableDatabase();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_materiel);
        listeMateriel = findViewById(R.id.listeMateriel);
        filtreDate = findViewById(R.id.filtreDate);
        filtreModele = findViewById(R.id.filtreModele);
        filtreMarquage = findViewById(R.id.filtreMarquage);
        search = findViewById(R.id.recherche);
        selection = findViewById(R.id.selection);
        selection.setOnItemSelectedListener(this);
        search.setOnQueryTextListener(this);
        filtreDate.setOnCheckedChangeListener(this);
        filtreMarquage.setOnCheckedChangeListener(this);
        filtreModele.setOnCheckedChangeListener(this);
        listeMateriels = new ArrayList<>();
        materielAdapter = new MaterielAdaptater(this, R.layout.listeview_item, listeMateriels);
        listeMateriel.setOnItemClickListener(this);

        ArrayList<String> filtres = new ArrayList<>();
        filtres.add("Modele");
        filtres.add("Marquage");
        filtres.add("Date");

        ArrayAdapter<CharSequence> filtresAdaptateur = ArrayAdapter.createFromResource(this, R.array.filtres, android.R.layout.simple_spinner_item);

        selection.setAdapter(filtresAdaptateur);

        Cursor cursor = db.rawQuery("SELECT * FROM materiel", null);
        SimpleDateFormat format = new SimpleDateFormat("y-m-d");



        while(cursor.moveToNext()) {
            Log.i("cursor", "id = " + cursor.getString(0));
            Date date_ac = new Date();
            Date date_pu = new Date();
            Date date_lr = new Date();
            Date date_f = new Date();

            try {
                date_ac = format.parse(cursor.getString(4));
                date_pu = format.parse(cursor.getString(5));
                date_lr = format.parse(cursor.getString(6));
                date_f = format.parse(cursor.getString(7));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Materiel materiel = new Materiel(cursor.getInt(0), cursor.getString(1), cursor.getString(2),cursor.getString(3), date_ac, date_pu, date_lr, date_f, cursor.getString(8), cursor.getString(9), cursor.getInt(10), cursor.getInt(11), cursor.getInt(12));
            listeMateriels.add(materiel);
        }
        listeMateriel.setAdapter(materielAdapter);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Materiel materiel = (Materiel) listeMateriel.getAdapter().getItem(i);
        infoMateriel = new Intent(this, InfoMateriel.class);
        Log.i("id", "id1 = " + materiel.getIdMateriel());
        infoMateriel.putExtra("idItemMateriel", materiel.getIdMateriel());
        startActivity(infoMateriel);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if (filtreModele.isChecked()) {
            //listeMateriel = new
            //materielAdapter = new MaterielAdaptater(this, R.layout.listeview_item, listeMateriels);
        }
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {

        ArrayList<Materiel> newListeMateriels = new ArrayList<>();

        for (Materiel materiel : listeMateriels) {
            if (materiel.getLibelle().toLowerCase().contains(s.toLowerCase())) {
                newListeMateriels.add(materiel);
            }
        }

        MaterielAdaptater newAdapteurMateriel = new MaterielAdaptater(this, R.layout.listeview_item, newListeMateriels);

        listeMateriel.setAdapter(newAdapteurMateriel);

        return false;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        ArrayList<Materiel> filtreListeMateriels = new ArrayList<>();

        if (adapterView.getItemAtPosition(i) == "Date") {

        }

        MaterielAdaptater filtreMaterielsAdaptateur = new MaterielAdaptater(this, R.layout.listeview_item, filtreListeMateriels);

        //listeMateriel.setAdapter(filtreMaterielsAdaptateur);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}