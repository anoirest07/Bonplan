/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entite.Produit;
import Services.ServiceProduit;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Yassine
 */
public class AfficherProduitClientFXMLController implements Initializable {

    private ObservableList<Produit> data;

    ServiceProduit service = new ServiceProduit();
    @FXML
    private Label label_nom;
    @FXML
    private TableView<Produit> table_produit;
    @FXML
    private TableColumn<Produit, ?> nom_produit;
    @FXML
    private TableColumn<Produit, ?> prix_produit;
    @FXML
    private ImageView photo2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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

    @FXML
    private void afficher(MouseEvent event) {
        Produit e = table_produit.getSelectionModel().getSelectedItem();

        label_nom.setText(e.getNom_produit());
        System.out.println("NOM PRODUIT >>>>>" + e.getNom_produit());

        javafx.scene.image.Image image = new javafx.scene.image.Image("http://localhost/image/" + e.getPhoto_produit(), 350, 350, true, true);

        photo2.setImage(image);

        System.out.println("http://localhost/image/" + e.getPhoto_produit());

    }

}
