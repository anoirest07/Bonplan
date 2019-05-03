/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Config.Connexion;
import Entite.Etablissement;
import Entite.Favoris;
import Entite.Utilisateur;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class ServiceFavoris {
    
    public Connection cnx;

    public ServiceFavoris() {
        this.cnx = Connexion.getInstance().getCon();
    }   
    
    public void ajouterFavoris(Favoris e )    {
     try {
 
            String query = "insert into `bonplan`.`favoris` (`id_etablissement`,`id`) values (?,?)";
            PreparedStatement st = Connexion.getInstance().getCon().prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);
            
            ServiceEtablissement se = new ServiceEtablissement();
            st.setInt(1,se.chercherEtablissement(e.getEtablissement().getId_etablissement()).getId_etablissement());
           
         
           ServiceUtilisateur su = new ServiceUtilisateur();
             
            st.setInt(2,su.getUserById(e.getUtilisateur().getId_user()).getId_user());
            

            st.executeUpdate();
             ResultSet result = st.getGeneratedKeys();
            int id = 0;
            while (result.next()) {
                id = result.getInt(1);
                e.setId_favoris(result.getInt(1));
                System.out.println("id ajout favoris "+id);
            }

          
         } catch (SQLException ex) {
               System.out.println(ex.getMessage());    
         } 
}
    
    public void Deletefavoris(Favoris f) 
    {
        try 
        {
        String delete = "DELETE FROM favoris WHERE id_favoris = ? ";
        PreparedStatement st2 = cnx.prepareStatement(delete);
         st2.setInt(1, f.getId_favoris());
        st2.executeUpdate();
       
        
        }
        catch (SQLException e)
        {

                    System.err.println("SQLException: "+e.getMessage());
                           }
    }
    
    public List<Etablissement> listfavorisEtabl(int id ){
        
        List<Etablissement> co=new ArrayList<>();
        
        try 
        {
        String select = "SELECT e.id_etablissement,e.nom_etablissement,e.adresse_etablissement,e.photo_etablissement FROM etablissement e JOIN favoris f ON (e.id_etablissement = f.id_etablissement) WHERE f.id = ?  ";
       Favoris f = new Favoris();
        PreparedStatement statement1 = cnx.prepareStatement(select);
        
        ServiceUtilisateur su = new ServiceUtilisateur();
        statement1.setInt(1, su.getUserById(f.getUtilisateur().getId_user()).getId_user());
        ResultSet result = statement1.executeQuery();
        
        while (result.next()) 
        {
            Etablissement h1 = new Etablissement();
            System.out.println(result.getString("nom_etablissement"));
            h1.setNom_etablissement(result.getString("nom_etablissement"));
           
            ServiceEtablissement sc = new ServiceEtablissement();
             f.setEtablissement(sc.chercherEtablissement(result.getInt("id_etablissement")));
            h1.setPhoto_etablissement(result.getString("photo_etablissement"));
            h1.setAdresse_etablissement(result.getString("adresse_etablissement"));
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
    
    
    
  /*  public List<Favoris> listFavoris() 
    {
                List<Favoris> co=new ArrayList<>();
        try 
        {
        String select = "SELECT * FROM favoris  ;";
        Statement statement1 = cnx.createStatement();
        
        ResultSet result = statement1.executeQuery(select);
        
        while (result.next()) 
        {
            Favoris h1 = new Favoris();
        
            h1.setId_etablissement(result.getInt("id_etablissement"));
            h1.setId_user(result.getInt("id"));
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
    */
  /*  public Favoris getFavorisById(int id_user) {
        List<Favoris> list = listfavorisEtabl(int id_user);
        for (Favoris f : list) {
            if (f.getId_user() == id_user) {
                return f;
            }
        }
        return null;
    }*/
    
    
}
