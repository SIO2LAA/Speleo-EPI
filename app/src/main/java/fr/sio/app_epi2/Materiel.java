package fr.sio.app_epi2;

import java.util.Date;

public class Materiel {
    private int idMateriel;
    private String libelle;
    private String modele;
    private String signeDistinctif;
    private Date dateAcquisition;
    private Date datePremiereUtilisation;
    private Date dateLimiteRebut;
    private Date dateFabrication;
    private String marquage;
    private String emplacementMarquage;


    public int getIdMateriel(){return idMateriel;}

    public String getLibelle(){return libelle;}

    public String getModele(){return modele;}

    public String getSigneDistinctif(){return signeDistinctif;}

    public Date getDateAcquisition(){return dateAcquisition;}

    public void setDateAcquisition(Date date){this.dateAcquisition=date; }

    public Date getDatePremiereUtilisation(){return datePremiereUtilisation;}

    public void setDatePremiereUtilisation(Date date){this.datePremiereUtilisation=date;}

    public Date getDateLimiteRebut(){return dateLimiteRebut;}

    public void setDateLimiteRebut(Date date){this.dateLimiteRebut=date;}

    public Date getDateFabrication(){return dateFabrication;}

    public void setDateFabrication(Date date){this.dateFabrication=date;}

    public String getMarquage(){return marquage;}

    public String getEmplacementMarquage(){return emplacementMarquage;}
}

