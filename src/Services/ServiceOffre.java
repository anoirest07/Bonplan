/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Config.Connexion;
import Entite.Offre;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import IServices.IServiceOffre;

/**
 *
 * @author admin
 */
public class ServiceOffre implements IServiceOffre
{
 private int IDD;
    @Override
    public void ajouteroffre(Offre o) {

        
        
try {
            String query = "INSERT INTO `offre`(`titre_offre`, `description_offre`, `date_debut`, `date_fin`, `photo_offre`, `nombre_like`, `nombre_dislike`, `id_etablissement`) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement st = Connexion.getInstance().getCon().prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);
           
           
             st.setString(1,o.getTitre_offre());
            st.setString(2,o.getDescription());
            st.setDate(3,o.getDate_debut());
            st.setDate(4,o.getDate_fin()); 
            st.setString(5,o.getPhoto()); 
            st.setInt(6,o.getNombre_like());
            st.setInt(7,o.getNombre_dislike());
            
            
           ServiceEtablissement sc = new ServiceEtablissement();
           st.setInt(8,sc.chercherUnEtablissementParNom(o.getEtablissement().getNom_etablissement()).getId_etablissement());

//st.setInt(8,sc.chercherEtablissement(o.getEtablissement().getId_etablissement()).getId_etablissement());

            st.executeUpdate();
             ResultSet result = st.getGeneratedKeys();

            
             int id = 0;
            while (result.next()) {
                id = result.getInt(1);
                o.setId_offre(id);
                System.out.println("id ajout offre: "+id);
                IDD=id;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceOffre.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    }

    @Override
    public List<Offre> afficheroffre() {
    
    
                List<Offre> offres =new ArrayList<>();
        try {
            Statement stm = Connexion.getInstance().getCon().createStatement();
            ResultSet rest= 
                    stm.executeQuery("select * from offre o, etablissement e  where o.id_etablissement=e.id_etablissement ");
            while(rest.next()){
                Offre off = new Offre();
                off.setId_offre(rest.getInt("id_offre"));
                off.setTitre_offre(rest.getString("titre_offre"));
                off.setDescription(rest.getString("description_offre"));
                off.setDate_debut(rest.getDate("date_debut"));
                off.setDate_fin(rest.getDate("date_fin"));
                off.setPhoto(rest.getString("photo_offre"));
                off.setNombre_like(rest.getInt("nombre_like"));
                off.setNombre_dislike(rest.getInt("nombre_dislike"));
                                ServiceEtablissement sc = new ServiceEtablissement();
                  off.setEtablissement(sc.chercherEtablissement(rest.getInt("id_etablissement")));
               
               
                
                offres.add(off);
                
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(ServiceOffre.class.getName()).log(Level.SEVERE, null, ex);
        }
         return offres;
    }

    
    @Override
    public List<Offre> afficherALLoffres() {
    
    
                List<Offre> offres =new ArrayList<>();
        try {
            Statement stm = Connexion.getInstance().getCon().createStatement();
            ResultSet rest= 
                    stm.executeQuery("SELECT * from `offre`");
            while(rest.next()){
                Offre off = new Offre();
                off.setId_offre(rest.getInt("id_offre"));
                off.setTitre_offre(rest.getString("titre_offre"));
                off.setDescription(rest.getString("description_offre"));
                off.setDate_debut(rest.getDate("date_debut"));
                off.setDate_fin(rest.getDate("date_fin"));
                off.setPhoto(rest.getString("photo_offre"));
                off.setNombre_like(rest.getInt("nombre_like"));
                off.setNombre_dislike(rest.getInt("nombre_dislike"));
                                     ServiceEtablissement sc = new ServiceEtablissement();
                  off.setEtablissement(sc.chercherEtablissement(rest.getInt("id_etablissement")));
            
               
                
                offres.add(off);
                
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(ServiceOffre.class.getName()).log(Level.SEVERE, null, ex);
        }
         return offres;
    }
    

    @Override
    public void modifieroffre(Offre o) {

try {
            String query = "UPDATE `offre` SET `titre_offre`=?,`description_offre`=?,`date_debut`=?,`date_fin`=?,`photo_offre`=? WHERE `id_etablissement`=? and id_offre=? ;";
            PreparedStatement st = Connexion.getInstance().getCon().prepareStatement(query);

            st.setString(1,o.getTitre_offre());
            st.setString(2,o.getDescription());
            st.setDate(3,o.getDate_debut());
            st.setDate(4,o.getDate_fin());
            st.setString(5,o.getPhoto());
            st.setInt(6,o.getEtablissement().getId_etablissement());
            st.setInt(7,o.getId_offre());

            st.executeUpdate();
            System.out.println(o.getId_offre());

        } catch (SQLException ex) {
            Logger.getLogger(ServiceOffre.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void supprimeroffre(Offre o) {

         try {
            String query = "delete from `offre` where id_offre =?";
            PreparedStatement st = Connexion.getInstance().getCon().prepareStatement(query);
            st.setInt(1, o.getId_offre());
            st.executeUpdate();
            System.out.println("supp off ok");

        } catch (SQLException ex) {
            Logger.getLogger(ServiceOffre.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Offre rechercheroffre(int id_et) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getIDD() {
        return IDD;
    }
        
    }
    