/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Config.Connexion;
import IServices.IServiceEvaluation;
import Entite.CriteresEvaluation;
import Entite.Evaluation;
import Entite.Experience;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

/**
 *
 * @author amine
 */
public class ServiceEvaluation implements IServices.IServiceEvaluation{
    
       public Connection cnx;

    public ServiceEvaluation() {
        this.cnx = Connexion.getInstance().getCon();
    }   
    
    
       @Override
       public void ajouterevaluation(Evaluation e)    {
     try {
    String query =("INSERT INTO `evaluation`(`id_exp`, `id_critere`, `note`) VALUES (?,?,?)");
         
           PreparedStatement state = Connexion.getInstance().getCon().prepareStatement(query);
    
            ServiceExperience sxp = new ServiceExperience();
            state.setInt(1,(sxp.FindExperience(e.getExperience().getId_exp())).getId_exp());
    
            ServiceCritere scr = new ServiceCritere();
            state.setInt(2,(scr.Findcritere(e.getCritere_evaluation().getId_critere())).getId_critere());
//    
//state.setInt(1,11);
//    state.setInt(2, 11);
               state.setDouble(3,e.getNote());
 
                state.executeUpdate();
//                    
//                ResultSet result = state.getGeneratedKeys();
//            int id = 0;
//            while (result.next()) {
//                id = result.getInt(1);
//        //        e.setId_Eval(result.getInt(1));
//                System.out.println("id ajout offre"+id);
//         
//             if(res > 0){
                System.out.println("Add Done eval num ");
//            }
//             else{System.out.println("add not");}
//            
//            
            
//            
//            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvaluation.class.getName()).log(Level.SEVERE, null, ex);
            
        }

        
   
}
       
       @Override 
       public void editevaluation(Evaluation cs) 
    {
     
        {
        String update = "UPDATE evaluation SET note= ?  WHERE id_exp = ? and id_critere=?";
        PreparedStatement statement2;
            try {
                statement2 = cnx.prepareStatement(update);
                
                
                statement2.setDouble(1,cs.getNote());
        statement2.setInt(2,cs.getExperience().getId_exp());
        statement2.setInt(3, cs.getCritere_evaluation().getId_critere());
        statement2.executeUpdate();
        
        System.out.println("note num"+cs.getCritere_evaluation().getId_critere()+" modifiÃ©e !!!");
            } catch (SQLException ex) {
                Logger.getLogger(ServiceExperience.class.getName()).log(Level.SEVERE, null, ex);
            
            }


}
    }

       @Override
 public void Deleteeval(Evaluation s) 
    {
        try 
        {
        String delete = "DELETE FROM evaluation WHERE WHERE id_exp = ? and id_critere=?";
        PreparedStatement st2 = cnx.prepareStatement(delete);
        st2.setInt(1,s.getExperience().getId_exp());
        st2.setInt(2,s.getCritere_evaluation().getId_critere());
        st2.executeUpdate();
       
        
        }
        catch (SQLException e)
        {

                    System.err.println("SQLException: "+e.getMessage());
                           }
    }

    @Override
    public Evaluation afficherEval(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Evaluation> listEvaluation() {
 List<Evaluation> lc = new ArrayList<>();
        try {
            String select = "SELECT  * FROM evaluation ;";

            Statement statement1 = cnx.createStatement();

            ResultSet result = statement1.executeQuery(select);

            while (result.next()) {
                Evaluation c = new Evaluation();

               ServiceExperience se = new ServiceExperience();
            c.setExperience(se.FindExperience(result.getInt("id_exp")));
            
             ServiceCritere sc = new ServiceCritere();
            c.setCritere_evaluation(sc.Findcritere(result.getInt("id_critere")));
                
                c.setNote(result.getDouble("note"));


                lc.add(c);

            }
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
            System.err.println("SQLSTATE: " + e.getSQLState());
            System.err.println("VnedorError: " + e.getErrorCode());
        }
        return lc;
    }
    
    
       @Override
     public double moyTotEtab(int id_etab)
     {
         Double moy=0.0;
         try {
            PreparedStatement st = cnx.prepareStatement("SELECT  avg( note)  from experience ex , etablissement et, evaluation ev where et.id_etablissement =ex.id_etablissement AND et.id_etablissement = '"+id_etab+"' and ex.id_exp=ev.id_exp");
            ResultSet rs = st.executeQuery();
            while (rs.next()) 
            {
                moy=rs.getDouble(1);
            }
           
             
        } 
         catch (SQLException ex)
         {
            Logger.getLogger(ServiceEtablissement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return moy;
    }
     
     
       @Override
    public List<Evaluation> listEvaluationExp(int id_exp) 
    {
 
        List<Evaluation> lc = new ArrayList<>();
        try {
            String select = "SELECT  * FROM evaluation where id_exp= '"+id_exp+"';";

            Statement statement1 = cnx.createStatement();

            ResultSet result = statement1.executeQuery(select);

            while (result.next())
            {
                Evaluation c = new Evaluation();

               ServiceExperience se = new ServiceExperience();
            c.setExperience(se.FindExperience(result.getInt("id_exp")));
            
             ServiceCritere sc = new ServiceCritere();
            c.setCritere_evaluation(sc.Findcritere(result.getInt("id_critere")));
                
                c.setNote(result.getDouble("note"));


                lc.add(c);

            }
        } 
        catch (SQLException e)
        {
            System.err.println("SQLException: " + e.getMessage());
            System.err.println("SQLSTATE: " + e.getSQLState());
            System.err.println("VnedorError: " + e.getErrorCode());
        }
        return lc;
    }
    
    
    
       @Override
      public double moyTotExp(int id_exp)
      {
            Double moy=0.0;

        try 
        {
            PreparedStatement st = cnx.prepareStatement("SELECT  avg( note)  from experience ex , evaluation ev where ex.id_exp = '"+id_exp+"' and ex.id_exp=ev.id_exp");
            ResultSet rs = st.executeQuery();
            while (rs.next()) 
            {
                moy=rs.getDouble(1);
            }
           
             
        } 
        catch (SQLException ex)
        {
            Logger.getLogger(ServiceEtablissement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return moy;
    }
      
            
            
       @Override
    public ObservableList<PieChart.Data> moyTotCritProp(int id_prop)/*id user*/ {
        ArrayList<PieChart.Data> list = new ArrayList<PieChart.Data>();
        try {
            PreparedStatement st = cnx.prepareStatement(" SELECT nom_critere_evaluation, AVG(note) from evaluation ev, etablissement et, experience ex, critere_evaluation cv where et.id_etablissement = ex.id_etablissement and et.id='"+id_prop+"' and ev.id_exp=ex.id_exp and cv.id_critere=ev.id_critere GROUP BY ev.id_critere");
            ResultSet rs = st.executeQuery();
            while (rs.next()) 
            {
                list.add(new PieChart.Data(rs.getString(1),rs.getDouble(2)));
            }
            ObservableList<PieChart.Data> observableList;
            observableList = FXCollections.observableList(list);
//            System.out.println("ici" + observableList.size());
            for (PieChart.Data data : observableList) {
            System.out.println("test ::"+data);
                
            }
            return observableList;

        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(ServiceEtablissement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
            
            
       @Override
      public List<Double> moyTotCritEtab (int id_etab) throws SQLException{
                    
         List<Double> ld = new ArrayList<>();
        try {
            PreparedStatement st = cnx.prepareStatement(" SELECT nom_critere_evaluation, AVG(note) from evaluation ev, etablissement et, experience ex, critere_evaluation cv where et.id_etablissement = ex.id_etablissement and ev.id_exp=ex.id_exp and cv.id_critere=ev.id_critere and et.id_etablissement='"+id_etab+"' GROUP BY ev.id_critere");
            ResultSet rs = st.executeQuery();
            
             while (rs.next()) {
                 
                  ld.add(rs.getDouble(2));
            
            }
            
            
        }    
         catch (SQLException ex) {
            Logger.getLogger(ServiceEtablissement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ld;
    }

}
