/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Config.Connexion;
import Entite.Proprietaire;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author malek
 */
public class ServiceProprietaire {
    
     public Connection cnx;
   
    
    public ServiceProprietaire(){
        this.cnx = Connexion.getInstance().getCon();
    }
    
    public void ajouterProprietaire(Proprietaire client){
         String req = "insert into user (nom,prenom,date_de_naissance,email,region,telephone,role,login,mdp,nom_etablissement,genre , date_inscription, photo_user) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement st1 = cnx.prepareStatement(req);
            
              st1.setString(1, client.getNom());
            st1.setString(2, client.getPrenom());
            st1.setDate(3, (java.sql.Date) client.getDate_de_naissance());
            st1.setString(4, client.getEmail());
               st1.setString(5, client.getRegion());
           
           
             st1.setInt(6,client.getNum_tel());
              
            st1.setString(7, client.getRoles());
             st1.setString(8,client.getUsername());
             st1.setString(9,client.getMot_de_passe());
              st1.setString(10, client.getSexe());
              st1.setString(11, client.getVille());
              st1.setDate(12, (java.sql.Date) client.getDate_inscription());
            st1.setString(13, client.getPhoto_user());
            st1.executeUpdate();
            
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
            
        }
    
    
    public void deleteProprietaire(int id_user){
        
        String req = "delete from user where id = ?";
        try {
            PreparedStatement st2 = cnx.prepareStatement(req);
             st2.setInt(1, id_user);
            st2.executeUpdate();
            
            
        } catch (Exception e) {
            
        }
        }
    
    public void modifierProprietaire(Proprietaire proprietaire , int id_user){
            
            try {
                String req = "UPDATE user SET nom=? ,prenom=? ,telephone=? where id=?";
                PreparedStatement st3 = cnx.prepareStatement(req);
                
                
              st3.setString(1, proprietaire.getNom());
              st3.setString(2,proprietaire.getPrenom());
            st3.setInt(3, proprietaire.getNum_tel());

            st3.setInt(4, id_user);
            st3.executeUpdate();
           
                
                
            } catch (SQLException ex) {
                 ex.printStackTrace();
            System.out.println("errueur dans la requete update");
                
            }
            
        }
     
    public List<Proprietaire> listProprietaire() 
    {
                List<Proprietaire> co=new ArrayList<>();
        try 
        {
        String select = "SELECT * FROM user ;";
        Statement statement1 = cnx.createStatement();
        
        ResultSet result = statement1.executeQuery(select);
        
        while (result.next()) 
        {
            Proprietaire h1 = new Proprietaire();
            h1.setId_user(result.getInt("id"));
            h1.setNom(result.getString("nom"));
            h1.setPrenom(result.getString("prenom"));
            h1.setMot_de_passe(result.getString("password"));
             h1.setRoles(result.getString("roles"));
            h1.setUsername(result.getString("username"));
            h1.setEmail(result.getString("email"));
            h1.setNum_tel(result.getInt("telephone"));
             h1.setVille(result.getString("ville"));
              h1.setDate_inscription(result.getDate("date_inscription"));
               h1.setPhoto_user(result.getString("photo_user"));
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
    
    
    
    public Proprietaire getProprietaireById(int id_user) {
        List<Proprietaire> list = listProprietaire();
        for (Proprietaire c : list) {
            if (c.getId_user() == id_user) {
                return c;
            }
        }
        return null;
    }
    
    
    
    
    
    
    
}
