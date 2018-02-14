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
public class DemandeCategorie {
    private int id_demande_cat;
    private String nom_categorie_demande;
    private DemandeEtablissement demande_etab;
    private List<DemandeCritere> demandes_criteres;

    public DemandeCategorie(int id_demande_cat, String nom_categorie_demande, DemandeEtablissement demande_etab, List<DemandeCritere> demandes_criteres) {
        this.id_demande_cat = id_demande_cat;
        this.nom_categorie_demande = nom_categorie_demande;
        this.demande_etab = demande_etab;
        this.demandes_criteres = demandes_criteres;
    }
    

    


    public DemandeCategorie(){
    
}

    public int getId_demande_cat() {
        return id_demande_cat;
    }

    public void setId_demande_cat(int id_demande_cat) {
        this.id_demande_cat = id_demande_cat;
    }

    public String getNom_categorie_demande() {
        return nom_categorie_demande;
    }

    public DemandeEtablissement getDemande_etab() {
        return demande_etab;
    }

    public void setNom_categorie_demande(String nom_categorie_demande) {
        this.nom_categorie_demande = nom_categorie_demande;
    }

    public void setDemande_etab(DemandeEtablissement demande_etab) {
        this.demande_etab = demande_etab;
    }

    public List<DemandeCritere> getDemandes_criteres() {
        return demandes_criteres;
    }

    public void setDemandes_criteres(List<DemandeCritere> demandes_criteres) {
        this.demandes_criteres = demandes_criteres;
    }

    @Override
    public String toString() {
        return "DemandeCategorie{" + "id_demande_cat=" + id_demande_cat + ", nom_categorie_demande=" + nom_categorie_demande + ", demande_etab=" + demande_etab + ", demandes_criteres=" + demandes_criteres + '}';
    }

    
    

    

   
    
}