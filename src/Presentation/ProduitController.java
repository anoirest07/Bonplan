/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entite.Produit;
import Services.ServiceProduit;
import bonplan.TestProduit;
import java.io.IOException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Yassine
 */
public class ProduitController {

    @FXML
    private Button add;
    @FXML
    private Button edit;
    @FXML
    private Button delete;


 
    private ObservableList<Produit> data;

    ServiceProduit service = new ServiceProduit();

    private TestProduit mainApp;
    @FXML
    private ImageView photo2;
    
    @FXML
    private TableView<Produit> table_produit;
    @FXML
 
    private TableColumn<Produit, ?> nom_produit;
    @FXML
    private TableColumn<Produit, ?> prix_produit;
    @FXML
    private Label label_nom;
//private Offre e = table_offre.getSelectionModel().getSelectedItem();;
    /**
     * Initializes the controller class.
     */
    public void initialize() {
        // TODO
        data = FXCollections.observableArrayList();
        List<Produit> ls = service.afficherProduit();
        ls.stream().forEach((Produit j) -> {
            data.add(j);
            System.out.println("    data PRODUIT >>>>>" + data.toString());
        });

       table_produit.setItems(data);
       nom_produit.setCellValueFactory(new PropertyValueFactory<>("nom_produit"));
        prix_produit.setCellValueFactory(new PropertyValueFactory<>("prix_produit"));
 


    }

    public void setMainApp(TestProduit mainApp) {
        this.mainApp = mainApp;
     
    }

    @FXML
    private void Add(ActionEvent event) throws IOException {

        Produit tempService = new Produit();
        boolean okClicked = showServiceAddDialog(tempService);
        if (okClicked) {
            service.ajouterProduit(tempService);
            initialize();

        } else {
            System.out.println("not done");
        }

    }

    @FXML
    private void Edit(ActionEvent event) {
        Produit selectedPerson = table_produit.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            boolean okClicked = showServiceEditDialog(selectedPerson);
            if (okClicked) {
                service.modifierProduit(selectedPerson);
                initialize();
            }

        } else {
            // Nothing selected.
          Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Offre Selected");
            alert.setContentText("Please select an Offer from the table.");

            alert.showAndWait();
        }
    }

    @FXML
    private void Delete(ActionEvent event) {
        int selectedIndex = table_produit.getSelectionModel().getSelectedIndex();
        Produit selectedPerson = table_produit.getSelectionModel().getSelectedItem();
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        if (selectedIndex >= 0) {
            table_produit.getItems().remove(selectedIndex);
            service.deleteProduit(selectedPerson);
            System.out.println("aa");
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



    public boolean showServiceEditDialog(Produit o) {
        try {
            FXMLLoader loader3 = new FXMLLoader();
            loader3.setLocation(TestProduit.class.getResource("../Presentation/ModifierProduitFXML.fxml"));
            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            AnchorPane page = (AnchorPane) loader3.load();
            dialogStage.setTitle("Modifier Produit");
            dialogStage.initModality(Modality.WINDOW_MODAL);
//            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            // Set the person into the controller.
            ModifierProduitFXMLController controller2 = loader3.getController();
            controller2.setDialogStage(dialogStage);
            controller2.setProduit(o);   
            controller2.afficher();
            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();
            // controller.isOkClicked();
            return controller2.isOkClicked();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

    public boolean showServiceAddDialog(Produit p) {
        try {
            FXMLLoader loader2 = new FXMLLoader();
            loader2.setLocation(TestProduit.class.getResource("../Presentation/AjouterProduitFXML.fxml"));
            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            AnchorPane page = (AnchorPane) loader2.load();
            dialogStage.setTitle("Ajouter Produit");
            dialogStage.initModality(Modality.WINDOW_MODAL);
//            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            // Set the person into the controller.
            AjouterProduitFXMLController controller1 = loader2.getController();
            controller1.setDialogStage(dialogStage);
//            controller.setOffre(o);            
            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();
            // controller.isOkClicked();
            return controller1.isOkClicked();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

    

    @FXML
    private void afficher(MouseEvent event) {
        
        
       Produit e = table_produit.getSelectionModel().getSelectedItem();

       label_nom.setText(e.getNom_produit());
        System.out.println("NOM PRODUIT >>>>>"+e.getNom_produit());
//          Image img = new Image(e.getPhoto());
//         photo2.setImage(img);
        // photo2.setViewport(e.getPhoto_publicite());
        //label1.setText(e.getPhoto_publicite());
        // System.out.println(e.getPhoto_publicite());
        // Image image = new Image(e.getPhoto_publicite());
        //  ImageView photo2 = new ImageView(image););
        javafx.scene.image.Image image = new javafx.scene.image.Image("http://localhost/image/" + e.getPhoto_produit(),350,350,true,true);
        //  ImageView photo2 = new ImageView(image);

        photo2.setImage(image);
        
                System.out.println("http://localhost/image/" + e.getPhoto_produit());

    }

    
}
