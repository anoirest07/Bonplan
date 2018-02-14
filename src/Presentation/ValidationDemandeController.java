/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entite.DemandeEtablissement;
import Services.ServiceDemandeEtablissement;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

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
    private void Traiter(ActionEvent event) {
        
    }
    
}
