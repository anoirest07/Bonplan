/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Entite.Favoris;
import java.util.List;

/**
 *
 * @author admin
 */
public interface IServiceFavoris {
     public  List<Favoris> afficherfavoris ();
    public void ajouterFavoris (Favoris f);
    public Favoris rechercherFavoris (int id ,int id_etablissement);
    public void modifierFavoris (Favoris f);
    public void supprimerFavoris (Favoris f);
    
}
