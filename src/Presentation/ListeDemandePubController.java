/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entite.Publicite;
import Services.ServicePublicite;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author user
 */
public class ListeDemandePubController  {

    @FXML
    private Button bt_menu;
    @FXML
    private VBox mainContainer;
    @FXML
    private Button valider;
    @FXML
    private Button remove;
    @FXML
    private ListView<Publicite> listePublicite;
    @FXML
    private ImageView photo3;
private ObservableList<Publicite> data ;
ServicePublicite service = new ServicePublicite();
    /**
     * Initializes the controller class.
     */
    
    public void initialize() {
         data = FXCollections.observableArrayList();
        List<Publicite> ls = service.listDemandesPublicites();
        ls.stream().forEach((j)->{
            data.add(j);
        });
        
        listePublicite.setItems(data);
        listePublicite.getSelectionModel().selectFirst();
        Publicite e=listePublicite.getSelectionModel().getSelectedItem();
        javafx.scene.image.Image image = new javafx.scene.image.Image("http://localhost/image/"+e.getPhoto_publicite());
        photo3.setImage(image);
       
        // TODO
    }    

    @FXML
    private void Edit(ActionEvent event) {
         Publicite selectedPerson = listePublicite.getSelectionModel().getSelectedItem();
          if ( selectedPerson != null) {
              service.updatepublicite(selectedPerson);
                initialize();
          }else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);           
            alert.setTitle("No Selection");
            alert.setHeaderText("No Publicite Selected");
            alert.setContentText("Please select a Publicite in the table.");

            alert.showAndWait();


        }
    }

    @FXML
    private void Delete(ActionEvent event) {
          int selectedIndex = listePublicite.getSelectionModel().getSelectedIndex();
         Publicite selectedPerson = listePublicite.getSelectionModel().getSelectedItem();
         Alert a = new Alert(Alert.AlertType.CONFIRMATION);
         if (selectedIndex >= 0) {
            listePublicite.getItems().remove(selectedIndex);
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

    @FXML
    private void afficher(MouseEvent event) {
         Publicite e=listePublicite.getSelectionModel().getSelectedItem();
       // photo2.setViewport(e.getPhoto_publicite());
       //label1.setText(e.getPhoto_publicite());
       // System.out.println(e.getPhoto_publicite());
      // Image image = new Image(e.getPhoto_publicite());
        System.out.println("http://localhost/image/"+e.getPhoto_publicite());
      //  ImageView photo2 = new ImageView(image););
       javafx.scene.image.Image image = new javafx.scene.image.Image("http://localhost/image/"+e.getPhoto_publicite());
      //  ImageView photo2 = new ImageView(image);
       
    photo3.setImage(image);
    }
    
}
