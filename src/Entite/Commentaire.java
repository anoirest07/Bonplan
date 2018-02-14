/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entite;

/**
 *
 * @author amine
 */
public class Commentaire {
    private int id_commentaire;
    private String Commentaire;
    private Experience experience;
    private Utilisateur utilisateur;

    public Commentaire(int id_commentaire, String Commentaire, Experience experience, Utilisateur utilisateur) {
        this.id_commentaire = id_commentaire;
        this.Commentaire = Commentaire;
        this.experience = experience;
        this.utilisateur = utilisateur;
    }

    public Commentaire() {
    }

    public int getId_commentaire() {
        return id_commentaire;
    }

    public String getCommentaire() {
        return Commentaire;
    }

    public Experience getExperience() {
        return experience;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setId_commentaire(int id_commentaire) {
        this.id_commentaire = id_commentaire;
    }

    public void setCommentaire(String Commentaire) {
        this.Commentaire = Commentaire;
    }

    public void setExperience(Experience experience) {
        this.experience = experience;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    @Override
    public String toString() {
        return "Commentaire{" + "id_commentaire=" + id_commentaire + ", Commentaire=" + Commentaire + ", experience=" + experience + ", utilisateur=" + utilisateur + '}';
    }

    
    
}