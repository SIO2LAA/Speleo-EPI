package fr.sio.app_epi2.models;

public class Tag {
    private String tag;
    private String libelle;

    public Tag(String tag, String libelle) {
        this.tag = tag;
        this.libelle = libelle;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}
