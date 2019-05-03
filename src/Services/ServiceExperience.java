/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Config.Connexion;
import Entite.Etablissement;
import Entite.Experience;
import IServices.IServiceExperience;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

/**
 *
 * @author amine
 */
public class ServiceExperience implements IServiceExperience{
    

    public Connection cnx;

    public ServiceExperience() {
        this.cnx = Connexion.getInstance().getCon();
    }
    
    
    @Override
 public void ajouterexperience(Experience e)    {
//     try {
//
//            String query=("INSERT INTO`experience`(description_experience,`preuve`,`id`,`id_etablissement`) VALUES ('"+e.getDescription_experience()+"','"+e.getPreuve()+"',"+e.getUtilisateur().getId_user()+","+e.getEtablissement().getId_etablissement()+");");
//            PreparedStatement st = Connexion.getInstance().getCon().prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);
//
//            
//         } catch (SQLException ex) {
//               System.out.println(ex.getMessage());    
//         }
        try {
            String query = "insert into `bonplan`.`experience` (`description_experience`,`preuve`,`date_exp`,`id`,`id_etablissement`) values (?,?,?,?,?)";
            PreparedStatement st = Connexion.getInstance().getCon().prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);
           
           
            st.setString(1,e.getDescription_experience());
            st.setString(2,e.getPreuve());
            st.setDate(3,e.getDate_exp());
              ServiceUtilisateur su = new ServiceUtilisateur();
              System.out.println("sqkjdsq"+e.getUtilisateur().getId_user());
            st.setInt(4,su.getUserById(e.getUtilisateur().getId_user()).getId_user());
            
            ServiceEtablissement se = new ServiceEtablissement();
            st.setInt(5,se.chercherEtablissement(e.getEtablissement().getId_etablissement()).getId_etablissement());
           
            st.executeUpdate();
             ResultSet result = st.getGeneratedKeys();
            int id = 0;
            while (result.next()) {
                id = result.getInt(1);
                e.setId_exp(result.getInt(1));
                System.out.println("id ajout exp"+id);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceOffre.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    }
        
   



public void modifierexperience(Experience e) 
    {
     
        {
        String update = "UPDATE experience SET description_experience= ? , preuve = ? WHERE id_exp = ? ";
        PreparedStatement statement2;
            try {
                statement2 = cnx.prepareStatement(update);
                
                
        statement2.setString(1,e.getDescription_experience() );  /*e.getDescription_experience()*/
        statement2.setString(2,e.getPreuve());
        statement2.setDate(3,e.getDate_exp());

        statement2.setInt(3, e.getId_exp());
     //   statement2.setInt(4, e.getUtilisateur().setId_user());
//        statement2.setInt(3,e.getEtablissement().getId_etablissement());
//        statement2.setInt(4,e.getUtilisateur().getId_user());
        statement2.executeUpdate();
        System.out.println("exp num"+e.getId_exp()+" modifiée !!");
            } catch (SQLException ex) {
                Logger.getLogger(ServiceExperience.class.getName()).log(Level.SEVERE, null, ex);
            
            }
        
      


}
    }


 public void supprimerexperience(Experience e) 
    {
        try 
        {
        String delete = "DELETE FROM experience WHERE id_exp = ? ";
        PreparedStatement st2 = cnx.prepareStatement(delete);
                    st2.setInt(1, e.getId_exp());

//        st2.setString(1,s.getEtablissement().getNom_etablissement());
//        st2.setInt(2,2);
        st2.executeUpdate();
       
            System.out.println(" exp num   "+ e.getId_exp()+"  supprimée");
        }
        catch (SQLException ex)
        {

                    System.err.println("SQLException: "+ex.getMessage());
                           }
    }
 
 
 
    @Override
 public Experience rechercherexperience(int id ,int id_etablissement) 
    {
        Experience h = new Experience ();
        try
        {
        String select = "SELECT * FROM experience WHERE id_exp = '"+id+"' and id_etablissement = '"+id_etablissement+"' ";
        Statement statement1 = cnx.createStatement();
        ResultSet result = statement1.executeQuery(select);
       
        while (result.next()) 
        {
            h.setId_exp(result.getInt("id_exp"));

            h.setDescription_experience(result.getString("description_experience"));
            h.setPreuve(result.getString("preuve"));
            h.setDate_exp(result.getDate("date_exp"));

 ServiceUtilisateur su = new ServiceUtilisateur();
           
          
            h.setUtilisateur(su.getUserById(result.getInt("id"))); 
            ServiceEtablissement se = new ServiceEtablissement();
            h.setEtablissement(se.chercherEtablissement(result.getInt("id_etablissement")));
       
        }
        }
        catch (SQLException e)
                {
                    System.err.println("SQLException: "+e.getMessage());
                    System.err.println("SQLSTATE: "+e.getSQLState());
                    System.err.println("VnedorError: "+e.getErrorCode());
                }
        return h;
    }

    @Override
    public List<Experience> afficherexperience() {

         List<Experience> le=new ArrayList<>();
        try 
        {
        Statement stm = cnx.createStatement();
            ResultSet result= 
                    stm.executeQuery("select * from `experience` ");
            while(result.next()){
            Experience e = new Experience();
            e.setId_exp(result.getInt("id_exp"));
            e.setDescription_experience(result.getString("description_experience"));
            e.setPreuve(result.getString("preuve")); 
            e.setDate_exp(result.getDate("date_exp"));
            
  ServiceUtilisateur su = new ServiceUtilisateur();
            e.setUtilisateur(su.getUserById(result.getInt("id")));
            
            ServiceEtablissement se = new ServiceEtablissement();
            e.setEtablissement(se.chercherEtablissement(result.getInt("id_etablissement")));            
          
            
            
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

  public List<Experience> afficherexperience(Etablissement etab) {

         List<Experience> le=new ArrayList<>();
        try 
        {
        Statement stm = cnx.createStatement();
            ResultSet result= 
                    stm.executeQuery("select * from `experience` where id_etablissement = '"+etab.getId_etablissement()+"' ");
            while(result.next()){
            Experience e = new Experience();
            e.setId_exp(result.getInt("id_exp"));
            e.setDescription_experience(result.getString("description_experience"));
            e.setPreuve(result.getString("preuve")); 
            e.setDate_exp(result.getDate("date_exp"));
            
  ServiceUtilisateur su = new ServiceUtilisateur();
            e.setUtilisateur(su.getUserById(result.getInt("id")));
            
            ServiceEtablissement se = new ServiceEtablissement();
            e.setEtablissement(se.chercherEtablissement(result.getInt("id_etablissement")));            
          
            
            
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
    
    @Override
    public List<Experience> afficherexperienceus(int id_user) {

         List<Experience> le=new ArrayList<>();
        try 
        {
        String select = "SELECT ex.* from experience ex, etablissement et where et.id = '"+id_user+"' and ex.id_etablissement=et.id_etablissement ";
        Statement statement1 = cnx.createStatement();
        ResultSet result = statement1.executeQuery(select);
            
            while(result.next()){
            Experience e = new Experience();
            e.setId_exp(result.getInt("id_exp"));
            e.setDescription_experience(result.getString("description_experience"));
            e.setPreuve(result.getString("preuve"));  
            e.setDate_exp(result.getDate("date_exp"));
 ServiceUtilisateur su = new ServiceUtilisateur();
           
          
            e.setUtilisateur(su.getUserById(result.getInt("id")));
            ServiceEtablissement se = new ServiceEtablissement();
            e.setEtablissement(se.chercherEtablissement(result.getInt("id_etablissement")));            
           
            
            
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


    
    
    @Override
    public Experience FindExperience(int id_exp) 
    {
        Experience ex= new  Experience();
        
        try
        {
        String select = "SELECT * FROM experience WHERE  id_exp = '"+id_exp+"' ";
        Statement statement1 = cnx.createStatement();
        ResultSet result = statement1.executeQuery(select);
       
        while (result.next()) 
        {
            ex.setId_exp(result.getInt("id_exp"));
            ex.setDescription_experience(result.getString("description_experience"));
            ex.setPreuve(result.getString("preuve"));
            ex.setDate_exp(result.getDate("date_exp"));
ServiceUtilisateur su = new ServiceUtilisateur();
           
          
            ex.setUtilisateur(su.getUserById(result.getInt("id")));     
            ServiceEtablissement se = new ServiceEtablissement();
            ex.setEtablissement(se.chercherEtablissement(result.getInt("id_etablissement")));
        
        }
        }
        catch (SQLException e)
                {
                    System.err.println("SQLException: "+e.getMessage());
                    System.err.println("SQLSTATE: "+e.getSQLState());
                    System.err.println("VnedorError: "+e.getErrorCode());
                }
        return ex;
    }

    @Override
    public Experience rechercherexperience(int id_user) {
    Experience ex= new  Experience();
        
        try
        {
        String select = "SELECT * FROM experience WHERE  id = '"+id_user+"' ";
        Statement statement1 = cnx.createStatement();
        ResultSet result = statement1.executeQuery(select);
       
        while (result.next()) 
        {
            ex.setId_exp(result.getInt("id_exp"));
            ex.setDescription_experience(result.getString("description_experience"));
            ex.setPreuve(result.getString("preuve"));
            ex.setDate_exp(result.getDate("date_exp"));
 ServiceUtilisateur su = new ServiceUtilisateur();
           
          
            ex.setUtilisateur(su.getUserById(result.getInt("id"))); 
            ServiceEtablissement se = new ServiceEtablissement();
            ex.setEtablissement(se.chercherEtablissement(result.getInt("id_etablissement")));
           
        }
        }
        catch (SQLException e)
                {
                    System.err.println("SQLException: "+e.getMessage());
                    System.err.println("SQLSTATE: "+e.getSQLState());
                    System.err.println("VnedorError: "+e.getErrorCode());
                }
        return ex;
    }


//    public ObservableList<PieChart.Data> nbExp() {
//        ArrayList<PieChart.Data> list = new ArrayList<PieChart.Data>();
//        try {
//            PreparedStatement st = cnx.prepareStatement("SELECT  count( id_exp)  from experience e , etablissement r where r.id_etablissement =e.id_etablissement AND r.id=2 GROUP by r.id_etablissement");
//            ResultSet rs = st.executeQuery();
//            while (rs.next()) {
//                list.add(new PieChart.Data(rs.getString(1), rs.getInt(1)));
//            }
//            ObservableList<PieChart.Data> observableList;
//            observableList = FXCollections.observableList(list);
////            System.out.println("ici" + observableList.size());
//for (PieChart.Data data : observableList) {System.out.println("test ::"+data);
//                
//            }
//            return observableList;
//
//        } catch (SQLException ex) {
//            Logger.getLogger(ServiceEtablissement.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return null;
//    }
    
             

     public ObservableList<PieChart.Data> moyEvalTotProp(int id_prop) {
        ArrayList<PieChart.Data> list = new ArrayList<PieChart.Data>();
        try {
            PreparedStatement st = cnx.prepareStatement("  SELECT AVG( note ) from evaluation ev, experience ex , etablissement et where et.id_etablissement =ex.id_etablissement AND et.id='"+id_prop+"' AND ev.id_exp=ex.id_exp");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new PieChart.Data(rs.getString(1),rs.getDouble(1)));
            }
            ObservableList<PieChart.Data> observableList;
            observableList = FXCollections.observableList(list);
//            System.out.println("ici" + observableList.size());
for (PieChart.Data data : observableList) {
    System.out.println("test ::"+data);
                
            }
            return observableList;

        } catch (SQLException ex) {
            Logger.getLogger(ServiceEtablissement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    

    }

   


