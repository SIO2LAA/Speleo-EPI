package fr.sio.app_epi2.models;

import java.util.Date;

public class Lot {

    private int idLot;
    private Date date;
    private int nombre;

    //Constructeur de la classe Lot
    public Lot(int idLot, Date date, int nombre){
        this.idLot = idLot;
        this.date = date;
        this.nombre = nombre;
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

    public int getNombre() {
        return nombre;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
    }
}
