/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Config.Connexion;
import Entite.Client;
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
public class ServiceClient {
 
     public Connection cnx;
      Statement st;
    ResultSet rs; 
   
    
    public ServiceClient(){
        this.cnx = Connexion.getInstance().getCon();
    }
    
    public void ajouterClient(Client client){
         String req = "insert into user (nom,prenom,date_de_naissance,email,region,telephone,roles,username,password,genre,ville,date_inscription , photo_user) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement st1 = Connexion.getInstance().getCon().prepareStatement(req,PreparedStatement.RETURN_GENERATED_KEYS);
            
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
            
            ResultSet result = st1.getGeneratedKeys();
            int id = 0;
            while (result.next()) {
                id = result.getInt(1);
                client.setId_user(result.getInt(1));
                System.out.println("id ajout"+id);
            
            } 
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public void addClient(String nom, String prenom ,String email,String region, String role , String username, String password , String ville,String photo_user){
        
       try {
             st= (Statement) cnx.createStatement();
         } catch (SQLException ex) {
             Logger.getLogger(ServiceClient.class.getName()).log(Level.SEVERE, null, ex);
         }
       try{
            String req = "insert into user (nom,prenom,email,region,roles,username,password,ville,photo_user) values ('"+nom+"','" +prenom+"','" +email+"','" +region+"','" +role+"','" +username+"','" +password+"','" +ville+"','" +photo_user+"')";
             st.executeUpdate(req);
         } catch (SQLException ex) {
             Logger.getLogger(ServiceClient.class.getName()).log(Level.SEVERE, null, ex);
         }
     
    }
    
    public void deleteClient(int id_user){
        
        String req = "delete from user where id = ?";
        try {
            PreparedStatement st2 = Connexion.getInstance().getCon().prepareStatement(req);
             st2.setInt(1, id_user);
            st2.executeUpdate();
            
            
        } catch (Exception e) {
            
        }
        }
    
    public void modifierClient(Client client , int id_user){
            
            try {
                String req = "UPDATE user SET nom=? ,prenom=? ,telephone=? where id=?";
                PreparedStatement st3 = Connexion.getInstance().getCon().prepareStatement(req);
                
                
              st3.setString(1, client.getNom());
              st3.setString(2, client.getPrenom());
            st3.setInt(3, client.getNum_tel());

            st3.setInt(4, id_user);
            st3.executeUpdate();
           
                
                
            } catch (SQLException ex) {
                 ex.printStackTrace();
            System.out.println("errueur dans la requete update");
                
            }
            
        }
    
    
    public Client getClientById(int id_user) {
        List<Client> list = listClient();
        for (Client c : list) {
            if (c.getId_user() == id_user) {
                return c;
            }
        }
        return null;
    }
    
    
    public List<Client> listClient() 
    {
                List<Client> co=new ArrayList<>();
        try 
        {
        String select = "SELECT * FROM user ;";
        Statement statement1 = cnx.createStatement();
        
        ResultSet result = statement1.executeQuery(select);
        
        while (result.next()) 
        {
            Client h1 = new Client();
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
    
    
     public Client getClientByEmail(String Email) {
    Client u = null;
        try {
            st=cnx.createStatement();
           rs=  st.executeQuery("select * from user WHERE  email='"+Email+"'");
           if (rs.next())
           u=new Client(rs.getString("nom"), rs.getString("prenom"), rs.getString("username"), rs.getString("password"),rs.getString("roles") , rs.getString("region"), rs.getString("ville"),rs.getString("email"),rs.getString("photo_user"));
        } catch (SQLException ex) {
            Logger.getLogger(ServiceClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(u);
        return u;}
    
     public Client getClientByLoginPassword(String Login, String Password) {
        Client u = null;
        try {
            st=cnx.createStatement();
           rs=  st.executeQuery("select * from user WHERE username='"+Login+"' and password='"+Password+"'");
           if (rs.next())
           u = new Client(rs.getString("nom"), rs.getString("prenom"), rs.getString("username"), rs.getString("password"),rs.getString("roles") , rs.getString("region"), rs.getString("ville"),rs.getString("email"),rs.getString("photo_user"));
           u.setId_user(rs.getInt(1));
        } catch (SQLException ex) {
            Logger.getLogger(ServiceClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(u);
        return u;
    }
    
    public Client getClientByPseudo(String Pseudo) {
        Client u = null;
        try {
            st=cnx.createStatement();
           rs=  st.executeQuery("select * from user WHERE username='"+Pseudo+"' ");
           if (rs.next())
           u = new Client(rs.getString("nom"), rs.getString("prenom"), rs.getString("username"), rs.getString("password"),rs.getString("roles") , rs.getString("region"), rs.getString("ville"),rs.getString("email"),rs.getString("photo_user"));
        } catch (SQLException ex) {
            Logger.getLogger(ServiceClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(u);
        return u;
    }
    
    
}
