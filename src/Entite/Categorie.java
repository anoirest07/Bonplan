/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entite;

import java.util.List;

/**
 *
 * @author user
 */
public class Categorie {
    private int id_categorie;
    private String nom_categorie;
    private List<Etablissement> etablissements;

    public Categorie(int id_categorie, String nom_categorie, List<Etablissement> etablissements) {
        this.id_categorie = id_categorie;
        this.nom_categorie = nom_categorie;
        this.etablissements = etablissements;
    }
    
    
   
    public Categorie() {
    }
    
    

    public int getId_categorie() {
        return id_categorie;
    }

    public void setId_categorie(int id_categorie) {
        this.id_categorie = id_categorie;
    }

    public String getNom_categorie() {
        return nom_categorie;
    }

    public void setNom_categorie(String nom_categorie) {
        this.nom_categorie = nom_categorie;
    }

    public List<Etablissement> getEtablissements() {
        return etablissements;
    }

    public void setEtablissements(List<Etablissement> etablissements) {
        this.etablissements = etablissements;
    }

    @Override
    public String toString() {
        return "Categorie{" + "id_categorie=" + id_categorie + ", nom_categorie=" + nom_categorie + ", etablissements=" + etablissements + '}';
    }

   
    
    
}
