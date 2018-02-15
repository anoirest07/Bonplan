/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Moianoir;

import Entite.Etablissement;
import Entite.Experience;
import Services.ServiceExperience;
import java.net.URL;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author user
 */
public class ListeExperiencesController implements Initializable {
    Experience e;
    ObservableList<Experience> ls;
    @FXML
    private TableView<Experience> tableview;
    @FXML
    private TableColumn<Experience, String> photo;
    @FXML
    private TableColumn<Experience,String> NomEtab;
    @FXML
    private TableColumn<Experience, String> Comm;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       ServiceExperience SE = new ServiceExperience();
       Etablissement e = new Etablissement();
       
       ObservableList<Experience> ls = FXCollections.observableArrayList(SE.afficherexperience());
       
       
       photo.setCellValueFactory(new PropertyValueFactory<>("preuve"));
       NomEtab.setCellValueFactory(new PropertyValueFactory<>("id_etablissement"));
       Comm.setCellValueFactory(new PropertyValueFactory<>("description_experience"));
       tableview.setItems(ls);
        // TODO
    }    
    
}
