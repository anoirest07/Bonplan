/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Config.Connexion;
import Entite.CriteresEvaluation;
import Entite.Etablissement;
import Entite.Experience;
import Entite.Favoris;
import Services.ServiceCritere;
import Services.ServiceEtablissement;
import Services.ServiceExperience;
import Services.ServiceFavoris;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author Nadia
 */
public class EtablissementClientController implements Initializable {

    @FXML
    private AnchorPane contenu;
    @FXML
    private AnchorPane infoetab;
    @FXML
    private Label label1;
    @FXML
    private Hyperlink href1;
    @FXML
    private Hyperlink href2;
    @FXML
    private Button btn1;
    @FXML
    private Label description;
    @FXML
    private Label Nom_etablissement;
    @FXML
    private Label nbr_exp;
    @FXML
    private Label horaire;
    @FXML
    private Label tel;
    @FXML
    private Label evaluation1;
    @FXML
    private Label evaluation2;
    @FXML
    private Label evaluation3;
    @FXML
    private Label evaluation4;
    @FXML
    private Label evaluation5;
    @FXML
    private Label adresse;
    @FXML
    private ImageView photoetab;
    @FXML
    private Rating note1;
    @FXML
    private Rating note2;
    @FXML
    private Rating note3;
    @FXML
    private Rating note4;
    @FXML
    private Rating note5;
private Etablissement e;
ServiceEtablissement setab=new ServiceEtablissement();
        ServiceCritere scrit= new ServiceCritere();

@FXML
    private Label budget;
    @FXML
    private Label categorie;
    @FXML
    private JFXButton supp;
    /**
     * Initializes the controller class.
     */
 public void setE(Etablissement e) {
        this.e = e;
    }

   public EtablissementClientController() {

        Connection con = Connexion.getInstance().getCon();
    }
    public void affich() throws SQLException{
        
       Nom_etablissement.setText(e.getNom_etablissement());
       adresse.setText(e.getAdresse_etablissement());
         javafx.scene.image.Image img = new javafx.scene.image.Image("http://localhost/image/"+e.getPhoto_etablissement(),350,350, true, true);
        
        photoetab.setImage(img);
   description.setText(e.getDescription_etablissement());
   horaire.setText(e.getHoraire_travail());
   
   budget.setText(e.getBudget().getName());
   label1.setText(e.getSite_web());
  tel.setText(String.valueOf( e.getTelephone_etablissement()));
  categorie.setText(e.getCategorie().getNom_categorie());
List<CriteresEvaluation> criteres = new ArrayList<>();
criteres =scrit.FindCritereEvalByCateg(e.getCategorie().getId_categorie());
        
for(CriteresEvaluation critere : criteres)
{
     evaluation1.setText(criteres.get(0).getNom_critere_evaluation());
  evaluation2.setText(criteres.get(1).getNom_critere_evaluation());
  evaluation3.setText(criteres.get(2).getNom_critere_evaluation());
   evaluation4.setText(criteres.get(3).getNom_critere_evaluation());
  evaluation5.setText(criteres.get(4).getNom_critere_evaluation());
    try {
         note1.setRating(setab.moyTotCritEtab(e.getId_etablissement()).get(0));
   note2.setRating(setab.moyTotCritEtab(e.getId_etablissement()).get(1));
    note3.setRating(setab.moyTotCritEtab(e.getId_etablissement()).get(2));
     note4.setRating(setab.moyTotCritEtab(e.getId_etablissement()).get(3));
      note5.setRating(setab.moyTotCritEtab(e.getId_etablissement()).get(4));
        
    } catch (Exception e) {
    
    }
 
   ServiceExperience sev= new ServiceExperience();
      List<Experience> le = new ArrayList<>();
     le= sev.afficherexperience(e);
 //System.out.println("-----"+setab.moyTotCritEtab(e.getId_etablissement()));
  //   note1.setRating();
    nbr_exp.setText( String.valueOf(le.size()));
}  
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        if(AuthentificationController.c.getRoles().equals("Administrateur"))
        {supp.setVisible(true);}
        System.out.println("++++++****"+AuthentificationController.c.getRoles());
    }    

    @FXML
    private void redirectionnnn(ActionEvent event) {
    }

    @FXML
    private void favoris(ActionEvent event) {
        ServiceFavoris fav= new ServiceFavoris();
        Favoris f= new Favoris();
        
        f.getEtablissement().setId_etablissement(e.getId_etablissement());
      // f.getEtablissement().setId_etablissement(e.getId_etablissement());
       f.getUtilisateur().setId_user(AuthentificationController.c.getId_user());
        fav.ajouterFavoris(f);
    }

    
    @FXML
    private void supp(ActionEvent event) {
        setab.supprimerEtablissement(e);
        
    }
    
}
