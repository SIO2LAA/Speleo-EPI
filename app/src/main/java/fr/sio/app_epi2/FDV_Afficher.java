package fr.sio.app_epi2;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import fr.sio.app_epi2.dao.Singleton;
import fr.sio.app_epi2.models.Materiel;

public class FDV_Afficher extends AppCompatActivity implements View.OnClickListener {
    private SQLiteDatabase db = Singleton.getDB(this).getDbOpenHelper().getReadableDatabase();
    private Button ok;
    private Materiel materiel;
    private TextView libelle;
    private TextView modele;
    private TextView fabricant;
    private TextView signe_distinctif;
    private TextView marquage;
    private TextView emplacemet_marquage;
    private TextView numero_serie;
    private TextView dateAcquisition;
    private TextView datePremiereUtilisation;
    private TextView dateLimiteRebut;
    private TextView dateFabrication;

    private SimpleDateFormat sdf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fdv_afficher);
        ok = findViewById(R.id.fdv_afficher_ok);
        libelle = findViewById(R.id.Tv_libelle);
        modele = findViewById(R.id.Tv_modele);
        fabricant = findViewById(R.id.Tv_fabricant);
        signe_distinctif = findViewById(R.id.Tv_signe_distinctif);
        marquage = findViewById(R.id.Tv_marquage);
        emplacemet_marquage = findViewById(R.id.Tv_emplacement_marquage);
        numero_serie = findViewById(R.id.Tv_numero_serie);
        dateAcquisition = findViewById(R.id.textView2);
        datePremiereUtilisation = findViewById(R.id.textView3);
        dateLimiteRebut = findViewById(R.id.textView9);
        dateFabrication = findViewById(R.id.textView10);

        ok.setOnClickListener(this);
        sdf = new SimpleDateFormat("dd/mm/yyyy");
        Intent intent = getIntent();
        int id = intent.getIntExtra("idItemMateriel", 1);
        Cursor cursor = db.rawQuery("SELECT * FROM materiel WHERE id = " + id, null);
        SimpleDateFormat format = new SimpleDateFormat("y-m-d");
        while(cursor.moveToNext()) {
            Date date_ac = new Date();
            Date date_pu = new Date();
            Date date_lr = new Date();
            Date date_f = new Date();
            //Date date_rfv = new Date();

            try {
                date_ac = format.parse(cursor.getString(4));
                date_pu = format.parse(cursor.getString(5));
                date_lr = format.parse(cursor.getString(6));
                date_f = format.parse(cursor.getString(7));
               //date_rfv = format.parse(cursor.getString(8));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            materiel = new Materiel(cursor.getInt(0), cursor.getString(1), cursor.getString(2),cursor.getString(3),date_ac, date_pu, date_lr, date_f, cursor.getString(8), cursor.getString(9), cursor.getInt(10), cursor.getInt(11), cursor.getInt(12));
        }

        libelle.setText("Libelle : " + materiel.getLibelle());
        modele.setText("Modèle : " + materiel.getModele());
        fabricant.setText("Fabricant : " + materiel.getIdFabricant());
        signe_distinctif.setText("Signe distinctif : " + materiel.getSigneDistinctif());
        marquage.setText("Marquage : " + materiel.getMarquage());
        emplacemet_marquage.setText("Emplacement de marquage : " + materiel.getEmplacementMarquage());
        //numero_serie.setText("Numero de serie : " + materiel.get);
        dateAcquisition.setText("Date d'acquisition : " + sdf.format(materiel.getDateAcquisition()));
        datePremiereUtilisation.setText("Date de première utilisation : " + sdf.format(materiel.getDatePremiereUtilisation()));
        dateLimiteRebut.setText("Date de limite rébut : " + sdf.format(materiel.getDateLimiteRebut()));
        dateFabrication.setText("Date de fabrication : " + sdf.format(materiel.getDateFabrication()));
        //dateFicheVie.setText("Date  redaction fiche de vie : " + sdf.format(materiel.getDat));

    }


    @Override
    public void onClick(View view) {
        if (ok.isPressed()){
            this.finish();
        }
    }


}
