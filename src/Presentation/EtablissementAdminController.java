/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entite.Categorie;
import Entite.Etablissement;
import Services.ServiceEtablissement;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author user
 */
public class EtablissementAdminController implements Initializable {

    @FXML
    private Button Enregistrer;
    @FXML
    private Button annuler;
    @FXML
    private JFXTextField nom;
    @FXML
    private JFXTextField tel;
    @FXML
    private JFXTextField site;
    @FXML
    private JFXTextField travail;
    @FXML
    private JFXTextField budget;
    @FXML
    private JFXTextField code;
    @FXML
    private JFXTextArea description;
    @FXML
    private JFXTextField adr;
    @FXML
    private ImageView photopat;
    @FXML
    private ImageView photoetab;
    @FXML
    private JFXTextField categorie;
private Etablissement e;
ServiceEtablissement setab=new ServiceEtablissement();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
  public void setE(Etablissement e) {
        this.e = e;
    }


    public void affich(){
       nom.setText(e.getNom_etablissement());
       adr.setText(e.getAdresse_etablissement());
        javafx.scene.image.Image img = new javafx.scene.image.Image("http://localhost/image/"+e.getPhoto_etablissement(),350,350, true, true);
        
        photoetab.setImage(img);
   javafx.scene.image.Image img2 = new javafx.scene.image.Image("http://localhost/image/"+e.getPhoto_patente());
        photopat.setImage(img2);
   
   description.setText(e.getDescription_etablissement());
   travail.setText(e.getHoraire_travail());
   code.setText(String.valueOf(e.getCode_postal()));
   budget.setText(e.getBudget().getName());
   site.setText(e.getSite_web());
  tel.setText(String.valueOf( e.getTelephone_etablissement()));
  categorie.setText(e.getCategorie().getNom_categorie());

}
  
    @FXML
    private void valiider(ActionEvent event) {
        e.setEnabled(1);
        System.out.println(e);
    setab.accepterEtablissement(e);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
    
            alert.setTitle("Demande validée");
            alert.setHeaderText("Etablissement ajouté");
            
            alert.showAndWait();     
      Parent homePage;
                 try {
                  FXMLLoader loader = new FXMLLoader(getClass().getResource("DemandesEtablissementAdmin.fxml"));
                    homePage = loader.load();
                        
        Scene homePage_scene = new Scene(homePage);

        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        app_stage.setScene(homePage_scene);

        app_stage.show();
     
                        
                        } catch (IOException ex) {
                            Logger.getLogger(EtablissementAdminController.class.getName()).log(Level.SEVERE, null, ex);
                        }
    }

    @FXML
    private void rejeter(ActionEvent event) {
         setab.supprimerEtablissement(e);
                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    
            alert.setTitle("Demande supprimée");
            alert.setHeaderText("Cette demande a été rejetée");
            
            alert.showAndWait();     
        
        Parent homePage;
                        try {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("DemandesEtablissementAdmin.fxml"));
                            homePage = loader.load();
                        
        Scene homePage_scene = new Scene(homePage);

        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        app_stage.setScene(homePage_scene);

        app_stage.show();
     
                        
                        } catch (IOException ex) {
                            Logger.getLogger(EtablissementAdminController.class.getName()).log(Level.SEVERE, null, ex);
                        }
    }
    
    
}
