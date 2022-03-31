package fr.sio.app_epi2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import fr.sio.app_epi2.models.Materiel;
import fr.sio.app_epi2.models.Tag;

public class InfoMateriel extends AppCompatActivity implements View.OnClickListener {
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

    //espace fiche controle
    private Button fcCreer;
    private Button fcModifer;
    private Button fcAfficher;
    private Button fcSupprimer;
    private Intent FDC_Creer;

    //espace fiche de vie
    private Button fvCreer;
    private Button fvModifier;
    private Button fvAfficher;
    private Button fvSupprimer;
    private Intent FDV_Creer;
    private Intent FDV_Afficher;
    private Intent FDV_Modifier;

    private static final int DIALOG_ALERT = 10;
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

        //espace fiche controle
        fcCreer = findViewById(R.id.FCCreer);
        fcSupprimer = findViewById(R.id.FCSupprimer);
        fcAfficher = findViewById(R.id.FCAfficher);
        fcModifer = findViewById(R.id.FCModifier);

        //listener fiche controle
        fcCreer.setOnClickListener(this);
        fcSupprimer.setOnClickListener(this);
        fcAfficher.setOnClickListener(this);
        fcModifer.setOnClickListener(this);

        //espace fiche de vie
        fvCreer = findViewById(R.id.FVCreer);
        fvSupprimer = findViewById(R.id.FVSupprimer);
        fvAfficher = findViewById(R.id.FVAfficher);
        fvModifier = findViewById(R.id.FVModifier);

        //listener fiche de vie
        fvCreer.setOnClickListener(this);
        fvSupprimer.setOnClickListener(this);
        fvAfficher.setOnClickListener(this);
        fvModifier.setOnClickListener(this);

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

    @Override
    public void onClick(View v) {
        //espace fiche de vie
        if(fvCreer.isPressed()){
            Tag tag = new Tag("tag1", "text1");
            ArrayList<Tag> listeTags = new ArrayList<>();
            listeTags.add(tag);
            xmlFile xmlFile = new xmlFile(this, "data", listeTags);
            xmlFile.exportDB(db);
            File file = new File("/data/data/" + getPackageName() + "/" + "data.xml");
            xmlFile.importDB(this, file);

            FDV_Creer = new Intent(this, FDV_Creer.class);
            startActivity(FDV_Creer);
        }
        if(fvAfficher.isPressed()){
            FDV_Afficher = new Intent(this, FDV_Afficher.class);
            startActivity(FDV_Afficher);
        }
        if(fvModifier.isPressed())
            FDV_Modifier = new Intent(this, FDV_Modifier.class);
            startActivity(FDV_Modifier);

        if (fcCreer.isPressed()){
            FDC_Creer = new Intent(this,FDC_Creer.class);
            startActivity(FDC_Creer);
        }


    }

}