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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import Entite.Evenement;
import Entite.Publicite;
import Entite.Utilisateur;
import Services.ServiceEtablissement;
import com.jfoenix.controls.JFXButton;
import javafx.scene.control.DatePicker;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.glyphfont.FontAwesome;
import Services.ServiceEvenement;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Date;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author amine
 */

public class AjouterevenementController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextArea desc;
    @FXML
    private Button validation;
    @FXML
    private DatePicker datep;
    @FXML
    private ImageView photoo;
    @FXML
    private JFXButton upload;
     private Stage dialogStage;
      private boolean okClicked = false; 
     
String imageFile;
    @FXML
    private ChoiceBox chb;
   private Evenement e ;
   
 ServiceEtablissement sc = new ServiceEtablissement();
   /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
  
        List<Etablissement> listeetab = new ArrayList<Etablissement>();
        listeetab= sc.listEtablissementParProp(AuthentificationController.c.getId_user());
        Utilisateur u = new Utilisateur();
        u.setId_user(AuthentificationController.c.getId_user());
        for (Etablissement c: listeetab)
        {
            chb.getItems().add(c.getNom_etablissement());
        }
       
     // choix= 
        System.out.println(sc.afficheretabParNom((String) chb.getSelectionModel().getSelectedItem(),u.getId_user()));       
       
        if(e != null){
            desc.setText(e.getDescription());
            
             //System.out.println(photo.getImage());
             
            
           // photo.setImage();
            
        }
        else{
            System.out.println("done");
        }   
        

// TODO
    }    

   Evenement event = new Evenement();
    @FXML
    private void Ajouterevenement() throws IOException {
    Evenement e;
    if (isInputValid())   {
    
    event.setNom_evenement(nom.getText());
    event.setDescription(desc.getText());
    event.setDate_evenement(datep.getValue());
    
      
     System.out.println(photoo.getImage());
     
    //e.setPhoto(imageFile);
//        System.out.println(e.getPhoto());
Etablissement et=new Etablissement();
et=sc.chercherEtablissementParNoms((String)chb.getSelectionModel().getSelectedItem());
        event.getEtab().setId_etablissement(et.getId_etablissement());

       
     
     
  ServiceEvenement  se = new  ServiceEvenement();
        se.ajouterevenement(event);
        System.out.println("workss");}
        
   
       
     Stage current = (Stage) datep.getScene().getWindow();
     current.hide();

    }
    private boolean isInputValid() {
        String errorMessage = "";

        if (nom.getText() == null || nom.getText().length()== 0){
        errorMessage += "No valid  nom!\n"; 
        }
        if (desc.getText() == null || desc.getText().length() == 0) {
            errorMessage += "No valid  description!\n"; 
        }
        if (photoo.getImage()== null || photoo.getImage().equals(photoo)) {
            errorMessage += "No valid photo, !\n"; 
       if (datep.getValue()== null || datep.getValue().equals("")){
       
         errorMessage += "No valid date, !\n";
       }
        }


         
        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    
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
    @FXML
    private void UploadPhoto() throws MalformedURLException, IOException {
           FileChooser fc = new FileChooser();
        File selectedFile = fc.showOpenDialog(null);
        if (selectedFile != null) {
            imageFile = selectedFile.toURI().toURL().toString();
            System.out.println(imageFile);
            upload(selectedFile);

            Image image = new Image(imageFile);
            event.setPhoto(selectedFile.getName());
            photoo.setImage(image);
        

        } else {
            System.out.println("file doesn't exist");
        }
    }
  
    }
    

