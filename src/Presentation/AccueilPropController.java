/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AccueilPropController implements Initializable {

    @FXML
    private Button bt_menu;
    @FXML
    private Button listeEtab;
    @FXML
    private Button listeEvenement;
    @FXML
    private Button listePublicite;
    @FXML
    private Button statistique;
    @FXML
    private AnchorPane anchor;
    @FXML
    private JFXButton listeOffre;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FilProp.fxml"));
            Parent root = (Parent) loader.load();
            FilPropController controller = loader.getController();
            anchor.getChildren().clear();
            //anchor.set().setRoot(root);
            anchor.getChildren().add(root);
            // TODO
        } catch (IOException ex) {
            Logger.getLogger(AccueilPropController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void OnactionEtab(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("etabprop.fxml"));
        Parent root = (Parent) loader.load();
        EtabpropController controller = loader.getController();
        anchor.getChildren().clear();
        //anchor.set().setRoot(root);
    anchor.getChildren().add(root);
        
    }

    @FXML
    private void OnactionOffre(ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("Offre.fxml"));
        Parent root = (Parent) loader.load();
        OffreController controller = loader.getController();
        anchor.getChildren().clear();
        //anchor.set().setRoot(root);
    anchor.getChildren().add(root);
    }

    @FXML
    private void OnactionEven(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Evenementprop.fxml"));
        Parent root = (Parent) loader.load();
        EvenementpropController controller = loader.getController();
        anchor.getChildren().clear();
        //anchor.set().setRoot(root);
    anchor.getChildren().add(root);
    
        
    }

    @FXML
    private void OnactionPub(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Publicite.fxml"));
        Parent root = (Parent) loader.load();
        PubliciteController controller = loader.getController();
        anchor.getChildren().clear();
        //anchor.set().setRoot(root);
    anchor.getChildren().add(root);
    
    }

    @FXML
    private void OnactionStat(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ListeStatique.fxml"));
        Parent root = (Parent) loader.load();
        ListeStatiqueController controller = loader.getController();
        anchor.getChildren().clear();
        //anchor.set().setRoot(root);
    anchor.getChildren().add(root);
    }

    @FXML
    private void deconnexion(MouseEvent event) throws IOException {
        Parent test2_parent = FXMLLoader.load(getClass().getResource("Authentification_1.fxml"));
         Scene test2_scene = new Scene(test2_parent);
        Stage test1_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        test1_stage.setScene(test2_scene);
        test1_stage.show();
    }

    @FXML
    private void onAccueil(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FilProp.fxml"));
        Parent root = (Parent) loader.load();
        FilPropController controller = loader.getController();
        anchor.getChildren().clear();
        //anchor.set().setRoot(root);
    anchor.getChildren().add(root);
    }
    
}
