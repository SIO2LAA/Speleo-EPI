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
    private ContentValues fabricant;
    private ContentValues materiel1;
    private ContentValues materiel2;
    private ContentValues materiel3;
    private ContentValues materiel4;
    private ContentValues materiel5;
    private ContentValues controleur1;
    private ContentValues controleur2;
    private ContentValues lot1;
    private ContentValues lot2;
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



        fabricant = new ContentValues();
        materiel1 = new ContentValues();
        materiel2 = new ContentValues();
        materiel3 = new ContentValues();
        materiel4 = new ContentValues();
        materiel5 = new ContentValues();
        controleur1 = new ContentValues();
        controleur2 = new ContentValues();
        lot1 = new ContentValues();
        lot2 = new ContentValues();

        insertRecord(fabricant, materiel1, materiel2, materiel3, materiel4, materiel5, controleur1, controleur2, lot1, lot2);

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
    private long insertRecord(ContentValues contentValuesFabricant, ContentValues contentValuesmateriel1, ContentValues contentValuesmateriel2, ContentValues contentValuesmateriel3, ContentValues contentValuesmateriel4, ContentValues contentValuesmateriel5, ContentValues contentValuescontroleur1, ContentValues contentValuescontroleur2, ContentValues contentValueslot1, ContentValues contentValueslot2) {
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

        contentValuescontroleur1.put(DBOpenHelper.Constants.idControleur, 1);
        contentValuescontroleur1.put(DBOpenHelper.Constants.nomControleur, "Verdon");
        contentValuescontroleur1.put(DBOpenHelper.Constants.prenomControleur, "Dejoie");

        contentValuescontroleur2.put(DBOpenHelper.Constants.idControleur, 2);
        contentValuescontroleur2.put(DBOpenHelper.Constants.nomControleur, "Dejoie");
        contentValuescontroleur2.put(DBOpenHelper.Constants.prenomControleur, "Frédéric");

        contentValueslot1.put(DBOpenHelper.Constants.numeroLot, 1);
        contentValueslot1.put(DBOpenHelper.Constants.dateLot, "2022-04-16");
        contentValueslot1.put(DBOpenHelper.Constants.quantiteLot, 68);
        contentValueslot1.put(DBOpenHelper.Constants.idMaterielLot, 1);

        contentValueslot2.put(DBOpenHelper.Constants.numeroLot, 2);
        contentValueslot2.put(DBOpenHelper.Constants.dateLot, "2022-05-29");
        contentValueslot2.put(DBOpenHelper.Constants.quantiteLot, 45);
        contentValueslot2.put(DBOpenHelper.Constants.idMaterielLot, 3);

        // Insert the line in the database
        long rowId = db.insert(DBOpenHelper.Constants.tableFabricant, null, contentValuesFabricant);
        long rowId2 = db.insert(DBOpenHelper.Constants.tableMateriel, null, contentValuesmateriel1);
        long rowId3 = db.insert(DBOpenHelper.Constants.tableMateriel, null, contentValuesmateriel2);
        long rowId4 = db.insert(DBOpenHelper.Constants.tableMateriel, null, contentValuesmateriel3);
        long rowId5 = db.insert(DBOpenHelper.Constants.tableMateriel, null, contentValuesmateriel4);
        long rowId6 = db.insert(DBOpenHelper.Constants.tableMateriel, null, contentValuesmateriel5);
        long rowId7 = db.insert(DBOpenHelper.Constants.tableControleur, null, contentValuescontroleur1);
        long rowId8 = db.insert(DBOpenHelper.Constants.tableControleur, null, contentValuescontroleur2);
        long rowId9 = db.insert(DBOpenHelper.Constants.tableLot, null, contentValueslot1);
        long rowId10 = db.insert(DBOpenHelper.Constants.tableLot, null, contentValueslot2);
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