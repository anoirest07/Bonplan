/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Config.Connexion;
import Entite.DemandeEtablissement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
public class ServiceDemandeEtablissement implements IServices.IServiceDemandeEtablissement{
 Connection con ;

    public ServiceDemandeEtablissement() {
        con = Connexion.getInstance().getCon();
    }
    @Override
    public void ajouterDemandeEtablissement(DemandeEtablissement de) {
        String sql = "INSERT INTO demandes_etablissement(nom_etablissement_demande,adresse_etablissement_demande,telephone_etablissement_demande,horaire_travail_demande,description_demande_etablissement,photo_demande_etablissement,photo_patente_demande,code_postal_demande,position_demande,budget_demande,site_web_demande,id_categorie,id) VAlUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement stm;
        try {
            stm = con.prepareStatement(sql);
            stm.setString(1,de.getNom_etab_demande());
            stm.setString(2,de.getAdresse_etab_demande());
            stm.setInt(3,de.getTelephone_etab_demande());
            stm.setString(4,de.getHoraire_travail_etab_demande());
            stm.setString(5,de.getDescription_etab_demande());
            stm.setString(6,de.getPhoto_etab_demande());
            stm.setString(7,de.getPhoto_patente_etab_demande());
            stm.setInt(8,de.getCode_postal_etab_demande());
            stm.setString(9,de.getPosition_etab_demande());
            stm.setString(10,de.getBudget_demande().toString());   
            stm.setString(11,de.getSite_web_etab_demande());
            stm.setInt(12, de.getCategorie().getId_categorie());
            stm.setInt(13,1);
            int res = stm.executeUpdate();
            if(res > 0){
                System.out.println("Add Done");
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifierDemandeEtablissement(DemandeEtablissement de) {
        try
        {
        String update = "UPDATE demandes_etablissement SET  nom_etablissement_demande=?,adresse_etablissement_demande=?,telephone_etablissement_demande=?,horaire_travail_demande=?,description_demande_etablissement=?,photo_demande_etablissement=?,photo_patente_demande=?,code_postal_demande=?,position_demande=?,budget_demande=?,site_web_demande=?,id_categorie=?,id=? WHERE id_demande_etab = ?";
        PreparedStatement statement2 = con.prepareStatement(update);
        
        statement2.setString(1, de.getNom_etab_demande());
        statement2.setString(2, de.getAdresse_etab_demande());      
        statement2.setInt(3, de.getTelephone_etab_demande());
        statement2.setString(4, de.getHoraire_travail_etab_demande());
        statement2.setString(5, de.getDescription_etab_demande());
        statement2.setString(6, de.getPhoto_etab_demande());
        statement2.setString(7, de.getPhoto_patente_etab_demande());
        statement2.setInt(8, de.getCode_postal_etab_demande());
        statement2.setString(9, de.getPosition_etab_demande());
        statement2.setString(10, de.getBudget_demande().toString());
        statement2.setString(11,de.getSite_web_etab_demande());
        statement2.setInt(12, de.getCategorie().getId_categorie());
        statement2.setInt(13, de.getUtilisateur().getId_user());
        statement2.setInt(14, de.getId_demande_etab());

        
        statement2.executeUpdate();
        System.out.println(""+de.getNom_etab_demande()+" modifiee !!!");
        
        }
        catch(SQLException ex)
                {
                    System.out.println(ex.getMessage());
                    System.err.println(""+de.getNom_etab_demande()+" non modifiee");
                } //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimerDemandeEtablissement(DemandeEtablissement de) {
         try 
        {
        String delete = "DELETE FROM demandes_etablissement WHERE id_demande_etab= ? ";
        PreparedStatement st2 = con.prepareStatement(delete);
        st2.setInt(1,de.getId_demande_etab());
        st2.executeUpdate();
        System.out.println(""+de.getNom_etab_demande()+" supprimee !!!");
        
        }
        catch (SQLException ex)
        {

                    System.err.println("SQLException: "+ex.getMessage());
                           } //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<DemandeEtablissement> listDemandeEtablissement() {
        List<DemandeEtablissement> ld=new ArrayList<>();
        try 
        {
        String select = "SELECT  * FROM demandes_etablissement ;"; 
                 
        Statement statement1 = con.createStatement();
        
        ResultSet result = statement1.executeQuery(select);
        
        while (result.next()) 
        {
            DemandeEtablissement de = new DemandeEtablissement();
             de.setId_demande_etab(result.getInt("id_demande_etab"));
            de.setNom_etab_demande(result.getString("nom_etablissement_demande"));
            de.setAdresse_etab_demande(result.getString("adresse_etablissement_demande"));  
            de.setTelephone_etab_demande(result.getInt("telephone_etablissement_demande"));
            de.setHoraire_travail_etab_demande(result.getString("horaire_travail_demande"));
            de.setDescription_etab_demande(result.getString("description_demande_etablissement"));
            de.setPhoto_etab_demande(result.getString("photo_demande_etablissement"));
            de.setPhoto_patente_etab_demande(result.getString("photo_patente_demande"));
            de.setCode_postal_etab_demande(result.getInt("code_postal_demande"));
            de.setPosition_etab_demande(result.getString("position_demande"));
            de.setBudget_demande(DemandeEtablissement.Budget.valueOf(result.getString("budget_demande")));
            de.setSite_web_etab_demande(result.getString("site_web_demande"));
            de.getCategorie().setId_categorie(result.getInt("id_categorie"));
           // de.getUtilisateur().setId_user(result.getInt("id"));
            
            
            ld.add(de);

        } 
    }   
        catch (SQLException ex)
                {
                    System.err.println("SQLException: "+ex.getMessage());
                    System.err.println("SQLSTATE: "+ex.getSQLState());
                    System.err.println("VnedorError: "+ex.getErrorCode());
                }
        return ld; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DemandeEtablissement afficherDemandeEtab(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
