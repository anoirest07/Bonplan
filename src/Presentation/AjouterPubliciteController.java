/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entite.Publicite;
import Services.ServiceEtablissement;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author user
 */
public class AjouterPubliciteController implements Initializable {

    @FXML
    private TextArea desc;
    
    @FXML
    private Button ajouter;
    
    @FXML
    private ChoiceBox chb;
    @FXML
    private Button cancel;
    private Stage dialogStage;
    private Publicite s;
    private boolean okClicked = false; 
        @FXML
    private ImageView pic;
    private String ImageFile;
    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
            
    {
        ServiceEtablissement sp = new ServiceEtablissement();
         List<String> l=new ArrayList<>();
        l=sp.nouri();
        for (int i=0;i<l.size();i++)
        {//System.out.println(l.get(i).toString());
chb.getItems().add(l.get(i).toString());
                }
        if(s != null){
            desc.setText(s.getDescription_publicite());
            
             //System.out.println(photo.getImage());
             
            
           // photo.setImage();
            
        }
        else{
            System.out.println("done");
        }
       
    }    
public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
 public void setPublicite(Publicite s) {
        this.s =s ;
        desc.setText(s.getDescription_publicite());
        
//        photo.setImage(s.getPhoto_publicite().to);
        
       // System.out.println(s.toString());
    }
 public boolean isOkClicked() {
       
        return okClicked;
    }
 @FXML
 private void ajouter() throws MalformedURLException {
        if (isInputValid()) {
            s.setDescription_publicite(desc.getText());
            s.setPhoto_publicite(pic.getImage().toString());

            //    getImageUrl = selectedFile.getAbsolutePath();
            // System.out.println(getImageUrl);
            // Image value = new Image(getImageUrl);
            //  img.setImage(value);
           
            okClicked = true;
            dialogStage.close();
        
    }}
 @FXML
 private void Cancel() {
        dialogStage.close();
    }
   //@FXML
//    private void ajouterPublicite(ActionEvent event) {
//        Etablissement e = new Etablissement();
//        e.setNom_etablissement((chb.getValue().toString()));
//        Publicite p = new Publicite(desc.getText(),photo.getText().toString(),e);
//        
//       Services.ServicePublicite SP=new ServicePublicite();
//        SP.ajouterpublicite(p);
//    }

//    @FXML
    
    
    private boolean isInputValid() {
        String errorMessage = "";

        if (desc.getText() == null || desc.getText().length() == 0) {
            errorMessage += "No valid  description!\n"; 
        }
        if (pic.getImage()== null || pic.getImage().equals(pic)) {
            errorMessage += "No valid photo, !\n"; 
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
            System.out.println(imageFile);

            Image image = new Image(imageFile);
            pic.setImage(image);
            //////a changer static
            /*IuserService is = new UserService();
            User u;
            u = is.findById(15);
            //////////
            u.setPath(imageFile);
            is.update(u);*/
            /////

        } else {
            System.out.println("file doesn't exist");
        }
    }
}
