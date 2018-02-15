/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entite;

/**
 *
 * @author user
 */
public class DemandeEtablissement {
    private int id_demande_etab;
    private String nom_etab_demande;
    private String adresse_etab_demande;
    private int telephone_etab_demande;
    private String horaire_travail_etab_demande;
    private String description_etab_demande;
    private String photo_etab_demande;
    private String photo_patente_etab_demande;
    private int code_postal_etab_demande;
    private String position_etab_demande;
    public enum Budget{Faible,Moyen,Cher};
    private Budget budget_demande ;
    private String site_web_etab_demande;
    private Categorie categorie;
    private Utilisateur utilisateur;

    public DemandeEtablissement( String nom_etab_demande, String adresse_etab_demande, int telephone_etab_demande, String horaire_travail_etab_demande, String description_etab_demande, String photo_etab_demande, String photo_patente_etab_demande, int code_postal_etab_demande, String position_etab_demande, Budget budget_demande, String site_web_etab_demande, Categorie categorie, Utilisateur utilisateur) {
        
        this.nom_etab_demande = nom_etab_demande;
        this.adresse_etab_demande = adresse_etab_demande;
        this.telephone_etab_demande = telephone_etab_demande;
        this.horaire_travail_etab_demande = horaire_travail_etab_demande;
        this.description_etab_demande = description_etab_demande;
        this.photo_etab_demande = photo_etab_demande;
        this.photo_patente_etab_demande = photo_patente_etab_demande;
        this.code_postal_etab_demande = code_postal_etab_demande;
        this.position_etab_demande = position_etab_demande;
        this.budget_demande = budget_demande;
        this.site_web_etab_demande = site_web_etab_demande;
        this.categorie = categorie;
        this.utilisateur = utilisateur;
    }

    
    
    
   public DemandeEtablissement(){
       categorie = new Categorie();
   }

    public int getId_demande_etab() {
        return id_demande_etab;
    }

    public void setId_demande_etab(int id_demande_etab) {
        this.id_demande_etab = id_demande_etab;
    }

    public String getNom_etab_demande() {
        return nom_etab_demande;
    }

    public String getAdresse_etab_demande() {
        return adresse_etab_demande;
    }

    public int getTelephone_etab_demande() {
        return telephone_etab_demande;
    }

    public String getHoraire_travail_etab_demande() {
        return horaire_travail_etab_demande;
    }

    public String getDescription_etab_demande() {
        return description_etab_demande;
    }

    public String getPhoto_etab_demande() {
        return photo_etab_demande;
    }

    public String getPhoto_patente_etab_demande() {
        return photo_patente_etab_demande;
    }

    public int getCode_postal_etab_demande() {
        return code_postal_etab_demande;
    }

    public String getPosition_etab_demande() {
        return position_etab_demande;
    }

    public String getBudget_demande() {
        return budget_demande.name();
    }

    public String getSite_web_etab_demande() {
        return site_web_etab_demande;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setNom_etab_demande(String nom_etab_demande) {
        this.nom_etab_demande = nom_etab_demande;
    }

    public void setAdresse_etab_demande(String adresse_etab_demande) {
        this.adresse_etab_demande = adresse_etab_demande;
    }

    public void setTelephone_etab_demande(int telephone_etab_demande) {
        this.telephone_etab_demande = telephone_etab_demande;
    }

    public void setHoraire_travail_etab_demande(String horaire_travail_etab_demande) {
        this.horaire_travail_etab_demande = horaire_travail_etab_demande;
    }

    public void setDescription_etab_demande(String description_etab_demande) {
        this.description_etab_demande = description_etab_demande;
    }

    public void setPhoto_etab_demande(String photo_etab_demande) {
        this.photo_etab_demande = photo_etab_demande;
    }

    public void setPhoto_patente_etab_demande(String photo_patente_etab_demande) {
        this.photo_patente_etab_demande = photo_patente_etab_demande;
    }

    public void setCode_postal_etab_demande(int code_postal_etab_demande) {
        this.code_postal_etab_demande = code_postal_etab_demande;
    }

    public void setPosition_etab_demande(String position_etab_demande) {
        this.position_etab_demande = position_etab_demande;
    }

    public void setBudget_demande(Budget budget_demande) {
        this.budget_demande = budget_demande;
    }

    public void setSite_web_etab_demande(String site_web_etab_demande) {
        this.site_web_etab_demande = site_web_etab_demande;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    @Override
    public String toString() {
        return "DemandeEtablissement{" + "id_demande_etab=" + id_demande_etab + ", nom_etab_demande=" + nom_etab_demande + ", adresse_etab_demande=" + adresse_etab_demande + ", telephone_etab_demande=" + telephone_etab_demande + ", horaire_travail_etab_demande=" + horaire_travail_etab_demande + ", description_etab_demande=" + description_etab_demande + ", photo_etab_demande=" + photo_etab_demande + ", photo_patente_etab_demande=" + photo_patente_etab_demande + ", code_postal_etab_demande=" + code_postal_etab_demande + ", position_etab_demande=" + position_etab_demande + ", budget_demande=" + budget_demande + ", site_web_etab_demande=" + site_web_etab_demande + ", categorie=" + categorie + ", utilisateur=" + utilisateur + '}';
    }

    
    
    
}
