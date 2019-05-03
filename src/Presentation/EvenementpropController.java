/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entite.Etablissement;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyEvent;
import org.controlsfx.control.textfield.CustomTextField;
import Entite.Evenement;
import Entite.Utilisateur;
import Services.ServiceEtablissement;
import Services.ServiceEvenement;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.ImageViewBuilder;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
/**
 * FXML Controller class
 *
 * @author amine
 */
public class EvenementpropController  {

    @FXML
    private CustomTextField rechercher;
    @FXML
    private DatePicker recherchedate;

    @FXML
    private Button ajouter;
     ServiceEvenement ps = new ServiceEvenement();
        
private ObservableList<Evenement> data ;
    @FXML
    private Button modifier;
    @FXML
    private ListView<Evenement> listview;
    @FXML
    private Label nomm;
    @FXML
    private Label dates;
    @FXML
    private Label desc;
    @FXML
    private TextField nom;
    @FXML
    private DatePicker date;
    @FXML
    private TextField descr;
    
    @FXML
    private ImageView pic;
    @FXML
    private Button photoe;
     /**
     * Initializes the controller class.
     */
   
    public void initialize() 
    {
        listview.setOnMouseClicked((MouseEvent event) -> {
        if (event.getClickCount() > 1) {
            try {
                Edition();
            } catch (IOException ex) {
                Logger.getLogger(EvenementpropController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(EvenementpropController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
});
        Utilisateur u = new Utilisateur();
       
     List<Evenement> ls = ps.afficherevenement(AuthentificationController.c.getId_user() );
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
    
    public void Edition() throws IOException, SQLException 
    {
        if (listview.getSelectionModel().getSelectedItem() != null) 
        {
            nomm.setVisible(true);
            dates.setVisible(true);
            desc.setVisible(true);
            nom.setVisible(true);
            date.setVisible(true);
            descr.setVisible(true);
        Evenement selectedPerson = listview.getSelectionModel().getSelectedItem();
        nom.setText(selectedPerson.getNom_evenement());
        date.setValue(selectedPerson.getDate_evenement());
        descr.setText(selectedPerson.getDescription());
        
        }
    }

    @FXML
    private void recherche(KeyEvent event) {
    List<Evenement> ls = ps.afficherevenementbynom(rechercher.getText());
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
   List<Evenement> ls = ps.afficherevenementbydate(recherchedate.getValue());
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

  
    @FXML
   

 
    private void supprimerevenement(ActionEvent event) {
     ServiceEvenement so = new  ServiceEvenement();

        int selectedIndex = listview.getSelectionModel().getSelectedIndex();
         Evenement selectedPerson = listview.getSelectionModel().getSelectedItem();
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
            alert.setContentText("Please select an evenement in the table.");
            alert.showAndWait();
        }
      
    }

    @FXML
    private void ajouterevenement(ActionEvent event) throws IOException {
   FXMLLoader loader = new FXMLLoader(getClass().getResource("Ajouterevenement.fxml"));
         Stage stage = new Stage();
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
  
    }
     public String upload(File file) throws FileNotFoundException, IOException {
        BufferedOutputStream stream = null;
        String globalPath="C:\\wamp64\\www\\image";
        String localPath="localhost:80/";
        String fileName = file.getName();
        fileName=fileName.replace(" ", "_");
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
            Logger.getLogger(AjouterevenementController.class.getName()).log(Level.SEVERE, null, ex);
            return "error1";
        } catch (IOException ex) {
            Logger.getLogger(AjouterevenementController.class.getName()).log(Level.SEVERE, null, ex);
            return "error2";
        }
    }
     Evenement evenement = new Evenement();
    @FXML
    private void edit(ActionEvent event) throws IOException 
    {
        Evenement selectedPerson = listview.getSelectionModel().getSelectedItem();
        ServiceEvenement cs = new ServiceEvenement();
        
        evenement.setId_evenement(selectedPerson.getId_evenement());
        evenement.setNom_evenement(nom.getText());
        evenement.setDescription(descr.getText());
        evenement.setDate_evenement(date.getValue());
        try
        {
        cs.modifierevenement(evenement);
        
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Evenement modifié");
            alert.setHeaderText("Evenement modifié avec succès");
            alert.setContentText("Evenement "+nom.getText()+" modifié avec succès");
            alert.showAndWait();
        }
        catch (Exception e)
                {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Evenement non modifié");
                alert.setHeaderText("Echec de la modification de l'evenement");
                alert.setContentText("Echec de la modification de l'evenement "+nom.getText());
                alert.showAndWait();
                }
        initialize();
//        Close(event);
    }
//        private void Close(ActionEvent event) throws IOException 
//        {
//        Parent homePage = FXMLLoader.load(getClass().getResource("evenementprop.fxml"));
//
//        Scene homePage_scene = new Scene(homePage);
//
//        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//
//        app_stage.setScene(homePage_scene);
//
//        app_stage.show();
//    }
  
        
    @FXML
        public void uploadpic() throws IOException{
            FileChooser fc = new FileChooser();
            File selectedFile = fc.showOpenDialog(null);
            if(selectedFile != null ){
                upload(selectedFile);
                Image img = new Image(selectedFile.toURI().toURL().toString());
                pic.setImage(img);
                evenement.setPhoto(selectedFile.getName());
                //return selectedFile.getName();
            }
           // return  null ;
            
        }

  
    }
    

