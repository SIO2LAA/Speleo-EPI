package fr.sio.app_epi2;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import fr.sio.app_epi2.dao.Singleton;
import fr.sio.app_epi2.models.Materiel;

public class FDV_Modifier extends AppCompatActivity implements View.OnClickListener {
    private Button annuler;
    private Button valider;
    private SimpleDateFormat sdf;
    private SQLiteDatabase db = Singleton.getDB(this).getDbOpenHelper().getWritableDatabase();
    private Materiel materiel;

    private EditText modele;
    private EditText fabricant;
    private EditText signe_distinctif;
    private EditText marquage;
    private EditText emplacemet_marquage;
    private EditText numero_serie;
    private EditText dateAcquisition;
    private EditText datePremiereUtilisation;
    private EditText dateLimiteRebut;
    private EditText dateFabrication;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fdv_modifier);

        annuler = findViewById(R.id.fdv_modifier_annuler);
        valider = findViewById(R.id.fdv_modifier_valider);
        modele = findViewById(R.id.Ed_modele);
        fabricant = findViewById(R.id.Ed_fabricant);
        signe_distinctif = findViewById(R.id.Ed_signe_distinctif);
        marquage = findViewById(R.id.Ed_marquage);
        emplacemet_marquage = findViewById(R.id.Ed_emplacement);
        numero_serie = findViewById(R.id.Ed_numero_serie);
        dateAcquisition = findViewById(R.id.Ed_date_acquisition);
        datePremiereUtilisation = findViewById(R.id.ED_date_pm_use);
        dateLimiteRebut = findViewById(R.id.ED_limite_rebus);
        dateFabrication = findViewById(R.id.ED_date_fabrication);

        // boutons sur écoute
        annuler.setOnClickListener(this);
        valider.setOnClickListener(this);

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

        modele.setText("" + materiel.getModele());
        fabricant.setText("" + materiel.getIdFabricant());
        signe_distinctif.setText("" + materiel.getSigneDistinctif());
        marquage.setText("" + materiel.getMarquage());
        emplacemet_marquage.setText("" + materiel.getEmplacementMarquage());
        //numero_serie.setText("Numero de serie : " + materiel.get());
        dateAcquisition.setText("" + sdf.format(materiel.getDateAcquisition()));
        datePremiereUtilisation.setText("" + sdf.format(materiel.getDatePremiereUtilisation()));
        dateLimiteRebut.setText("" + sdf.format(materiel.getDateLimiteRebut()));
        dateFabrication.setText("" + sdf.format(materiel.getDateFabrication()));
        //dateFicheVie.setText("Date  redaction fiche de vie : " + sdf.format(materiel.getdate));

    }

    @Override
    public void onClick(View v) {


        if (annuler.isPressed()) {
            this.finish();
        }

        if (valider.isPressed()) {
           // ajout du message dans la BD
            Intent intent = getIntent();
            int id = intent.getIntExtra("idItemMateriel", 1);
            ContentValues contentValues = new ContentValues();
            String str = modele.getText().toString();
            contentValues.put("modele", str);

            db.update("materiel", contentValues ,  "id = " + id, null);

            this.finish();
        }
    }
}