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
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.ImageViewBuilder;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author user
 */
public class PubliciteController {

    @FXML
    private Button bt_menu;
    @FXML
   // private VBox mainContainer;
   // private TableView<Publicite> table_publicite;
   // private TableColumn<Publicite, Image> photo;
   // private TableColumn<Publicite, String> details;
   // @FXML
    private Button add;
    @FXML
    private Button edit;
    @FXML
    private Button delete;
    
    private ObservableList<Publicite> data ;
    ServicePublicite service = new ServicePublicite();
    private TestPublicite mainApp;
    @FXML
    private ListView<Publicite> description;
    @FXML
    private ImageView photo2;
    @FXML
    private VBox mainContainer;
    
    /**
     * Initializes the controller class.
     */
   
   
    public void initialize() {
        data = FXCollections.observableArrayList();
        List<Publicite> ls = service.afficherpublicite();
        ls.stream().forEach((j)->{
            data.add(j);
        });
        
        description.setItems(data);
        description.getSelectionModel().selectFirst();
        Publicite e=description.getSelectionModel().getSelectedItem();
        javafx.scene.image.Image image = new javafx.scene.image.Image("http://localhost/image/"+e.getPhoto_publicite());
        photo2.setImage(image);
       

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
            boolean okClicked = showServiceEditDialog(tempService);
            if (okClicked) {
                service.ajouterpublicite(tempService);
                initialize();
               
        
        }
        else{
            System.out.println("not done");
        };
        
    }
    

    @FXML
    private void Edit(ActionEvent event) {
        Publicite selectedPerson = description.getSelectionModel().getSelectedItem();
        if ( selectedPerson != null) {
            boolean okClicked = showServiceEditDialog(selectedPerson);
            if (okClicked) {
                service.modifierpublicite(selectedPerson);
                initialize();
            }

        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
//            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Publicite Selected");
            alert.setContentText("Please select a Publicite in the table.");

            alert.showAndWait();


        }
    }

    @FXML
    private void Delete(ActionEvent event) {
         int selectedIndex = description.getSelectionModel().getSelectedIndex();
         Publicite selectedPerson = description.getSelectionModel().getSelectedItem();
         Alert a = new Alert(Alert.AlertType.CONFIRMATION);
         if (selectedIndex >= 0) {
            description.getItems().remove(selectedIndex);
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
         Publicite e=description.getSelectionModel().getSelectedItem();
       // photo2.setViewport(e.getPhoto_publicite());
       //label1.setText(e.getPhoto_publicite());
       // System.out.println(e.getPhoto_publicite());
      // Image image = new Image(e.getPhoto_publicite());
        System.out.println("http://localhost/image/"+e.getPhoto_publicite());
      //  ImageView photo2 = new ImageView(image););
       javafx.scene.image.Image image = new javafx.scene.image.Image("http://localhost/image/"+e.getPhoto_publicite());
      //  ImageView photo2 = new ImageView(image);
       
    photo2.setImage(image);
    }

    
    
        public boolean showServiceEditDialog(Publicite s) {
        try {
            FXMLLoader loader2 = new FXMLLoader();
            loader2.setLocation(getClass().getResource("../Presentation/AjouterPublicite.fxml"));
            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            AnchorPane page = (AnchorPane) loader2.load();
            dialogStage.setTitle("Edit Publicite");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            //dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            // Set the person into the controller.
            AjouterPubliciteController controller = loader2.getController();            
            controller.setDialogStage(dialogStage);
            controller.setPublicite(s);            
            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();
           // controller.isOkClicked();
            return controller.isOkClicked();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
