package fr.sio.app_epi2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

 class DBOpenHelper extends SQLiteOpenHelper {

     private String update;
     private String insert;

     // Constructeur
     public DBOpenHelper(Context context, String name, int version, String update, String insert) {
         super(context, name, null,version);
         this.update = update;
         this.insert = insert;
     }

     // @goals This class aims to show the constant to use for the DBOpenHelper */
    public static class Constants implements BaseColumns {

        // The database name
        public static final String DATABASE_NAME = "EPI2.db";

        // The database version
        public static final int DATABASE_VERSION = 1;

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
        public static final String marquageMateriel = "marquage";
        public static final String empalcementMarquageMateriel = "emplacementMarquage";
        public static final String idFabricantMateriel = "idFabricant";
        public static final String idTypeMateriel = "idType";
        public static final String idControleurMateriel = "idControleur";

         // Table Lot
         public static final String dateLot = "date";// Mandatory
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
             + Constants.nomType + " VARCHAR(100) )";

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
             + " integer primary key autoincrement, "
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
             + Constants.tableLot + "(" + Constants.dateLot
             + " DATE, "
             + Constants.quantiteLot + " INTEGER,"
             + Constants.idMaterielLot + " INTEGER,"
             + "PRIMARY KEY (" + Constants.dateLot + ", " + Constants.idMaterielLot + "),"
             + "CONSTRAINT fk_id_materiel_lot FOREIGN KEY (" + Constants.idMaterielLot + ") REFERENCES " + Constants.tableMateriel + "(" + Constants.idMateriel + ")"
             + ")";






     /**
     * @param context
     * @param name
     * @param factory
     * @param version
     */
    public DBOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                        int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create the new database using the SQL string Database_create
        db.execSQL("DROP TABLE IF EXISTS " + DBOpenHelper.Constants.tableFabricant + DBOpenHelper.Constants.tableTypes + DBOpenHelper.Constants.tableControle + DBOpenHelper.Constants.tableControleur + DBOpenHelper.Constants.tableMateriel);
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
        // Drop the old database
        db.execSQL("DROP TABLE IF EXISTS " + Constants.tableFabricant + Constants.tableTypes + Constants.tableControle + Constants.tableControleur + Constants.tableMateriel);
        // Create the new one
        onCreate(db);
        // or do a smartest stuff
    }
}
