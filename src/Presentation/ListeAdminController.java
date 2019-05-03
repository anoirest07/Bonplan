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
public class ListeAdminController implements Initializable {

    @FXML
    private Button bt;
    @FXML
    private TableView<Admin> table;
    @FXML
    private TableColumn<Admin, Integer> id;
    @FXML
    private TableColumn<Admin, String> nom;
    @FXML
    private TableColumn<Admin, String> prenom;
    @FXML
    private TableColumn<Admin, String> email;
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
    private void bt_onClicked(ActionEvent event) {
        ServiceAdmin sa = new ServiceAdmin();
        //ObservableList<Admin> oa = FXCollections.observableArrayList(sa.listAdmin());
        ObservableList<Admin> oa = FXCollections.observableArrayList(sa.listAdmin());
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        table.setItems(oa);
    }

    @FXML
    private void on_clickedAccueil(ActionEvent event) throws IOException {
        Parent test2_parent = FXMLLoader.load(getClass().getResource("accueilAdmin.fxml"));
         Scene test2_scene = new Scene(test2_parent);
        Stage test1_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        test1_stage.setScene(test2_scene);
        test1_stage.show();
    }
    
}
