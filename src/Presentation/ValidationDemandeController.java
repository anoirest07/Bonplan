/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entite.DemandeEtablissement;
import Services.ServiceDemandeEtablissement;
import com.jfoenix.controls.JFXTextField;
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
public class ValidationDemandeController implements Initializable {

    
    @FXML
    private TableColumn<DemandeEtablissement, String> nomEtab;
    @FXML
    private TableColumn<DemandeEtablissement, String> photo;
    
    @FXML
    private Button traiter;
    @FXML
    private VBox mainContainer;
    @FXML
    private TableView<DemandeEtablissement> table_etablissement;
    @FXML
    Services.ServiceDemandeEtablissement service = new ServiceDemandeEtablissement();
    private ObservableList<DemandeEtablissement> data ;
     
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        data = FXCollections.observableArrayList();
        List<DemandeEtablissement> ls = service.listDemandeEtablissement();
        ls.stream().forEach((j)->{
            data.add(j);
        });
        
        table_etablissement.setItems(data);
        nomEtab.setCellValueFactory(new PropertyValueFactory<>("nom_etab_demande"));
        photo.setCellValueFactory(new PropertyValueFactory<>("photo_etab_demande"));
        // TODO
    }    

    @FXML
    private void Traiter(ActionEvent event) throws IOException {
     // Parent loader = new FXMLLoader(getClass().getResource("ValiderAjoutEtab.fxml"));
     DemandeEtablissement selectedPerson = table_etablissement.getSelectionModel().getSelectedItem();
     if ( selectedPerson != null) {
         FXMLLoader loader = new FXMLLoader(getClass().getResource(
               "ValiderAjoutEtab.fxml"));
         Parent root = (Parent) loader.load();
     // Parent root =FXMLLoader.load(getClass().getResource("ValiderAjoutEtab.fxml"));
      ValiderAjoutEtabController controller = loader.getController();
      controller.setEtab(selectedPerson);
      
      Scene scene = new Scene(root);
      Stage stage = new Stage();
      stage.setScene(scene);
      stage.showAndWait();  
       }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Etablissment Selected");
            alert.setContentText("Please select a Etablissment in the table.");

            alert.showAndWait();
     }
//      Parent root = loader.load();
//      filter.getScene().setRoot(root);
    }
    
}
