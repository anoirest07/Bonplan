/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Config.Connexion;
import Entite.Admin;
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
public class ServiceAdmin {
    
    
    public Connection cnx;
    Statement st;
    ResultSet rs; 
    
    public ServiceAdmin(){
        this.cnx = Connexion.getInstance().getCon();
    } 
    
     public void ajouterAdmin(Admin admin){
         String req = "insert into user (nom,prenom,roles,username,password,email) values (?,?,?,?,?,?)";
        try {
            PreparedStatement st1 = cnx.prepareStatement(req);
            
            st1.setString(1, admin.getNom());
            st1.setString(2, admin.getPrenom());
            st1.setString(3, admin.getRoles());
            st1.setString(4,admin.getUsername());
             st1.setString(5,admin.getMot_de_passe());
             st1.setString(6,admin.getEmail());
            
            
            
            st1.executeUpdate();
            
        } catch (SQLException ex) {
             System.out.println(ex);
        }
    }
    
    public List<Admin> listAdmin() 
    {
                ArrayList<Admin> lcr=new ArrayList<>();
        try 
        {
        String select = "SELECT * FROM user  WHERE roles = 'Administrateur' ; ";
        Statement statement1 = cnx.createStatement();
        
        ResultSet result = statement1.executeQuery(select);
        
        while (result.next()) 
        {
            Admin cr = new Admin();
            
            cr.setId_user(result.getInt("id"));
            cr.setNom(result.getString("nom"));
            cr.setPrenom(result.getString("prenom"));
            cr.setMot_de_passe(result.getString("password"));
             cr.setRoles(result.getString("roles"));
            cr.setUsername(result.getString("username"));
            cr.setEmail(result.getString("email"));
          
            
            lcr.add(cr);

        } 
    }   
        catch (SQLException e)
                {
                    System.err.println("SQLException: "+e.getMessage());
                    System.err.println("SQLSTATE: "+e.getSQLState());
                    System.err.println("VnedorError: "+e.getErrorCode());
                }
        return lcr; 
    }

    public void deleteAdmin(int id_user){
        
        String req = "delete from user where id = ?";
        try {
            PreparedStatement st2 = cnx.prepareStatement(req);
             st2.setInt(1, id_user);
            st2.executeUpdate();
            
            
        } catch (Exception e) {
            System.out.println(e);
        }
        }
     
     public void modifierAdmin(Admin admin , int id_user){
            
            try {
                String req = "UPDATE user SET nom=? ,prenom=?  where id=?";
                PreparedStatement st3 = cnx.prepareStatement(req);
                
                
              st3.setString(1, admin.getNom());
              st3.setString(2,admin.getPrenom());
            st3.setInt(4, id_user);
            st3.executeUpdate();
           
                
                
            } catch (SQLException ex) {
                 ex.printStackTrace();
            System.out.println("errueur dans la requete update");
                
            }
            
        }
     
     
      public Admin getAdminByLoginPassword(String Login, String Password) {
        Admin u = null;
        try {
            st=cnx.createStatement();
           rs=  st.executeQuery("select * from user WHERE username='"+Login+"' and password='"+Password+"'");
           if (rs.next())
           u=new Admin(rs.getString("nom"), rs.getString("prenom"), rs.getString("username"), rs.getString("password"),rs.getString("roles") , rs.getString("email"));
        } catch (SQLException ex) {
            Logger.getLogger(ServiceAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(u);
        return u;
    }
    
    
    
}
