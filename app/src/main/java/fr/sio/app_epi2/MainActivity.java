package fr.sio.app_epi2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import java.util.UUID;

import fr.sio.app_epi2.dao.Singleton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button boutonMenu;
    private SQLiteDatabase db;
    private ContentValues value;
    private ContentValues value2;
    private ContentValues value3;
    private ContentValues value4;
    private ContentValues value5;
    private ContentValues value6;
    private Intent gestionMateriel;
    // The database creator and updater helper
    private Singleton singleton = Singleton.getDB(this);
    private DBOpenHelper dbOpenHelper;
    private Intent xmlExport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boutonMenu = findViewById(R.id.BoutonMenu);
        boutonMenu.setOnClickListener(this);
        // Create or retrieve the database

        dbOpenHelper = singleton.getDbOpenHelper();
        openDB();

        int oldV = DBOpenHelper.Constants.DATABASE_VERSION;
        int newV = DBOpenHelper.Constants.DATABASE_VERSION + 1;

        

        dbOpenHelper.onUpgrade(db, oldV, newV);

        

        value = new ContentValues();
        value2 = new ContentValues();
        value3 = new ContentValues();
        value4 = new ContentValues();
        value5 = new ContentValues();
        value6 = new ContentValues();

        insertRecord(value, value2, value3, value4, value5, value6);

    }

    public String generateUniqueID() {
        String uniqueID;
        uniqueID = UUID.randomUUID().toString();


        return uniqueID;
    }


    /**
     * * Open the database* *
     *
     * @throws SQLiteException
     */
    public void openDB() throws SQLiteException {
        try {
            db = dbOpenHelper.getWritableDatabase();
        } catch (SQLiteException ex) {
            db = dbOpenHelper.getReadableDatabase();
        }
    }



    /** *Close Database */
    public void closeDB() {
        db.close();
    }


    /**
     * Insert a record
     *
     * @param contentValuesFabricant
     *            (an empty contentValues)
     * @param contentValuesmateriel1
     *            (an empty contentValues
     * @param contentValuesmateriel2
     *            (an empty contentValues)
     * @param contentValuesmateriel3
     *            (an empty contentValues)
     * @param contentValuesmateriel4
     *            (an empty contentValues)
     * @param contentValuesmateriel5
     *            (an empty contentValues)
     *
     * @return the inserted row id
     */
    private long insertRecord(ContentValues contentValuesFabricant, ContentValues contentValuesmateriel1, ContentValues contentValuesmateriel2, ContentValues contentValuesmateriel3, ContentValues contentValuesmateriel4, ContentValues contentValuesmateriel5) {
        // Assign the values for each column.
        contentValuesFabricant.put(DBOpenHelper.Constants.idFabricant, 1);
        contentValuesFabricant.put(DBOpenHelper.Constants.nomFabricant, "BOSCH");

        contentValuesmateriel1.put(DBOpenHelper.Constants.idMateriel, 1);
        contentValuesmateriel1.put(DBOpenHelper.Constants.libelleMateriel, "Sangle");
        contentValuesmateriel1.put(DBOpenHelper.Constants.modeleMateriel, "S3");
        contentValuesmateriel1.put(DBOpenHelper.Constants.signeDistinctifMateriel, "Orange");
        contentValuesmateriel1.put(DBOpenHelper.Constants.dateAcquisitionMateriel, "2022-01-01");
        contentValuesmateriel1.put(DBOpenHelper.Constants.datePremiereUtilisationMateriel, "2022-02-01");
        contentValuesmateriel1.put(DBOpenHelper.Constants.dateLimiteRebutMateriel, "2025-05-31");
        contentValuesmateriel1.put(DBOpenHelper.Constants.dateFabricationMateriel, "2021-11-31");
        contentValuesmateriel1.put(DBOpenHelper.Constants.marquageMateriel, "numero de serie");
        contentValuesmateriel1.put(DBOpenHelper.Constants.empalcementMarquageMateriel, "derriere");
        contentValuesmateriel1.put(DBOpenHelper.Constants.idFabricantMateriel, 1);
        contentValuesmateriel1.put(DBOpenHelper.Constants.idTypeMateriel, "null");
        contentValuesmateriel1.put(DBOpenHelper.Constants.idControleurMateriel, "null");

        contentValuesmateriel2.put(DBOpenHelper.Constants.idMateriel, 2);
        contentValuesmateriel2.put(DBOpenHelper.Constants.libelleMateriel, "Corde");
        contentValuesmateriel2.put(DBOpenHelper.Constants.modeleMateriel, "L2-32");
        contentValuesmateriel2.put(DBOpenHelper.Constants.signeDistinctifMateriel, "Rouge liseré noire");
        contentValuesmateriel2.put(DBOpenHelper.Constants.dateAcquisitionMateriel, "2022-05-02");
        contentValuesmateriel2.put(DBOpenHelper.Constants.datePremiereUtilisationMateriel, "2022-05-25");
        contentValuesmateriel2.put(DBOpenHelper.Constants.dateLimiteRebutMateriel, "2025-11-29");
        contentValuesmateriel2.put(DBOpenHelper.Constants.dateFabricationMateriel, "2021-03-08");
        contentValuesmateriel2.put(DBOpenHelper.Constants.marquageMateriel, "Trait bleu");
        contentValuesmateriel2.put(DBOpenHelper.Constants.empalcementMarquageMateriel, "bout de corde");
        contentValuesmateriel2.put(DBOpenHelper.Constants.idFabricantMateriel, 1);
        contentValuesmateriel2.put(DBOpenHelper.Constants.idTypeMateriel, "null");
        contentValuesmateriel2.put(DBOpenHelper.Constants.idControleurMateriel, "null");

        contentValuesmateriel3.put(DBOpenHelper.Constants.idMateriel, 3);
        contentValuesmateriel3.put(DBOpenHelper.Constants.libelleMateriel, "Casque");
        contentValuesmateriel3.put(DBOpenHelper.Constants.modeleMateriel, "Mastiffiant");
        contentValuesmateriel3.put(DBOpenHelper.Constants.signeDistinctifMateriel, "Rouge liseré bleu");
        contentValuesmateriel3.put(DBOpenHelper.Constants.dateAcquisitionMateriel, "2022-06-02");
        contentValuesmateriel3.put(DBOpenHelper.Constants.datePremiereUtilisationMateriel, "2022-05-25");
        contentValuesmateriel3.put(DBOpenHelper.Constants.dateLimiteRebutMateriel, "2025-11-29");
        contentValuesmateriel3.put(DBOpenHelper.Constants.dateFabricationMateriel, "2021-03-08");
        contentValuesmateriel3.put(DBOpenHelper.Constants.marquageMateriel, "Trait noir");
        contentValuesmateriel3.put(DBOpenHelper.Constants.empalcementMarquageMateriel, "arrière casque");
        contentValuesmateriel3.put(DBOpenHelper.Constants.idFabricantMateriel, 1);
        contentValuesmateriel3.put(DBOpenHelper.Constants.idTypeMateriel, "null");
        contentValuesmateriel3.put(DBOpenHelper.Constants.idControleurMateriel, "null");

        contentValuesmateriel4.put(DBOpenHelper.Constants.idMateriel, 4);
        contentValuesmateriel4.put(DBOpenHelper.Constants.libelleMateriel, "Baudrier");
        contentValuesmateriel4.put(DBOpenHelper.Constants.modeleMateriel, "Mastiffiant");
        contentValuesmateriel4.put(DBOpenHelper.Constants.signeDistinctifMateriel, "Rouge liseré bleu");
        contentValuesmateriel4.put(DBOpenHelper.Constants.dateAcquisitionMateriel, "2022-06-02");
        contentValuesmateriel4.put(DBOpenHelper.Constants.datePremiereUtilisationMateriel, "2022-05-25");
        contentValuesmateriel4.put(DBOpenHelper.Constants.dateLimiteRebutMateriel, "2025-11-29");
        contentValuesmateriel4.put(DBOpenHelper.Constants.dateFabricationMateriel, "2021-03-08");
        contentValuesmateriel4.put(DBOpenHelper.Constants.marquageMateriel, "Trait noir");
        contentValuesmateriel4.put(DBOpenHelper.Constants.empalcementMarquageMateriel, "étiquette baudrier");
        contentValuesmateriel4.put(DBOpenHelper.Constants.idFabricantMateriel, 1);
        contentValuesmateriel4.put(DBOpenHelper.Constants.idTypeMateriel, "null");
        contentValuesmateriel4.put(DBOpenHelper.Constants.idControleurMateriel, "null");

        contentValuesmateriel5.put(DBOpenHelper.Constants.idMateriel, 5);
        contentValuesmateriel5.put(DBOpenHelper.Constants.libelleMateriel, "Basquette");
        contentValuesmateriel5.put(DBOpenHelper.Constants.modeleMateriel, "Lescalade");
        contentValuesmateriel5.put(DBOpenHelper.Constants.signeDistinctifMateriel, "carré orange");
        contentValuesmateriel5.put(DBOpenHelper.Constants.dateAcquisitionMateriel, "2022-07-02");
        contentValuesmateriel5.put(DBOpenHelper.Constants.datePremiereUtilisationMateriel, "2022-05-25");
        contentValuesmateriel5.put(DBOpenHelper.Constants.dateLimiteRebutMateriel, "2025-11-29");
        contentValuesmateriel5.put(DBOpenHelper.Constants.dateFabricationMateriel, "2021-03-08");
        contentValuesmateriel5.put(DBOpenHelper.Constants.marquageMateriel, "Trait bleu");
        contentValuesmateriel5.put(DBOpenHelper.Constants.empalcementMarquageMateriel, "dessus de chaussure");
        contentValuesmateriel5.put(DBOpenHelper.Constants.idFabricantMateriel, 1);
        contentValuesmateriel5.put(DBOpenHelper.Constants.idTypeMateriel, "null");
        contentValuesmateriel5.put(DBOpenHelper.Constants.idControleurMateriel, "null");

        // Insert the line in the database
        long rowId = db.insert(DBOpenHelper.Constants.tableFabricant, null, contentValuesFabricant);
        long rowId2 = db.insert(DBOpenHelper.Constants.tableMateriel, null, contentValuesmateriel1);
        long rowId3 = db.insert(DBOpenHelper.Constants.tableMateriel, null, contentValuesmateriel2);
        long rowId4 = db.insert(DBOpenHelper.Constants.tableMateriel, null, contentValuesmateriel3);
        long rowId5 = db.insert(DBOpenHelper.Constants.tableMateriel, null, contentValuesmateriel4);
        long rowId6 = db.insert(DBOpenHelper.Constants.tableMateriel, null, contentValuesmateriel5);

        // Test to see if the insertion was ok
        if (rowId == -1 || rowId2 == -1 || rowId3 == -1 || rowId4 == -1 || rowId5 == -1 || rowId6 == -1) {
            Toast.makeText(this, "Erreur dans l'insertion des données",
                    Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Aucun problème dans l'insertion !",
                    Toast.LENGTH_LONG).show();
        }

        return rowId;
    }

    @Override
    public void onClick(View view) {
        if (boutonMenu.isPressed()) {
            gestionMateriel = new Intent(this, GestionMateriel.class); //Initialement mis GestionMateriel.class InfoMateriel est provisoire
            startActivity(gestionMateriel);


        }
    }
}