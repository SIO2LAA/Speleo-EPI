package fr.sio.app_epi2.models;

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

    //Constructeur de la classe Materiel
    public Materiel(int idMateriel, String libelle, String modele, String signeDistinctif, Date dateAcquisition, Date datePremiereUtilisation, Date dateLimiteRebut, Date dateFabrication, String marquage, String emplacementMarquage){
        this.idMateriel = idMateriel;
        this.libelle = libelle;
        this.modele = modele;
        this.signeDistinctif = signeDistinctif;
        this.dateAcquisition = dateAcquisition;
        this.dateLimiteRebut = dateLimiteRebut;
        this.marquage = marquage;
        this.emplacementMarquage = emplacementMarquage;
    }

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

