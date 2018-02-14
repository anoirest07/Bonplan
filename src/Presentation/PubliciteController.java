/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entite.Publicite;
import Services.ServicePublicite;
import bonplan.TestPublicite;
import java.io.IOException;
import java.net.URL;
import java.util.List;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author user
 */
public class PubliciteController {

    @FXML
    private Button bt_menu;
    @FXML
    private VBox mainContainer;
    @FXML
    private TableView<Publicite> table_publicite;
    @FXML
    private TableColumn<Publicite, String> photo;
    @FXML
    private TableColumn<Publicite, String> details;
    @FXML
    private Label wcount;
    @FXML
    private Button add;
    @FXML
    private Button edit;
    @FXML
    private Button delete;
    
    private ObservableList<Publicite> data ;
    ServicePublicite service = new ServicePublicite();
    private TestPublicite mainApp;
    
    /**
     * Initializes the controller class.
     */
   
    public void initialize() {
        // TODO
        data = FXCollections.observableArrayList();
        List<Publicite> ls = service.afficherpublicite();
        ls.stream().forEach((j)->{
            data.add(j);
        });
        
        table_publicite.setItems(data);
        photo.setCellValueFactory(new PropertyValueFactory<>("photo_publicite"));
        details.setCellValueFactory(new PropertyValueFactory<>("description_publicite"));

    }    
 public void setMainApp(TestPublicite mainApp) {
        this.mainApp = mainApp;
        // Add observable list data to the table
       // personTable.setItems(mainApp.getPersonData());
    }
    @FXML
    private void Add(ActionEvent event) throws IOException {
//Parent homePage = FXMLLoader.load(getClass().getResource("AjouterPublicite.fxml"));
//
//        Scene homePage_scene = new Scene(homePage);
//
//        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//
//        app_stage.setScene(homePage_scene);
//
//        app_stage.show();
Publicite tempService = new Publicite();
        if(mainApp != null ){
            boolean okClicked = mainApp.showServiceEditDialog(tempService);
            if (okClicked) {
                service.ajouterpublicite(tempService);
                initialize();
        }
        }
        else{
            System.out.println("not done");
        };
        
    }
    

    @FXML
    private void Edit(ActionEvent event) {
        Publicite selectedPerson = table_publicite.getSelectionModel().getSelectedItem();
        if ( selectedPerson != null) {
            boolean okClicked = mainApp.showServiceEditDialog(selectedPerson);
            if (okClicked) {
                service.modifierpublicite(selectedPerson);
                initialize();
            }

        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Publicite Selected");
            alert.setContentText("Please select a Publicite in the table.");

            alert.showAndWait();


        }
    }

    @FXML
    private void Delete(ActionEvent event) {
         int selectedIndex = table_publicite.getSelectionModel().getSelectedIndex();
         Publicite selectedPerson = table_publicite.getSelectionModel().getSelectedItem();
         Alert a = new Alert(Alert.AlertType.CONFIRMATION);
         if (selectedIndex >= 0) {
            table_publicite.getItems().remove(selectedIndex);
            service.supprimerpublicite(selectedPerson);
        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
           // alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Publicite Selected");
            alert.setContentText("Please select a Publicite in the table.");
            alert.showAndWait();
        }
    }
    
}
