/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entite.Produit;
import Services.ServiceProduit;

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

import javafx.scene.control.Label;

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
public class ModifierProduitFXMLController implements Initializable {

    @FXML
    private ImageView picajout;

    @FXML
    private JFXButton upload_add_offre;
    

    private Stage odialogStage;
    private Produit p= new Produit();
    private boolean okClicked = false;
    ServiceProduit serp = new ServiceProduit();

    @FXML
    private Button canoffre;
    @FXML
    private Button ajoutoffre;
    @FXML
    private TextField nommodif;
    @FXML
    private TextField prixmodif;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        List<String> l = new ArrayList<>();
        // l=sp.nouri();
        if (p != null) {
            System.out.println("modif not empty");
        } else {
            System.out.println("empty modif");
        }

    }

    public void setDialogStage(Stage dialogStage) {
        this.odialogStage = dialogStage;
    }

    public void setProduit(Produit p) {
        this.p = p;
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
            p.setPhoto_produit(selectedFile.getName());

        } else {
            System.out.println("file doesn't exist");
        }
    }
    
    public void afficher(){
   nommodif.setText(p.getNom_produit());
   prixmodif.setText(String.valueOf(p.getPrix_produit()));

//        ddebutoffre.setValue(java.sql.Date.valueOf(o.getDate_debut()));
    javafx.scene.image.Image image = new javafx.scene.image.Image("http://localhost/image/" + p.getPhoto_produit(),350,350,true,true);
    picajout.setImage(image);
    }
     
    @FXML
    private void modifier(ActionEvent event) {
      
        if (isInputValid()) {
            System.out.println("Prooduit >>>>"
                    + ">>>>>>>>>> ");
            System.out.println(nommodif.getText());

            try {
                 p.setNom_produit(nommodif.getText());
                p.setPrix_produit(Double.parseDouble(prixmodif.getText()));
//    
//              ofre.setPhoto(picajout.getImage().toString());

            } catch (Exception e) {
                System.out.println("PRoduiiit ELEMENTS >>>>>>>>> : " + e.getMessage());
            }

            System.out.println(" oo : " + p.toString());
            serp.modifierProduit(p);
         okClicked = true;
           }

    }

    private boolean isInputValid() {
        String errorMessage = "";
  
        if (nommodif.getText() == null || nommodif.getText().length() == 0) {
            errorMessage += "No valid  Name!\n";
        }
         if (prixmodif.getText() == null || prixmodif.getText().length() == 0) {
            errorMessage += "No valid  Price!\n";
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

