package fr.sio.app_epi2.models;

import java.util.Date;

public class Lot {

    private int idLot;
    private Date date;
    private int quantite;
    private int idMateriel;

    //Constructeur de la classe Lot
    public Lot(int idLot, Date date, int quantite, int idMateriel) {
        this.idLot = idLot;
        this.date = date;
        this.quantite = quantite;
        this.idMateriel = idMateriel;
    }

    public int getIdLot() {
        return idLot;
    }

    public void setIdLot(int idLot) {
        this.idLot = idLot;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public int getIdMateriel() {
        return idMateriel;
    }

    public void setIdMateriel(int idMateriel) {
        this.idMateriel = idMateriel;
    }
}
