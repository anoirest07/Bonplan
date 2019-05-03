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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author malek
 */
public class AccueilAdminController implements Initializable {

    @FXML
    private Button ajout;
    @FXML
    private Button suppr;
    @FXML
    private Button listeA;
    @FXML
    private ImageView backgroud;
    @FXML
    private Button deconn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Image  image  = new Image("http://127.0.0.1/img/im", true); 
        backgroud.setImage(image); 
    }    

    @FXML
    private void OnclickedAjouter(ActionEvent event) throws IOException {
         Parent test2_parent = FXMLLoader.load(getClass().getResource("AjoutAdministrateur.fxml"));
         Scene test2_scene = new Scene(test2_parent);
        Stage test1_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        test1_stage.setScene(test2_scene);
        test1_stage.show();
    }

    @FXML
    private void OnclickedSupprimer(ActionEvent event) throws IOException {
        
         Parent test2_parent = FXMLLoader.load(getClass().getResource("SupprimerUtilisateur.fxml"));
         Scene test2_scene = new Scene(test2_parent);
        Stage test1_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        test1_stage.setScene(test2_scene);
        test1_stage.show();
    }

    @FXML
    private void OnclickedConsulter(ActionEvent event) throws IOException {
        Parent test2_parent = FXMLLoader.load(getClass().getResource("ListeAdmin.fxml"));
         Scene test2_scene = new Scene(test2_parent);
        Stage test1_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        test1_stage.setScene(test2_scene);
        test1_stage.show();
    }

    @FXML
    private void onclickedDeocnn(ActionEvent event) throws IOException {
         Parent test2_parent = FXMLLoader.load(getClass().getResource("LoginAdmin.fxml"));
         Scene test2_scene = new Scene(test2_parent);
        Stage test1_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        test1_stage.setScene(test2_scene);
        test1_stage.show();
        
    }
    
}
