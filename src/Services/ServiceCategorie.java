/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Config.Connexion;
import Entite.Categorie;
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
public class ServiceCategorie implements IServices.IServiceCategorie{
Connection con ;

    public ServiceCategorie() {
        con = Connexion.getInstance().getCon();
    }
    @Override
    public void ajouterCategorie(Categorie c) {
          try {
            Statement state = con.createStatement();
            state.executeUpdate("INSERT INTO`categorie`(`nom_categorie`) VALUES ('"+c.getNom_categorie()+"');");
                    
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());
        
    } //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifierCategorie(Categorie c) {
        try
        {
        String update = "UPDATE categorie SET nom_categorie = ? WHERE id_categorie = ?";
        PreparedStatement statement2 = con.prepareStatement(update);
        statement2.setString(1, c.getNom_categorie());
        statement2.setInt(2, c.getId_categorie());
        
        statement2.executeUpdate();
        System.out.println(""+c.getNom_categorie()+" modifiee !!!");
        
        }
        catch (SQLException e)
                {
                    System.out.println(e.getMessage());
                    System.err.println(""+c.getNom_categorie()+" non modifiee");
                } //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimerCategorie(Categorie c) {
        try 
        {
        String delete = "DELETE FROM categorie WHERE id_categorie = ?";
        PreparedStatement st2 = con.prepareStatement(delete);
        st2.setInt(1,c.getId_categorie());
        
        st2.executeUpdate();
        
        
        }
        catch (SQLException e)
        {

                    System.err.println("SQLException: "+e.getMessage());
                           } //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Categorie> listCategorie() {
        List<Categorie> lc=new ArrayList<>();
        try 
        {
        String select = "SELECT  * FROM categorie ;"; 
                 
        Statement statement1 = con.createStatement();
        
        ResultSet result = statement1.executeQuery(select);
        
        while (result.next()) 
        {
            Categorie c = new Categorie();
            
            c.setId_categorie(result.getInt("id_categorie"));
            c.setNom_categorie(result.getString("nom_categorie"));
            
            
            lc.add(c);

        } 
    }   
        catch (SQLException e)
                {
                    System.err.println("SQLException: "+e.getMessage());
                    System.err.println("SQLSTATE: "+e.getSQLState());
                    System.err.println("VnedorError: "+e.getErrorCode());
                }
        return lc;
} //To change body of generated methods, choose Tools | Templates.

    @Override
    public Categorie afficherCategorie(int id) {
                           Categorie c = new Categorie();

        try 
        {
                   Statement stm = Connexion.getInstance().getCon().createStatement();
            ResultSet rest= 
                    stm.executeQuery("select * from `categorie` where id_categorie='"+id+"'");
        
        while (rest.next()) 
        {
            
            c.setId_categorie(rest.getInt("id_categorie"));
            c.setNom_categorie(rest.getString("nom_categorie"));
            
        } 

    }   
        catch (SQLException e)
                {
                    System.err.println("SQLException: "+e.getMessage());
                    System.err.println("SQLSTATE: "+e.getSQLState());
                    System.err.println("VnedorError: "+e.getErrorCode());
                }
        return c;

        
    }
    }//To change body of generated methods, choose Tools | Templates.
    
    
    

