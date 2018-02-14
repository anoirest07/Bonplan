/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Config.Connexion;
import Entite.CriteresEvaluation;
import Entite.Commentaire;
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
public class ServiceCommentaire {
   public Connection cnx;

    public ServiceCommentaire() {
        this.cnx = Connexion.getInstance().getCon();
    }   
    public void ajoutercommentaire(Commentaire e)    {
     try {
 
            Statement state = cnx.createStatement();
            state.executeUpdate("INSERT INTO`commentaire`(`commentaire`,`id_exp`,`id_ucomm`) VALUES ('"+e.getCommentaire()+"',"+e.getExperience().getId_exp()+","+e.getUtilisateur().getId_user()+");");

          
         } catch (SQLException ex) {
               System.out.println(ex.getMessage());    
         } 
}

 public void editcommentaire(Commentaire cs) 
    {
     
        {
        String update = "UPDATE commentaire SET commentaire= ?  WHERE id_commentaire = ? ";
        PreparedStatement statement2;
            try {
                statement2 = cnx.prepareStatement(update);
                
                
                statement2.setString(1,cs.getCommentaire());
        statement2.setInt(2,cs.getId_commentaire());
        statement2.executeUpdate();
        System.out.println("le commentaire"+cs.getCommentaire()+" modifiÃ©e !!!");
            } catch (SQLException ex) {
                Logger.getLogger(ServiceExperience.class.getName()).log(Level.SEVERE, null, ex);
            
            }


}
    }
 public void Deletecommentaire(Commentaire ce) 
    {
        try 
        {
        String delete = "DELETE FROM commentaire WHERE id_commentaire = ? ";
        PreparedStatement st2 = cnx.prepareStatement(delete);
        st2.setInt(1,ce.getId_commentaire());
        st2.executeUpdate();
       
        
        }
        catch (SQLException e)
        {

                    System.err.println("SQLException: "+e.getMessage());
                           }
    }
   
public Commentaire Findcommentaire(int id_commentaire) 
    {
        Commentaire ce= new  Commentaire();
        
        try
        {
        String select = "SELECT * FROM commentaire WHERE  id_commentaire = '"+id_commentaire+"' ";
        Statement statement1 = cnx.createStatement();
        ResultSet result = statement1.executeQuery(select);
       
        while (result.next()) 
        {
            ce.setId_commentaire(result.getInt("id_commentaire"));
            ce.setCommentaire(result.getString("commentaire"));
            ce.getExperience().setId_exp(result.getInt("id_exp"));
            ce.getUtilisateur().setId_user(result.getInt("id_ucomm"));
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
 public List<Commentaire> listcommentaire() 
    {
                List<Commentaire> co=new ArrayList<>();
        try 
        {
        String select = "SELECT * FROM commentaire  ;";
        Statement statement1 = cnx.createStatement();
        
        ResultSet result = statement1.executeQuery(select);
        
        while (result.next()) 
        {
            Commentaire h1 = new Commentaire();
            h1.setId_commentaire(result.getInt("id_commentaire"));
            h1.setCommentaire(result.getString("commentaire"));
            h1.getExperience().setId_exp(result.getInt("id_exp"));
            h1.getUtilisateur().setId_user(result.getInt("id_ucomm"));
            co.add(h1);

        } 
    }   
        catch (SQLException e)
                {
                    System.err.println("SQLException: "+e.getMessage());
                    System.err.println("SQLSTATE: "+e.getSQLState());
                    System.err.println("VnedorError: "+e.getErrorCode());
                }
        return co;

    }

   
}
