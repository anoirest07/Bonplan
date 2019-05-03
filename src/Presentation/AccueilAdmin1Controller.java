/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AccueilAdmin1Controller implements Initializable {

    @FXML
    private Button bt_menu;
    @FXML
    private Button listeEtab;
    @FXML
    private Button listeOfrre;
    @FXML
    private Button listeEvenement;
    @FXML
    private AnchorPane anchor;
    @FXML
    private Button filDactualite;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void OnactionEtablissement(ActionEvent event) throws IOException {
           FXMLLoader loader = new FXMLLoader(getClass().getResource("ListeDesEtablissements.fxml"));
        Parent root = (Parent) loader.load();
        ListeDesEtablissementsController controller = loader.getController();
        anchor.getChildren().clear();
        //anchor.set().setRoot(root);
    anchor.getChildren().add(root);
        
    }

    @FXML
    private void OnactionOff(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherOffreClientFXML.fxml"));
        Parent root = (Parent) loader.load();
       // EvenementclientController controller = loader.getController();
        anchor.getChildren().clear();
        //anchor.set().setRoot(root);
    anchor.getChildren().add(root);
    }

    @FXML
    private void OnactionEvenement(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("Evenementadmin.fxml"));
        Parent root = (Parent) loader.load();
        EvenementadminController controller = loader.getController();
        anchor.getChildren().clear();
        //anchor.set().setRoot(root);
    anchor.getChildren().add(root);
    }

    @FXML
    private void deconnex(MouseEvent event) throws IOException {
         Parent test2_parent = FXMLLoader.load(getClass().getResource("Authentification_1.fxml"));
         Scene test2_scene = new Scene(test2_parent);
        Stage test1_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        test1_stage.setScene(test2_scene);
        test1_stage.show();
    }

    @FXML
    private void DemandeEtablissement(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("DemandesEtablissementAdmin.fxml"));
        Parent root = (Parent) loader.load();
        DemandesEtablissementAdminController controller = loader.getController();
        anchor.getChildren().clear();
        //anchor.set().setRoot(root);
    anchor.getChildren().add(root);
    }

    @FXML
    private void demandeCategorie(ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterCategorieAdmin.fxml"));
        Parent root = (Parent) loader.load();
        AjouterCategorieAdminController controller = loader.getController();
        anchor.getChildren().clear();
        //anchor.set().setRoot(root);
    anchor.getChildren().add(root);
        
    }

    @FXML
    private void demandePublicite(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ListeDemandePub.fxml"));
        Parent root = (Parent) loader.load();
        ListeDemandePubController controller = loader.getController();
        anchor.getChildren().clear();
        //anchor.set().setRoot(root);
    anchor.getChildren().add(root);
    }

    @FXML
    private void OnactionActualite(ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("FilAdmin.fxml"));
        Parent root = (Parent) loader.load();
        //FilClientController controller = loader.getController();
        anchor.getChildren().clear();
        //anchor.set().setRoot(root);
    anchor.getChildren().add(root);
    }

    @FXML
    private void onActionAccueil(ActionEvent event) throws IOException {
         Parent test2_parent = FXMLLoader.load(getClass().getResource("AccueilAdmin1.fxml"));
         Scene test2_scene = new Scene(test2_parent);
        Stage test1_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        test1_stage.setScene(test2_scene);
        test1_stage.show();
    }
    
}
