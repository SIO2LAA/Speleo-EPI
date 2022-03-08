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
    private int quantite;
    private int idFabricant;
    private int idType;
    private int idControleur;


    //Constructeur de la classe Materiel


    public Materiel(int idMateriel, String libelle, String modele, String signeDistinctif, Date dateAcquisition, Date datePremiereUtilisation, Date dateLimiteRebut, Date dateFabrication, String marquage, String emplacementMarquage, int quantite, int idFabricant, int idType, int idControleur) {
        this.idMateriel = idMateriel;
        this.libelle = libelle;
        this.modele = modele;
        this.signeDistinctif = signeDistinctif;
        this.dateAcquisition = dateAcquisition;
        this.datePremiereUtilisation = datePremiereUtilisation;
        this.dateLimiteRebut = dateLimiteRebut;
        this.dateFabrication = dateFabrication;
        this.marquage = marquage;
        this.emplacementMarquage = emplacementMarquage;
        this.quantite = quantite;
        this.idFabricant = idFabricant;
        this.idType = idType;
        this.idControleur = idControleur;
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

    public void setIdMateriel(int idMateriel) {
        this.idMateriel = idMateriel;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public void setSigneDistinctif(String signeDistinctif) {
        this.signeDistinctif = signeDistinctif;
    }

    public void setMarquage(String marquage) {
        this.marquage = marquage;
    }

    public void setEmplacementMarquage(String emplacementMarquage) {
        this.emplacementMarquage = emplacementMarquage;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public int getIdFabricant() {
        return idFabricant;
    }

    public void setIdFabricant(int idFabricant) {
        this.idFabricant = idFabricant;
    }

    public int getIdType() {
        return idType;
    }

    public void setIdType(int idType) {
        this.idType = idType;
    }

    public int getIdControleur() {
        return idControleur;
    }

    public void setIdControleur(int idControleur) {
        this.idControleur = idControleur;
    }
}

