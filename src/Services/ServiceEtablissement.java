/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Config.Connexion;
import Entite.Etablissement;
import Entite.Etablissement.Budget;
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
public class ServiceEtablissement implements IServices.IServiceEtablissement{
     
    Connection con ;

    public ServiceEtablissement() {
        con = Connexion.getInstance().getCon();
    }
    
    
    

    @Override
    public void ajouterEtablissement(Etablissement e) {
        String sql = "INSERT INTO etablissement(nom_etablissement,adresse_etablissement,telephone_etablissement,horaire_travail,description_etablissement,photo_etablissement,photo_patente,code_postal"
                + ",position,budget,site_web,id_categorie) VAlUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement stm;
        try {
            stm = con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            stm.setString(1,e.getNom_etablissement());
            stm.setString(2,e.getAdresse_etablissement());
            stm.setInt(3,e.getTelephone_etablissement());
            stm.setString(4,e.getHoraire_travail());
            stm.setString(5,e.getDescription_etablissement());
            stm.setString(6,e.getPhoto_etablissement());
            stm.setString(7,e.getPhoto_patente());
            stm.setInt(8,e.getCode_postal());
            stm.setString(9,e.getPosition());
            stm.setString(10,e.getBudget().toString());
            stm.setString(11,e.getSite_web());
            ServiceCategorie sc = new ServiceCategorie();
            stm.setInt(12,sc.afficherCategorie(e.getCategorie().getId_categorie()).getId_categorie() );
//            stm.setInt(13,e.getUtilisateur().getId_user());
            
            int res = stm.executeUpdate();
            ResultSet reset = stm.getGeneratedKeys();
            if(res > 0){
                System.out.println("Add Done");
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
    }

    @Override
    public void modifierEtablissement(Etablissement e) {
        try
        {
        String update = "UPDATE etablissement SET  nom_etablissement = ? , adresse_etablissement = ? , telephone_etablissement = ? , horaire_travail = ? , "
                + "description_etablissement = ? , photo_etablissement = ? ,photo_patente = ? , code_postal = ? , position = ? , budget = ? ,site_web = ? , id_categorie = ? , id = ?  "
                + "WHERE id_etablissement = ?";
        PreparedStatement statement2 = con.prepareStatement(update);
        
        statement2.setString(1, e.getNom_etablissement());
        statement2.setString(2, e.getAdresse_etablissement());      
        statement2.setInt(3, e.getTelephone_etablissement());
        statement2.setString(4, e.getHoraire_travail());
        statement2.setString(5, e.getDescription_etablissement());
        statement2.setString(6, e.getPhoto_etablissement());
        statement2.setString(7, e.getPhoto_patente());
        statement2.setInt(8, e.getCode_postal());
        statement2.setString(9, e.getPosition());
        statement2.setString(10, e.getBudget().toString());
        statement2.setString(11,e.getSite_web());
        statement2.setInt(12, e.getCategorie().getId_categorie());
      //  statement2.setInt(13, e.getUtilisateur().getId_user());
        statement2.setInt(14, e.getId_etablissement());
        
        
        statement2.executeUpdate();
        System.out.println(""+e.getNom_etablissement()+" modifiee !!!");
        
        }
        catch(SQLException ex)
                {
                    System.out.println(ex.getMessage());
                    System.err.println(""+e.getNom_etablissement()+" non modifiee");
                }
    }

    @Override
    public void supprimerEtablissement(Etablissement e) {
        try 
        {
        String delete = "DELETE FROM etablissement WHERE id_etablissement= ? ";
        PreparedStatement st2 = con.prepareStatement(delete);
        st2.setInt(1,e.getId_etablissement());
        st2.executeUpdate();
        System.out.println(""+e.getNom_etablissement()+" supprimee !!!");
        
        }
        catch (SQLException ex)
        {

                    System.err.println("SQLException: "+ex.getMessage());
                           } //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Etablissement chercherEtablissement(int id) {
         Etablissement e = new Etablissement();
        try
        {
        String select = "SELECT * FROM etablissement WHERE id_etablissement = '"+id+"' ";
        Statement statement1 = con.createStatement();
        ResultSet result = statement1.executeQuery(select);
       
        while (result.next()) 
        { 
            e.setNom_etablissement(result.getString("nom_etablissment"));
            e.setAdresse_etablissement(result.getString("adresse_etablissment"));  
            e.setTelephone_etablissement(result.getInt("telephone_etablissment"));
            e.setHoraire_travail(result.getString("horaire_travail"));
            e.setDescription_etablissement(result.getString("description_etablissment"));
            e.setPhoto_etablissement(result.getString("photo_etablissment"));
            e.setPhoto_patente(result.getString("photo_patente"));
            e.setCode_postal(result.getInt("code_postal"));
            e.setPosition(result.getString("position"));
            e.setSite_web(result.getString("site_web"));
            e.setBudget(Etablissement.Budget.valueOf(result.getString("budget")));
            e.getCategorie().setId_categorie(result.getInt("id_categorie"));
      //      e.getUtilisateur().setId_user(result.getInt("id"));
            }
        }
        catch (SQLException ex)
                {
                    System.err.println("SQLException: "+ex.getMessage());
                    System.err.println("SQLSTATE: "+ex.getSQLState());
                    System.err.println("VnedorError: "+ex.getErrorCode());
                }
        return e; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Etablissement> listEtablissement() {
        List<Etablissement> le=new ArrayList<>();
        try 
        {
        String select = "SELECT  * FROM etablissement ;"; 
                 
        Statement statement1 = con.createStatement();
        
        ResultSet result = statement1.executeQuery(select);
        
        while (result.next()) 
        {
            Etablissement e = new Etablissement();
            e.setId_etablissement(result.getInt("Id_etablissment"));
            e.setNom_etablissement(result.getString("nom_etablissment"));
            e.setAdresse_etablissement(result.getString("adresse_etablissment"));  
            e.setTelephone_etablissement(result.getInt("telephone_etablissment"));
            e.setHoraire_travail(result.getString("horaire_travail"));
            e.setDescription_etablissement(result.getString("description_etablissment"));
            e.setPhoto_etablissement(result.getString("photo_etablissment"));
            e.setPhoto_patente(result.getString("photo_patente"));
            e.setCode_postal(result.getInt("code_postal"));
            e.setPosition(result.getString("position"));
            e.setBudget(Etablissement.Budget.valueOf(result.getString("budget")));
            e.setSite_web(result.getString("site_web"));
            e.getCategorie().setId_categorie(result.getInt("id_categorie"));
        //    e.getUtilisateur().setId_user(result.getInt("id"));
            
            
            le.add(e);

        } 
    }   
        catch (SQLException ex)
                {
                    System.err.println("SQLException: "+ex.getMessage());
                    System.err.println("SQLSTATE: "+ex.getSQLState());
                    System.err.println("VnedorError: "+ex.getErrorCode());
                }
        return le;
} //To change body of generated methods, choose Tools | Templates.
 public List<String> nouri() {
        List<String> le=new ArrayList<>();
        try 
        {
        String select = "SELECT  nom_etablissement FROM etablissement ;"; 
                 
        Statement statement1 = con.createStatement();
        
        ResultSet result = statement1.executeQuery(select);
        
        while (result.next()) 
        {
            Etablissement e = new Etablissement();
            
           e.setNom_etablissement(result.getString("nom_etablissement"));
           
            le.add(e.getNom_etablissement());
    }}catch (SQLException ex)
                {
                    System.err.println("SQLException: "+ex.getMessage());
                    System.err.println("SQLSTATE: "+ex.getSQLState());
                    System.err.println("VnedorError: "+ex.getErrorCode());
                }
        return le;}
 
    public Etablissement ChercherEtablissement(int id) {
         Etablissement e = new Etablissement();
        try
        {
        String select = "SELECT * FROM etablissement WHERE id_etablissement = "+id+" ";
        Statement statement1 = con.createStatement();
        ResultSet result = statement1.executeQuery(select);
       
        while (result.next()) 
        { 
            e.setNom_etablissement(result.getString("nom_etablissement"));
            e.setAdresse_etablissement(result.getString("adresse_etablissement"));  
            e.setTelephone_etablissement(result.getInt("telephone_etablissement"));
            e.setHoraire_travail(result.getString("horaire_travail"));
            e.setDescription_etablissement(result.getString("description_etablissement"));
            e.setPhoto_etablissement(result.getString("photo_etablissement"));
            e.setPhoto_patente(result.getString("photo_patente"));
            e.setCode_postal(result.getInt("code_postal"));
            e.setPosition(result.getString("position"));
            e.setSite_web(result.getString("site_web"));
            e.setBudget(Budget.valueOf(result.getString("budget")));
            e.getCategorie().setId_categorie(result.getInt("id_categorie"));
//            e.getUtilisateur().setId_user(result.getInt("id"));
            }
        }
        catch (SQLException ex)
                {
                    System.err.println("SQLException: "+ex.getMessage());
                    System.err.println("SQLSTATE: "+ex.getSQLState());
                    System.err.println("VnedorError: "+ex.getErrorCode());
                }
        return e; //To change body of generated methods, choose Tools | Templates.
    }
   
   
    
    
}
