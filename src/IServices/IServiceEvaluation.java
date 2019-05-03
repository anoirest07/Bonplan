/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Entite.Evaluation;
import java.sql.SQLException;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

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
    public List<Evaluation> listEvaluationExp(int id_exp) ;

    public double moyTotEtab(int id_etab);
    public double moyTotExp(int id_exp);
        public ObservableList<PieChart.Data> moyTotCritProp(int id_prop);

    public List<Double> moyTotCritEtab (int id_etab)throws SQLException;





}
