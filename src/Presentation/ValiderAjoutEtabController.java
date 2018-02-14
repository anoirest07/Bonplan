/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author user
 */
public class ValiderAjoutEtabController implements Initializable {

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
    private Button validation;
    @FXML
    private Button echec;
    @FXML
    private TextField categ;
    @FXML
    private TextField budget;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Ajouter(ActionEvent event) {
    }

    @FXML
    private void Annuler(ActionEvent event) {
    }
    
}
