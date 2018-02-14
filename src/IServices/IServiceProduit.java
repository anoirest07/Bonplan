/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package IServices;

import Entite.Produit;
import java.util.List;

/**
 *
 * @author malek
 */
public interface IServiceProduit {
     public void ajouterProduit(Produit prod);
     public void deleteProduit(int id_produit);
     public List<Produit> afficherProduit();
           public Produit chercherProduit(String nom_produit);
             public void modifierProduit(Produit p);

}
