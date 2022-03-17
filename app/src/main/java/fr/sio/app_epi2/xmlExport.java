package fr.sio.app_epi2;

import android.os.Bundle;
import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class xmlExport {
   private String name;
   private String path;
   private ArrayList<String> listeTag;

    public xmlExport(String name, String path, ArrayList<String> listeTag) {
        this.name = name;
        this.path = path;
        this.listeTag = listeTag;
    }

    public void createFile(){


       File newxmlfile = new File("/data/data.xml");
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
           serializer.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", true);
           serializer.startTag(null, "DATA");
            serializer.startTag(null, "FABRICANT");

            serializer.endTag(null, "FABRICANT");
           serializer.startTag(null, "Child2");
           serializer.attribute(null, "attribute", "value");
           serializer.endTag(null, "Child2");
           serializer.startTag(null, "Child3");
           serializer.text("Some text inside child 3");
           serializer.endTag(null,"Child3");
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
