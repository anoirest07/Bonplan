/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entite.Categorie;

import Entite.Etablissement;
import Entite.budget;
import Services.ServiceCategorie;
import Services.ServiceEtablissement;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

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
import com.lynden.gmapsfx.service.directions.DirectionStatus;
import com.lynden.gmapsfx.service.directions.DirectionsResult;
import com.lynden.gmapsfx.service.directions.DirectionsServiceCallback;
import com.lynden.gmapsfx.service.geocoding.GeocodingService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;
import org.controlsfx.control.textfield.TextFields;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author Nadia
 */
public class ListeDesEtablissementsController implements Initializable , MapComponentInitializedListener, DirectionsServiceCallback {
 private GeocodingService geocodingService;
  private MarkerOptions markerOptions;
   private GoogleMap map;
    @FXML
    private AnchorPane container;
    @FXML
    private VBox vbox1;
    private TextField recherche;
    @FXML
    private ImageView photo9;
    @FXML
    private GoogleMapView mapView;
    List<String>les=new ArrayList<>();
    @FXML
    private MenuButton menuchoix;
    @FXML
    private JFXComboBox<String> typerecherche;
    @FXML
    private JFXTextField recheche;
private Services.ServiceEtablissement sr = new ServiceEtablissement();
private ServiceCategorie sc = new ServiceCategorie();
    @FXML
    private MenuItem affiche1;
    @FXML
    private MenuItem affiche2;

    /**
     * Initializes the controller class.
     */
//    public void afficherEtablissemnt(Etablissement e){
//    
//      
//
//      
//                    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
mapView.addMapInializedListener(this);
        //ObservableList<String> competence = FXCollections.observableArrayList();
  
// TODO
           
        List <Etablissement> etab= new ArrayList<>();
        etab=sr.listEtablissement();
        for (Etablissement e : etab){
                HBox h1= new HBox();
                VBox v1= new VBox();
                Label lab1= new Label();
                Label lab2= new Label();
                Label lab3= new Label();
                Label lab4= new Label();
                Label lab5= new Label();
  Rating r1 =new Rating();               
                lab1.setText(e.getNom_etablissement());
                lab2.setText(e.getAdresse_etablissement());
                lab3.setText(e.getHoraire_travail());
                lab4.setText("Budget:"+e.getBudget().toString());
               lab5.setText(" ");
                  r1.setStyle("-fx-padding: 0;" + 
                  " -fx-pref-width: 2 ;" + 
                  "               \n" +
"            -fx-pref-height: 2 ;" +
                  "  -fx-background-size: cover;"   );  
        r1.setRating(sr.EvaluationParEtab(e));
              v1.getChildren().addAll(lab1,lab2,lab3,lab4,r1,lab5);
                ImageView image= new ImageView();
   javafx.scene.image.Image img = new javafx.scene.image.Image("http://localhost/image/"+e.getPhoto_etablissement(),350,350, true, true);
        
        image.setImage(img);
                h1.getChildren().add(image);
            h1.getChildren().add(v1);
           h1.setSpacing(10);    
    h1.setStyle("-fx-padding: 10;" + 
                  "-fx-border-style: solid inside;" + 
                  "-fx-border-width: 1;" +
                  "-fx-border-insets: 5;" + 
                  "-fx-border-radius: 5;" + 
                  "-fx-border-color: black;"+
                  "-fx-width: 2;"     );
           
            vbox1.getChildren().addAll(h1);
    vbox1.setStyle("-fx-padding: 10;" + 
            "-fx-border-style: solid inside;" + 
                  "-fx-border-width: 2;" +
                  "-fx-border-insets: 5;" + 
                  "-fx-border-radius: 5;" + 
                  "-fx-border-color: black;"+
                  "-fx-width: 15;"     );
  h1.setOnMouseClicked(new EventHandler<MouseEvent>
  () {
                    @Override
                    public void handle(MouseEvent event) {
        Parent homePage;
                        try {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("EtablissementClient.fxml"));
                            homePage = loader.load();
                            EtablissementClientController c1 = new EtablissementClientController();
                            c1 = loader.getController();
                            e.setNom_etablissement(e.getNom_etablissement());
                             e.setNom_etablissement(e.getNom_etablissement());               
                            e.setAdresse_etablissement(e.getAdresse_etablissement());
                            e.setCode_postal(e.getCode_postal());
                            e.setDescription_etablissement(e.getDescription_etablissement());
                            e.setBudget(e.getBudget());
                            e.setHoraire_travail(e.getHoraire_travail());
                            e.setPhoto_etablissement(e.getPhoto_etablissement());
                            e.setPhoto_patente(e.getPhoto_patente());
                            e.setSite_web(e.getSite_web());
                            e.setTelephone_etablissement(e.getTelephone_etablissement());
                            e.setCategorie(e.getCategorie());
                         
                            c1.setE(e);
                          
                           c1.affich();
        Scene homePage_scene = new Scene(homePage);

        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        app_stage.setScene(homePage_scene);

        app_stage.show();
    } catch (IOException ex) {
                            Logger.getLogger(ListeDesCategoriesController.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (SQLException ex) {
                            Logger.getLogger(ListeDesEtablissementsController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
     les.add(e.getNom_etablissement());
    TextFields.bindAutoCompletion(recheche,les);  
    recheche.validate();
        }
           
    
    }    
      @Override
    public void mapInitialized() {
   geocodingService = new GeocodingService();
        MapOptions options = new MapOptions();

        options.center(new LatLong(34.3055732, 11.255412))
                .zoomControl(true)
                .zoom(6)
                .overviewMapControl(false)
                .mapType(MapTypeIdEnum.ROADMAP);
        GoogleMap map = mapView.createMap(options);
        List <Etablissement> etab= new ArrayList<>();
        etab=sr.listEtablissement();
        for (Etablissement e : etab){
            
        LatLong l = new LatLong(e.getLong(),e.getLat());
         Marker myMarker = null;
        markerOptions = new MarkerOptions();
        markerOptions.position(l)
                .title(e.getNom_etablissement())
                .visible(true);

        myMarker = new Marker(markerOptions);
        InfoWindowOptions infoOptions = new InfoWindowOptions();
        infoOptions.content(e.getNom_etablissement())
                .position(l);

        InfoWindow window = new InfoWindow(infoOptions);
        window.open(map, myMarker);
        map.addMarker(myMarker);
        
    }    
        
    }


    @FXML
    private void choix(MouseEvent event) {
     typerecherche.setVisible(true); 
    }

    @FXML
    private void  affiche1(ActionEvent event) {
          //vbox1.getChildren().clear();

        typerecherche.setPromptText("");
        typerecherche.setPromptText("Choisir catégorie");
        typerecherche.getItems().clear();
        
        List<Categorie> listecat = new ArrayList<Categorie>();
        listecat= sc.listCategorie();
        for (Categorie c: listecat)
        {
            typerecherche.getItems().add(c.getNom_categorie());
        }
        
    typerecherche.setOnAction((ActionEvent e)->{
          vbox1.getChildren().clear();

        ServiceCategorie service = new ServiceCategorie();
       Categorie choix = new Categorie();    
        choix= sc.afficherCategorieParNom(typerecherche.getSelectionModel().getSelectedItem());       
       
        
        System.out.println("****************"+choix);
     
       List<Etablissement> resultat = new ArrayList<>();
resultat=sr.listEtablissementParCategorie(choix); 

        MapOptions options = new MapOptions();

        options.center(new LatLong(34.3055732, 11.255412))
                .zoomControl(true)
                .zoom(6)
                .overviewMapControl(false)
                .mapType(MapTypeIdEnum.ROADMAP);
        GoogleMap map = mapView.createMap(options);
    for (Etablissement x : resultat){
     

        //  System.out.println("/////////////////////"+x);
    if(x != null){    
        
         Services.ServiceEtablissement sr = new ServiceEtablissement();
  
        LatLong l = new LatLong(x.getLong(),x.getLat());
         Marker myMarker = null;
        markerOptions = new MarkerOptions();
        markerOptions.position(l)
                .title(x.getNom_etablissement())
                .visible(true);

        myMarker = new Marker(markerOptions);
        InfoWindowOptions infoOptions = new InfoWindowOptions();
        infoOptions.content(x.getNom_etablissement())
                .position(l);

        InfoWindow window = new InfoWindow(infoOptions);
        window.open(map, myMarker);
        map.addMarker(myMarker);

           }     HBox h1= new HBox();
                VBox v1= new VBox();
                Label lab1= new Label();
                Label lab2= new Label();
                Label lab3= new Label();
                Label lab4= new Label();
                Label lab5= new Label();
  Rating r1 =new Rating();               
                lab1.setText(x.getNom_etablissement());
                lab2.setText(x.getAdresse_etablissement());
                lab3.setText(x.getHoraire_travail());
                lab4.setText("Budget:"+x.getBudget().toString());
               lab5.setText(" ");
        r1.setRating(sr.EvaluationParEtab(x));

               r1.setStyle("-fx-padding: 0;" + 
                  " -fx-pref-width: 2 ;" + 
                  "               \n" +
"            -fx-pref-height: 2 ;" +
                  "  -fx-background-size: cover;"   );  
        
              v1.getChildren().addAll(lab1,lab2,lab3,lab4,r1,lab5);
                ImageView image= new ImageView();
   javafx.scene.image.Image img = new javafx.scene.image.Image("http://localhost/image/"+x.getPhoto_etablissement(),350,350, true, true);
        
        image.setImage(img);
              h1.getChildren().add(image);
            h1.getChildren().add(v1);
           h1.setSpacing(10);    
    h1.setStyle("-fx-padding: 10;" + 
                  "-fx-border-style: solid inside;" + 
                  "-fx-border-width: 2;" +
                  "-fx-border-insets: 5;" + 
                  "-fx-border-radius: 5;" + 
                  "-fx-border-color: black;"+
                  "-fx-width: 2;"     );
     
    h1.setOnMouseClicked(new EventHandler<MouseEvent>
  () {
                    @Override
                    public void handle(MouseEvent event) {
        Parent homePage;
                        try {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("EtablissementClient.fxml"));
                            homePage = loader.load();
                            EtablissementClientController c1 = new EtablissementClientController();
                            c1 = loader.getController();
                            x.setNom_etablissement(x.getNom_etablissement());
                            x.setNom_etablissement(x.getNom_etablissement());               
                            x.setAdresse_etablissement(x.getAdresse_etablissement());
                            x.setCode_postal(x.getCode_postal());
                            x.setDescription_etablissement(x.getDescription_etablissement());
                            x.setBudget(x.getBudget());
                            x.setHoraire_travail(x.getHoraire_travail());
                            x.setPhoto_etablissement(x.getPhoto_etablissement());
                            x.setPhoto_patente(x.getPhoto_patente());
                            x.setSite_web(x.getSite_web());
                            x.setTelephone_etablissement(x.getTelephone_etablissement());
                            x.setCategorie(x.getCategorie());
                         
                            c1.setE(x);
                          
                           c1.affich();
        Scene homePage_scene = new Scene(homePage);

        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        app_stage.setScene(homePage_scene);

        app_stage.show();
    } catch (IOException ex) {
                            Logger.getLogger(ListeDesCategoriesController.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (SQLException ex) {
                            Logger.getLogger(ListeDesEtablissementsController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });     
//    ScrollBar s = new ScrollBar();
          //  s.set
  
          vbox1.getChildren().add(h1);
            
   vbox1.setStyle("-fx-padding: 10;" + 
            "-fx-border-style: solid inside;" + 
                  "-fx-border-width: 2;" +
                  "-fx-border-insets: 5;" + 
                  "-fx-border-radius: 5;" + 
                  "-fx-border-color: black;"+
                  "-fx-width: 15;"     );
       //  resultat.add(x);   
       // System.out.println(x);
    //    resultat.add(x);
  System.out.println(x);
            
    }
    });
            // vbox1.getChildren().clear();

    }

    @FXML
    private void affiche2(ActionEvent event) {
        typerecherche.setPromptText("");
        typerecherche.setPromptText("Séléctionnez votre budget");
    typerecherche.getItems().clear();
        List<budget>budgets = new ArrayList<>();
            for(budget b : budget.values())
            { 
                typerecherche.getItems().add(b.getName());
            }
        typerecherche.setOnAction((ActionEvent e)->{
      vbox1.getChildren().clear();
     
       List<Etablissement> resultat = new ArrayList<>();

 String budg= typerecherche.getSelectionModel().getSelectedItem();
resultat=sr.listEtablissementParBudget(budg);     
 MapOptions options = new MapOptions();
        options.center(new LatLong(34.3055732, 11.255412))
                .zoomControl(true)
                .zoom(6)
                .overviewMapControl(false)
                .mapType(MapTypeIdEnum.ROADMAP);
           GoogleMap map = mapView.createMap(options);   
         //  bounds = new google.maps.LatLngBounds();

for (Etablissement x : resultat){
              //  System.out.println(x);
    if(x != null){
      

             
              LatLong l = new LatLong(x.getLong(),x.getLat());
         Marker myMarker = null;
        markerOptions = new MarkerOptions();
        markerOptions.position(l)
                .title(x.getNom_etablissement())
                .visible(true);

        myMarker = new Marker(markerOptions);
        InfoWindowOptions infoOptions = new InfoWindowOptions();
        infoOptions.content(x.getNom_etablissement())
                .position(l);
       // bounds.extend(l);
        InfoWindow window = new InfoWindow(infoOptions);
        window.open(map, myMarker);
        map.addMarker(myMarker);
        //map.fitBounds(bounds);
    }
HBox h1= new HBox();
                VBox v1= new VBox();
                Label lab1= new Label();
                Label lab2= new Label();
                Label lab3= new Label();
                Label lab4= new Label();
                Label lab5= new Label();
  Rating r1 =new Rating();               
                lab1.setText(x.getNom_etablissement());
                lab2.setText(x.getAdresse_etablissement());
                lab3.setText(x.getHoraire_travail());
                lab4.setText("Budget:"+x.getBudget().toString());
               lab5.setText(" ");
                 r1.setRating(sr.EvaluationParEtab(x));

                  r1.setStyle("-fx-padding: 0;" + 
                  " -fx-pref-width: 2 ;" + 
                  "               \n" +
"            -fx-pref-height: 2 ;" +
                  "  -fx-background-size: cover;"   );  
        
              v1.getChildren().addAll(lab1,lab2,lab3,lab4,r1,lab5);
                ImageView image= new ImageView();
   javafx.scene.image.Image img = new javafx.scene.image.Image("http://localhost/image/"+x.getPhoto_etablissement(),350,350, true, true);
        
        image.setImage(img);
              h1.getChildren().add(image);
            h1.getChildren().add(v1);
           h1.setSpacing(10);    
    h1.setStyle("-fx-padding: 10;" + 
                  "-fx-border-style: solid inside;" + 
                  "-fx-border-width: 2;" +
                  "-fx-border-insets: 5;" + 
                  "-fx-border-radius: 5;" + 
                  "-fx-border-color: black;"+
                  "-fx-width: 2;"     );
   
        h1.setOnMouseClicked(new EventHandler<MouseEvent>
  () {
                    @Override
                    public void handle(MouseEvent event) {
        Parent homePage;
                        try {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("EtablissementClient.fxml"));
                            homePage = loader.load();
                            EtablissementClientController c1 = new EtablissementClientController();
                            c1 = loader.getController();
                            x.setNom_etablissement(x.getNom_etablissement());
                            x.setNom_etablissement(x.getNom_etablissement());               
                            x.setAdresse_etablissement(x.getAdresse_etablissement());
                            x.setCode_postal(x.getCode_postal());
                            x.setDescription_etablissement(x.getDescription_etablissement());
                            x.setBudget(x.getBudget());
                            x.setHoraire_travail(x.getHoraire_travail());
                            x.setPhoto_etablissement(x.getPhoto_etablissement());
                            x.setPhoto_patente(x.getPhoto_patente());
                            x.setSite_web(x.getSite_web());
                            x.setTelephone_etablissement(x.getTelephone_etablissement());
                            x.setCategorie(x.getCategorie());
                         
                            c1.setE(x);
                          
                           c1.affich();
        Scene homePage_scene = new Scene(homePage);

        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        app_stage.setScene(homePage_scene);

        app_stage.show();
    } catch (IOException ex) {
                            Logger.getLogger(ListeDesCategoriesController.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (SQLException ex) {
                            Logger.getLogger(ListeDesEtablissementsController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });    
       //  resultat.add(x);   
       // System.out.println(x);
    //    resultat.add(x);
  System.out.println(x);
    }        
    
                });
    }

    @FXML
    private void Chercher(MouseEvent event) {
                        vbox1.getChildren().clear();
        List <Etablissement> etab= new ArrayList<>();
    etab= sr.chercherEtablissementParNom(recheche.getText());
     for(Etablissement e : etab)
     {
    if(e != null){
        
           MapOptions options = new MapOptions();

        options.center(new LatLong(34.3055732, 11.255412))
                .zoomControl(true)
                .zoom(6)
                .overviewMapControl(false)
                .mapType(MapTypeIdEnum.ROADMAP);
        GoogleMap map = mapView.createMap(options);
         Services.ServiceEtablissement sr = new ServiceEtablissement();
  
        LatLong l = new LatLong(e.getLong(),e.getLat());
         Marker myMarker = null;
        markerOptions = new MarkerOptions();
        markerOptions.position(l)
                .title(e.getNom_etablissement())
                .visible(true);

        myMarker = new Marker(markerOptions);
        InfoWindowOptions infoOptions = new InfoWindowOptions();
        infoOptions.content(e.getNom_etablissement())
                .position(l);

        InfoWindow window = new InfoWindow(infoOptions);
        window.open(map, myMarker);
        map.addMarker(myMarker);
        
    
    }
            HBox h1 = new HBox();
                 VBox v1= new VBox();
                Label lab1= new Label();
                Label lab2= new Label();
                Label lab3= new Label();
                Label lab4= new Label();
                Label lab5= new Label();
  Rating r1 =new Rating();               
                lab1.setText(e.getNom_etablissement());
                lab2.setText(e.getAdresse_etablissement());
                lab3.setText(e.getHoraire_travail());
                lab4.setText("Budget:"+e.getBudget().toString());
               lab5.setText(" ");
                  r1.setStyle("-fx-padding: 0;" + 
                  " -fx-pref-width: 2 ;" + 
                  "               \n" +
"            -fx-pref-height: 2 ;" +
                  "  -fx-background-size: cover;"   );  
        
              v1.getChildren().addAll(lab1,lab2,lab3,lab4,r1,lab5);
                ImageView image= new ImageView();
   javafx.scene.image.Image img = new javafx.scene.image.Image("http://localhost/image/"+e.getPhoto_etablissement(),350,350, true, true);
        
        image.setImage(img);
              h1.getChildren().add(image);
            h1.getChildren().add(v1);
           h1.setSpacing(10);    
    h1.setStyle("-fx-padding: 10;" + 
                  "-fx-border-style: solid inside;" + 
                  "-fx-border-width: 2;" +
                  "-fx-border-insets: 5;" + 
                  "-fx-border-radius: 5;" + 
                  "-fx-border-color: black;"+
                  "-fx-width: 2;"     );
   
     h1.setOnMouseClicked(new EventHandler<MouseEvent>
  () {
                    @Override
                    public void handle(MouseEvent event) {
        Parent homePage;
                        try {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("EtablissementClient.fxml"));
                            homePage = loader.load();
                            EtablissementClientController c1 = new EtablissementClientController();
                            c1 = loader.getController();
                            e.setNom_etablissement(e.getNom_etablissement());
                             e.setNom_etablissement(e.getNom_etablissement());               
                            e.setAdresse_etablissement(e.getAdresse_etablissement());
                            e.setCode_postal(e.getCode_postal());
                            e.setDescription_etablissement(e.getDescription_etablissement());
                            e.setBudget(e.getBudget());
                            e.setHoraire_travail(e.getHoraire_travail());
                            e.setPhoto_etablissement(e.getPhoto_etablissement());
                            e.setPhoto_patente(e.getPhoto_patente());
                            e.setSite_web(e.getSite_web());
                            e.setTelephone_etablissement(e.getTelephone_etablissement());
                            e.setCategorie(e.getCategorie());
                         
                            c1.setE(e);
                          
                           c1.affich();
        Scene homePage_scene = new Scene(homePage);

        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        app_stage.setScene(homePage_scene);

        app_stage.show();
    } catch (IOException ex) {
                            Logger.getLogger(ListeDesCategoriesController.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (SQLException ex) {
                            Logger.getLogger(ListeDesEtablissementsController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
     
       vbox1.getChildren().add(h1);
   
 
  
    
    }
    }
@Override
    public void directionsReceived(DirectionsResult dr, DirectionStatus ds) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }




 


}