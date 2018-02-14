/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Config.Connexion;
import IServices.IServiceCritere;
import Entite.CriteresEvaluation;
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

/**
 *
 * @author amine
 */
public class ServiceCritere implements IServices.IServiceCritere{
 public Connection cnx;

    public ServiceCritere() {
        this.cnx = Connexion.getInstance().getCon();
    }   

public void ajoutercritere(CriteresEvaluation e)    {
     try {
 
            Statement state = cnx.createStatement();
            state.executeUpdate("INSERT INTO`critere_evaluation`(`nom_critere_evaluation`,`id_categorie`) VALUES ('"+e.getNom_critere_evaluation()+"',"+e.getCategorie().getId_categorie()+");");

            // 
         } catch (SQLException ex) {
               System.out.println(ex.getMessage());    
         } 
}


public void editcritere(CriteresEvaluation cs) 
    {
     
        {
        String update = "UPDATE critere_evaluation SET nom_critere_evaluation= ?  WHERE id_critere = ?";
        PreparedStatement statement2;
            try {
                statement2 = cnx.prepareStatement(update);
                
                
                statement2.setString(1,cs.getNom_critere_evaluation());
        statement2.setInt(2,cs.getId_critere());
        statement2.executeUpdate();
        System.out.println("critere num"+cs.getId_critere()+" modifiÃ©e !!!");
            } catch (SQLException ex) {
                Logger.getLogger(ServiceExperience.class.getName()).log(Level.SEVERE, null, ex);
            
            }


}
    }


 public void Deletecritere(CriteresEvaluation ce) 
    {
        try 
        {
        String delete = "DELETE FROM critere_evaluation WHERE id_critere = ? ";
        PreparedStatement st2 = cnx.prepareStatement(delete);
        st2.setInt(1,ce.getId_critere());
        st2.executeUpdate();
       
        
        }
        catch (SQLException e)
        {

                    System.err.println("SQLException: "+e.getMessage());
                           }
    }
 
 
 
public CriteresEvaluation Findcritere(int id_crit) 
    {
        CriteresEvaluation ce= new  CriteresEvaluation();
        
        try
        {
        String select = "SELECT * FROM critere_evaluation WHERE  id_critere = '"+id_crit+"' ";
        Statement statement1 = cnx.createStatement();
        ResultSet result = statement1.executeQuery(select);
       
        while (result.next()) 
        {
            ce.setId_critere(result.getInt("id_critere"));
            ce.setNom_critere_evaluation(result.getString("nom_critere_evaluation"));
            ce.getCategorie().setId_categorie(result.getInt("id_categorie"));
        
        }
        }
        catch (SQLException e)
                {
                    System.err.println("SQLException: "+e.getMessage());
                    System.err.println("SQLSTATE: "+e.getSQLState());
                    System.err.println("VnedorError: "+e.getErrorCode());
                }
        return ce;
    }


 @Override 
 public List<CriteresEvaluation> listcritereevaluation() 
    {
                List<CriteresEvaluation> ht=new ArrayList<>();
        try 
        {
        String select = "SELECT * FROM critere_evaluation  ;";
        Statement statement1 = cnx.createStatement();
        
        ResultSet result = statement1.executeQuery(select);
        
        while (result.next()) 
        {
            CriteresEvaluation h1 = new CriteresEvaluation();
            h1.setId_critere(result.getInt("id_critere"));
            h1.setNom_critere_evaluation(result.getString("nom"));
            h1.getCategorie().setId_categorie(result.getInt("id_categorie"));
            ht.add(h1);

        } 
    }   
        catch (SQLException e)
                {
                    System.err.println("SQLException: "+e.getMessage());
                    System.err.println("SQLSTATE: "+e.getSQLState());
                    System.err.println("VnedorError: "+e.getErrorCode());
                }
        return ht;
}

 
}

