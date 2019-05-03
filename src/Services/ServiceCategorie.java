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
 public  Connection con;

    public ServiceCategorie() {
       this.con = Connexion.getInstance().getCon();
    }

    @Override
    public void ajouterCategorie(Categorie c) {
        String req = "INSERT INTO`categorie`(`nom_categorie`,`enabled`) VALUES ( ?,? ) ";

        try {
            PreparedStatement st1 = con.prepareStatement(req, PreparedStatement.RETURN_GENERATED_KEYS);

            st1.setString(1, c.getNom_categorie());
            st1.setInt(2, 1);

            st1.executeUpdate();

            ResultSet result = st1.getGeneratedKeys();
            int id = 0;
            while (result.next()) {
                id = result.getInt(1);
                c.setId_categorie(result.getInt(1));
                System.out.println("id ajout" + id);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void modifierCategorie(Categorie c) {
        try {
            String update = "UPDATE categorie SET nom_categorie = ?, enabled=? WHERE id_categorie = ?";
            PreparedStatement statement2 = con.prepareStatement(update);
            statement2.setString(1, c.getNom_categorie());
            statement2.setInt(2, c.getEnabled());

            statement2.setInt(3, c.getId_categorie());

            statement2.executeUpdate();
            System.out.println("" + c.getNom_categorie() + " modifiee !!!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.err.println("" + c.getNom_categorie() + " non modifiee");
        } //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimerCategorie(Categorie c) {
        try {
            String delete = "DELETE FROM categorie WHERE id_categorie = ?";
            PreparedStatement st2 = con.prepareStatement(delete);
            st2.setInt(1, c.getId_categorie());

            st2.executeUpdate();

        } catch (SQLException e) {

            System.err.println("SQLException: " + e.getMessage());
        } //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Categorie> listCategorie() {
        List<Categorie> lc = new ArrayList<>();
        try {
            String select = "SELECT  * FROM categorie where enabled=1;";

            Statement statement1 = con.createStatement();

            ResultSet result = statement1.executeQuery(select);

            while (result.next()) {
                Categorie c = new Categorie();

                c.setId_categorie(result.getInt("id_categorie"));
                c.setNom_categorie(result.getString("nom_categorie"));
                c.setEnabled(result.getInt("enabled"));


                lc.add(c);

            }
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
            System.err.println("SQLSTATE: " + e.getSQLState());
            System.err.println("VnedorError: " + e.getErrorCode());
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
            c.setEnabled(rest.getInt("enabled"));

            
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
    
    
    
    @Override
     public Categorie afficherCategorieParNom(String nom) {
                    Categorie c = new Categorie();

        try 
        {
                   Statement stm = Connexion.getInstance().getCon().createStatement();
            ResultSet rest= 
                    stm.executeQuery("select * from `categorie` where nom_categorie='"+nom+"'");
        
        while (rest.next()) 
        {
            
            c.setId_categorie(rest.getInt("id_categorie"));
            c.setNom_categorie(rest.getString("nom_categorie"));
            c.setEnabled(rest.getInt("enabled"));

            
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
 @Override
    public List<Categorie> listDemandesCategorie() {
        List<Categorie> lc = new ArrayList<>();
        try {
            String select = "SELECT  * FROM categorie where enabled=0 ;";

            Statement statement1 = con.createStatement();

            ResultSet result = statement1.executeQuery(select);

            while (result.next()) {
                Categorie c = new Categorie();

                c.setId_categorie(result.getInt("id_categorie"));
                c.setNom_categorie(result.getString("nom_categorie"));
                c.setEnabled(result.getInt("enabled"));


                lc.add(c);

            }
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
            System.err.println("SQLSTATE: " + e.getSQLState());
            System.err.println("VnedorError: " + e.getErrorCode());
        }
        return lc;
    } //To change body of generated methods, choose Tools | Templates.

    @Override
    public void ajouterDemandeCategorie(Categorie c) {
        String req = "INSERT INTO`categorie`(`nom_categorie`,`enabled`) VALUES ( ?,? ) ";

        try {
            PreparedStatement st1 = con.prepareStatement(req, PreparedStatement.RETURN_GENERATED_KEYS);

            st1.setString(1, c.getNom_categorie());
            st1.setInt(2, 0);

            st1.executeUpdate();

            ResultSet result = st1.getGeneratedKeys();
            int id = 0;
            while (result.next()) {
                id = result.getInt(1);
                c.setId_categorie(result.getInt(1));
                System.out.println("id ajout" + id);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    
    }
    

