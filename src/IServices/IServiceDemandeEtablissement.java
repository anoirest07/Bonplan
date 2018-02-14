/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;


import Entite.DemandeEtablissement;
import java.util.List;

/**
 *
 * @author user
 */
public interface IServiceDemandeEtablissement {
    
    public void ajouterDemandeEtablissement(DemandeEtablissement de);
    public void modifierDemandeEtablissement(DemandeEtablissement de);
    public void supprimerDemandeEtablissement(DemandeEtablissement de);
    public DemandeEtablissement afficherDemandeEtab(int id);

    public List<DemandeEtablissement> listDemandeEtablissement();
    
}
