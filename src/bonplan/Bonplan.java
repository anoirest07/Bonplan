/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bonplan;

import Config.Connexion;
import Entite.Categorie;
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

import com.sun.org.apache.bcel.internal.generic.AALOAD;
import Entite.Evaluation;
import Entite.Produit;
import Services.ServiceCategorie;
import Services.ServiceEtablissement;
import Services.ServiceProduit;



/**
 *
 * @author Yassine
 */
public class Bonplan {

  

  /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Connexion.getInstance();
  
        // --------------Anoir-------------- //

// ServiceCategorie SC= new ServiceCategorie();
       Categorie c = new Categorie();
//       SC.ajouterCategorie(c);
//        c.setNom_categorie("lounge");
//        c.setId_categorie(1);
////      SC.modifierCategorie(c);
////        c.setId_categorie(2);
////        SC.supprimerCategorie(c);
//        for(Categorie cat:SC.listCategorie()){
//            System.out.println(cat.toString());
//        }
        
      ServiceEtablissement SE = new ServiceEtablissement();
        
        Etablissement et = new Etablissement("cafe bristol", "menchiya", 92332029 , "24h", "yezzi frere", "google it","photo patente", 4011, "google it", Etablissement.Budget.Moyen,"www.anoir.com",c);
       SE.ajouterEtablissement(et);
//        et.getCategorie().setId_categorie(5);
//        et.setId_etablissement(7);
//        SE.modifierEtablissement(et);
//       et.setId_etablissement(7);
      // SE.supprimerEtablissement(et);
//       System.out.println(SE.chercherEtablissement("cafe bristol"));
//       for(Etablissement etab:SE.listEtablissement()){
//            System.out.println(etab.toString());
//        }
       
      
// --------------Anoir-------------- //

// --------------Mariem-------------- //
  
        
//                ServiceOffre so = new ServiceOffre();
//
//        Offre o1 = new Offre();
//        o1.setDescription("decsription de l'offre 1");
//        o1.setDate_debut(new java.sql.Date(2011-1900,10,5));
//        o1.setDate_fin(new java.sql.Date(2012-1900,5,12));
//        o1.setPhoto("photo offre 1");
////        so.ajouteroffre(o1);
//        
//        for (Offre o:so.afficheroffre())
//        {
//            System.out.println(o.toString());
//        }
        
//        so.modifieroffre(o1);
//            so.supprimeroffre(1);
        
//        ServicePublicite sp = new ServicePublicite();
//
//        Publicite p1 = new Publicite();
//        p1.setDescription("decsription de pub 1");
//    
//        p1.setPhoto("photo pub 1");
////        sp.ajouterpublicite(p1);
//        
//        for (Publicite p:sp.afficherpublicite())
//        {
//            System.out.println(p.toString());
//        }
//        
//              sp.modifierpublicite(p1);
//              sp.supprimerpublicite(p1);
              
//               ServiceEvenement se = new ServiceEvenement();
//
//        Evenement e1 = new Evenement();
//        e1.setDescription("decsription de even 1");
//        e1.setDate_evenement(new java.sql.Date(2018-1900,2,2));
//        e1.setPhoto("photo even 1");
////        se.ajouterevenement(e1);
////        
//        for (Evenement e:se.afficherevenement())
//        {
//            System.out.println(e.toString());
//        }
//        
////            se.modifierevenement(e1);
//            se.supprimerevenement(e1);   
////            
//            
                
//               ServiceExperience sx = new ServiceExperience();
//        Utilisateur u1 = new Utilisateur(1,"user1");
//        Etablissement etab1 = new Etablissement(1, "etab55");
//        Experience x1 = new Experience();
//        x1.setDescription("decsription de exp 3");
//        x1.setPreuve("preuve exp3");
//        x1.setId_user(u1.getId_user());
//        x1.setId_etablissement(etab1.getId_etablissement());
//////        sx.ajouterexperience(x1);
//        
//        for (Experience x:sx.afficherexperience())
//        {
//            System.out.println(x.toString());
//        }
        
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

    }
    

    
}
