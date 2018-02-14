/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entite;

import java.sql.Date;

/**
 *
 * @author admin
 */
public class Evenement 
{
    private int id_evenement;
    private String nom_evenement;
    private String description_evenement;
    private Date date_evenement;
    private String photo_evenement;
    private int nbr_participant;
    private int nbr_interesse;
    private Etablissement etablissement;

    public Evenement() 
    {
    }

    public Evenement(int id_evenement, String nom_evenement, String description_evenement, Date date_evenement, String photo_evenement, int nbr_participant, int nbr_interesse, Etablissement etablissement) {
        this.id_evenement = id_evenement;
        this.nom_evenement = nom_evenement;
        this.description_evenement = description_evenement;
        this.date_evenement = date_evenement;
        this.photo_evenement = photo_evenement;
        this.nbr_participant = nbr_participant;
        this.nbr_interesse = nbr_interesse;
        this.etablissement = etablissement;
    }

    public int getId_evenement() {
        return id_evenement;
    }

    public String getNom_evenement() {
        return nom_evenement;
    }

    public String getDescription_evenement() {
        return description_evenement;
    }

    public Date getDate_evenement() {
        return date_evenement;
    }

    public String getPhoto_evenement() {
        return photo_evenement;
    }

    public int getNbr_participant() {
        return nbr_participant;
    }

    public int getNbr_interesse() {
        return nbr_interesse;
    }

    public Etablissement getEtablissement() {
        return etablissement;
    }

    public void setId_evenement(int id_evenement) {
        this.id_evenement = id_evenement;
    }

    public void setNom_evenement(String nom_evenement) {
        this.nom_evenement = nom_evenement;
    }

    public void setDescription_evenement(String description_evenement) {
        this.description_evenement = description_evenement;
    }

    public void setDate_evenement(Date date_evenement) {
        this.date_evenement = date_evenement;
    }

    public void setPhoto_evenement(String photo_evenement) {
        this.photo_evenement = photo_evenement;
    }

    public void setNbr_participant(int nbr_participant) {
        this.nbr_participant = nbr_participant;
    }

    public void setNbr_interesse(int nbr_interesse) {
        this.nbr_interesse = nbr_interesse;
    }

    public void setEtablissement(Etablissement etablissement) {
        this.etablissement = etablissement;
    }

    @Override
    public String toString() {
        return "Evenement{" + "id_evenement=" + id_evenement + ", nom_evenement=" + nom_evenement + ", description_evenement=" + description_evenement + ", date_evenement=" + date_evenement + ", photo_evenement=" + photo_evenement + ", nbr_participant=" + nbr_participant + ", nbr_interesse=" + nbr_interesse + ", etablissement=" + etablissement + '}';
    }

    

    
    
    
}
