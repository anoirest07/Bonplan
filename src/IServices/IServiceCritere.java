/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Entite.CriteresEvaluation;
import java.util.List;

/**
 *
 * @author amine
 */
public interface IServiceCritere {
    public void ajoutercritere(CriteresEvaluation e);
    public void editcritere(CriteresEvaluation cs);
     public void Deletecritere(CriteresEvaluation ce);
    public CriteresEvaluation Findcritere(int id_crit); 
     public List<CriteresEvaluation> listcritereevaluation();




    
}
