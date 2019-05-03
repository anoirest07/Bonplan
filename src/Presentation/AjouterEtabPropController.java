/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entite.Categorie;
import Entite.CriteresEvaluation;
import Entite.Etablissement;
import Entite.budget;
import Services.ServiceCategorie;
import Services.ServiceCritere;
import Services.ServiceEtablissement;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.DirectionsPane;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import com.lynden.gmapsfx.service.directions.DirectionStatus;
import com.lynden.gmapsfx.service.directions.DirectionsResult;
import com.lynden.gmapsfx.service.directions.DirectionsService;
import com.lynden.gmapsfx.service.directions.DirectionsServiceCallback;
import com.lynden.gmapsfx.service.geocoding.GeocoderStatus;
import com.lynden.gmapsfx.service.geocoding.GeocodingResult;
import com.lynden.gmapsfx.service.geocoding.GeocodingService;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author Nadia
 */
public class AjouterEtabPropController implements Initializable , MapComponentInitializedListener, DirectionsServiceCallback {
 private GeocodingService geocodingService;
  private MarkerOptions markerOptions;
   private GoogleMap map;
   protected DirectionsPane directionsPane;
List<String> l = new ArrayList();
protected DirectionsService directionsService;
String[] s;
protected StringProperty from = new SimpleStringProperty();
GoogleMapView mapView = new GoogleMapView();

    @FXML
    private Label nometab;
    @FXML
    private ImageView phetab;
    @FXML
    private ImageView phpat;
//    @FXML
//    private TextField position;
    @FXML
    private TextField longi;
    @FXML
    private TextField lat;
    @FXML
    private JFXTextField adr;
    @FXML
    private JFXComboBox<budget> combobudget;
    @FXML
    private JFXComboBox<String> combocat;
    @FXML
    private JFXTextArea desc;
    @FXML
    private JFXTextField cpos;
    @FXML
    private JFXTextField nom;
    @FXML
    private JFXTextField site;
    @FXML
    private JFXTextField tel;
    @FXML
    private JFXTextField horraire;
    @FXML
    private JFXButton Suivant;
    @FXML
    private JFXButton autre;
    @FXML
    private Separator sep;
    @FXML
    private Label lab1;
    @FXML
    private Label lab2;
    @FXML
    private Label lab3;
    @FXML
    private Label lab4;
    @FXML
    private Label lab5;
    @FXML
    private HBox hbox1;
    @FXML
    private Label nouvelle;
    @FXML
    private JFXTextField nomnouv;
    @FXML
    private JFXButton save;
    @FXML
    private JFXButton redirection;
    @FXML
    private JFXButton upload;
 private Etablissement e = new Etablissement();
    @FXML
    private JFXButton upload2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          mapView.addMapInializedListener(this);
      
      
        mapView.addMapInializedListener(this);

       
        from.bindBidirectional(adr.textProperty());


       
      adr.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (!newPropertyValue) {
                    adr.validate();
                }
            }
      });
        // TODO
          ServiceCategorie sc = new ServiceCategorie();
        List<Categorie> listecat = new ArrayList<Categorie>();
        listecat= sc.listCategorie();
        for (Categorie c: listecat)
        {
            combocat.getItems().add(c.getNom_categorie());
        }
        combocat.setOnAction(e->{
         ServiceCritere service = new ServiceCritere();
        List<CriteresEvaluation> criteres= new ArrayList<>();
 
      Categorie choix = new Categorie();
     
        choix= sc.afficherCategorieParNom(combocat.getSelectionModel().getSelectedItem());       
        System.out.println(choix);
    criteres= service.FindCritereEvalByCateg(choix.getId_categorie()); 
  for(CriteresEvaluation c: criteres){
       lab1.setText(criteres.get(0).getNom_critere_evaluation());
  lab2.setText(criteres.get(1).getNom_critere_evaluation());
  lab3.setText(criteres.get(2).getNom_critere_evaluation());
   lab4.setText(criteres.get(3).getNom_critere_evaluation());
  lab5.setText(criteres.get(4).getNom_critere_evaluation());
  }            
        
        });
  
                            
                List<budget>budgets = new ArrayList<>();
                for(budget b : budget.values())
                {   combobudget.getItems().add(b);}  
                   }    

    @FXML
    private void fromOnKeyTypedEvent(KeyEvent event) {
      try{
        geocodingService = new GeocodingService();
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
   geocodingService.geocode(adr.getText(), (GeocodingResult[] results, GeocoderStatus status) -> {
        l.clear();
        //int i;                 
  for(int i =0;i<results.length;i++){
        s=new String[results.length];
      s[i] = results[i].getFormattedAddress();
      System.out.println(results[i].getJSObject());
         l.add(results[i].getFormattedAddress());
        
         
  }
     
            
       for (GeocodingResult result : results) {
           
       
              TextFields.bindAutoCompletion(adr, s);
              longi.setText(result.getGeometry().getLocation().getLatitude()+"");
             lat.setText(result.getGeometry().getLocation().getLongitude()+"");

            }
       
        TextFields.bindAutoCompletion(adr, t-> {
 
            return l;
 
        });
       
          

        });
    }
     public void mapInitialized() {
   geocodingService = new GeocodingService();
        MapOptions options = new MapOptions();

        options.center(new LatLong(34.3055732, 11.255412))
                .zoomControl(true)
                .zoom(6)
                .overviewMapControl(false)
                .mapType(MapTypeIdEnum.ROADMAP);
        GoogleMap map = mapView.createMap(options);
      directionsService = new DirectionsService();
      directionsPane = mapView.getDirec();
        
 }

    @FXML
    private void suivant(ActionEvent event) {
       Alert alertBox = new Alert(Alert.AlertType.ERROR);
            alertBox.setTitle("Error");
             if (nom.getText().isEmpty())
        {
            alertBox.setHeaderText("Veuillez saisir le nom de votre établissement");
            alertBox.show();
        }
       
                else if (combocat.getSelectionModel().isEmpty())
        {
            alertBox.setHeaderText("Veuillez saisir la catégorie de votre établissement");
            alertBox.show();
        }   else if (combobudget.getSelectionModel().isEmpty())
        {
            alertBox.setHeaderText("Veuillez séléctionner le budget de votre établissement");
            alertBox.show();
        }  
        else if(upload.getText().isEmpty()){
            alertBox.setHeaderText("Veuillez saisir la photo de votre établissement");
            alertBox.show();
        }
//            }
//        else if (phetab.)
//        {
//            alertBox.setHeaderText("Veuillez saisir la photo de votre établissement");
//            alertBox.show();
//        }   else if (phpat.getText().isEmpty())
//        {
//            alertBox.setHeaderText("Veuillez saisir la photo de votre patente pour la validation");
//            alertBox.show();
//        }
         else if (adr.getText().isEmpty())
        {
            alertBox.setHeaderText("Veuillez saisir l'adresse de votre établissement");
            alertBox.show();
        }
          else if (tel.getText().length()!=8)
        {
            alertBox.setHeaderText("Numéro de téléphone invalide");
            alertBox.show();
        }
        else if (horraire.getText().isEmpty())
        {
            alertBox.setHeaderText("Veuillez saisir vos horaires de travail");
            alertBox.show();
        }
         else if (desc.getText().isEmpty())
        {
            alertBox.setHeaderText("Vous devez saisir une description");
            alertBox.show();
        }
       
        else{        
        
       System.err.println(e.getPhoto_etablissement());
        e.setAdresse_etablissement(adr.getText());
        e.setCode_postal(Integer.parseInt(cpos.getText()));
        e.setDescription_etablissement(desc.getText());
        e.setEnabled(0);
        e.setHoraire_travail(horraire.getText());
        e.setNom_etablissement(nom.getText());
      
       System.err.println(e.getPhoto_patente());
        e.setTelephone_etablissement(Integer.parseInt(tel.getText()));
//        e.setPosition(position.getText());
        e.setSite_web(site.getText());
        Categorie c= new Categorie();
        ServiceCategorie sc = new ServiceCategorie();
       c= sc.afficherCategorieParNom(combocat.getSelectionModel().getSelectedItem());
      e.setCategorie(c);
      e.setUtilisateur(AuthentificationController.c);
     e.setBudget(combobudget.getSelectionModel().getSelectedItem());
 e.setLong(Double.parseDouble(longi.getText()));
 e.setLat(Double.parseDouble(lat.getText()));
        ServiceEtablissement se= new ServiceEtablissement();
    
        se.ajouterEtablissement(e);
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
    
            alert.setTitle("Demande enregistrée");
            alert.setHeaderText("Merci pour votre demande");
            alert.setContentText("Veuillez attendre la validation de votre demande ");
            alert.showAndWait(); 
            Parent homePage;
                 try {
                  FXMLLoader loader = new FXMLLoader(getClass().getResource("etabprop.fxml"));
                    homePage = loader.load();
                        
        Scene homePage_scene = new Scene(homePage);

        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        app_stage.setScene(homePage_scene);

        app_stage.show();
     
                        
                        } catch (IOException ex) {
                            Logger.getLogger(AjouterEtabPropController.class.getName()).log(Level.SEVERE, null, ex);
                        }
            
    }
      }
 @Override
    public void directionsReceived(DirectionsResult dr, DirectionStatus ds) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @FXML
    private void AjouterCateg(ActionEvent event) {
        hbox1.setVisible(true);
        save.setVisible(true);
    }

    @FXML
    private void nouv(ActionEvent event) {
  
    Categorie c = new Categorie();
    ServiceCategorie sc = new ServiceCategorie();
    c.setNom_categorie(nomnouv.getText());
    sc.ajouterDemandeCategorie(c);
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
    
            alert.setTitle("Demande enregistrée");
            alert.setHeaderText("Merci pour votre demande");
            alert.setContentText("Veuillez attendre la validation de votre demande ");
            alert.showAndWait();     
      Parent homePage;
                 try {
                  FXMLLoader loader = new FXMLLoader(getClass().getResource("etabprop.fxml"));
                    homePage = loader.load();
                        
        Scene homePage_scene = new Scene(homePage);

        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        app_stage.setScene(homePage_scene);

        app_stage.show();
     
                        
                        } catch (IOException ex) {
                            Logger.getLogger(AjouterEtabPropController.class.getName()).log(Level.SEVERE, null, ex);
                        }
    }

    @FXML
    private void redirection(ActionEvent event) {
//      Parent homePage;
//                 try {
//                  FXMLLoader loader = new FXMLLoader(getClass().getResource("DemandesEtablissementAdmin.fxml"));
//                    homePage = loader.load();
//                        
//        Scene homePage_scene = new Scene(homePage);
//
//        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//
//        app_stage.setScene(homePage_scene);
//
//        app_stage.show();
//     
//                        
//                        } catch (IOException ex) {
//                            Logger.getLogger(ListeDesCategoriesController.class.getName()).log(Level.SEVERE, null, ex);
//                        }
    }

    @FXML
    private void UploadPhoto(ActionEvent event) throws MalformedURLException, IOException {
 
        System.err.println(e.getPhoto_etablissement());
         FileChooser fc = new FileChooser();
        File selectedFile = fc.showOpenDialog(null);
        if (selectedFile != null) {
            e.setPhoto_etablissement(selectedFile.getName());
        String    imageFile = selectedFile.toURI().toURL().toString();
           upload(selectedFile);
           Image image = new Image(imageFile);
            
            phetab.setImage(image);

        } else {
            System.out.println("fichier inexistant");
        }
    }
 public String upload(File file) throws FileNotFoundException, IOException {
       System.err.println(e.getPhoto_etablissement());
        BufferedOutputStream stream = null;
        String globalPath="C:\\wamp\\www\\image";
        String localPath="localhost:80/";
        String fileName = file.getName();
        fileName=fileName.replace(' ', '_');
      
        try {
            Path p = file.toPath();
            
            byte[] bytes = Files.readAllBytes(p);
    
            File dir = new File(globalPath);
            if (!dir.exists())
                dir.mkdirs();
            // Create the file on server
            File serverFile = new File(dir.getAbsolutePath()+File.separator + fileName);
            stream = new BufferedOutputStream(new FileOutputStream(serverFile));
            stream.write(bytes);
            stream.close();
            return localPath+"/"+fileName;
        } catch (FileNotFoundException ex) {
          Logger.getLogger(AjouterEtabPropController.class.getName()).log(Level.SEVERE, null, ex);
            return "error1";
        } catch (IOException ex) {
            Logger.getLogger(AjouterEtabPropController.class.getName()).log(Level.SEVERE, null, ex);
            return "error2";
        }
    }

    @FXML
    private void UploadPhotopat(ActionEvent event) throws IOException {
 
        System.err.println(e.getPhoto_patente());
         FileChooser fc = new FileChooser();
        File selectedFile = fc.showOpenDialog(null);
        if (selectedFile != null) {
            e.setPhoto_patente(selectedFile.getName());
        String    imageFile = selectedFile.toURI().toURL().toString();
           upload(selectedFile);
           Image image = new Image(imageFile);
            
            phpat.setImage(image);

        } else {
            System.out.println("fichier inexistant");
        }
    
    }
  
    
}
