/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Config.Connexion;
import Entite.Utilisateur;
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
 * @author malek
 */
public class ServiceUtilisateur {
    
     public Connection cnx;
     Statement st;
    ResultSet rs; 
    
    public ServiceUtilisateur(){
        this.cnx = Connexion.getInstance().getCon();
    }
    
    public void ajouterUtilisateur(Utilisateur utilisateur){
         String req = "insert into user (nom,prenom ,roles,username,password) values (?,?,?,?,?)";
        try {
            PreparedStatement st1 = Connexion.getInstance().getCon().prepareStatement(req,PreparedStatement.RETURN_GENERATED_KEYS);
            
             st1.setString(1, utilisateur.getNom());
            st1.setString(2, utilisateur.getPrenom());  
            st1.setString(3, utilisateur.getRoles());
            st1.setString(4,utilisateur.getUsername());
             st1.setString(5,utilisateur.getMot_de_passe());
            
            
            
            st1.executeUpdate();
            
             ResultSet result = st.getGeneratedKeys();
            int id = 0;
            while (result.next()) {
                id = result.getInt(1);
                utilisateur.setId_user(result.getInt(1));
                System.out.println("id ajout user "+id);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    public List<Utilisateur> listUtilisateur() 
    {
                ArrayList<Utilisateur> lcr=new ArrayList<>();
        try 
        {
        String select = "SELECT * FROM `user` ; ";
        Statement statement1 = cnx.createStatement();
        
        ResultSet result = statement1.executeQuery(select);
        
        while (result.next()) 
        {
            Utilisateur cr = new Utilisateur();
            
            cr.setId_user(result.getInt("id"));
            cr.setNom(result.getString("nom"));
            cr.setPrenom(result.getString("prenom"));
            cr.setMot_de_passe(result.getString("password"));
             cr.setRoles(result.getString("roles"));
            cr.setUsername(result.getString("username"));
            
          
            
            lcr.add(cr);

        } 
    }   catch (SQLException e)
                {
                    System.err.println("SQLException: "+e.getMessage());
                    System.err.println("SQLSTATE: "+e.getSQLState());
                    System.err.println("VnedorError: "+e.getErrorCode());
                }
        return lcr; 
    }

     public void deleteUtilisateur(int id){
        
        String req = "delete from user where id = ?";
        try {
            PreparedStatement st2 = cnx.prepareStatement(req);
             st2.setInt(1, id);
            st2.executeUpdate();
            
            
        } catch (Exception e) {
            
        }
        }
     
      public Utilisateur getUserByRole(String role) {
        Utilisateur u = null;
        try {
            st=cnx.createStatement();
           rs=  st.executeQuery("select * from user WHERE username='"+role+"'");
           if (rs.next())
           u=new Utilisateur(rs.getString("nom"), rs.getString("prenom"), rs.getString("username"), rs.getString("password"),rs.getString("roles"));
           u.setId_user(rs.getInt("id"));
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUtilisateur.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(u);
        return u;
    }
    
       public Utilisateur getUserById(int id) {
        Utilisateur u = new Utilisateur();
        try {
            st=cnx.createStatement();
           rs=  st.executeQuery("select * from user WHERE id='"+id+"'");
           if (rs.next())
           u=new Utilisateur(rs.getString("nom"), rs.getString("prenom"), rs.getString("username"), rs.getString("password"),rs.getString("roles"));
           u.setId_user(rs.getInt("id"));
        
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUtilisateur.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(u);
        return u;
    }
    
    
    
}
