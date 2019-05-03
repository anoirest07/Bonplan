/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entite.Admin;
import Services.ServiceAdmin;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author malek
 */
public class AjoutAdministrateurController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField login;
    @FXML
    private TextField mdp;
    @FXML
    private Button valider;
    @FXML
    private Button acc;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouterAdmin(ActionEvent event) throws IOException {
          Admin sess= new Admin();
        sess.setNom(nom.getText());
       sess.setPrenom(prenom.getText());
       sess.setUsername(login.getText());
       sess.setRoles("Administrateur");
       sess.setMot_de_passe(mdp.getText());
        
        ServiceAdmin sessionservice = new ServiceAdmin();
         sessionservice.ajouterAdmin(sess);
    }

    private void ajouter(ActionEvent event) throws IOException {
        
        Parent test2_parent = FXMLLoader.load(getClass().getResource("AjoutAdministrateur.fxml"));
         Scene test2_scene = new Scene(test2_parent);
        Stage test1_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        test1_stage.setScene(test2_scene);
        test1_stage.show();
    }

    private void supprimer(ActionEvent event) throws IOException {
        
        Parent test2_parent = FXMLLoader.load(getClass().getResource("SupprimerUtilisateur.fxml"));
         Scene test2_scene = new Scene(test2_parent);
        Stage test1_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        test1_stage.setScene(test2_scene);
        test1_stage.show();
    }

    private void consulter(ActionEvent event) throws IOException {
        
        Parent test2_parent = FXMLLoader.load(getClass().getResource("ListeAdmin.fxml"));
         Scene test2_scene = new Scene(test2_parent);
        Stage test1_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        test1_stage.setScene(test2_scene);
        test1_stage.show();
    }

    @FXML
    private void onclickedAcc(ActionEvent event) throws IOException {
        Parent test2_parent = FXMLLoader.load(getClass().getResource("accueilAdmin.fxml"));
         Scene test2_scene = new Scene(test2_parent);
        Stage test1_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        test1_stage.setScene(test2_scene);
        test1_stage.show();
    }

   
    
}
