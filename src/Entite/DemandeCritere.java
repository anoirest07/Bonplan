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
public class DemandeCritere {
  private int id_demande_crit;  
  private String nom_demande_crit;
  private  DemandeCategorie demande_cat;

    public DemandeCritere(int id_demande_crit, String nom_demande_crit, DemandeCategorie demande_cat) {
        this.id_demande_crit = id_demande_crit;
        this.nom_demande_crit = nom_demande_crit;
        this.demande_cat = demande_cat;
    }

  

    
  
  public DemandeCritere()
  {
  }

    public int getId_demande_crit() {
        return id_demande_crit;
    }

    public void setId_demande_crit(int id_demande_crit) {
        this.id_demande_crit = id_demande_crit;
    }

    public String getNom_demande_crit() {
        return nom_demande_crit;
    }

    public DemandeCategorie getDemande_cat() {
        return demande_cat;
    }

    public void setNom_demande_crit(String nom_demande_crit) {
        this.nom_demande_crit = nom_demande_crit;
    }

    public void setDemande_cat(DemandeCategorie demande_cat) {
        this.demande_cat = demande_cat;
    }

    @Override
    public String toString() {
        return "DemandeCritere{" + "id_demande_crit=" + id_demande_crit + ", nom_demande_crit=" + nom_demande_crit + ", demande_cat=" + demande_cat + '}';
    }

    
    

}

