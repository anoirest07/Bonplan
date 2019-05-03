/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entite.Etablissement;
import Services.ServiceEtablissement;
import com.jfoenix.controls.JFXTextArea;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.controlsfx.control.textfield.CustomTextField;

/**
 * FXML Controller class
 *
 * @author Nadia
 */
public class EtabpropController {

    @FXML
    private Button ajouter;
    @FXML
    private Button supp;
    @FXML
    private Button modifier;
    @FXML
    private ListView<Etablissement> listview;
    @FXML
    private Label nomm;
    @FXML
    private Label desc;
    @FXML
    private Label phot;
    @FXML
    private TextField nom;
    @FXML
    private JFXTextArea descr;
    @FXML
    private Button photoe;
    @FXML
    private ImageView pic;
    @FXML
    private TextField numero;
    private ObservableList<Etablissement> data ;
  Etablissement etabl = new Etablissement();
  ServiceEtablissement ps = new ServiceEtablissement();
    /**
     * Initializes the controller class.
     */
    
    public void initialize() {
         List<Etablissement> ls = ps.listEtablissementParProp(AuthentificationController.c.getId_user());
        data = FXCollections.observableArrayList();
        ls.stream().forEach((j)->{
            data.add(j);
                       
        });
        listview.setItems(data);
        listview.setCellFactory(new Callback<ListView<Etablissement>, ListCell<Etablissement>>() {

                    @Override
                    public ListCell<Etablissement> call(ListView<Etablissement> param) {
                        return new ListViewCell2();
                        
                        
                
                    }
                });
listview.setOnMouseClicked(new EventHandler<Event>() {
        @Override
        public void handle(Event event) {
            try {
                Edition();
            } catch (IOException ex) {
               // Logger.getLogger(EtabpropController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(EtabpropController.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }
    });
    }    
  public void Edition() throws IOException, SQLException 
    {
        if (listview.getSelectionModel().getSelectedItem() != null) 
        {
            
            
       
            nom.setVisible(true);
            numero.setVisible(true);
            descr.setVisible(true);
            
        Etablissement selectedPerson = listview.getSelectionModel().getSelectedItem();
        nom.setText(selectedPerson.getNom_etablissement());
        numero.setText(""+selectedPerson.getTelephone_etablissement());
        descr.setText(selectedPerson.getDescription_etablissement());
        desc.setWrapText(true);
        }
    }
    
    private void affichageproprietaire(ActionEvent event) {
        List<Etablissement> ls = ps.listEtablissementParProp(AuthentificationController.c.getId_user());
        data = FXCollections.observableArrayList();
        ls.stream().forEach((j)->{
            data.add(j);
                       
        });
        listview.setItems(data);
        listview.setCellFactory(new Callback<ListView<Etablissement>, ListCell<Etablissement>>() {

                    @Override
                    public ListCell<Etablissement> call(ListView<Etablissement> param) {
                        return new ListViewCell2();
                        
                        
                
                    }
                });
    }


    @FXML
    private void ajouteretablissement(ActionEvent event) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterEtabProp.fxml"));
         Stage stage = new Stage();
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void supprimeretablissement(ActionEvent event) {
        ServiceEtablissement so = new  ServiceEtablissement();

        int selectedIndex = listview.getSelectionModel().getSelectedIndex();
         Etablissement selectedPerson = listview.getSelectionModel().getSelectedItem();
         Alert a = new Alert(Alert.AlertType.CONFIRMATION);
         
        if (selectedIndex >= 0) {
          //  serviceTable.getItems().remove(selectedIndex);
          so.supprimerEtablissement(selectedPerson);
            affichageproprietaire(event);
        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Service Selected");
            alert.setContentText("Please select a etablissement in the table.");
            alert.showAndWait();
        }
    }

    @FXML
    private void edit(ActionEvent event) {
          Etablissement selectedPerson = listview.getSelectionModel().getSelectedItem();
        ServiceEtablissement cs = new ServiceEtablissement();
        
 
       selectedPerson.setNom_etablissement(nom.getText());
        selectedPerson.setDescription_etablissement(descr.getText());
        selectedPerson.setTelephone_etablissement(Integer.parseInt(numero.getText()) );
        selectedPerson.setEnabled(1);
        try
        {
        cs.modifierEtablissement(selectedPerson);
        
        
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Etablissmeent modifié");
            alert.setHeaderText("Etablissement modifié avec succès");
            alert.setContentText("Etablissement "+nom.getText()+" modifié avec succès");
            alert.showAndWait();
        }
        catch (Exception e)
                {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Etablissement non modifié");
                alert.setHeaderText("Echec de la modification de l'etablissement");
                alert.setContentText("Echec de la modification de l'etablissement "+nom.getText());
                alert.showAndWait();
                }
        initialize();
     
    }

    @FXML
    private void uploadpic(ActionEvent event) throws IOException {
        FileChooser fc = new FileChooser();
            File selectedFile = fc.showOpenDialog(null);
            if(selectedFile != null ){
                upload(selectedFile);
                Image img = new Image(selectedFile.toURI().toURL().toString());
                pic.setImage(img);
                etabl.setPhoto_etablissement(selectedFile.getName());
                //return selectedFile.getName();
            }
           // return  null ;
            
    }
    
      public String upload(File file) throws FileNotFoundException, IOException {
        BufferedOutputStream stream = null;
        String globalPath="C:\\wamp\\www\\image";
        String localPath="localhost:80/";
        String fileName = file.getName();
      // fileName=fileName.replace(" ", "_");
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
            Logger.getLogger(EtabpropController.class.getName()).log(Level.SEVERE, null, ex);
            return "error1";
        } catch (IOException ex) {
            //Logger.getLogger(ajou.class.getName()).log(Level.SEVERE, null, ex);
            return "error2";
        }
    }

    }
    

