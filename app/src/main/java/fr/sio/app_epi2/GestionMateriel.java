package fr.sio.app_epi2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import fr.sio.app_epi2.dao.Singleton;
import fr.sio.app_epi2.models.Filtre;
import fr.sio.app_epi2.models.Materiel;
import fr.sio.app_epi2.models.MaterielAdaptater;
//import fr.sio.app_epi2.models.MaterielAdaptater;

public class GestionMateriel extends AppCompatActivity implements AdapterView.OnItemClickListener, SearchView.OnQueryTextListener, AdapterView.OnItemSelectedListener, View.OnClickListener {

    private ListView listeMateriel;
    private Button Btnimport;
    private Intent infoMateriel;
    private SearchView search;
    private Spinner selection;
    private MaterielAdaptater materielAdapter;
    private ArrayList<Materiel> listeMateriels;
    private SQLiteDatabase db = Singleton.getDB(this).getDbOpenHelper().getReadableDatabase();
    private SimpleDateFormat format = new SimpleDateFormat("y-m-d");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_materiel);
        TextView dateView = (TextView)findViewById(R.id.date_jour);
        setDate(dateView);
        listeMateriel = findViewById(R.id.listeMateriel);
        Btnimport = findViewById(R.id.button_import);
        search = findViewById(R.id.recherche);
        selection = findViewById(R.id.selection);
        selection.setOnItemSelectedListener(this);
        search.setOnQueryTextListener(this);
        listeMateriels = new ArrayList<>();
        materielAdapter = new MaterielAdaptater(this, R.layout.listeview_item, listeMateriels);
        listeMateriel.setOnItemClickListener(this);
        Btnimport.setOnClickListener(this);

        Filtre filtreTout = new Filtre(1, "Aucun");
        Filtre filtreDateAcquisition = new Filtre(1, "Date Acquisition");
        Filtre filtreDateUtilisation = new Filtre(1, "Date Utilisation");

        ArrayList<Filtre> listeFiltres = new ArrayList<>();
        listeFiltres.add(filtreTout);
        listeFiltres.add(filtreDateAcquisition);
        listeFiltres.add(filtreDateUtilisation);

        ArrayAdapter filtresAdaptateur = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listeFiltres);

        filtresAdaptateur.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        selection.setAdapter(filtresAdaptateur);

        Cursor cursor = db.rawQuery("SELECT * FROM materiel", null);

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

        Filtre filtre = (Filtre) adapterView.getSelectedItem();

        Log.i("filtre", filtre.getLibelle());

        if (filtre.getLibelle() == "Aucun") {
            filtreListeMateriels = listeMateriels;
        }

        if (filtre.getLibelle() == "Date Acquisition") {
            Cursor cursor2 = db.rawQuery("SELECT * FROM materiel ORDER BY dateAcquisition DESC", null);

            while(cursor2.moveToNext()) {
                Log.i("cursor", "id = " + cursor2.getString(0));
                Date date_ac = new Date();
                Date date_pu = new Date();
                Date date_lr = new Date();
                Date date_f = new Date();

                try {
                    date_ac = format.parse(cursor2.getString(4));
                    date_pu = format.parse(cursor2.getString(5));
                    date_lr = format.parse(cursor2.getString(6));
                    date_f = format.parse(cursor2.getString(7));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Materiel materiel = new Materiel(cursor2.getInt(0), cursor2.getString(1), cursor2.getString(2),cursor2.getString(3), date_ac, date_pu, date_lr, date_f, cursor2.getString(8), cursor2.getString(9), cursor2.getInt(10), cursor2.getInt(11), cursor2.getInt(12));
                filtreListeMateriels.add(materiel);
            }

        }

        if (filtre.getLibelle() == "Date Utilisation") {
            Cursor cursor2 = db.rawQuery("SELECT * FROM materiel ORDER BY datePremiereUtilisation DESC", null);

            while(cursor2.moveToNext()) {
                Log.i("cursor", "id = " + cursor2.getString(0));
                Date date_ac = new Date();
                Date date_pu = new Date();
                Date date_lr = new Date();
                Date date_f = new Date();

                try {
                    date_ac = format.parse(cursor2.getString(4));
                    date_pu = format.parse(cursor2.getString(5));
                    date_lr = format.parse(cursor2.getString(6));
                    date_f = format.parse(cursor2.getString(7));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Materiel materiel = new Materiel(cursor2.getInt(0), cursor2.getString(1), cursor2.getString(2), cursor2.getString(3), date_ac, date_pu, date_lr, date_f, cursor2.getString(8), cursor2.getString(9), cursor2.getInt(10), cursor2.getInt(11), cursor2.getInt(12));
                filtreListeMateriels.add(materiel);
            }
        }


        MaterielAdaptater filtreMaterielsAdaptateur = new MaterielAdaptater(this, R.layout.listeview_item, filtreListeMateriels);

        listeMateriel.setAdapter(filtreMaterielsAdaptateur);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void setDate(TextView v){
        Date today = Calendar.getInstance().getTime();//getting date
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String date = formatter.format(today);
        v.setText(date);
    }

    @Override
    public void onClick(View view) {
        xmlFile file = new xmlFile(this, db);
        File export = new File("/data/data/fr.sio.app_epi2/exportation.xml");
        Log.i("file", export.getAbsolutePath());
        file.importDB("exportation.xml");

    }
}