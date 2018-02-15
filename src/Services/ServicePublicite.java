/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Config.Connexion;
import Entite.Publicite;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import IServices.IServicePublicite;
import IServices.IServiceEvenement;
import java.sql.Connection;

/**
 *
 * @author admin
 */
public class ServicePublicite implements IServicePublicite{
Connection con ;

    public ServicePublicite() {
        con = Connexion.getInstance().getCon();
    }
    @Override
    public Publicite rechercherpublicite(int p){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Publicite> afficherpublicite() {

    {
                List<Publicite> pubs =new ArrayList<>();
        try {
            Statement stm = Connexion.getInstance().getCon().createStatement();
            ResultSet rest= 
                    stm.executeQuery("select * from `publicite` ");
            while(rest.next()){
                Publicite pub = new Publicite();
                pub.setId_publicite(rest.getInt("id_publicite"));
                pub.setDescription_publicite(rest.getString("description_publicite"));
                pub.setPhoto_publicite(rest.getString("photo_publicite"));
                
                pubs.add(pub);
                
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(ServicePublicite.class.getName()).log(Level.SEVERE, null, ex);
        }
         return pubs;
    }    }

    @Override
    public void ajouterpublicite(Publicite p) {
try {
    String query = "insert into `bonplan`.`publicite` (`description_publicite`,`photo_publicite`,`id_etablissement`) values (?,?,?)";
    
    PreparedStatement st =
            Connexion.getInstance().getCon().prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);
           
            st.setString(1,p.getDescription_publicite());
            st.setString(2,p.getPhoto_publicite());
             ServiceEtablissement sc = new ServiceEtablissement();
             st.setInt(3,2);
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

    @Override
    public void modifierpublicite(Publicite p) {
 try
        {
        String update = "UPDATE publicite SET description_publicite = ? , photo_publicite = ? WHERE id_publicite = ?";
        PreparedStatement statement2 = con.prepareStatement(update);
        statement2.setString(1, p.getDescription_publicite());
        statement2.setString(2, p.getPhoto_publicite());
        statement2.setInt(3, p.getId_publicite());
        
        statement2.executeUpdate();
        
            System.out.println("update Done");
        }
        catch (SQLException e)
                {
                    System.out.println(e.getMessage());
                    
                }
    }

    @Override
    public void supprimerpublicite(Publicite p) {

         try {
            String query = "delete from `publicite` where id_publicite =?";
            PreparedStatement st = Connexion.getInstance().getCon().prepareStatement(query);
            st.setInt(1, p.getId_publicite());
            st.executeUpdate();
            System.out.println("supp pub ok");

        } catch (SQLException ex) {
            Logger.getLogger(ServicePublicite.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }

    
    
}
