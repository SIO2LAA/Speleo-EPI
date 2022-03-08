package fr.sio.app_epi2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button boutonMenu;
    private SQLiteDatabase db;
    private ContentValues value;
    private Intent gestionMateriel;
    // The database creator and updater helper
    DBOpenHelper dbOpenHelper;

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
     * @param contentValues
     *            (an empty contentValues)
     * @return the inserted row id
     */
    private long insertRecord(ContentValues contentValues) {
        // Assign the values for each column.
        contentValues.put(DBOpenHelper.Constants.idFabricant, "1");
        contentValues.put(DBOpenHelper.Constants.nomFabricant, "BOSCH");


        // Insert the line in the database
        long rowId = db.insert(DBOpenHelper.Constants.tableFabricant, null, contentValues);

        // Test to see if the insertion was ok
        if (rowId == -1) {
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
            insertRecord(value);
            gestionMateriel = new Intent(this, GestionMateriel.class);
            startActivity(gestionMateriel);
        }
    }
}