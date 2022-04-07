package fr.sio.app_epi2.models;

import java.util.Date;

public class Controle {

    private int idControle;
    private Date date;
    private String observation;
    private String nature;
    private String lieu;
    private int idMateriel;
    private int idControleur;

    //Constructeur de la classe Controle
    public Controle(int idControle, Date date, String observation, String nature, String lieu, int idMateriel, int idControleur) {
        this.idControle = idControle;
        this.date = date;
        this.observation = observation;
        this.nature = nature;
        this.lieu = lieu;
        this.idMateriel = idMateriel;
        this.idControleur = idControleur;
    }

    public int getIdControle() {
        return idControle;
    }

    public void setIdControle(int idControle) {
        this.idControle = idControle;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public int getIdMateriel() {
        return idMateriel;
    }

    public void setIdMateriel(int idMateriel) {
        this.idMateriel = idMateriel;
    }

    public int getIdControleur() {
        return idControleur;
    }

    public void setIdControleur(int idControleur) {
        this.idControleur = idControleur;
    }
}
