/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class AjoutEtablissementController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField tel;
    @FXML
    private TextField site;
    @FXML
    private TextField horraire;
    @FXML
    private TextField phetab;
    @FXML
    private TextField phpat;
    @FXML
    private TextField adr;
    @FXML
    private TextField cpos;
    @FXML
    private TextArea desc;
    @FXML
    private TextField map;
    @FXML
    private Button Suivant;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void suivant(ActionEvent event) throws IOException {
        
        System.out.println("TEST BOUTON");
        Parent test2_parent = FXMLLoader.load(getClass().getResource("ajoutEtablissement2.fxml"));
        Scene test2_scene = new Scene(test2_parent);
        Stage test1_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        test1_stage.setScene(test2_scene);
        test1_stage.show();
    }
    
}
