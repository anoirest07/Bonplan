/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entite.Evenement;
import Entite.Interesser;
import Entite.Utilisateur;
import Services.ServiceEvenement;
import Services.ServiceInteresser;
import Services.ServiceUtilisateur;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.DirectionsPane;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.InfoWindow;
import com.lynden.gmapsfx.javascript.object.InfoWindowOptions;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import com.lynden.gmapsfx.service.directions.DirectionsService;
import com.lynden.gmapsfx.service.geocoding.GeocodingService;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.controlsfx.control.textfield.CustomTextField;

/**
 * FXML Controller class
 *
 * @author amine
 */
public class EvenementclientController implements MapComponentInitializedListener{
 private GeocodingService geocodingService;
  private MarkerOptions markerOptions;
   private GoogleMap map;
   protected DirectionsPane directionsPane;
    
private ObservableList<Evenement> data ;
    @FXML
    private CustomTextField recherche;
    @FXML
    private DatePicker recherchepardate;
    @FXML
    private ListView<Evenement> listview;
    
    ServiceEvenement ps = new ServiceEvenement();
    Evenement p ;
    private Button interesse;
    private Button participant;
    //private ImageView localisation;
    private Button mapss;
  
    @FXML
    private GoogleMapView mapview;
    @FXML
    private Button interesser;
 /**
     * Initializes the controller class.
     */
    
  
    public void interaction() throws IOException, SQLException 
    {
       
        if (listview.getSelectionModel().getSelectedItem() != null) 
        {   
          
Evenement evene = listview.getSelectionModel().getSelectedItem();
        ServiceEvenement cs = new ServiceEvenement();
         evene.setId_evenement(evene.getId_evenement());
            ServiceInteresser abService = new ServiceInteresser();
       
            ServiceUtilisateur userService = new ServiceUtilisateur();
      Utilisateur u = new Utilisateur();
      u.setId_user(AuthentificationController.c.getId_user());
        Interesser ab = new Interesser(evene,u);

      System.out.println(abService.isinteresser(u,evene));
        if (abService.isinteresser(u, evene)) {
            System.out.println("interesser");
            abService.Deleteinteresser(ab);
            initialize();
       


        } else {
            System.out.println("n'est pas interesser");
            abService.Createinteresser(ab);
         evene.setId_evenement(evene.getId_evenement());     
            initialize();
        }       
            }

      

    }

   

    @FXML
    private void recherche(KeyEvent event) {
   List<Evenement> ls = ps.afficherevenementbynom(recherche.getText());
       data= FXCollections.observableArrayList();
        ls.stream().forEach((j)->{
            data.add(j);
        });
        listview.setItems(data);
        listview.setCellFactory(new Callback<ListView<Evenement>, ListCell<Evenement>>() {

                    @Override
                    public ListCell<Evenement> call(ListView<Evenement> param) {
                        return new ListViewCell();
                    }
                });  
    }

    @FXML
    private void recherchedate(ActionEvent event) {
        List<Evenement> ls = ps.afficherevenementbydate(recherchepardate.getValue());
       data = FXCollections.observableArrayList();
        ls.stream().forEach((j)->{
            data.add(j);
        });
        listview.setItems(data);
        listview.setCellFactory(new Callback<ListView<Evenement>, ListCell<Evenement>>() {

                    @Override
                    public ListCell<Evenement> call(ListView<Evenement> param) {
                        return new ListViewCell();
                    }
                }); }

    @FXML
    private void Interesse(ActionEvent event) throws SQLException 
    {}
   
    Evenement e = new Evenement();
    private ServiceEvenement se = new ServiceEvenement();
    
 @Override
    public void mapInitialized() 
    {
        LatLong pos;
        pos = new LatLong(se.afficherlangitude(e),se.afficherlatitude(e));
        
        
        
        //Set the initial properties of the map.
        MapOptions mapOptions = new MapOptions();
        
        mapOptions.center(new LatLong(se.afficherlangitude(e),se.afficherlatitude(e)))
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

      
    public void initialize() {
      
     mapview.addMapInializedListener(this);
          listview.setOnMouseClicked((MouseEvent event) -> {
            if (event.getClickCount() > 1) {
            try {
             
                interaction();
            } catch (IOException ex) {
                Logger.getLogger(EvenementpropController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(EvenementpropController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
});    
     List<Evenement> ls = ps.afficherevenementzzzz();
        data = FXCollections.observableArrayList();
        ls.stream().forEach((j)->{
            data.add(j);
        });
        listview.setItems(data);
        listview.setCellFactory(new Callback<ListView<Evenement>, ListCell<Evenement>>() {

                    @Override
                    public ListCell<Evenement> call(ListView<Evenement> param) {
                        return new ListViewCell();
                    }
                });
   
    }
    }

   



