/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyEvent;
import org.controlsfx.control.textfield.CustomTextField;

/**
 * FXML Controller class
 *
 * @author amine
 */
import  Entite.Evenement;
import Entite.Experience;
import Services.ServiceEvenement;
import Services.ServiceExperience;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.InfoWindow;
import com.lynden.gmapsfx.javascript.object.InfoWindowOptions;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import java.io.File;
import java.io.FilenameFilter;
import java.util.List;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumnBuilder;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.ImageViewBuilder;
import javafx.util.Callback;
import static jdk.nashorn.internal.objects.NativeArray.map;
public class EvenementadminController implements  MapComponentInitializedListener {

    @FXML
    private CustomTextField recherchee;
    @FXML
    private DatePicker rechercherdate;
    @FXML
    private Button supprimereven;
    
 private MarkerOptions markerOptions;
   private GoogleMap map;
private ObservableList<Evenement> e ;
    @FXML
    private ListView<Evenement> tableview;
     ServiceEvenement ps = new ServiceEvenement();
    Evenement p ;
    @FXML
    private GoogleMapView mapview;
    /**
     * Initializes the controller class.
     */
    
    public void initialize() {
       mapview.addMapInializedListener(this);
        // TODO
    List<Evenement> ls = ps.afficherevenementzzzz();
        e = FXCollections.observableArrayList();
        ls.stream().forEach((j)->{
            e.add(j);
        });
        tableview.setItems(e);
        tableview.setCellFactory(new Callback<ListView<Evenement>, ListCell<Evenement>>() {

                    @Override
                    public ListCell<Evenement> call(ListView<Evenement> param) {
                        return new ListViewCell();
                    }
                });
    }    

   


    @FXML
    private void recherchebydate(ActionEvent event) {

    List<Evenement> ls = ps.afficherevenementbydate(rechercherdate.getValue());
       e = FXCollections.observableArrayList();
        ls.stream().forEach((j)->{
            e.add(j);
        });
        tableview.setItems(e);
        tableview.setCellFactory(new Callback<ListView<Evenement>, ListCell<Evenement>>() {

                    @Override
                    public ListCell<Evenement> call(ListView<Evenement> param) {
                        return new ListViewCell();
                    }
                });}
        

    @FXML
    private void supprimereven(ActionEvent event) {
        ServiceEvenement so = new  ServiceEvenement();

        int selectedIndex = tableview.getSelectionModel().getSelectedIndex();
         Evenement selectedPerson = tableview.getSelectionModel().getSelectedItem();
         Alert a = new Alert(Alert.AlertType.CONFIRMATION);
         
        if (selectedIndex >= 0) {
          //  serviceTable.getItems().remove(selectedIndex);
          so.supprimerevenement(selectedPerson);
            initialize();
        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Service Selected");
            alert.setContentText("Please select a experience in the table.");
            alert.showAndWait();
        }

    }
    

    @FXML
    private void recherchereven(KeyEvent event) {

 List<Evenement> ls = ps.afficherevenementbynom(recherchee.getText());
       e = FXCollections.observableArrayList();
        ls.stream().forEach((j)->{
            e.add(j);
        });
        tableview.setItems(e);
        tableview.setCellFactory(new Callback<ListView<Evenement>, ListCell<Evenement>>() {

                    @Override
                    public ListCell<Evenement> call(ListView<Evenement> param) {
                        return new ListViewCell();
                    }
                });
    }
   
    private ServiceEvenement se = new ServiceEvenement();
   

   
      Evenement ev=new Evenement();
    
    public void mapInitialized() {
       
        LatLong pos;
        pos = new LatLong(se.afficherlangitude(ev),se.afficherlatitude(ev));
        
        
        
        //Set the initial properties of the map.
        MapOptions mapOptions = new MapOptions();
        
        mapOptions.center(new LatLong(se.afficherlangitude(ev),se.afficherlatitude(ev)))
                .mapType(MapTypeIdEnum.ROADMAP)
                .overviewMapControl(true)
                .panControl(true)
                .rotateControl(true)
                .scaleControl(true)
                .streetViewControl(true)
                .zoomControl(true)
                .zoom(20);
                   
        map = mapview.createMap(mapOptions);

        //Add markers to the map
        MarkerOptions markerOptions1 = new MarkerOptions();
        markerOptions1.position(pos);
        
        
        
        Marker posMarker = new Marker(markerOptions1);
        
        
        map.addMarker( posMarker );
        
        
       InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
        infoWindowOptions.content("<h2>"+"welcom"+"</h2>" );

       InfoWindow fredWilkeInfoWindow = new InfoWindow(infoWindowOptions);
        fredWilkeInfoWindow.open(map, posMarker);
    }

   

   
    }
    
        
