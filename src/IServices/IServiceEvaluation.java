/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Entite.Evaluation;
import java.util.List;

/**
 *
 * @author amine
 */
public interface IServiceEvaluation {
    
    public void ajouterevaluation(Evaluation e);
    public void editevaluation(Evaluation cs);
    public void Deleteeval(Evaluation s); 
    public Evaluation afficherEval(int id);
        public List<Evaluation> listEvaluation();





}
