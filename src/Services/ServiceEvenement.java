/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Config.Connexion;
import Entite.Evenement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import IServices.IServiceEvenement;

/**
 *
 * @author admin
 */
public class ServiceEvenement implements IServiceEvenement{

    @Override
    public List<Evenement> afficherevenement() 
    {
        List<Evenement> evs =new ArrayList<>();
        try {
            Statement stm = Connexion.getInstance().getCon().createStatement();
            ResultSet rest= 
                    stm.executeQuery("select * from `evenement` ");
            while(rest.next()){
                Evenement ev = new Evenement();
                ev.setId_evenement(rest.getInt("id_evenement"));
                ev.setNom_evenement(rest.getString("nom_evenement"));

                ev.setDescription_evenement(rest.getString("description_evenement"));
                
                ev.setDate_evenement(rest.getDate("date_evenement"));
                ev.setPhoto_evenement(rest.getString("photo_evenement"));
                ev.setNbr_participant(rest.getInt("nombre_participant"));
                ev.setNbr_interesse(rest.getInt("nombre_intersse"));
                ev.getEtablissement().setId_etablissement(rest.getInt("id_etablissement"));
                evs.add(ev);
                
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }
         return evs;
    }

   

    

    @Override
    public void modifierevenement(Evenement e) {
try {
            String query = 
                    
         "update `bonplan`.`evenement` set  description_evenement=?, date_evenement=?, photo_evenement=? where id_etablissement =?  ;";
            
            PreparedStatement st = Connexion.getInstance().getCon().prepareStatement(query);

            st.setString(1,e.getDescription_evenement());
            st.setDate(2,e.getDate_evenement());
            st.setString(3,e.getPhoto_evenement());
            st.setInt(4,e.getEtablissement().getId_etablissement());

            st.executeUpdate();
            System.out.println(e.getId_evenement());

        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void supprimerevenement(Evenement e) {
 try {
            String query = "delete from `evenement` where id_evenement =?";
            PreparedStatement st = Connexion.getInstance().getCon().prepareStatement(query);
            st.setInt(1, e.getId_evenement());
            st.executeUpdate();
            System.out.println("supp even ok");

        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @Override
    public void ajouterevenement(Evenement e) {
try {
            String query = "insert into `bonplan`.`evenement` (`description_evenement`,`date_evenement`,`photo_evenement`,`id_etablissement`) values (?,?,?,?)";
            PreparedStatement st = Connexion.getInstance().getCon().prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);
           
           
            st.setString(1,e.getDescription_evenement());
            st.setDate(2,e.getDate_evenement());
            st.setString(3,e.getPhoto_evenement()); 
            st.setInt(4,e.getEtablissement().getId_etablissement());

            st.executeUpdate();
             ResultSet result = st.getGeneratedKeys();
            int id = 0;
            while (result.next()) {
                id = result.getInt(1);
                e.setId_evenement(result.getInt(1));
                System.out.println("id ajout even "+id);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
            
        }    
    }

    @Override
    public Evenement rechercherevenment(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
 }

 