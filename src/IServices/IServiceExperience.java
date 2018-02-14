 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Entite.Experience;
import java.util.List;

/**
 *
 * @author admin
 */
public interface IServiceExperience {
    
    public  List<Experience> afficherexperience ();
    public void ajouterexperience (Experience e);
    public Experience rechercherexperience (int id ,int id_etablissement);
    public void modifierexperience (Experience e);
    public void supprimerexperience (Experience e);



  
}