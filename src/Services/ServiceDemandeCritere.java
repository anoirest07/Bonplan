/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Config.Connexion;

import Entite.DemandeCritere;
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
public class ServiceDemandeCritere implements IServices.IServiceDemandeCritere{
Connection con ;

    public ServiceDemandeCritere() {
        con = Connexion.getInstance().getCon();
    }
    @Override
    public void ajouterDemandeCritere(DemandeCritere dcr) {
        try {
            Statement state = con.createStatement();
            state.executeUpdate("INSERT INTO`demandes_critere_evaluation`(`nom_critere_demande`) VALUES ('"+dcr.getNom_demande_crit()+"');");    
                    
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());
        
    } //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifierDemandeCritere(DemandeCritere dcr) {
        try
        {
        String update = "UPDATE demandes_critere_evaluation SET nom_critere_demande= ? WHERE id_demande_crit = ?";
        PreparedStatement statement2 = con.prepareStatement(update);
        statement2.setString(1, dcr.getNom_demande_crit());
        statement2.setInt(2, dcr.getId_demande_crit());
        
        statement2.executeUpdate();
        System.out.println(""+dcr.getNom_demande_crit()+" modifiee !!!");
        
        }
        catch (SQLException e)
                {
                    System.out.println(e.getMessage());
                    System.err.println(""+dcr.getNom_demande_crit()+" non modifiee");
                } //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimerDemandeCritere(DemandeCritere dcr) {
         try 
        {
        String delete = "DELETE FROM demandes_critere_evaluation WHERE id_demande_crit= ? ";
        PreparedStatement st2 = con.prepareStatement(delete);
        st2.setInt(1,dcr.getId_demande_crit());
        st2.executeUpdate();
        System.out.println(""+dcr.getNom_demande_crit()+" supprimee !!!");
        
        }
        catch (SQLException ex)
        {

                    System.err.println("SQLException: "+ex.getMessage());
                           } //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<DemandeCritere> listDemandeCritere() {
        List<DemandeCritere> ldcr=new ArrayList<>();
        try 
        {
        String select = "SELECT  * FROM demandes_critere_evaluation ;"; 
                 
        Statement statement1 = con.createStatement();
        
        ResultSet result = statement1.executeQuery(select);
        
        while (result.next()) 
        {
            DemandeCritere dcr = new DemandeCritere();
            
            dcr.setId_demande_crit(result.getInt("id_demande_crit"));
            dcr.setNom_demande_crit(result.getString("nom_critere_demande"));  
            dcr.getDemande_cat().setId_demande_cat(result.getInt("id_demande_cat"));
            
            
            
            ldcr.add(dcr);

        } 
    }   
        catch (SQLException ex)
                {
                    System.err.println("SQLException: "+ex.getMessage());
                    System.err.println("SQLSTATE: "+ex.getSQLState());
                    System.err.println("VnedorError: "+ex.getErrorCode());
                }
        return ldcr; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DemandeCritere afficherDemandeCrit(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
    
    
    
}
