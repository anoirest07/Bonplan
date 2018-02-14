/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Config.Connexion;
import Entite.DemandeCategorie;
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
public class ServicesDemandeCategorie implements IServices.IServicesDemandeCategorie{
Connection con ;

    public ServicesDemandeCategorie() {
        con = Connexion.getInstance().getCon();
    }
    @Override
    public void ajouterDemandeCategorie(DemandeCategorie dc) {
        try {
            Statement state = con.createStatement();
            state.executeUpdate("INSERT INTO`demandes_categorie`(`nom_categorie_demande`,`id_demande_etab`) VALUES ('"+dc.getNom_categorie_demande()+"','"+dc.getDemande_etab().getId_demande_etab()+");");
                 
                    
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());
        
    } //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifierDemandeCategorie(DemandeCategorie dc) {
        try
        {
        String update = "UPDATE demandes_categorie SET nom_categorie_demande = ? WHERE id_demande_cat = ?";
        PreparedStatement statement2 = con.prepareStatement(update);
        statement2.setString(1, dc.getNom_categorie_demande());
        statement2.setInt(2, dc.getId_demande_cat());
        
        statement2.executeUpdate();
        System.out.println(""+dc.getNom_categorie_demande()+" modifiee !!!");
        
        }
        catch (SQLException e)
                {
                    System.out.println(e.getMessage());
                    System.err.println(""+dc.getNom_categorie_demande()+" non modifiee");
                } //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimerDemandeCategorie(DemandeCategorie dc) {
         try 
        {
        String delete = "DELETE FROM demandes_categorie WHERE id_demande_cat= ? ";
        PreparedStatement st2 = con.prepareStatement(delete);
        st2.setInt(1,dc.getId_demande_cat());
        st2.executeUpdate();
        System.out.println(""+dc.getNom_categorie_demande()+" supprimee !!!");
        
        }
        catch (SQLException ex)
        {

                    System.err.println("SQLException: "+ex.getMessage());
                           } //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<DemandeCategorie> listDemandeCategorie() {
        List<DemandeCategorie> ldc=new ArrayList<>();
        try 
        {
        String select = "SELECT  * FROM demandes_categorie ;"; 
                 
        Statement statement1 = con.createStatement();
        
        ResultSet result = statement1.executeQuery(select);
        
        while (result.next()) 
        {
            DemandeCategorie dc = new DemandeCategorie();
            
            dc.setId_demande_cat(result.getInt("id_demande_cat"));
            dc.setNom_categorie_demande(result.getString("nom_categorie_demande"));  
            dc.getDemande_etab().setId_demande_etab(result.getInt("id_demande_etab"));
            
            
            ldc.add(dc);

        } 
    }   
        catch (SQLException ex)
                {
                    System.err.println("SQLException: "+ex.getMessage());
                    System.err.println("SQLSTATE: "+ex.getSQLState());
                    System.err.println("VnedorError: "+ex.getErrorCode());
                }
        return ldc;
} //To change body of generated methods, choose Tools | Templates.

    @Override
    public DemandeCategorie rechercherDemandeCat(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    }
    
    
