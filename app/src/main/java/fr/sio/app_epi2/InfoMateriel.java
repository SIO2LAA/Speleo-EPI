package fr.sio.app_epi2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import fr.sio.app_epi2.dao.Singleton;
import fr.sio.app_epi2.models.Materiel;

public class InfoMateriel extends AppCompatActivity implements View.OnClickListener {
    private SQLiteDatabase db = Singleton.getDB(this).getDbOpenHelper().getReadableDatabase();
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

    //espace fiche de controle
    private Button fcCreer;
    private Button fcModifer;
    private Button fcAfficher;
    private Button fcSupprimer;
    private Intent FDC_Creer;
    private Intent FDC_Afficher;

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
            FDV_Creer = new Intent(this, FDV_Creer.class);
            startActivity(FDV_Creer);
        }
        if(fvAfficher.isPressed()){
            FDV_Afficher = new Intent(this, FDV_Afficher.class).putExtra("idItemMateriel", materiel.getIdMateriel());
            startActivity(FDV_Afficher);
        }
        if(fvModifier.isPressed()) {
            FDV_Modifier = new Intent(this, FDV_Modifier.class).putExtra("idItemMateriel", materiel.getIdMateriel());
            startActivity(FDV_Modifier);
        }

        Intent intent = getIntent();
        
        int id = intent.getIntExtra("idItemMateriel", 1);

        Cursor cursor = db.rawQuery("SELECT idMateriel FROM controle WHERE idMateriel = " + id , null);

        if (fcCreer.isPressed()){

            if(cursor.getCount()==1){
                Toast.makeText(InfoMateriel.this, "Fiche existe déjà ! Supprimez d'abord avant créer", Toast.LENGTH_SHORT).show();
            }else if(cursor.getCount()==0) {
                FDC_Creer = new Intent(this,FDC_Creer.class);
                FDC_Creer.putExtra("idItemMateriel", id);
                startActivity(FDC_Creer);
            }
        }
        if (fcAfficher.isPressed()) {

            Cursor res = db.rawQuery("SELECT * FROM controle WHERE idMateriel = " + id , null);
            if(res.getCount()==0){
                Toast.makeText(InfoMateriel.this, "Fiche non existant", Toast.LENGTH_SHORT).show();
                return;
            }
            StringBuffer buffer = new StringBuffer();
            while(res.moveToNext()){
                buffer.append("Date : "+res.getString(1)+"\n");
                buffer.append("Observation : "+res.getString(2)+"\n");
            }

            androidx.appcompat.app.AlertDialog.Builder builder = new AlertDialog.Builder(InfoMateriel.this);
            builder.setCancelable(true);
            builder.setTitle("Fiche de contrôle");
            builder.setMessage(buffer.toString());
            builder.show();
        }


    }

}