package fr.sio.app_epi2;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.microedition.khronos.egl.EGLDisplay;

import fr.sio.app_epi2.models.Materiel;

public class FDV_Modifier extends AppCompatActivity implements View.OnClickListener {
    private Button annuler;
    private Button valider;
    private SimpleDateFormat sdf;
    private SQLiteDatabase db = MainActivity.dbOpenHelper.getReadableDatabase();
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
        //dateAcquisition = findViewById(R.id.textView2);
        //datePremiereUtilisation = findViewById(R.id.textView3);
        //dateLimiteRebut = findViewById(R.id.textView9);
        //dateFabrication = findViewById(R.id.textView10);

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
            // Date date_rfv = new Date();

            try {
                date_ac = format.parse(cursor.getString(4));
                date_pu = format.parse(cursor.getString(5));
                date_lr = format.parse(cursor.getString(6));
                date_f = format.parse(cursor.getString(7));
                // date_rfv = format.parse(cursor.getString(8));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            materiel = new Materiel(cursor.getInt(0), cursor.getString(1), cursor.getString(2),cursor.getString(3),date_ac, date_pu, date_lr, date_f, cursor.getString(8), cursor.getString(9), cursor.getInt(10), cursor.getInt(11), cursor.getInt(12));
        }

        modele.setText("Libelle : " + materiel.getModele());
        fabricant.setText("Modèle : " + materiel.getIdFabricant());
        signe_distinctif.setText("Signe distinctif : " + materiel.getSigneDistinctif());
        marquage.setText("Marquage : " + materiel.getMarquage());
        emplacemet_marquage.setText("Emplacement de marquage : " + materiel.getEmplacementMarquage());
        //numero_serie.setText("Numero de serie : " + materiel.get());
        dateAcquisition.setText("Date d'acquisition : " + sdf.format(materiel.getDateAcquisition()));
        datePremiereUtilisation.setText("Date de première utilisation : " + sdf.format(materiel.getDatePremiereUtilisation()));
        dateLimiteRebut.setText("Date de limite rébut : " + sdf.format(materiel.getDateLimiteRebut()));
        dateFabrication.setText("Date de fabrication : " + sdf.format(materiel.getDateFabrication()));
        //dateFicheVie.setText("Date  redaction fiche de vie : " + sdf.format(materiel.getdate));

    }

    @Override
    public void onClick(View v) {
        // affichage du layout
        annuler = findViewById(R.id.btnAnnuler);
        valider = findViewById(R.id.btnValider);

        // boutons sur écoute
        annuler.setOnClickListener(this);
        valider.setOnClickListener(this);

        if (annuler.isPressed()) {
            this.finish();
        }

        if (valider.isPressed()) {
          /* // ajout du message dans la BD
            ContentValues values = new ContentValues();
            values.put("date", this.dateButton.getText().toString());
            values.put("observation", this.observation.getText().toString());
            long res = this.writeBD.insert("controle", null, values);

            if (res>0) {
                Toast toast = Toast.makeText(this, "Les données ont été ajouté dans la base de données", Toast.LENGTH_LONG);
                toast.show();
            }else {
                Toast toast = Toast.makeText(this, "Les données ont été ajouté dans la base de données", Toast.LENGTH_LONG);
                toast.show();
            }
*/
        }
    }
}