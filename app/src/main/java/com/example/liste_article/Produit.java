package com.example.liste_article;

public class Produit {
    private String libelle;
    private String pu;

    public Produit(String libelle, String pu) {
        this.libelle = libelle;
        this.pu = pu;
    }

    public String getLibelle() {
        return libelle;
    }

    public String getPu() {
        return pu;
    }
}
