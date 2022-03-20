package fr.sio.app_epi2;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;
import android.util.Xml;

import androidx.annotation.RequiresApi;

import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class xmlImport {
    private Context context;
    private String name;
    private String path;
    private ArrayList<String> listeTag;

    public xmlImport(Context context, String name, ArrayList<String> listeTag) {
        this.context = context;
        this.name = name;
        this.path = "/data/data/" + context.getPackageName() + "/";
        this.listeTag = listeTag;
    }

    public void createFile(){

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
            serializer.startTag(null, "test");
            serializer.text("Bonjour c'est un message de test");
            serializer.endTag(null, "test");
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


}
