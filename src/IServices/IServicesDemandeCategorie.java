/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Entite.DemandeCategorie;
import java.util.List;

/**
 *
 * @author user
 */
public interface IServicesDemandeCategorie {
    public void ajouterDemandeCategorie(DemandeCategorie dc);
    public void modifierDemandeCategorie(DemandeCategorie dc);
    public void supprimerDemandeCategorie(DemandeCategorie dc);
    public List<DemandeCategorie> listDemandeCategorie();
    public DemandeCategorie rechercherDemandeCat (int id);
    
}
