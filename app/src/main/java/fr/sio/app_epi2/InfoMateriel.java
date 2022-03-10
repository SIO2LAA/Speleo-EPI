package fr.sio.app_epi2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import fr.sio.app_epi2.models.Materiel;

public class InfoMateriel extends AppCompatActivity {
    private SQLiteDatabase db = MainActivity.dbOpenHelper.getReadableDatabase();
    private Materiel materiel;
    private TextView libelle;
    private TextView modele;
    private TextView signeDistinctif;
    private TextView dateAcquisition;
    private TextView datePremiereUtilisation;
    private TextView dateLimiteRebut;
    private TextView dateFabrication;
    private TextView marquage;
    private TextView emplacementMarquage;
    private SimpleDateFormat sdf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_materiel);
        libelle = findViewById(R.id.nomInfo);
        modele = findViewById(R.id.modeleMateriel);
        signeDistinctif = findViewById(R.id.signeDistinctif);
        dateAcquisition = findViewById(R.id.dateAcquisition);
        datePremiereUtilisation = findViewById(R.id.datePremiereUtilisation);
        dateLimiteRebut = findViewById(R.id.dateLimiteRebut);
        dateFabrication = findViewById(R.id.dateFabrication);
        marquage = findViewById(R.id.marquage);
        emplacementMarquage = findViewById(R.id.emplacementMarquage);

        sdf = new SimpleDateFormat("dd/mm/yyyy");
        Intent intent = getIntent();
        Log.i("id", "id2 = " + intent.getIntExtra("idItemMateriel", 1));
        int id = intent.getIntExtra("idItemMateriel", 1);
        Cursor cursor = db.rawQuery("SELECT * FROM materiel WHERE id = " + id, null);
        SimpleDateFormat format = new SimpleDateFormat("y-m-d");

        while(cursor.moveToNext()) {
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

            materiel = new Materiel(cursor.getInt(0), cursor.getString(1), cursor.getString(2),cursor.getString(3), date_ac, date_pu, date_lr, date_f, cursor.getString(8), cursor.getString(9), cursor.getInt(10), cursor.getInt(11), cursor.getInt(12));
        }

        libelle.setText("Libelle : " + materiel.getLibelle());
        modele.setText("Modèle : " + materiel.getModele());
        signeDistinctif.setText("Signe distinctif : " + materiel.getSigneDistinctif());
        dateAcquisition.setText("Date d'acquisition : " + sdf.format(materiel.getDateAcquisition()));
        datePremiereUtilisation.setText("Date de première utilisation : " + sdf.format(materiel.getDatePremiereUtilisation()));
        dateLimiteRebut.setText("Date de limite rébut : " + sdf.format(materiel.getDateLimiteRebut()));
        dateFabrication.setText("Date de fabrication : " + sdf.format(materiel.getDateFabrication()));
        marquage.setText("Marquage : " + materiel.getMarquage());
        emplacementMarquage.setText("Emplacement de marquage : " + materiel.getEmplacementMarquage());

    }
}