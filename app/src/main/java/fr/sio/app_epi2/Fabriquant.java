package fr.sio.app_epi2;

public class Fabriquant {
    private int idFabriquant;
    private String nom;

    //Constructeur de la classe Fabriquant
    public Fabriquant(int idFabriquant, String nom){
        this.idFabriquant = idFabriquant;
        this.nom = nom;
    }

    public int getIdFabriquant(){return idFabriquant;}

    public String getNom(){return nom;}
}
