/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Entite.DemandeCritere;
import java.util.List;

/**
 *
 * @author user
 */
public interface IServiceDemandeCritere {
    
    public void ajouterDemandeCritere(DemandeCritere dcr);
    public void modifierDemandeCritere(DemandeCritere dcr);
    public void supprimerDemandeCritere(DemandeCritere dcr);
    public DemandeCritere afficherDemandeCrit(int id);
    public List<DemandeCritere> listDemandeCritere();
    
}
