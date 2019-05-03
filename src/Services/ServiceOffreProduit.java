/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Config.Connexion;
import Entite.Offre;
import Entite.OffreProduit;
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
 * @author Yassine
 */
public class ServiceOffreProduit implements IServices.IServiceOffreProduit{

    @Override
    public void ajouteroffreProduit(OffreProduit op) {
        try {
     
             String query = "INSERT INTO `offreproduit`(`id_offre`, `id_produit`) VALUES (?,?)";
            PreparedStatement st = Connexion.getInstance().getCon().prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);
        st.setInt(1,op.getId_offre());
        st.setInt(2,op.getId_produit());
         
        st.executeUpdate();
          
          ResultSet result = st.getGeneratedKeys();
            int id = 0;
            while (result.next()) {
                id = result.getInt(1);
                op.setId_op(result.getInt(1));
                System.out.println("id ajout offreProduit: "+id);
        
        } 
        }
            catch (SQLException ex) {
            Logger.getLogger(ServiceOffreProduit.class.getName()).log(Level.SEVERE, null, ex);
        }
       
   

           

    }

    @Override
    public void supprimeroffreProduit(OffreProduit op) {
 try {
            String query = "DELETE FROM `offreproduit` WHERE where id_op =?";
            PreparedStatement st = Connexion.getInstance().getCon().prepareStatement(query);
            
            st.setInt(1, op.getId_op());
            
            st.executeUpdate();
            System.out.println("supp offPROD ok");

        } catch (SQLException ex) {
            Logger.getLogger(ServiceOffre.class.getName()).log(Level.SEVERE, null, ex);
        }    }
    
    
   
    public List<OffreProduit> recupOffreSelect(int id_offre) {
   
    
                List<OffreProduit> ops =new ArrayList<>();
        try {
            
            
            String select = "SELECT * from `offreproduit` where id_offre = '"+id_offre+"' ";
        Statement stm = Connexion.getInstance().getCon().createStatement();
        ResultSet rest = stm.executeQuery(select);
       
            
            while(rest.next()){
                OffreProduit op = new OffreProduit();
                 

                op.setId_op(rest.getInt("id_op"));
                op.setId_offre(rest.getInt("id_offre"));
                op.setId_produit(rest.getInt("id_produit"));
                
               
                
                ops.add(op);
                
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(ServiceOffre.class.getName()).log(Level.SEVERE, null, ex);
        }
         return ops;
    }
    
    
}
