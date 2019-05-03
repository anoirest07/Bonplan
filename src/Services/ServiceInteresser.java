/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Config.Connexion;
import Entite.Evenement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Entite.Interesser;
import Entite.Utilisateur;
/**
 *
 * @author amine
 */
public class ServiceInteresser {
     Statement st;
    Connection c = Config.Connexion.getInstance().getCon();
    PreparedStatement pst;
    ResultSet rs;
     public void Createinteresser(Interesser i) throws SQLException{
        String req1 = "insert into interesser (id_evenement, id) values (?,?)";
        pst = c.prepareStatement(req1);
        pst.setInt(1, i.getE().getId_evenement());
        pst.setInt(2, i.getU().getId_user());
        
        pst.executeUpdate();
    }
    
    public void Deleteinteresser(Interesser in) throws SQLException{
        
       
        String req1 = "delete from interesser where id_evenement=? and id=?";
        pst = c.prepareStatement(req1);
        pst.setInt(1, in.getE().getId_evenement());
        pst.setInt(2, in.getU().getId_user());
        
        pst.execute();
    }
    
    
    public List<Interesser> GetinteressersByuserid(int id) throws SQLException {

        List<Interesser> listinteresser= new ArrayList<Interesser>();
       
        
            String req = "SELECT * FROM interesser WHERE id=?";
            pst = c.prepareStatement(req);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            while (rs.next()){
                Interesser interesser1 = new Interesser();
            interesser1.getU().setId_user(rs.getInt("id"));
            interesser1.getE().setId_evenement(rs.getInt("id_evenement"));
                   listinteresser.add(interesser1); }
        
        return listinteresser;

    }
    
     public int GetinteresserByevenementid(Evenement even) throws SQLException {

        List<Interesser> listinteresser = new ArrayList<Interesser>();
      
        Evenement e = new Evenement();
        
            String req = "SELECT * FROM interesser WHERE id_evenement=?";
            pst = c.prepareStatement(req);
            pst.setInt(1,even.getId_evenement());
            rs = pst.executeQuery();
            while (rs.next()){
                Interesser interesser1 = new Interesser();
                interesser1.setE(new ServiceEvenement().rechercherevenmentbyid(rs.getInt(2)));
           interesser1.setU(new ServiceUtilisateur().getUserById(rs.getInt(3)));
           
              
                   listinteresser.add(interesser1); }
        
        return listinteresser.size();

    }
    
     
      public boolean isinteresser(Utilisateur ut,Evenement e) throws SQLException {
           
      
             pst = c.prepareStatement ("SELECT * FROM interesser WHERE id=? and id_evenement=?");
             
            pst.setInt(1,ut.getId_user());
            pst.setInt(2,e.getId_evenement());
            rs = pst.executeQuery();
           
            
            
        while (rs.next()) {
            return true;
        }

        return false;

    }
   
}
