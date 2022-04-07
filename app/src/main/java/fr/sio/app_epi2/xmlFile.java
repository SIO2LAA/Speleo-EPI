package fr.sio.app_epi2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.util.Xml;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import fr.sio.app_epi2.models.Controle;
import fr.sio.app_epi2.models.Controleur;
import fr.sio.app_epi2.models.Fabricant;
import fr.sio.app_epi2.models.Lot;
import fr.sio.app_epi2.models.Materiel;
import fr.sio.app_epi2.models.Tag;
import fr.sio.app_epi2.models.Type;

public class xmlFile {
    private Context context;
    private SQLiteDatabase db;
    private String name;
    private String path;
    private ArrayList<Tag> listeTag;

    public xmlFile(Context context, String name, ArrayList<Tag> listeTag, SQLiteDatabase db) {
        this.context = context;
        this.db = db;
        this.name = name;
        this.path = "/data/data/" + context.getPackageName() + "/";
        this.listeTag = listeTag;
    }

    public void importDB(Context context, File xmlFile) {
        SimpleDateFormat format = new SimpleDateFormat("y-m-d");

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder d = null;
        Boolean existing = false;
        ArrayList<String> listeDB = new ArrayList<>();
        ArrayList<Fabricant> listeFabricants = new ArrayList<>();
        ArrayList<Controle> listeControles = new ArrayList<>();
        ArrayList<Controleur> listeControleurs = new ArrayList<>();
        ArrayList<Lot> listeLots = new ArrayList<>();
        ArrayList<Materiel> listeMateriels = new ArrayList<>();
        ArrayList<Type> listeTypes = new ArrayList<>();

        Log.i("liste", "ENTER1");
        try {
            d = dbf.newDocumentBuilder();
            Document document = d.parse(xmlFile);
            document.getDocumentElement().normalize();
            Log.i("liste", "ENTER2");
            Cursor cursorFabricant = db.rawQuery("SELECT id FROM fabricant", null);
            Cursor cursorControle = db.rawQuery("SELECT id FROM controle", null);
            Cursor cursorControleur = db.rawQuery("SELECT id FROM controleur", null);
            Cursor cursorLot = db.rawQuery("SELECT numero FROM lot", null);
            Cursor cursorMateriel = db.rawQuery("SELECT id FROM materiel", null);
            Cursor cursorTypes = db.rawQuery("SELECT id FROM types", null);
            Log.i("liste", "ENTER SKIP DB");

            if (document.getElementsByTagName("FABRICANT").item(0) != null) {
                NodeList nFAB = document.getElementsByTagName("FAB");
                for (int temp = 0; temp < nFAB.getLength(); temp++) {
                    Node nNode = nFAB.item(temp);
                            /*nFAB.item(temp);*/
                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element eElement = (Element) nNode;
                        Log.i("liste", "ENTER FABRICANT");
                        try {

                            while (cursorFabricant.moveToNext()) {
                                if (cursorFabricant.getString(0) == eElement.getAttribute("id")) {
                                    Log.i("xml", "Info existe déjà dans la base de données !");
                                    existing = true;
                                } else {
                                    Fabricant fab = new Fabricant(Integer.valueOf(eElement.getAttribute("id")), eElement.getElementsByTagName("NOMFABRICANT").item(0).getTextContent());
                                    listeFabricants.add(fab);
                                }
                            }

                            for (int i = 0; i < listeFabricants.size(); i++) {
                                Log.i("liste", "FAB : " + listeFabricants.get(i).getNom());
                            }

                        }
                        catch (Exception e) {
                            Log.i("liste", e.getMessage());
                            System.out.println("Problème lors de l'import des données");
                        }
                    }
                }
            }

            if (document.getElementsByTagName("CONTROLE").item(0) != null) {
                NodeList nCTL = document.getElementsByTagName("CTL");
                for (int temp = 0; temp < nCTL.getLength(); temp++) {
                    Node nNode = nCTL.item(temp);
                    /*nFAB.item(temp);*/
                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element eElement = (Element) nNode;
                        Log.i("liste", "ENTER CONTROLE");
                        try {

                            while (cursorControle.moveToNext()) {
                                if (cursorControle.getString(0) == eElement.getAttribute("id")) {
                                    Log.i("xml", "Info existe déjà dans la base de données !");
                                    existing = true;
                                } else {
                                    Controle controle = new Controle(Integer.valueOf(eElement.getAttribute("id")), format.parse(eElement.getElementsByTagName("DATECONTROLE").item(0).getTextContent()), eElement.getElementsByTagName("OBSERVATIONCONTROLE").item(0).getTextContent(), eElement.getElementsByTagName("NATURECONTROLE").item(0).getTextContent(), eElement.getElementsByTagName("LIEUCONTROLE").item(0).getTextContent(), Integer.valueOf(eElement.getElementsByTagName("IDMATERIELCONTROLE").item(0).getTextContent()), Integer.valueOf(eElement.getElementsByTagName("IDCONTROLEURCONTROLE").item(0).getTextContent()));
                                    listeControles.add(controle);
                                }
                            }

                        }
                        catch (Exception e) {
                            Log.i("liste", e.getMessage());
                            System.out.println("Problème lors de l'import des données");
                        }
                    }
                }
            }

            if (document.getElementsByTagName("CONTROLEUR").item(0) != null) {
                NodeList nCTLEUR = document.getElementsByTagName("CTLEUR");
                for (int temp = 0; temp < nCTLEUR.getLength(); temp++) {
                    Node nNode = nCTLEUR.item(temp);
                    /*nFAB.item(temp);*/
                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element eElement = (Element) nNode;
                        Log.i("liste", "ENTER CONTROLEUR");
                        try {

                            while (cursorControleur.moveToNext()) {
                                if (cursorControleur.getString(0) == eElement.getAttribute("id")) {
                                    Log.i("xml", "Info existe déjà dans la base de données !");
                                    existing = true;
                                } else {
                                    Controleur controleur = new Controleur(Integer.valueOf(eElement.getAttribute("id")), eElement.getElementsByTagName("NOMCONTROLEUR").item(0).getTextContent(), eElement.getElementsByTagName("PRENOMCONTROLEUR").item(0).getTextContent());
                                    listeControleurs.add(controleur);
                                }
                            }

                        }
                        catch (Exception e) {
                            Log.i("liste", e.getMessage());
                            System.out.println("Problème lors de l'import des données");
                        }
                    }
                }
            }

            if (document.getElementsByTagName("LOT").item(0) != null) {
                NodeList nL = document.getElementsByTagName("L");
                for (int temp = 0; temp < nL.getLength(); temp++) {
                    Node nNode = nL.item(temp);
                    /*nFAB.item(temp);*/
                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element eElement = (Element) nNode;
                        Log.i("liste", "ENTER LOT");
                        try {

                            while (cursorLot.moveToNext()) {
                                if (cursorLot.getString(0) == eElement.getAttribute("id")) {
                                    Log.i("xml", "Info existe déjà dans la base de données !");
                                    existing = true;
                                } else {
                                    Lot lot = new Lot(Integer.valueOf(eElement.getAttribute("numero")), format.parse(eElement.getElementsByTagName("DATELOT").item(0).getTextContent()), Integer.valueOf(eElement.getElementsByTagName("QUANTITELOT").item(0).getTextContent()), Integer.valueOf(eElement.getElementsByTagName("IDMATERIELLOT").item(0).getTextContent()));
                                    listeLots.add(lot);
                                }
                            }

                        }
                        catch (Exception e) {
                            Log.i("liste", e.getMessage());
                            System.out.println("Problème lors de l'import des données");
                        }
                    }
                }
            }

            if (document.getElementsByTagName("MATERIEL").item(0) != null) {
                NodeList nMAT = document.getElementsByTagName("MAT");
                for (int temp = 0; temp < nMAT.getLength(); temp++) {
                    Node nNode = nMAT.item(temp);
                    /*nFAB.item(temp);*/
                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element eElement = (Element) nNode;
                        Log.i("liste", "ENTER MATERIEL");
                        try {

                            while (cursorMateriel.moveToNext()) {
                                if (cursorMateriel.getString(0) == eElement.getAttribute("id")) {
                                    Log.i("xml", "Info existe déjà dans la base de données !");
                                    existing = true;
                                } else {
                                    Materiel materiel = new Materiel(Integer.valueOf(eElement.getAttribute("id")), eElement.getElementsByTagName("LIBELLEMATERIEL").item(0).getTextContent(), eElement.getElementsByTagName("MODELEMATERIEL").item(0).getTextContent(), eElement.getElementsByTagName("SIGNEDISTINCTIFMATERIEL").item(0).getTextContent(), format.parse(eElement.getElementsByTagName("DATEACQUISITIONMATERIEL").item(0).getTextContent()), format.parse(eElement.getElementsByTagName("DATEPREMIEREUTILISATIONMATERIEL").item(0).getTextContent()), format.parse(eElement.getElementsByTagName("DATELIMITEREBUTMATERIEL").item(0).getTextContent()), format.parse(eElement.getElementsByTagName("DATEFABRICATIONMATERIEL").item(0).getTextContent()), eElement.getElementsByTagName("MARQUAGEMATERIEL").item(0).getTextContent(), eElement.getElementsByTagName("EMPLACEMENTMARQUAGEMATERIEL").item(0).getTextContent(), Integer.valueOf(eElement.getElementsByTagName("IDFABRICANTMATERIEL").item(0).getTextContent()), Integer.valueOf(eElement.getElementsByTagName("IDTYPEMATERIEL").item(0).getTextContent()), Integer.valueOf(eElement.getElementsByTagName("IDCONTROLEURMATERIEL").item(0).getTextContent()));
                                    listeMateriels.add(materiel);
                                }
                            }

                            for (int i = 0; i < listeMateriels.size(); i++) {
                                Log.i("liste", listeMateriels.get(i).getLibelle());
                            }

                        }
                        catch (Exception e) {
                            Log.i("liste", e.getMessage());
                            System.out.println("Problème lors de l'import des données");
                        }
                    }
                }
            }

            if (document.getElementsByTagName("TYPES").item(0) != null) {
                NodeList nTY = document.getElementsByTagName("TY");
                for (int temp = 0; temp < nTY.getLength(); temp++) {
                    Node nNode = nTY.item(temp);
                    /*nFAB.item(temp);*/
                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element eElement = (Element) nNode;
                        Log.i("liste", "ENTER TYPES");
                        try {

                            while (cursorTypes.moveToNext()) {
                                if (cursorTypes.getString(0) == eElement.getAttribute("id")) {
                                    Log.i("xml", "Info existe déjà dans la base de données !");
                                    existing = true;
                                } else {
                                    Type type = new Type(Integer.valueOf(eElement.getAttribute("id")), eElement.getElementsByTagName("NOMTYPE").item(0).getTextContent());
                                    listeTypes.add(type);
                                }
                            }

                        }
                        catch (Exception e) {
                            Log.i("liste", e.getMessage());
                            System.out.println("Problème lors de l'import des données");
                        }
                    }
                }
            }

        } catch (ParserConfigurationException e) {
            //Log.i("liste", e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            //Log.i("liste", e.getMessage());
            e.printStackTrace();
        } catch (SAXException e) {
            //Log.i("liste", e.getMessage());
            e.printStackTrace();
        }

    }

    public void exportDB(SQLiteDatabase db) {
        //Log.i("path", this.path);

        File dateFile = new File(this.path + "data" + ".xml");
        try{
            dateFile.createNewFile();
        }catch(IOException e)
        {
            Log.e("IOException", "Exception in create new File");
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
        serializer.startTag(null, "DATA");

            if (controleCursor.getCount() != 0) {
                serializer.startTag(null, "CONTROLE");
                while (controleCursor.moveToNext()) {
                    serializer.startTag(null, "CTL");
                    serializer.attribute(null, controleCursor.getColumnName(0), controleCursor.getString(0));
                    serializer.startTag(null, controleCursor.getColumnName(1).toUpperCase() + "CONTROLE");
                    serializer.text(controleCursor.getString(1));
                    serializer.endTag(null, controleCursor.getColumnName(1).toUpperCase() + "CONTROLE");
                    serializer.startTag(null, controleCursor.getColumnName(2).toUpperCase() + "CONTROLE");
                    serializer.text(controleCursor.getString(2));
                    serializer.endTag(null, controleCursor.getColumnName(2).toUpperCase() + "CONTROLE");
                    serializer.startTag(null, controleCursor.getColumnName(3).toUpperCase() + "CONTROLE");
                    serializer.text(controleCursor.getString(3));
                    serializer.endTag(null, controleCursor.getColumnName(3).toUpperCase() + "CONTROLE");
                    serializer.startTag(null, controleCursor.getColumnName(4).toUpperCase() + "CONTROLE");
                    serializer.text(controleCursor.getString(4));
                    serializer.endTag(null, controleCursor.getColumnName(4).toUpperCase() + "CONTROLE");
                    serializer.startTag(null, controleCursor.getColumnName(5).toUpperCase() + "CONTROLE");
                    serializer.text(controleCursor.getString(5));
                    serializer.endTag(null, controleCursor.getColumnName(5).toUpperCase() + "CONTROLE");
                    serializer.startTag(null, controleCursor.getColumnName(6).toUpperCase() + "CONTROLE");
                    serializer.text(controleCursor.getString(6));
                    serializer.endTag(null, controleCursor.getColumnName(6).toUpperCase() + "CONTROLE");
                    serializer.endTag(null, "CTL");
                }
                serializer.endTag(null, "CONTROLE");
            }

            if (controleurCursor.getCount() != 0) {
                serializer.startTag(null, "CONTROLEUR");
                while (controleurCursor.moveToNext()) {
                    serializer.startTag(null, "CTLEUR");
                    serializer.attribute(null, controleurCursor.getColumnName(0), controleurCursor.getString(0));
                    serializer.startTag(null, controleurCursor.getColumnName(1).toUpperCase() + "CONTROLEUR");
                    serializer.text(controleurCursor.getString(1));
                    serializer.endTag(null, controleurCursor.getColumnName(1).toUpperCase() + "CONTROLEUR");
                    serializer.startTag(null, controleurCursor.getColumnName(2).toUpperCase() + "CONTROLEUR");
                    serializer.text(controleurCursor.getString(2));
                    serializer.endTag(null, controleurCursor.getColumnName(2).toUpperCase() + "CONTROLEUR");
                    serializer.endTag(null, "CTLEUR");
                }
                serializer.endTag(null, "CONTROLEUR");
            }

            if (fabricantCursor.getCount() != 0) {
                serializer.startTag(null, "FABRICANT");
                while (fabricantCursor.moveToNext()) {
                    serializer.startTag(null, "FAB");
                    serializer.attribute(null, fabricantCursor.getColumnName(0), fabricantCursor.getString(0));
                    serializer.startTag(null, fabricantCursor.getColumnName(1).toUpperCase() + "FABRICANT");
                    serializer.text(fabricantCursor.getString(1));
                    serializer.endTag(null, fabricantCursor.getColumnName(1).toUpperCase() + "FABRICANT");
                    serializer.endTag(null, "FAB");
                }
                serializer.endTag(null, "FABRICANT");
            }

            if (lotCursor.getCount() != 0) {
                serializer.startTag(null, "LOT");
                while (lotCursor.moveToNext()) {
                    serializer.startTag(null, "L");
                    serializer.attribute(null, lotCursor.getColumnName(0), lotCursor.getString(0));
                    serializer.startTag(null, lotCursor.getColumnName(1).toUpperCase() + "LOT");
                    serializer.text(lotCursor.getString(1));
                    serializer.endTag(null, lotCursor.getColumnName(1).toUpperCase() + "LOT");
                    serializer.startTag(null, lotCursor.getColumnName(2).toUpperCase() + "LOT");
                    serializer.text(lotCursor.getString(2));
                    serializer.endTag(null, lotCursor.getColumnName(2).toUpperCase() + "LOT");
                    serializer.endTag(null, "L");
                }
                serializer.endTag(null, "LOT");
            }

            if (materielCursor.getCount() != 0) {
                serializer.startTag(null, "MATERIEL");
                while (materielCursor.moveToNext()) {
                    serializer.startTag(null, "MAT");
                    serializer.attribute(null, materielCursor.getColumnName(0), materielCursor.getString(0));
                    serializer.startTag(null, materielCursor.getColumnName(1).toUpperCase() + "MATERIEL");
                    serializer.text(materielCursor.getString(1));
                    serializer.endTag(null, materielCursor.getColumnName(1).toUpperCase() + "MATERIEL");
                    serializer.startTag(null, materielCursor.getColumnName(2).toUpperCase() + "MATERIEL");
                    serializer.text(materielCursor.getString(2));
                    serializer.endTag(null, materielCursor.getColumnName(2).toUpperCase() + "MATERIEL");
                    serializer.startTag(null, materielCursor.getColumnName(3).toUpperCase() + "MATERIEL");
                    serializer.text(materielCursor.getString(3));
                    serializer.endTag(null, materielCursor.getColumnName(3).toUpperCase() + "MATERIEL");
                    serializer.startTag(null, materielCursor.getColumnName(4).toUpperCase() + "MATERIEL");
                    serializer.text(materielCursor.getString(4));
                    serializer.endTag(null, materielCursor.getColumnName(4).toUpperCase() + "MATERIEL");
                    serializer.startTag(null, materielCursor.getColumnName(5).toUpperCase() + "MATERIEL");
                    serializer.text(materielCursor.getString(5));
                    serializer.endTag(null, materielCursor.getColumnName(5).toUpperCase() + "MATERIEL");
                    serializer.startTag(null, materielCursor.getColumnName(6).toUpperCase() + "MATERIEL");
                    serializer.text(materielCursor.getString(6));
                    serializer.endTag(null, materielCursor.getColumnName(6).toUpperCase() + "MATERIEL");
                    serializer.startTag(null, materielCursor.getColumnName(7).toUpperCase() + "MATERIEL");
                    serializer.text(materielCursor.getString(7));
                    serializer.endTag(null, materielCursor.getColumnName(7).toUpperCase() + "MATERIEL");
                    serializer.startTag(null, materielCursor.getColumnName(8).toUpperCase() + "MATERIEL");
                    serializer.text(materielCursor.getString(8));
                    serializer.endTag(null, materielCursor.getColumnName(8).toUpperCase() + "MATERIEL");
                    serializer.startTag(null, materielCursor.getColumnName(9).toUpperCase() + "MATERIEL");
                    serializer.text(materielCursor.getString(9));
                    serializer.endTag(null, materielCursor.getColumnName(9).toUpperCase() + "MATERIEL");
                    serializer.startTag(null, materielCursor.getColumnName(10).toUpperCase() + "MATERIEL");
                    serializer.text(materielCursor.getString(10));
                    serializer.endTag(null, materielCursor.getColumnName(10).toUpperCase() + "MATERIEL");
                    serializer.startTag(null, materielCursor.getColumnName(11).toUpperCase() + "MATERIEL");
                    serializer.text(materielCursor.getString(11));
                    serializer.endTag(null, materielCursor.getColumnName(11).toUpperCase() + "MATERIEL");
                    serializer.startTag(null, materielCursor.getColumnName(12).toUpperCase() + "MATERIEL");
                    serializer.text(materielCursor.getString(12));
                    serializer.endTag(null, materielCursor.getColumnName(12).toUpperCase() + "MATERIEL");
                    serializer.endTag(null, "MAT");
                }
                serializer.endTag(null, "MATERIEL");
            }

            if (typesCursor.getCount() != 0) {
                serializer.startTag(null, "TYPES");
                while (typesCursor.moveToNext()) {
                    serializer.startTag(null, "TY");
                    serializer.attribute(null, typesCursor.getColumnName(0), typesCursor.getString(0));
                    serializer.startTag(null, typesCursor.getColumnName(1).toUpperCase() + "TYPES");
                    serializer.text(typesCursor.getString(1));
                    serializer.endTag(null, typesCursor.getColumnName(1).toUpperCase() + "TYPES");
                    serializer.endTag(null, "TY");
                }
                serializer.endTag(null, "TYPES");
            }

            serializer.endTag(null,"DATA");
            serializer.endDocument();
            serializer.flush();
            fileos.close();

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
