package fr.sio.app_epi2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fr.sio.app_epi2.models.Tag;

public class xmlFile {
    private Context context;
    private String name;
    private String path;
    private ArrayList<Tag> listeTag;

    public xmlFile(Context context, String name, ArrayList<Tag> listeTag) {
        this.context = context;
        this.name = name;
        this.path = "/data/data/" + context.getPackageName() + "/";
        this.listeTag = listeTag;
    }

    public void exportDB(SQLiteDatabase db) {
        Log.i("path", this.path);

        File dateFile = new File(this.path + "data" + ".xml");
        try{
            dateFile.createNewFile();
        }catch(IOException e)
        {
            Log.e("IOException", "Exception in create new File(");
        }
        FileOutputStream fileos = null;
        try{
            fileos = new FileOutputStream(dateFile);

        }catch(FileNotFoundException e)
        {
            Log.e("FileNotFoundException",e.toString());
        }

        XmlSerializer serializer = Xml.newSerializer();
        Cursor controleCursor = db.query("controle", null,null,null,null,null,null);
        Cursor controleurCursor = db.query("controleur", null,null,null,null,null,null);
        Cursor fabricantCursor = db.query("fabricant", null,null,null,null,null,null);
        Cursor lotCursor = db.query("lot", null,null,null,null,null,null);
        Cursor materielCursor = db.query("materiel", null,null,null,null,null,null);
        Cursor typesCursor = db.query("types", null,null,null,null,null,null);

        try{
            serializer.setOutput(fileos, "UTF-8");
            serializer.startDocument(null, Boolean.valueOf(true));
            serializer.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", true);
            serializer.startTag(null, "root");
            serializer.startTag(null, "controle");
            while (controleCursor.moveToNext()) {
                serializer.startTag(null, "cont");
                serializer.attribute(null, controleCursor.getColumnName(0), controleCursor.getString(0));
                serializer.startTag(null, controleCursor.getColumnName(1));
                serializer.text(controleCursor.getString(1));
                serializer.endTag(null, controleCursor.getColumnName(1));
                serializer.startTag(null, controleCursor.getColumnName(2));
                serializer.text(controleCursor.getString(2));
                serializer.endTag(null, controleCursor.getColumnName(2));
                serializer.endTag(null, "cont");
            }
            serializer.endTag(null, "controle");

            serializer.startTag(null, "controleur");
            while (controleurCursor.moveToNext()) {
                serializer.startTag(null, "contro");
                serializer.attribute(null, controleurCursor.getColumnName(0), controleurCursor.getString(0));
                serializer.startTag(null, controleurCursor.getColumnName(1));
                serializer.text(controleurCursor.getString(1));
                serializer.endTag(null, controleurCursor.getColumnName(1));
                serializer.startTag(null, controleurCursor.getColumnName(2));
                serializer.text(controleurCursor.getString(2));
                serializer.endTag(null, controleurCursor.getColumnName(2));
                serializer.endTag(null, "contro");
            }
            serializer.endTag(null, "controleur");

            serializer.startTag(null, "fabricant");
            while (fabricantCursor.moveToNext()) {
                serializer.startTag(null, "fab");
                serializer.attribute(null, fabricantCursor.getColumnName(0), fabricantCursor.getString(0));
                serializer.startTag(null, fabricantCursor.getColumnName(1));
                serializer.text(fabricantCursor.getString(1));
                serializer.endTag(null, fabricantCursor.getColumnName(1));
                serializer.endTag(null, "fab");
            }
            serializer.endTag(null, "fabricant");

            serializer.startTag(null, "lot");
            while (lotCursor.moveToNext()) {
                serializer.startTag(null, "l");
                serializer.attribute(null, lotCursor.getColumnName(0), lotCursor.getString(0));
                serializer.startTag(null, lotCursor.getColumnName(1));
                serializer.text(lotCursor.getString(1));
                serializer.endTag(null, lotCursor.getColumnName(1));
                serializer.startTag(null, lotCursor.getColumnName(2));
                serializer.text(lotCursor.getString(2));
                serializer.endTag(null, lotCursor.getColumnName(2));
                serializer.endTag(null, "l");
            }
            serializer.endTag(null, "lot");

            serializer.startTag(null, "materiel");
            while (materielCursor.moveToNext()) {
                serializer.startTag(null, "mat");
                serializer.attribute(null, materielCursor.getColumnName(0), materielCursor.getString(0));
                serializer.startTag(null, materielCursor.getColumnName(1));
                serializer.text(materielCursor.getString(1));
                serializer.endTag(null, materielCursor.getColumnName(1));
                serializer.startTag(null, materielCursor.getColumnName(2));
                serializer.text(materielCursor.getString(2));
                serializer.endTag(null, materielCursor.getColumnName(2));
                serializer.startTag(null, materielCursor.getColumnName(3));
                serializer.text(materielCursor.getString(3));
                serializer.endTag(null, materielCursor.getColumnName(3));
                serializer.startTag(null, materielCursor.getColumnName(4));
                serializer.text(materielCursor.getString(4));
                serializer.endTag(null, materielCursor.getColumnName(4));
                serializer.startTag(null, materielCursor.getColumnName(5));
                serializer.text(materielCursor.getString(5));
                serializer.endTag(null, materielCursor.getColumnName(5));
                serializer.startTag(null, materielCursor.getColumnName(6));
                serializer.text(materielCursor.getString(6));
                serializer.endTag(null, materielCursor.getColumnName(6));
                serializer.startTag(null, materielCursor.getColumnName(7));
                serializer.text(materielCursor.getString(7));
                serializer.endTag(null, materielCursor.getColumnName(7));
                serializer.startTag(null, materielCursor.getColumnName(8));
                serializer.text(materielCursor.getString(8));
                serializer.endTag(null, materielCursor.getColumnName(8));
                serializer.startTag(null, materielCursor.getColumnName(9));
                serializer.text(materielCursor.getString(9));
                serializer.endTag(null, materielCursor.getColumnName(9));
                serializer.startTag(null, materielCursor.getColumnName(10));
                serializer.text(materielCursor.getString(10));
                serializer.endTag(null, materielCursor.getColumnName(10));
                serializer.startTag(null, materielCursor.getColumnName(11));
                serializer.text(materielCursor.getString(11));
                serializer.endTag(null, materielCursor.getColumnName(11));
                serializer.startTag(null, materielCursor.getColumnName(12));
                serializer.text(materielCursor.getString(12));
                serializer.endTag(null, materielCursor.getColumnName(12));
                serializer.endTag(null, "mat");
            }
            serializer.endTag(null, "materiel");

            serializer.startTag(null, "types");
            while (typesCursor.moveToNext()) {
                serializer.startTag(null, "ty");
                serializer.attribute(null, typesCursor.getColumnName(0), typesCursor.getString(0));
                serializer.startTag(null, typesCursor.getColumnName(1));
                serializer.text(typesCursor.getString(1));
                serializer.endTag(null, typesCursor.getColumnName(1));
                serializer.endTag(null, "ty");
            }
            serializer.endTag(null, "types");

            serializer.endTag(null,"root");
            serializer.endDocument();
            serializer.flush();
            fileos.close();
            //TextView tv = (TextView)findViewById(R.);

        }catch(Exception e)
        {
            Log.e("Exception","Exception occured in wroting");
        }
    }

    public void createFV(){

        Log.i("path", this.path);

        File newxmlfile = new File(this.path + this.name + ".xml");
        try{
            newxmlfile.createNewFile();
        }catch(IOException e)
        {
            Log.e("IOException", "Exception in create new File(");
        }
        FileOutputStream fileos = null;
        try{
            fileos = new FileOutputStream(newxmlfile);

        }catch(FileNotFoundException e)
        {
            Log.e("FileNotFoundException",e.toString());
        }


        XmlSerializer serializer = Xml.newSerializer();
        try{
            serializer.setOutput(fileos, "UTF-8");
            serializer.startDocument(null, Boolean.valueOf(true));
            //serializer.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", true);
            serializer.startTag(null, "root");
            serializer.startTag(null, "materiel");
            serializer.attribute(null, "id", "41");
            serializer.startTag(null, "nom");
            serializer.text("Casque bleu");
            serializer.endTag(null, "nom");
            serializer.endTag(null, "materiel");
            serializer.endTag(null,"root");
            serializer.endDocument();
            serializer.flush();
            fileos.close();
            //TextView tv = (TextView)findViewById(R.);

        }catch(Exception e)
        {
            Log.e("Exception","Exception occured in wroting");
        }

    }

    public void readFV(File file){

        FileInputStream fileout = null;
        try{
            fileout = new FileInputStream(file);
        } catch(FileNotFoundException e)
        {
            Log.e("FileNotFoundException",e.toString());
        } catch(IOException e)
        {
            Log.e("IOException", "Exception in create new File(");
        }

        XmlPullParser parser = Xml.newPullParser();

        try{
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(fileout, null);
            parser.nextTag();
            fileout.close();
            //https://developer.android.com/training/basics/network-ops/xml

        }catch(Exception e)
        {
            Log.e("Exception","Exception occured in wroting");
        }

    }
}
