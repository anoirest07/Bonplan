/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Entite.Offre;
import java.util.List;

/**
 *
 * @author admin
 */
public interface IServiceOffre 
{
    public  List<Offre> afficheroffre ();
    public Offre rechercheroffre(int id_et);
    public void ajouteroffre (Offre o);
    public void modifieroffre(Offre o);
    public void supprimeroffre (int id);    
}
