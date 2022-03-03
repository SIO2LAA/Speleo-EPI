package fr.sio.app_epi2.models;

import java.util.Date;

public class Controle {

    private int idControle;
    private Date date;
    private String observation;
    private String nature;
    private String lieu;

    //Constructeur de la classe Controle
    public Controle(int idControle, Date date, String observation, String nature, String lieu){
        this.idControle = idControle;
        this.date = date;
        this.observation = observation;
        this.nature = nature;
        this.lieu = lieu;
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
}
