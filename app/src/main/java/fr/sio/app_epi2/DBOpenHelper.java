package fr.sio.app_epi2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBOpenHelper extends SQLiteOpenHelper {

     private String update;
     private String insert;

    public DBOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    // @goals This class aims to show the constant to use for the DBOpenHelper */
    public static class Constants implements BaseColumns {

        // The database version
        public static int DATABASE_VERSION = 1;
        public static String DATABASE_NAME = "db";

         // The table Name
        public static final String tableFabricant = "fabricant";
        public static final String tableTypes = "types";
        public static final String tableControle = "controle";
        public static final String tableControleur = "controleur";
        public static final String tableMateriel = "materiel";
        public static final String tableLot = "lot";

        // Noms de colonnes
        // /!\Si vous utilisez une base de données, les noms des colonnes ont
        // les mêmes que ceux de
        // votre base, de même pour les index.
        // My Column ID and the associated explanation for end-users

        // Table Fabricant
        public static final String idFabricant = "id";// Mandatory
        public static final String nomFabricant = "nom";

        // Table Type
        public static final String idType= "id";// Mandatory
        public static final String nomType = "nom";

        // Table Controle
        public static final String idControle = "id";// Mandatory
        public static final String dateControle = "date";
        public static final String observationControle = "observation";
        public static final String natureControle = "nature";
        public static final String lieuControle = "lieu";
        public static final String idMaterielControle = "idMateriel";
        public static final String idControleurControle = "idControleur";

        // Table Controleur
        public static final String idControleur= "id";// Mandatory
        public static final String nomControleur = "nom";
        public static final String prenomControleur = "prenom";

        // Table Materiel
        public static final String idMateriel = "id";// Mandatory
        public static final String libelleMateriel = "libelle";
        public static final String modeleMateriel = "modele";
        public static final String signeDistinctifMateriel = "signeDistinctif";
        public static final String dateAcquisitionMateriel = "dateAcquisition";
        public static final String datePremiereUtilisationMateriel = "datePremiereUtilisation";
        public static final String dateLimiteRebutMateriel = "dateLimiteRebut";
        public static final String dateFabricationMateriel = "dateFabrication";
         public static final String dateredactionFicheMateriel = "dateRedactionFiche";
        public static final String marquageMateriel = "marquage";
        public static final String empalcementMarquageMateriel = "emplacementMarquage";
        public static final String idFabricantMateriel = "idFabricant";
        public static final String idTypeMateriel = "idType";
        public static final String idControleurMateriel = "idControleur";

         // Table Lot
         public static final String numeroLot = "numero";// Mandatory
         public static final String dateLot = "date";
         public static final String quantiteLot = "quantite";
         public static final String idMaterielLot = "idMateriel";


    }

    // The static string to create the database.
    private static final String FABRICANT_TABLE = "create table "
            + Constants.tableFabricant + "(" + Constants.idFabricant
            + " integer primary key autoincrement, "
            + Constants.nomFabricant + " VARCHAR(50) )";


    private static final String TYPE_TABLE = "create table "
             + Constants.tableTypes + "(" + Constants.idType
             + " integer primary key autoincrement, "
             + Constants.nomType + " VARCHAR(255) )";

     private static final String CONTROLE_TABLE = "create table "
             + Constants.tableControle + "(" + Constants.idControle
             + " integer primary key autoincrement, "
             + Constants.dateControle + " DATE ,"
             + Constants.observationControle + " VARCHAR(255) ,"
             + Constants.natureControle + " VARCHAR(255) ,"
             + Constants.lieuControle + " VARCHAR(255) ,"
             + Constants.idMaterielControle + " INTEGER REFERENCES " + Constants.tableMateriel + "(" + Constants.idMateriel + "),"
             + Constants.idControleurControle + " INTEGER REFERENCES " + Constants.tableControleur + "(" + Constants.idControleur + ") )";

     private static final String CONTROLEUR_TABLE = "create table "
             + Constants.tableControleur + "(" + Constants.idControleur
             + " integer primary key, "
             + Constants.nomControleur + " VARCHAR(50),"
             + Constants.prenomControleur + " VARCHAR(50) )";

     private static final String MATERIEL_TABLE = "create table "
             + Constants.tableMateriel + "(" + Constants.idControleur
             + " integer primary key autoincrement, "
             + Constants.libelleMateriel + " VARCHAR(255),"
             + Constants.modeleMateriel + " VARCHAR(255),"
             + Constants.signeDistinctifMateriel + " VARCHAR(255),"
             + Constants.dateAcquisitionMateriel + " DATE,"
             + Constants.datePremiereUtilisationMateriel + " DATE,"
             + Constants.dateLimiteRebutMateriel + " DATE,"
             + Constants.dateFabricationMateriel + " DATE,"
             + Constants.marquageMateriel + " VARCHAR(255),"
             + Constants.empalcementMarquageMateriel + " VARCHAR(255) ,"
             + Constants.idFabricantMateriel + " INTEGER REFERENCES " + Constants.tableFabricant + "(" + Constants.idFabricant + "),"
             + Constants.idTypeMateriel + " INTEGER REFERENCES " + Constants.tableTypes + "(" + Constants.idType + "),"
             + Constants.idControleurMateriel + " INTEGER REFERENCES " + Constants.tableControleur + "(" + Constants.idControleur + ") )";


     private static final String LOT_TABLE = "create table "
             + Constants.tableLot + "(" + Constants.numeroLot
             + " INTEGER, "
             + Constants.dateLot + " DATE,"
             + Constants.quantiteLot + " INTEGER,"
             + Constants.idMaterielLot + " INTEGER,"
             + "PRIMARY KEY (" + Constants.numeroLot + ", " + Constants.idMaterielLot + "),"
             + "CONSTRAINT fk_id_materiel_lot FOREIGN KEY (" + Constants.idMaterielLot + ") REFERENCES " + Constants.tableMateriel + "(" + Constants.idMateriel + ")"
             + ")";


    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create the new database using the SQL string Database_create
        db.execSQL("DROP TABLE IF EXISTS " + Constants.tableMateriel);
        db.execSQL("DROP TABLE IF EXISTS " + Constants.tableLot);
        db.execSQL("DROP TABLE IF EXISTS " + Constants.tableControleur);
        db.execSQL("DROP TABLE IF EXISTS " + Constants.tableControle);
        db.execSQL("DROP TABLE IF EXISTS " + Constants.tableTypes);
        db.execSQL("DROP TABLE IF EXISTS " + Constants.tableFabricant);

        db.execSQL(FABRICANT_TABLE);
        db.execSQL(TYPE_TABLE);
        db.execSQL(CONTROLE_TABLE);
        db.execSQL(CONTROLEUR_TABLE);
        db.execSQL(MATERIEL_TABLE);
        db.execSQL(LOT_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w("DBOpenHelper", "Mise à jour de la version " + oldVersion
                + " vers la version " + newVersion
                + ", les anciennes données seront détruites ");
        // Create the new one
        Constants.DATABASE_VERSION = newVersion;
        onCreate(db);
        // or do a smartest stuff
    }

    // Requete pour Fiche de Controle (en test)
    public boolean addDataFDC(String dateControle, String observationControle){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("date", Constants.dateControle);
        contentValues.put("observation", Constants.observationControle);

        long result = db.insert(CONTROLE_TABLE, null , contentValues);

        if (result == -1){
            return false;
        }else {
            return true;
        }
    }


    /**
     * Getting all labels
     * returns list of labels
     * */
    public List<String> getAllLabels(){
        List<String> labels = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + Constants.tableControleur;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning lables
        return labels;
    }
}
