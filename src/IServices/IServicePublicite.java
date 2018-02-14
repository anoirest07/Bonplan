/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Entite.Publicite;
import java.util.List;

/**
 *
 * @author admin
 */
public interface IServicePublicite 
{
    public Publicite rechercherpublicite(int p);
    public List<Publicite> afficherpublicite ();
        public void ajouterpublicite (Publicite p);

    public void modifierpublicite(Publicite p);
    public void supprimerpublicite (Publicite p); 
    
  
}
