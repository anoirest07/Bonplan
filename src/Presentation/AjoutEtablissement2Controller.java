/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class AjoutEtablissement2Controller implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField phprdt;
    @FXML
    private TextField prx;
    @FXML
    private Button Valider;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void valid(ActionEvent event) {
        
        System.out.println(" TEST VALID BOUTON");
        nom.setText("");
    }
    
}
