/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Config.Connexion;
import Entite.Produit;
import Entite.Publicite;
import Entite.Utilisateur;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import IServices.IServicePublicite;
import java.sql.Connection;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

/**
 *
 * @author admin
 */
public class ServicePublicite implements IServicePublicite{
Connection con ;

    public ServicePublicite() {
        con = Connexion.getInstance().getCon();
    }
    
    public Publicite rechercherpublicite(int p){
        Publicite e = new Publicite();
        try
        {
        String select = "SELECT * FROM publicite WHERE id_publicite = "+p+" ";
        Statement statement1 = con.createStatement();
        ResultSet result = statement1.executeQuery(select);
       
        while (result.next()) 
        {   e.setId_publicite(result.getInt("id_publicite"));
            
            e.setDescription_publicite(result.getString("description_publicite"));
            e.setTitre(result.getString("titre"));  
            e.setPhoto_publicite(result.getString("photo_publicite"));
            
         //  e.getUtilisateur().setId_user(result.getInt("id"));
         
            }
        }
        catch (SQLException ex)
                {
                    System.err.println("SQLException: "+ex.getMessage());
                    System.err.println("SQLSTATE: "+ex.getSQLState());
                    System.err.println("VnedorError: "+ex.getErrorCode());
                }
        return e; //To change body of generated methods, choose Tools | Templates.
    //To change body of generated methods, choose Tools | Templates.
    }
   
    public Publicite afficherpubliciteClick(String img) 

    {
                    Publicite pub = new Publicite();
                try {
            Statement stm = con.createStatement();
            ResultSet rest= 
                    stm.executeQuery("select * from publicite   where photo_publicite = '"+img+"' ");
            while(rest.next()){
                
                pub.setId_publicite(rest.getInt("id_publicite"));
                pub.setTitre(rest.getString("titre"));
                pub.setDescription_publicite(rest.getString("description_publicite"));
                pub.setPhoto_publicite(rest.getString("photo_publicite"));
                ServiceEtablissement sc = new ServiceEtablissement();
                pub.setEtablissement(sc.chercherEtablissement(rest.getInt("id_etablissement")));
               
             pub.setNbre_click(rest.getInt("nbr_click"));
           
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(ServicePublicite.class.getName()).log(Level.SEVERE, null, ex);
        }
         return pub;
    }    
    
    public List<Publicite> afficherpublicite(int id) {

    {
                List<Publicite> pubs =new ArrayList<>();
        try {
            Statement stm = con.createStatement();
            ResultSet rest= 
                    stm.executeQuery("select * from publicite p, etablissement e  where p.id_etablissement=e.id_etablissement and e.id = '"+id+"' ");
            while(rest.next()){
                Publicite pub = new Publicite();
                pub.setId_publicite(rest.getInt("id_publicite"));
                pub.setTitre(rest.getString("titre"));
                pub.setDescription_publicite(rest.getString("description_publicite"));
                pub.setPhoto_publicite(rest.getString("photo_publicite"));
                ServiceEtablissement sc = new ServiceEtablissement();
                pub.setEtablissement(sc.chercherEtablissement(rest.getInt("id_etablissement")));
               
            
                
                
                
                pubs.add(pub);
                
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(ServicePublicite.class.getName()).log(Level.SEVERE, null, ex);
        }
         return pubs;
    }    }
    
    public ObservableList<PieChart.Data> StatPublicite(int id) {
        ArrayList<PieChart.Data> list = new ArrayList<PieChart.Data>();
        try {
            PreparedStatement st = con.prepareStatement("SELECT p.titre, SUM( p.nbr_click ) \n" +
"FROM publicite p, etablissement e, user u\n" +
"WHERE e.id = u.id\n" +
"AND u.id ='"+id+"'\n" +
"AND p.id_etablissement = e.id_etablissement\n" +
"GROUP BY p.id_publicite;");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new PieChart.Data(rs.getString(1), rs.getDouble(2)));
            }
            ObservableList<PieChart.Data> observableList;
            observableList = FXCollections.observableList(list);
                     return observableList;

        } catch (SQLException ex) {
            Logger.getLogger(ServiceEtablissement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    public List<Publicite> afficherPhoto() {

    {
                List<Publicite> pic =new ArrayList<>();
        try {
            Statement stm = con.createStatement();
            ResultSet rest= 
                    stm.executeQuery("select * from `publicite` where dateDebut > ' "+
                            java.sql.Date.valueOf(LocalDate.now().minusDays(7))+"' ORDER BY dateDebut DESC;");
            System.out.println(LocalDate.now().minusDays(7));
            
            while(rest.next()){
                Publicite pub = new Publicite();
                pub.setId_publicite(rest.getInt("id_publicite"));
                pub.setDescription_publicite(rest.getString("description_publicite"));
                pub.setPhoto_publicite(rest.getString("photo_publicite"));
                pub.toString();
                
                pic.add(pub);
                
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(ServicePublicite.class.getName()).log(Level.SEVERE, null, ex);
        }
             return pic;
    }    }

   
    
    public void ajouterpublicite(Publicite p) {
try {
    String query = "insert into `bonplan`.`publicite` (`description_publicite`,`photo_publicite`,`id_etablissement`,enabled,titre,dateDebut) values (?,?,?,?,?,?)";
    
    PreparedStatement st =
            con.prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);
           
            st.setString(1,p.getDescription_publicite());
            st.setString(2,p.getPhoto_publicite());
            
             ServiceEtablissement sc = new ServiceEtablissement();
             st.setInt(3,sc.chercherUnEtablissementParNom(p.getEtablissement().getNom_etablissement()).getId_etablissement());
             System.out.println(sc.chercherEtablissement(p.getEtablissement().getId_etablissement()).getId_etablissement());
            
             st.setInt(4, 0);
             st.setString(5, p.getTitre());
             st.setDate(6, java.sql.Date.valueOf(LocalDate.now()));
                
             
         //   st.setInt(3,sc.ChercherEtablissement(p.getEtablissement().getId_etablissement()).getId_etablissement() );

            st.executeUpdate();
            ResultSet result = st.getGeneratedKeys();
            result.next();
           System.out.println("publicite est ajouter");
             
             
            int id = 0;
//            while (result.next()) {
//                id = result.getInt(1);
//                //p.setId_publicite(result.getInt(1));
//                System.out.println("id ajout pub"+id);
//            }
        } catch (SQLException ex) {
            Logger.getLogger(ServicePublicite.class.getName()).log(Level.SEVERE, null, ex);
            
        }   
    }

    
    public void modifierpublicite(Publicite p) {
 try
        {
        String update = "UPDATE publicite SET description_publicite = ? , photo_publicite = ? , titre = ? , nbr_click = ? WHERE id_publicite = ?";
        PreparedStatement statement2 = con.prepareStatement(update);
        statement2.setString(1, p.getDescription_publicite());
        statement2.setString(2, p.getPhoto_publicite());
        statement2.setString(3, p.getTitre());
        statement2.setInt(4, p.getNbre_click());
        statement2.setInt(5, p.getId_publicite());
        
        statement2.executeUpdate();
        
            System.out.println("update Done");
        }
        catch (SQLException e)
                {
                    System.out.println(e.getMessage());
                    
                }
    }
     public void updatepublicite(Publicite p) {
 try
        {
        String update = "UPDATE publicite SET enabled = ? WHERE id_publicite = ?";
        PreparedStatement statement2 = con.prepareStatement(update);
        statement2.setInt(1, 1);
        statement2.setInt(2,p.getId_publicite());
       
        
        statement2.executeUpdate();
        
            System.out.println("update Done");
        }
        catch (SQLException e)
                {
                    System.out.println(e.getMessage());
                    
                }
    }

    
    public void supprimerpublicite(Publicite p) {

         try {
            String query = "delete from `publicite` where id_publicite =?";
            PreparedStatement st = con.prepareStatement(query);
            st.setInt(1, p.getId_publicite());
            st.executeUpdate();
            System.out.println("supp pub ok");

        } catch (SQLException ex) {
            Logger.getLogger(ServicePublicite.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
     public List<Publicite> listDemandesPublicites() {
        List<Publicite> lc = new ArrayList<>();
        try {
            String select = "SELECT  * FROM publicite where enabled=0 ;";

            Statement statement1 = con.createStatement();

            ResultSet result = statement1.executeQuery(select);

            while (result.next()) {
                Publicite c = new Publicite();

                c.setId_publicite(result.getInt("id_publicite"));
                c.setDescription_publicite(result.getString("description_publicite"));
                c.setPhoto_publicite(result.getString("photo_publicite"));
                c.setTitre(result.getString("titre"));
                c.setEnabled(result.getInt("enabled"));


                lc.add(c);

            }
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
            System.err.println("SQLSTATE: " + e.getSQLState());
            System.err.println("VnedorError: " + e.getErrorCode());
        }
        return lc;
    } //To change body of generated methods, choose Tools | Templates.

    
 


    
    
}
