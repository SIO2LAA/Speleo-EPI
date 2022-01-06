package fr.sio.app_epi2;

public class Controleur {

    private int idControleur;
    private String nom;
    private String prenom;

    //Constructeur de la classe Controleur
    public Controleur(int idControleur, String nom, String prenom){
        this.idControleur = idControleur;
        this.nom = nom;
        this.prenom = prenom;
    }

    public int getIdControleur() {
        return idControleur;
    }

    public void setIdControleur(int idControleur) {
        this.idControleur = idControleur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
}
