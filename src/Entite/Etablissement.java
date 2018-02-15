/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entite;

import java.util.List;

/**
 *
 * @author admin
 */
public class Etablissement {
    private int id_etablissement;
    private String nom_etablissement;
    private String adresse_etablissement;
    private int telephone_etablissement;
    private String horaire_travail;
    private String description_etablissement;
    private String photo_etablissement;
    private String photo_patente;
    private int code_postal;
    private String position;
    public enum Budget{Faible,Moyen,Cher};
    private Budget budget ;
    private String site_web;
    private Categorie categorie;
  //  private Utilisateur utilisateur;
//    private List<Offre> offres;
//    private List<Evenement> evenements;
//    private List<Publicite> publicites;

    
    
    public Etablissement() {
    }

    public Etablissement( String nom_etablissement, String adresse_etablissement, int telephone_etablissement, String horaire_travail, String description_etablissement, String photo_etablissement, String photo_patente, int code_postal, String position, Budget budget, String site_web, Categorie categorie) {
        
        this.nom_etablissement = nom_etablissement;
        this.adresse_etablissement = adresse_etablissement;
        this.telephone_etablissement = telephone_etablissement;
        this.horaire_travail = horaire_travail;
        this.description_etablissement = description_etablissement;
        this.photo_etablissement = photo_etablissement;
        this.photo_patente = photo_patente;
        this.code_postal = code_postal;
        this.position = position;
        this.budget = budget;
        this.site_web = site_web;
        this.categorie = categorie;
    //    this.utilisateur = utilisateur;
//        this.offres = offres;
//        this.evenements = evenements;
//        this.publicites = publicites;
    }

    
    
    
    
    public int getId_etablissement() {
        return id_etablissement;
    }

    public String getNom_etablissement() {
        return nom_etablissement;
    }

    public String getAdresse_etablissement() {
        return adresse_etablissement;
    }

    public int getTelephone_etablissement() {
        return telephone_etablissement;
    }

    public String getHoraire_travail() {
        return horaire_travail;
    }

    public String getDescription_etablissement() {
        return description_etablissement;
    }

    public String getPhoto_etablissement() {
        return photo_etablissement;
    }

    public String getPhoto_patente() {
        return photo_patente;
    }

    public int getCode_postal() {
        return code_postal;
    }

    public String getPosition() {
        return position;
    }

    public String getBudget() {
        return budget.name();
    }

    public String getSite_web() {
        return site_web;
    }

    public Categorie getCategorie() {
        return categorie;
    }

  /*  public Utilisateur getUtilisateur() {
        return utilisateur;
    }*/

    public void setId_etablissement(int id_etablissement) {
        this.id_etablissement = id_etablissement;
    }

    public void setNom_etablissement(String nom_etablissement) {
        this.nom_etablissement = nom_etablissement;
    }

    public void setAdresse_etablissement(String adresse_etablissement) {
        this.adresse_etablissement = adresse_etablissement;
    }

    public void setTelephone_etablissement(int telephone_etablissement) {
        this.telephone_etablissement = telephone_etablissement;
    }

    public void setHoraire_travail(String horaire_travail) {
        this.horaire_travail = horaire_travail;
    }

    public void setDescription_etablissement(String description_etablissement) {
        this.description_etablissement = description_etablissement;
    }

    public void setPhoto_etablissement(String photo_etablissement) {
        this.photo_etablissement = photo_etablissement;
    }

    public void setPhoto_patente(String photo_patente) {
        this.photo_patente = photo_patente;
    }

    public void setCode_postal(int code_postal) {
        this.code_postal = code_postal;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setBudget(Budget budget) {
        this.budget = budget;
    }

    public void setSite_web(String site_web) {
        this.site_web = site_web;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

  /*  public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }*/

//    public List<Offre> getOffres() {
//        return offres;
//    }
//
//    public List<Evenement> getEvenements() {
//        return evenements;
//    }
//
//    public List<Publicite> getPublicites() {
//        return publicites;
//    }
//
//    public void setOffres(List<Offre> offres) {
//        this.offres = offres;
//    }
//
//    public void setEvenements(List<Evenement> evenements) {
//        this.evenements = evenements;
//    }
//
//    public void setPublicites(List<Publicite> publicites) {
//        this.publicites = publicites;
//    }

//    @Override
//    public String toString() {
//        return "Etablissement{" + "id_etablissement=" + id_etablissement + ", nom_etablissement=" + nom_etablissement + ", adresse_etablissement=" + adresse_etablissement + ", telephone_etablissement=" + telephone_etablissement + ", horaire_travail=" + horaire_travail + ", description_etablissement=" + description_etablissement + ", photo_etablissement=" + photo_etablissement + ", photo_patente=" + photo_patente + ", code_postal=" + code_postal + ", position=" + position + ", budget=" + budget + ", site_web=" + site_web + ", categorie=" + categorie + "}";
//    }

    @Override
    public String toString() {
        return "Etablissement{" + "id_etablissement=" + id_etablissement + ", nom_etablissement=" + nom_etablissement + ", adresse_etablissement=" + adresse_etablissement + ", telephone_etablissement=" + telephone_etablissement + ", horaire_travail=" + horaire_travail + ", description_etablissement=" + description_etablissement + ", photo_etablissement=" + photo_etablissement + ", photo_patente=" + photo_patente + ", code_postal=" + code_postal + ", position=" + position + ", budget=" + budget + ", site_web=" + site_web + ", categorie=" + categorie + '}';
    }

   
    
   

    

    
    
}
    

