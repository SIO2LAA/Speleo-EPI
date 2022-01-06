package fr.sio.app_epi2;

public class Type {

    private int idType;
    private String nom;


    //Constructeur de la classe Type
    public Type(int idType, String nom){
        this.idType = idType;
        this.nom = nom;
    }
    public int getIdType() { return idType; }

    public String getNom() {return nom;}
}
