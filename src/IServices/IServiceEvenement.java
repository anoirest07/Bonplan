/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Entite.Evenement;
import java.util.List;


/**
 *
 * @author admin
 */
public interface IServiceEvenement 
{
    public  List<Evenement> afficherevenement ();
    public Evenement rechercherevenment(int id);
    public void ajouterevenement (Evenement e);
    public void modifierevenement(Evenement e);
    public void supprimerevenement (Evenement e);
    
}
