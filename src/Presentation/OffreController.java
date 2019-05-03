/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entite.Offre;
import Entite.OffreProduit;
import Entite.Produit;

import Services.ServiceOffre;
import Services.ServiceOffreProduit;
import Services.ServiceProduit;
import bonplan.TestOffre;
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
public class OffreController {

    @FXML
    private Button add;
    @FXML
    private Button edit;
    @FXML
    private Button delete;

    @FXML
    private TableColumn<?, ?> titre_offre;

    private ObservableList<Offre> data;

    ServiceOffre service = new ServiceOffre();
    Produit pp = new Produit();
    ServiceProduit serviceprod = new ServiceProduit();
    private TestOffre mainApp;
    @FXML
    private Label label_description;
    @FXML
    private ImageView photo2;
    @FXML
    private TableView table_offre;
    @FXML
    private TableColumn debut_offre;
    @FXML
    private TableColumn fin_offre;
    OffreProduit opp = new OffreProduit();
    ServiceOffreProduit serop = new ServiceOffreProduit();

    @FXML
    private Label produitsoff;

    /**
     * Initializes the controller class.
     */
    public void initialize() {
        // TODO
        data = FXCollections.observableArrayList();
        List<Offre> ls = service.afficheroffre();
        ls.stream().forEach((Offre j) -> {
            data.add(j);
            System.out.println("    data OFFRE >>>>>" + data.toString());
        });

        table_offre.setItems(data);
        titre_offre.setCellValueFactory(new PropertyValueFactory<>("titre_offre"));
        debut_offre.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
        fin_offre.setCellValueFactory(new PropertyValueFactory<>("date_fin"));

    }

    public void setMainApp(TestOffre mainApp) {
        this.mainApp = mainApp;
        // Add observable list data to the table
        // personTable.setItems(mainApp.getPersonData());
    }

    @FXML
    private void Add(ActionEvent event) throws IOException {

        Offre tempService = new Offre();
        boolean okClicked = showServiceAddDialog(tempService);
        if (okClicked) {
            service.ajouteroffre(tempService);
            initialize();

        } else {
            System.out.println("not done");
        }

    }

    @FXML
    private void Edit(ActionEvent event) {
        Offre selectedPerson = (Offre) table_offre.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            boolean okClicked = showServiceEditDialog(selectedPerson);
            if (okClicked) {
                service.modifieroffre(selectedPerson);
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
        int selectedIndex = table_offre.getSelectionModel().getSelectedIndex();
        Offre selectedPerson = (Offre) table_offre.getSelectionModel().getSelectedItem();
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        if (selectedIndex >= 0) {
            table_offre.getItems().remove(selectedIndex);
            service.supprimeroffre(selectedPerson);
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

    public boolean showServiceEditDialog(Offre o) {
        try {
            FXMLLoader loader3 = new FXMLLoader();
            loader3.setLocation(TestOffre.class.getResource("../Presentation/ModifierOffreFXML.fxml"));

            Stage dialogStage = new Stage();
            AnchorPane page = (AnchorPane) loader3.load();
            dialogStage.setTitle("Modifier Offre");
            dialogStage.initModality(Modality.WINDOW_MODAL);

            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            ModifierOffreFXMLController controller2 = loader3.getController();
            controller2.setDialogStage(dialogStage);
            controller2.setOffre(o);
            controller2.afficher();

            dialogStage.showAndWait();
            return controller2.isOkClicked();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

    public boolean showServiceAddDialog(Offre o) {
        try {
            FXMLLoader loader2 = new FXMLLoader();
            loader2.setLocation(TestOffre.class.getResource("../Presentation/AjouterOffreFXML.fxml"));
            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            AnchorPane page = (AnchorPane) loader2.load();
            dialogStage.setTitle("Ajouter Offre");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            AjouterOffreFXMLController controller1 = loader2.getController();
            controller1.setDialogStage(dialogStage);
            dialogStage.showAndWait();
            return controller1.isOkClicked();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

    @FXML
    private void afficher(MouseEvent event) {
        String ch = "";
        Offre e = (Offre) table_offre.getSelectionModel().getSelectedItem();

        List<OffreProduit> lsop = serop.recupOffreSelect(e.getId_offre());
        List<Produit> lsp = serviceprod.afficherProduit();

        System.out.println("NNNNNNNNNNNNNNNNNNNNNNNNN");
        for (int i = 0; i < lsp.size(); i++) {
            for (int j = 0; j < lsop.size(); j++) {
                if (lsp.get(i).getId_produit() == lsop.get(j).getId_produit()) {
                    ch += " ~ " + lsp.get(i).getNom_produit() + " ~ ";

                }
            }
        }
        System.out.println("cchh = " + ch);
        System.out.println("YYYYYYYYYYYYYYY");
        produitsoff.setText(ch);

        label_description.setWrapText(true);
        label_description.setText(e.getDescription());
        System.out.println("DESCRIPTION OFFRE >>>>>" + e.getDescription());

        javafx.scene.image.Image image = new javafx.scene.image.Image("http://localhost/image/" + e.getPhoto(), 350, 350, true, true);
        //  ImageView photo2 = new ImageView(image);

        photo2.setImage(image);

        System.out.println("http://localhost/image/" + e.getPhoto());

    }

}
