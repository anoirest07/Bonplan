/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entite.Utilisateur;
import Services.ServiceUtilisateur;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author malek
 */
public class SupprimerUtilisateurController implements Initializable {

    @FXML
    private Button butt;
    @FXML
    private TableView<Utilisateur> table;
    @FXML
    private TableColumn<Utilisateur, Integer> id;
    @FXML
    private TableColumn<Utilisateur, String> nom;
    @FXML
    private TableColumn<Utilisateur, String> prenom;
    @FXML
    private TableColumn<Utilisateur, String> role;
    @FXML
    private Button button;
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
    private void on_Clicked(ActionEvent event) {
        
          Utilisateur user = (Utilisateur) table.getSelectionModel().getSelectedItem();
        ServiceUtilisateur utilis = new ServiceUtilisateur();
        utilis.deleteUtilisateur(user.getId_user());
    }

    @FXML
    private void on_supprimer(ActionEvent event) {
        
        ServiceUtilisateur sa = new ServiceUtilisateur();
        //ObservableList<Admin> oa = FXCollections.observableArrayList(sa.listAdmin());
        ObservableList<Utilisateur> oa = FXCollections.observableArrayList(sa.listUtilisateur());
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        role.setCellValueFactory(new PropertyValueFactory<>("roles"));
        
        table.setItems(oa);
    }

    @FXML
    private void on_ClickedAccueil(ActionEvent event) throws IOException {
        
         Parent test2_parent = FXMLLoader.load(getClass().getResource("accueilAdmin.fxml"));
         Scene test2_scene = new Scene(test2_parent);
        Stage test1_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        test1_stage.setScene(test2_scene);
        test1_stage.show();
    }
    
}
