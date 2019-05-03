/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Entite.OffreProduit;

/**
 *
 * @author Yassine
 */
public interface IServiceOffreProduit {
    public void ajouteroffreProduit (OffreProduit op);
    public void supprimeroffreProduit (OffreProduit op);
}
