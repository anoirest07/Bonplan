/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Config.Connexion;
import Entite.DemandePub;
import Entite.Etablissement;
import Entite.Publicite;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import IServices.IServiceDemandePub;

/**
 *
 * @author admin
 */
public class ServiceDemandePub implements IServiceDemandePub{

    @Override
    public List<DemandePub> afficherDemPublicite() {

                List<DemandePub> demPubs =new ArrayList<>();
        try {
            Statement stm = Connexion.getInstance().getCon().createStatement(); 
            String query= "select * from demandes_publicite ";
            ResultSet rest= 
                    stm.executeQuery(query);
            while(rest.next()){
DemandePub demp=new DemandePub();
                demp.setId_demande_pub(rest.getInt("id_demande_pub"));
                demp.setDescription_demande_pub(rest.getString("description_demande_pub"));
                demp.setPhoto_demande_pub(rest.getString("photo_demande_publicite"));
                demp.getEtablissement().setId_etablissement(rest.getInt("id_etablissement"));
               
//               ServiceEtablissement se = new ServiceEtablissement();
//               Etablissement et= se.afficheretablissement(rest.getInt("id_etablissement"));
//                demp.setEtab(et);
//                demp.getEtab().setId_etablissement(rest.getInt("id_etablissement"));
     //               demp.se

                demPubs.add(demp);
                
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(ServicePublicite.class.getName()).log(Level.SEVERE, null, ex);
        }
         return demPubs;

    }

    @Override
    public void ajouterDemPublicite(DemandePub dp) {
try {
    String query = "insert into `bonplan`.`demandes_publicite` (`description_demande_pub`,`photo_demande_publicite`,`id_etablissement`) values (?,?,?)";
    
    PreparedStatement st =
            Connexion.getInstance().getCon().prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);
           
            st.setString(1,dp.getDescription_demande_pub());
            st.setString(2,dp.getPhoto_demande_pub());
            st.setInt(3,dp.getEtablissement().getId_etablissement());


            st.executeUpdate();

             ResultSet result = st.getGeneratedKeys();
            int id = 0;
            while (result.next()) {
                id = result.getInt(1);
                dp.setId_demande_pub(result.getInt(1));
                System.out.println("id ajout dempub"+id);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServicePublicite.class.getName()).log(Level.SEVERE, null, ex);
            
        }   
    }    

    @Override
    public void modifierDemPublicite(DemandePub dp) {
try {
            String query = "update `bonplan`.`demandes_publicite` set description_demande_pub =?, photo_demande_publicite=? where id_etablissement =?  ;";
            PreparedStatement st = Connexion.getInstance().getCon().prepareStatement(query);

            st.setString(1,dp.getDescription_demande_pub());
            st.setString(2,dp.getPhoto_demande_pub());
            st.setInt(3,dp.getEtablissement().getId_etablissement());

            st.executeUpdate();
            System.out.println(dp.getId_demande_pub());

        } catch (SQLException ex) {
            Logger.getLogger(ServicePublicite.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }

    
    @Override
    public void supprimerDemPublicite(DemandePub dp) {
try {
            String query = "delete from `demandes_publicite` where id_demande_pub =?";
            PreparedStatement st = Connexion.getInstance().getCon().prepareStatement(query);
            st.setInt(1,dp.getId_demande_pub());
            st.executeUpdate();
            System.out.println("supp pub ok");

        } catch (SQLException ex) {
            Logger.getLogger(ServicePublicite.class.getName()).log(Level.SEVERE, null, ex);
        }        }

    @Override
    public DemandePub afficherDemandePub(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
