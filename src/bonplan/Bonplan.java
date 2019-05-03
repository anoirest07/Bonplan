/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bonplan;

import Config.Connexion;
import Entite.Admin;
import Entite.Categorie;
import Entite.Client;
import Entite.Commentaire;
import Entite.CriteresEvaluation;
import Entite.Etablissement;
import Entite.Evenement;
import Entite.Experience;
import Entite.Offre;
import Entite.Publicite;
import Entite.Utilisateur;
import Services.ServiceCritere;
import Services.ServiceEvaluation;
import Services.ServiceEvenement;
import Services.ServiceExperience;
import Services.ServiceOffre;
import Services.ServicePublicite;

import Entite.Evaluation;
import Entite.Favoris;
import Entite.Produit;
import Entite.Test;
import Services.ServiceAdmin;
import Services.ServiceCategorie;
import Services.ServiceClient;
import Services.ServiceCommentaire;
import Services.ServiceEtablissement;
import Services.ServiceFavoris;
import Services.ServiceProduit;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



/**
 *
 * @author Yassine
 */
public class Bonplan {

  

  /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        Connexion.getInstance();
//        Services.ServiceEtablissement sr = new ServiceEtablissement();
//       System.out.println(sr.listEtablissementParProp(3));
//        // --------------Anoir-------------- //
//
// ServiceCategorie SC= new ServiceCategorie();
////        Categorie c = new Categorie();
//       SC.ajouterCategorie(c);
//        c.setNom_categorie("lounge");
//        c.setId_categorie(1);
////      SC.modifierCategorie(c);
////        c.setId_categorie(2);
//        SC.supprimerCategorie(c);
//        for(Categorie cat:SC.listCategorie()){
//            System.out.println(cat.toString());
//        }
//        
//        ServiceEtablissement SE = new ServiceEtablissement();
//                Utilisateur u1 = new Utilisateur(1,"user1");
//
//        Etablissement et = new Etablissement("cafe bristol", "menchiya", 92332029 , "24h", "yezzi frere", "google it","photo patente", 4011, "google it", Etablissement.Budget.Moyen,"www.anoir.com",c,u1);
//        SE.ajouterEtablissement(et);
//        et.getCategorie().setId_categorie(5);
//        et.setId_etablissement(7);
//        SE.modifierEtablissement(et);
//       et.setId_etablissement(7);
//       SE.supprimerEtablissement(et);
//       System.out.println(SE.chercherEtablissement("cafe bristol"));
//       for(Etablissement etab:SE.listEtablissement()){
//            System.out.println(etab.toString());
//        }
       
      
// --------------Anoir-------------- //

// --------------Mariem-------------- //
  
        
              ServiceOffre so = new ServiceOffre();

     Offre o1 = new Offre();
     o1.setTitre_offre("titre22 offre");
       o1.setDescription("decsription de l'offre 1");
        o1.setDate_debut(Date.valueOf("2017-03-10"));
       o1.setDate_fin(Date.valueOf("2022-04-11"));
       o1.setNombre_dislike(15);
       o1.setNombre_like(78);
      o1.setPhoto("photo offre 1");
        so.ajouteroffre(o1);
       
//       for (Offre o:so.afficheroffre())
//       {
//           System.out.println(o.toString());
//       } 
   
        
//        so.modifieroffre(o1);
//            so.supprimeroffre(1);
        
//      ServicePublicite sp = new ServicePublicite();
//        System.out.println("+++++"+sp.afficherpubliciteClick("27337011_1983502348638795_1644983878581746683_n.jpg")); 
////
//        Publicite p1 = new Publicite();
//        p1.setDescription_publicite("jygfuf");
//        p1.setPhoto_publicite("jyfuyfuy");
//       // sp.ajouterpublicite(p1, 2);
//        
//                
//        for (Publicite p:sp.afficherpublicite())
//        {
//            System.out.println(p.toString());
//        }
     
     
     
//        
//              sp.modifierpublicite(p1);
//              sp.supprimerpublicite(p1);
              
           /*  ServiceEvenement se = new ServiceEvenement();

        Evenement e1 = new Evenement();
        e1.setDate_evenement(Date.valueOf("2017-03-10"));
        e1.setDescription_evenement("heyel");
        e1.setNbr_interesse(48);
        e1.setNbr_participant(80);
        e1.setNom_evenement("gala");
        e1.setPhoto_evenement("photoeve");
       // se.ajouterevenement(e1,2);
        
        for (Evenement e:se.afficherevenement())
        {
            System.out.println(e.toString());
        } */
           
//        
////            se.modifierevenement(e1);
//            se.supprimerevenement(e1);   
////            
//            
                
      /*         ServiceExperience sx = new ServiceExperience();
        
       Experience exp = new Experience();
       exp.setDescription_experience("kheeeeyba");
       exp.setId_exp(1);
       exp.setPreuve("yfiufiyt");
      // sx.ajouterexperience(exp, 2);
        
       
        
        for (Experience x:sx.afficherexperience())
        {
           System.out.println(x.toString());
        } */
      
      
      
      
        
//            sx.modifierexperience(x1);
//            sx.supprimerexperience(x1);
//    

// --------------Mariem-------------- //

// --------------Amine-------------- //

// ServiceExperience ex = new  ServiceExperience();
//        Experience e = new Experience("description", "preuve", 1, 1);
  // e.setId_etablissement(1);
   
    //   e.setId(1);
       
      // e.setDescription("amine");
//ex.ajouterexperience(e);

//ex.editexp(e);
       
       //ex.Deleteexp(e);
  //  System.out.println(ex.Findexp(1,1));
    
//        ServiceCritere cs =new  ServiceCritere();
//        CriteresEvaluation ce = new  CriteresEvaluation();
//    ce.setId_categorie(2);
//   
//    ce.setNom("qualiter");
//      //  cs.ajoutercritere(ce);
//        //System.out.println(  cs.Findcritere(1) );
//         
//         
//         
//        //for (criteresevaluation ca :cs.listcritereevaluation()){
//          //  System.out.println(ce.toString());}
//          ServiceEvaluation es= new  ServiceEvaluation();
//          Evaluation k = new Evaluation(5, 1, 5);
//       //es.ajouterevaluation(k);
//       k.setNote(4);
//      es.editevaluation(k);

// --------------Amine-------------- //




// --------------Malek-------------- //
//        ServiceProduit s1 =new ServiceProduit();
//        Produit p=new Produit();
//       
//        p.setNom_produit("mojito");
//        p.setPhoto_produit("jkyufytcytfyt");
//        p.setPrix_produit(11f);
//        
//       // System.out.println(s1.chercherProduit("grey couse"));
//           s1.ajouterProduit(p);
        
// --------------Malek-------------- //
//
//  ServiceClient s1= new ServiceClient();
// // s1.getClientByEmail("malek.zaaf@espr.tn");
//        
//       Client b=new Client();
//        
//        b.setDate_de_naissance(Date.valueOf("2017-02-7"));
//       
//        b.setRegion("hamamet");
//       // c.setId_user(1);
//       b.setEmail("mahdi.znai@espr");
//        b.setRoles("Client");
//       
//        b.setUsername("chicha");
//        b.setMot_de_passe("wal3a");
//        b.setSexe("homme");
//        
//        b.setNom("zneydi");
//        b.setPrenom("mahdi");
//        b.setVille("hamamet sud");
//      
//        b.setNum_tel(21356);
//        b.setDate_inscription(Date.valueOf("2020-03-1"));
        
      //  s1.addClient(b);
 
//  Client f = s1.getClientById(7);
 // System.out.println(f.toString());
 
 
 
 
 
        
     /* ServiceAdmin s2= new ServiceAdmin();
        Admin c = new Admin();
       c.setRoles("Administrateur");
       c.setEmail("kjvbj@fuuy");
        c.setUsername("yass");
        c.setMot_de_passe("aaaa");
        
        
        c.setNom("dhu");
        c.setPrenom("gargo");
 
 s2.ajouterAdmin(c); */
        
     //   for(Admin admin : s2.listAdmin()){
           //System.out.println(admin.toString());
       //}
 
 
     ServiceCommentaire sc = new ServiceCommentaire();
     Commentaire c = new Commentaire();
      //c.setCommentaire("je n'aime plus");
       // System.out.println(sc.Findcommentaire(2));
       
     //Commentaire c = sc.getCommentaireById(3);
    // System.out.println(c.toString());    
     for(Test comm : sc.listcommentaire(17)){
          System.out.println("++++"+comm.toString());
     }
      


    // ServiceFavoris sf = new ServiceFavoris();
    // Favoris f = new Favoris();
     //sf.ajouterFavoris(f, 2, 3);
     
    // for(Favoris fav : sf.listFavoris()){
       //   System.out.println(fav.toString());
      // }

       // Favoris f = sf.getFavorisById(3);
   // System.out.println(f.toString());


//ServiceEvenement se = new ServiceEvenement();
//////
//        Evenement e1 = new Evenement();
//for (Evenement e:se.afficherevenement1())
//       {
//           System.out.println(e.toString());
//       }
//
//    }
    

    }
}
