package com.produit.PrduitCatalogue.entities;
import groovyjarjarantlr4.v4.runtime.misc.NotNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
public class Produit implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) //generate auto eb basedonne
    private Long id;
    @NotNull
    @Size(min = 5,max = 70) //had les annotation kisuportiw l'internationalit√©
    private  String designation;
    @DecimalMin("100")
    private  double prix;
    private int quantite;

    public Produit() {
    }

    public Produit(Long id, String designation, double prix, int quantite) {
        this.id = id;
        this.designation = designation;
        this.prix = prix;
        this.quantite = quantite;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
}

