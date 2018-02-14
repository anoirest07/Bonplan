/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Config.Connexion;
import Entite.Experience;
import IServices.IServiceExperience;
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
public class ServiceExperience implements IServiceExperience{
    

    public Connection cnx;

    public ServiceExperience() {
        this.cnx = Connexion.getInstance().getCon();
    }
    @Override
 public void ajouterexperience(Experience e)    {
     try {

            Statement state = cnx.createStatement();
            state.executeUpdate("INSERT INTO`experience`(description_experience,`preuve`,`id`,`id_etablissement`) VALUES ('"+e.getDescription_experience()+"','"+e.getPreuve()+"',"+e.getUtilisateur().getId_user()+","+e.getEtablissement().getId_etablissement()+");");

            // 
         } catch (SQLException ex) {
               System.out.println(ex.getMessage());    
         }
        
        
   
}


public void modifierexperience(Experience e) 
    {
     
        {
        String update = "UPDATE experience SET description_experience= ? , preuve = ? WHERE id_etablissement = ? and id =?";
        PreparedStatement statement2;
            try {
                statement2 = cnx.prepareStatement(update);
                
                
                statement2.setString(1, e.getDescription_experience());
        statement2.setString(2,e.getPreuve());
        statement2.setInt(3,e.getEtablissement().getId_etablissement());
        statement2.setInt(4,e.getUtilisateur().getId_user());
        statement2.executeUpdate();
        System.out.println("exp num"+e.getId_exp()+" modifiÃ©e !!!");
            } catch (SQLException ex) {
                Logger.getLogger(ServiceExperience.class.getName()).log(Level.SEVERE, null, ex);
            
            }
        
      


}
    }


 public void supprimerexperience(Experience s) 
    {
        try 
        {
        String delete = "DELETE FROM experience WHERE id_etablissement = ? and id =?";
        PreparedStatement st2 = cnx.prepareStatement(delete);
        st2.setInt(1,s.getEtablissement().getId_etablissement());
        st2.setInt(2,s.getUtilisateur().getId_user());
        st2.executeUpdate();
       
        
        }
        catch (SQLException e)
        {

                    System.err.println("SQLException: "+e.getMessage());
                           }
    }
 public Experience rechercherexperience(int id ,int id_etablissement) 
    {
        Experience h = new Experience ();
        try
        {
        String select = "SELECT * FROM experience WHERE id = '"+id+"' and id_etablissement = '"+id_etablissement+"' ";
        Statement statement1 = cnx.createStatement();
        ResultSet result = statement1.executeQuery(select);
       
        while (result.next()) 
        {
            h.setId_exp(result.getInt("id_exp"));

            h.setDescription_experience(result.getString("description_experience"));
                        h.setPreuve(result.getString("preuve"));

            h.getUtilisateur().setId_user(result.getInt("id"));
            h.getEtablissement().setId_etablissement(result.getInt("id_etablissement"));
        
        }
        }
        catch (SQLException e)
                {
                    System.err.println("SQLException: "+e.getMessage());
                    System.err.println("SQLSTATE: "+e.getSQLState());
                    System.err.println("VnedorError: "+e.getErrorCode());
                }
        return h;
    }

    @Override
    public List<Experience> afficherexperience() {
        List<Experience> le=new ArrayList<>();
        try 
        {
        String select = "SELECT  * FROM Experience e INNER JOIN etablissement etab on e.id_etablissement = etab.id_etablissement ;"; 
       // String select2 ="SELECT * From Experience e INNER JOIN Commantaire c on e.id_exp = c.id_exp ;";
                 
        Statement statement1 = cnx.createStatement();
        
        ResultSet result = statement1.executeQuery(select);
        
        while (result.next()) 
        {
            Experience e = new Experience();
            
            e.setPreuve(result.getString("preuve"));
            e.setDescription_experience(result.getString("description_experience"));
            e.getEtablissement().setNom_etablissement(result.getString("nom_etablissement"));
          // e.getEtablissement().setId_etablissement(result.getInt("id_etablissement"));
//           e.getUser().setId_user(result.getInt("id"));
            
            
            le.add(e);

        } 
    }   
        catch (SQLException ex)
                {
                    System.err.println("SQLException: "+ex.getMessage());
                    System.err.println("SQLSTATE: "+ex.getSQLState());
                    System.err.println("VnedorError: "+ex.getErrorCode());
                }
        return le; //To change body of generated methods, choose Tools | Templates.
    }


}