package fr.sio.app_epi2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button boutonMenu;
    private SQLiteDatabase db;
    private ContentValues value;
    private ContentValues value2;
    private Intent gestionMateriel;
    // The database creator and updater helper
    public static DBOpenHelper dbOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boutonMenu = findViewById(R.id.BoutonMenu);
        boutonMenu.setOnClickListener(this);
        // Create or retrieve the database
        dbOpenHelper = new DBOpenHelper(this, DBOpenHelper.Constants.DATABASE_NAME, null,
                DBOpenHelper.Constants.DATABASE_VERSION);
        openDB();
        value = new ContentValues();
        value2 = new ContentValues();
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
     * @param contentValuesmateriel
     *            (an empty contentValues)
     *
     * @return the inserted row id
     */
    private long insertRecord(ContentValues contentValuesFabricant, ContentValues contentValuesmateriel) {
        // Assign the values for each column.
        contentValuesFabricant.put(DBOpenHelper.Constants.idFabricant, 1);
        contentValuesFabricant.put(DBOpenHelper.Constants.nomFabricant, "BOSCH");
        contentValuesmateriel.put(DBOpenHelper.Constants.idMateriel, 1);
        contentValuesmateriel.put(DBOpenHelper.Constants.libelleMateriel, "Sangle");
        contentValuesmateriel.put(DBOpenHelper.Constants.modeleMateriel, "S3");
        contentValuesmateriel.put(DBOpenHelper.Constants.signeDistinctifMateriel, "Orange");
        contentValuesmateriel.put(DBOpenHelper.Constants.dateAcquisitionMateriel, "2022-01-01");
        contentValuesmateriel.put(DBOpenHelper.Constants.datePremiereUtilisationMateriel, "2022-02-01");
        contentValuesmateriel.put(DBOpenHelper.Constants.dateLimiteRebutMateriel, "2025-05-31");
        contentValuesmateriel.put(DBOpenHelper.Constants.dateFabricationMateriel, "2021-11-31");
        contentValuesmateriel.put(DBOpenHelper.Constants.marquageMateriel, "numero de serie");
        contentValuesmateriel.put(DBOpenHelper.Constants.empalcementMarquageMateriel, "derriere");
        contentValuesmateriel.put(DBOpenHelper.Constants.idFabricantMateriel, 1);
        contentValuesmateriel.put(DBOpenHelper.Constants.idTypeMateriel, "null");
        contentValuesmateriel.put(DBOpenHelper.Constants.idControleurMateriel, "null");


        // Insert the line in the database
        long rowId = db.insert(DBOpenHelper.Constants.tableFabricant, null, contentValuesFabricant);
        long rowId2 = db.insert(DBOpenHelper.Constants.tableMateriel, null, contentValuesmateriel);

        // Test to see if the insertion was ok
        if (rowId == -1 || rowId2 == -1) {
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
            insertRecord(value, value2);
            gestionMateriel = new Intent(this, GestionMateriel.class); //Initialement mis GestionMateriel.class InfoMateriel est provisoire
            startActivity(gestionMateriel);
        }
    }
}