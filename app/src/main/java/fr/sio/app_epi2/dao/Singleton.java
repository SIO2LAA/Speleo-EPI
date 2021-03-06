package fr.sio.app_epi2.dao;

//import fr.sio.app_epi2.DBOpenHelper;

import android.content.Context;

import fr.sio.app_epi2.DBOpenHelper;

public final class Singleton {
    private static Singleton instance;
    private DBOpenHelper dbOpenHelper;
    private final String DATABASE_NAME = "EPI2.db";
    private int DATABASE_VERSION = 1;
    private static int test = 0;

    private Singleton(Context context) {
        // The following code emulates slow initialization.
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        dbOpenHelper = new DBOpenHelper(context, this.DATABASE_NAME, null, this.DATABASE_VERSION
        );
        test++;
    }

    public DBOpenHelper getDbOpenHelper() {
        return this.dbOpenHelper;
    }

    public static Singleton getDB(Context context) {
        if (instance == null) {
            instance = new Singleton(context);
        }
        
        return instance;
    }

}