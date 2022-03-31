package fr.sio.app_epi2.models;

public class Fabricant {
    private int idFabricant;
    private String nom;

    //Constructeur de la classe Fabricant
    public Fabricant(int idFabricant, String nom){
        this.idFabricant = idFabricant;
        this.nom = nom;
    }

    public int getIdFabricant(){return idFabricant;}

    public String getNom(){return nom;}
}
