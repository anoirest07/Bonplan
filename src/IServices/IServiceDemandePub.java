/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Entite.DemandePub;
import java.util.List;

/**
 *
 * @author admin
 */
public interface IServiceDemandePub {
    public List<DemandePub> afficherDemPublicite ();
        public DemandePub afficherDemandePub(int id);

    public void ajouterDemPublicite (DemandePub dp);
    public void modifierDemPublicite(DemandePub dp);
    public void supprimerDemPublicite (DemandePub dp); 
}
