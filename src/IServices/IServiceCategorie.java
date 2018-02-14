/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Entite.Categorie;
import java.util.List;

/**
 *
 * @author user
 */
public interface IServiceCategorie {
    
    public void ajouterCategorie(Categorie c);
    public void modifierCategorie(Categorie c);
    public void supprimerCategorie(Categorie c);
    public Categorie afficherCategorie(int id);
    public List<Categorie> listCategorie();
    
}
