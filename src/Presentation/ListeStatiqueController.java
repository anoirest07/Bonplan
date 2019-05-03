/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author user
 */
public class ListeStatiqueController implements Initializable {

    @FXML
    private AnchorPane anchor;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void pageStatPub(MouseEvent event) throws IOException {
         FXMLLoader loader =new FXMLLoader(getClass().getResource("StatistiquePub.fxml"));
         Parent root = (Parent) loader.load();
         Stage stage = new Stage();
         
         Scene scene = new Scene(root);
         stage.setScene(scene);
         stage.show();
        
    }

    @FXML
    private void pageStatExp(MouseEvent event) throws IOException {
         FXMLLoader loader =new FXMLLoader(getClass().getResource("FXMLStatistiques.fxml"));
         Parent root = (Parent) loader.load();
         Stage stage = new Stage();
         
         Scene scene = new Scene(root);
         stage.setScene(scene);
         stage.show();
         //++++++++++++//
    }
    
}
