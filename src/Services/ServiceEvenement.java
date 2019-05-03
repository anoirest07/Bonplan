/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Config.Connexion;
import Entite.Evenement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import IServices.IServiceEvenement;
import java.sql.Connection;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 *
 * @author admin
 */
public class ServiceEvenement implements IServiceEvenement{
public Connection cnx;

    public ServiceEvenement() {
        this.cnx = Connexion.getInstance().getCon();
    }   
    
    public List<Evenement> afficherevenement(int id) 
    {
        List<Evenement> evs =new ArrayList<>();
        try {
            Statement stm = Connexion.getInstance().getCon().createStatement();
            ResultSet rest= 
                    stm.executeQuery("select * from evenement e ,etablissement et where et.id="+id+" and e.id_etablissement=et.id_etablissement ");
            while(rest.next()){
                Evenement ev = new Evenement();
                ev.setId_evenement(rest.getInt("id_evenement"));
                ev.setDescription(rest.getString("description_evenement"));
               Timestamp input =rest.getTimestamp("date_evenement");   
         LocalDate date = input.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
         LocalDateTime newD = rest.getTimestamp("date_evenement").toLocalDateTime();
         java.util.Date ddd = new java.util.Date();
         ev.setDate_evenement(date);
         
                ev.setPhoto(rest.getString("photo_evenement"));
                ev.getEtab().setId_etablissement(rest.getInt("id_etablissement"));
                ev.setNom_evenement(rest.getString("nom_evenement"));
                ev.setNombre_interesse(rest.getInt("Nombre_interesse"));
                ev.setNombre_participant(rest.getInt("Nombre_participant"));
                
                evs.add(ev);
                
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }
         return evs;
    }
     public List<Evenement> afficherevenement1() 
    {
        List<Evenement> evs =new ArrayList<>();
        try {
            Statement stm = Connexion.getInstance().getCon().createStatement();
            ResultSet rest= 
                    stm.executeQuery("select * from evenement ");
            while(rest.next()){
                Evenement ev = new Evenement();
               // ev.setId_evenement(rest.getInt("id_evenement"));
                ev.setDescription(rest.getString("description_evenement"));
               Timestamp input =rest.getTimestamp("date_evenement");   
         LocalDate date = input.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
         LocalDateTime newD = rest.getTimestamp("date_evenement").toLocalDateTime();
         java.util.Date ddd = new java.util.Date();
         ev.setDate_evenement(date);
         
                ev.setPhoto(rest.getString("photo_evenement"));
                ev.getEtab().setId_etablissement(rest.getInt("id_etablissement"));
                ev.setNom_evenement(rest.getString("nom_evenement"));
                ev.setNombre_interesse(rest.getInt("Nombre_interesse"));
                ev.setNombre_participant(rest.getInt("Nombre_participant"));
                
                evs.add(ev);
                
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }
         return evs;
    }
    public List<Evenement> afficherevnementbyid(int id) {
    
    {
                List<Evenement> evenements =new ArrayList<>();
        try {
            Statement stm = Connexion.getInstance().getCon().createStatement();
            ResultSet rest= 
                    stm.executeQuery("select * from `evenement` where id_etablissement="+id);
            while(rest.next()){
                Evenement eve = new Evenement();
                 eve.setId_evenement(rest.getInt("id_evenement"));
                eve.setDescription(rest.getString("description_evenement"));
                Timestamp input =rest.getTimestamp("date_evenement");   
         LocalDate date = input.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
         LocalDateTime newD = rest.getTimestamp("date_evenement").toLocalDateTime();
         java.util.Date ddd = new java.util.Date();
         eve.setDate_evenement(date);
         
                eve.setPhoto(rest.getString("photo_evenement"));
                eve.getEtab().setId_etablissement(rest.getInt("id_etablissement"));
                eve.setNom_evenement(rest.getString("nom_evenement"));
                evenements.add(eve);
               
 eve.setEtab(new ServiceEtablissement().chercherEtablissement(rest.getInt(8)));
                evenements.add(eve);
                
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(ServiceOffre.class.getName()).log(Level.SEVERE, null, ex);
        }
         return evenements;
    }

    }
    

     public List<Evenement> afficherevenementbynom(String nom) 
    {
        List<Evenement> evs =new ArrayList<>();
        try {
            Statement stm = Connexion.getInstance().getCon().createStatement();
            ResultSet rest= 
                    stm.executeQuery("select * from `evenement` where nom_evenement LIKE '%"+nom +"%' ;");
            while(rest.next()){
                Evenement ev = new Evenement();
                ev.setId_evenement(rest.getInt("id_evenement"));
                ev.setDescription(rest.getString("description_evenement"));
               Timestamp input =rest.getTimestamp("date_evenement");   
         LocalDate date = input.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
         LocalDateTime newD = rest.getTimestamp("date_evenement").toLocalDateTime();
         java.util.Date ddd = new java.util.Date();
         ev.setDate_evenement(date);
         
                ev.setPhoto(rest.getString("photo_evenement"));
                ev.getEtab().setId_etablissement(rest.getInt("id_etablissement"));
                ev.setNom_evenement(rest.getString("nom_evenement"));
                evs.add(ev);
                
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }
         return evs;
    }
 public List<Evenement> afficherevenementbydate(LocalDate date) 
    {
        List<Evenement> evs =new ArrayList<>();
        try {
            Statement stm = Connexion.getInstance().getCon().createStatement();
            ResultSet rest= 
                    stm.executeQuery("select * from `evenement` where date_evenement='"+date+"';");
            while(rest.next()){
                Evenement ev = new Evenement();
                ev.setId_evenement(rest.getInt("id_evenement"));
                ev.setDescription(rest.getString("description_evenement"));
               Timestamp input =rest.getTimestamp("date_evenement");   
         LocalDate date1 = input.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
         LocalDateTime newD = rest.getTimestamp("date_evenement").toLocalDateTime();
         java.util.Date ddd = new java.util.Date();
         ev.setDate_evenement(date1);
         
                ev.setPhoto(rest.getString("photo_evenement"));
                ev.getEtab().setId_etablissement(rest.getInt("id_etablissement"));
                ev.setNom_evenement(rest.getString("nom_evenement"));
                evs.add(ev);
                
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }
         return evs;
    }

    @Override
    public void modifierevenement(Evenement e) {

 String sql ="UPDATE evenement SET nom_evenement = '"+e.getNom_evenement()+"',description_evenement = '"
                    +e.getDescription()+"',date_evenement = '"+e.getDate_evenement()+"', photo_evenement = '"
                    +e.getPhoto()+"' WHERE id_evenement ="+ e.getId_evenement()+";";
        try 
        {
            Statement stl = cnx.createStatement();
            stl.executeUpdate(sql);
            System.out.println("Update Evenement done");
        } 
        catch (SQLException ex) 
        {
            System.err.println("SQLException: " + ex.getMessage());
            System.err.println("SQLState: " + ex.getSQLState());
            System.err.println("VendorError: " + ex.getErrorCode());
        }        }
    
    @Override
    public void supprimerevenement(Evenement e) {
 try { String delete = "DELETE FROM evenement WHERE id_evenement = ? ";
        PreparedStatement st2 = cnx.prepareStatement(delete);
        int id = e.getId_evenement();
        
        st2.setInt(1,id);
 


        st2.executeUpdate();
       

        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    
    public void ajouterevenement(Evenement e) {
try {
            String query = "insert into `evenement` (`description_evenement`,`date_evenement`,`photo_evenement`,`id_etablissement`,`nom_evenement`) values ('"+e.getDescription()+"','"+e.getDate_evenement()+"','"+e.getPhoto()+"',"+e.getEtab().getId_etablissement()+",'"+e.getNom_evenement()+"')";
            PreparedStatement st = Connexion.getInstance().getCon().prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);
           
           
            st.executeUpdate();
             ResultSet result = st.getGeneratedKeys();
            int id = 0;
            while (result.next()) {
                id = result.getInt(1);
                e.setId_evenement(result.getInt(1));
                System.out.println("id ajout even "+id);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
            
        }    
    }

    @Override
    public Evenement rechercherevenment(int id_etablissement) {
Evenement of= new  Evenement();
        
        try
        {
        String select = "SELECT * FROM evenement WHERE  id_etablissement = '"+id_etablissement+"' ";
        Statement statement1 = cnx.createStatement();
        ResultSet result = statement1.executeQuery(select);
       
        while (result.next()) 
        {Timestamp input =result.getTimestamp("date_evenement");   
         LocalDate date = input.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
         LocalDateTime newD = result.getTimestamp("date_evenement").toLocalDateTime();
         java.util.Date ddd = new java.util.Date();
         of.setDate_evenement(date);
            
            of.setDescription(result.getString("description_evenement"));
            of.setId_evenement(result.getInt("id_evenement"));
                                of.setEtab(new ServiceEtablissement().chercherEtablissement(result.getInt(8)));

        }
        }
        catch (SQLException e)
                {
                    System.err.println("SQLException: "+e.getMessage());
                    System.err.println("SQLSTATE: "+e.getSQLState());
                    System.err.println("VnedorError: "+e.getErrorCode());
                }
        return of;
    }

   public double afficherlangitude(Evenement a) 
    {
     double position = 0;
        try {
            Statement stm = cnx.createStatement();
            ResultSet rest= 
                    stm.executeQuery("select `longitude` from evenement e , etablissement b where e.id_etablissement=b.id_etablissement  and  e.id_evenement = "+a.getId_evenement()+";  ");
            while(rest.next()){

                
              position = rest.getDouble("longitude");
             
               
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }
    
         return position;
    }
      public double afficherlatitude(Evenement a) 
    {
     double position = 0;
        try {
            Statement stm = cnx.createStatement();
            ResultSet rest= 
                    stm.executeQuery("select `latitude` from evenement e , etablissement b where e.id_etablissement=b.id_etablissement  and  e.id_evenement = "+a.getId_evenement()+"; ");
            while(rest.next()){

                
              position = rest.getDouble("latitude");
             
               
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }
    
         return position;
    }
     
  
 int participer ;
   public void participant(Evenement e) 

     {
        try 
    {   
        Statement stm = cnx.createStatement();
    
            ResultSet rest= 
                    stm.executeQuery("SELECT nombre_participant FROM evenement where id_evenement = "+e.getId_evenement()+" ;");
            while(rest.next()){

                
                
              this.participer = rest.getInt("nombre_participant");
                System.err.println(participer);}
             this.participer = participer +1 ;
             System.out.println(participer);
     
        String sql ="UPDATE evenement SET nombre_participant = "+participer +" where id_evenement = "+e.getId_evenement()+";";
       
//            System.out.println(e.getNombre_interesse());
            Statement stl = cnx.createStatement();
            stl.executeUpdate(sql);
//            System.out.println(e.getId_evenement());
            System.out.println(e.getNombre_participant());
//            System.out.println("Update Evenement done");

        } 
        catch (SQLException ex) 
        {
            System.err.println("SQLExceptioninteressant: " + ex.getMessage());
            System.err.println("SQLState: " + ex.getSQLState());
            System.err.println("VendorError: " + ex.getErrorCode());
        }        }

                
 
int interesser ;
 public void interesse(Evenement e) 
    {
        try 
    {   
        Statement stm = cnx.createStatement();
    
            ResultSet rest= 
                    stm.executeQuery("SELECT nombre_interesse FROM evenement where id_evenement = "+e.getId_evenement()+" ;");
            while(rest.next()){

                
                
              this.interesser = rest.getInt("nombre_interesse");
                System.err.println(interesser);}
             this.interesser = interesser +1 ;
             System.out.println(interesser);
     
        String sql ="UPDATE evenement SET nombre_interesse = "+interesser +" where id_evenement = "+e.getId_evenement()+";";
       
//            System.out.println(e.getNombre_interesse());
            Statement stl = cnx.createStatement();
            stl.executeUpdate(sql);
//            System.out.println(e.getId_evenement());
            System.out.println(e.getNombre_interesse());
//            System.out.println("Update Evenement done");

        } 
        catch (SQLException ex) 
        {
            System.err.println("SQLExceptioninteressant: " + ex.getMessage());
            System.err.println("SQLState: " + ex.getSQLState());
            System.err.println("VendorError: " + ex.getErrorCode());
        }        }
 
 public Evenement rechercherevenmentbyid(int id_evenement) {
Evenement of= new  Evenement();
        
        try
        {
        String select = "SELECT * FROM evenement WHERE  id_evenement = '"+id_evenement+"' ";
        Statement statement1 = cnx.createStatement();
        ResultSet result = statement1.executeQuery(select);
       
        while (result.next()) 
        {Timestamp input =result.getTimestamp("date_evenement");   
         LocalDate date = input.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
         LocalDateTime newD = result.getTimestamp("date_evenement").toLocalDateTime();
         java.util.Date ddd = new java.util.Date();
         of.setDate_evenement(date);
            
            of.setDescription(result.getString("description_evenement"));
            of.setId_evenement(result.getInt("id_evenement"));
                                of.setEtab(new ServiceEtablissement().chercherEtablissement(result.getInt(8)));

        }
        }
        catch (SQLException e)
                {
                    System.err.println("SQLException: "+e.getMessage());
                    System.err.println("SQLSTATE: "+e.getSQLState());
                    System.err.println("VnedorError: "+e.getErrorCode());
                }
        return of;
    }
 public List<Evenement> afficherevenementzzzz() 
    {
        List<Evenement> evs =new ArrayList<>();
        try {
            Statement stm = Connexion.getInstance().getCon().createStatement();
            ResultSet rest= 
                    stm.executeQuery("select * from evenement");
            while(rest.next()){
                Evenement ev = new Evenement();
                ev.setId_evenement(rest.getInt("id_evenement"));
                ev.setDescription(rest.getString("description_evenement"));
               Timestamp input =rest.getTimestamp("date_evenement");   
         LocalDate date = input.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
         LocalDateTime newD = rest.getTimestamp("date_evenement").toLocalDateTime();
         java.util.Date ddd = new java.util.Date();
         ev.setDate_evenement(date);
         
                ev.setPhoto(rest.getString("photo_evenement"));
                ev.getEtab().setId_etablissement(rest.getInt("id_etablissement"));
                ev.setNom_evenement(rest.getString("nom_evenement"));
                ev.setNombre_interesse(rest.getInt("Nombre_interesse"));
                ev.setNombre_participant(rest.getInt("Nombre_participant"));
                
                evs.add(ev);
                
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }
         return evs;
    }
 }

 