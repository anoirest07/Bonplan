/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entite.Offre;
import Services.ServiceEtablissement;
import Services.ServiceOffre;
import com.jfoenix.controls.JFXButton;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Yassine
 */
public class ModifierOffreFXMLController implements Initializable {

    @FXML
    private ImageView picajout;
    @FXML
    private TextArea descajout;
    @FXML
    private Button canoffre;
    @FXML
    private Button ajoutoffre;
    @FXML
    private JFXButton upload_add_offre;
    @FXML
    private DatePicker ddebutoffre;
    @FXML
    private DatePicker dfinoffre;

    private Stage odialogStage;
    private Offre o= new Offre();
    private boolean okClicked = false;
    ServiceOffre sero = new ServiceOffre();
    @FXML
    private Label titre1;
    @FXML
    private TextField ajouttitre;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        List<String> l = new ArrayList<>();
        // l=sp.nouri();
        if (o != null) {
            System.out.println("offre déja fait");
        } else {
            System.out.println("done modif");
        }

    }

    public void setDialogStage(Stage dialogStage) {
        this.odialogStage = dialogStage;
    }

    public void setOffre(Offre o) {
        this.o = o;
        }

    public boolean isOkClicked() {

        return okClicked;
    }

     public String upload(File file) throws FileNotFoundException, IOException {
        BufferedOutputStream stream = null;
        String globalPath = "C:\\wamp64\\www\\image";
        String localPath = "localhost:3306/";
        String fileName = file.getName();
        fileName = fileName.replace(" ", "_");
        try {
            Path p = file.toPath();

            byte[] bytes = Files.readAllBytes(p);

            File dir = new File(globalPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            // Create the file on server
            File serverFile = new File(dir.getAbsolutePath() + File.separator + fileName);
            stream = new BufferedOutputStream(new FileOutputStream(serverFile));
            stream.write(bytes);
            stream.close();
            return localPath + "/" + fileName;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AjouterPubliciteController.class.getName()).log(Level.SEVERE, null, ex);
            return "error1";
        } catch (IOException ex) {
            Logger.getLogger(AjouterPubliciteController.class.getName()).log(Level.SEVERE, null, ex);
            return "error2";
        }
    }

    @FXML
    private void uploadPic(ActionEvent event) throws MalformedURLException, IOException {
        FileChooser fc = new FileChooser();
        File selectedFile = fc.showOpenDialog(null);
        if (selectedFile != null) {
  
                 upload(selectedFile);
            String imageFile = selectedFile.toURI().toURL().toString();
            System.err.println(selectedFile.getName());

            
            System.out.println(imageFile);

            Image image = new Image(imageFile);
            picajout.setImage(image);
            System.out.println("NAAAMMMEEE ::::: >>>>>>>"+selectedFile.getName());   
            o.setPhoto(selectedFile.getName());

        } else {
            System.out.println("file doesn't exist");
        }
    }
    
    public void afficher(){
   ajouttitre.setText(o.getTitre_offre());
   descajout.setText(o.getDescription());

//        ddebutoffre.setValue(java.sql.Date.valueOf(o.getDate_debut()));
    javafx.scene.image.Image image = new javafx.scene.image.Image("http://localhost/image/" + o.getPhoto(),350,350,true,true);
    picajout.setImage(image);
    }
     
    @FXML
    private void modifier(ActionEvent event) {
      
        if (isInputValid()) {
            System.out.println("OFFRE >>>>"
                    + ">>>>>>>>>> ");
            System.out.println(descajout.getText());

            try {
                o.setTitre_offre(ajouttitre.getText());
                o.setDescription(descajout.getText());
//              ofre.setPhoto(picajout.getImage().toString());

                o.setDate_debut(java.sql.Date.valueOf(ddebutoffre.getValue()));
                o.setDate_fin(java.sql.Date.valueOf(dfinoffre.getValue()));
            } catch (Exception e) {
                System.out.println("OFFRE ELEMENTS >>>>>>>>> : " + e.getMessage());
            }

            System.out.println(" oo : " + o.toString());
            sero.modifieroffre(o);
            okClicked = true;
            odialogStage.close();
        }

    }

    private boolean isInputValid() {
        String errorMessage = "";
  
        if (ajouttitre.getText() == null || ajouttitre.getText().length() == 0) {
            errorMessage += "No valid  Title!\n";
        } 
        if (descajout.getText() == null || descajout.getText().length() == 0) {
            errorMessage += "No valid  description!\n";
        }
        if (ddebutoffre.getValue() == null || ddebutoffre.getValue().lengthOfMonth() == 0) {
            errorMessage += "No valid  Starting Date!\n";
        }
        if (dfinoffre.getValue() == null || dfinoffre.getValue().lengthOfMonth() == 0) {
            errorMessage += "No valid  Ending DATE!\n";
        }
        if (picajout.getImage() == null || picajout.getImage().equals(picajout)) {
            errorMessage += "No valid photo, !\n";
        }
        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(odialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }

    @FXML
    private void Cancel(ActionEvent event) {
        odialogStage.close();

    }

   
}


/*        


*/