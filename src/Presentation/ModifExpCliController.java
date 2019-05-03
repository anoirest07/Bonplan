/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entite.Experience;
import Services.ServiceExperience;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class ModifExpCliController implements Initializable {

    @FXML
    private Button bt_menu;
    @FXML
    private TextArea desc;
    @FXML
    private ImageView preuve;
    @FXML
    private AnchorPane anchor1;
    private Experience exper;
    @FXML
    private Button modifpreuve;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    

    public void setExperience (Experience exper)
    {
        this.exper=exper;
    }
    
     public String upload(File file) throws FileNotFoundException, IOException {
        BufferedOutputStream stream = null;
        String globalPath="C:\\wamp64\\www\\image";
        String localPath="localhost:3306/";
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

            Image image = new Image(imageFile);
            preuve.setImage(image);
            exper.setPreuve(selectedFile.getName());
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
    
    
    public void afficher ()
    {
        desc.setText(exper.getDescription_experience());
       javafx.scene.image.Image img = new javafx.scene.image.Image("http://localhost/image/"+exper.getPreuve(),350,350, true, true);
        preuve.setImage(img);
    }
    
   
    @FXML
    private void validermodif(ActionEvent event) throws IOException {
        Services.ServiceExperience sexp = new ServiceExperience();
       
        exper.setDescription_experience(desc.getText());
//        exper.setPreuve(preuve.getImage().toString());

       javafx.scene.image.Image img = new javafx.scene.image.Image("http://localhost/image/"+exper.getPreuve(),350,350, true, true);
       preuve.setImage(img);
        
        sexp.modifierexperience(exper);
//        
//        FXMLLoader root = new FXMLLoader(getClass().getResource("FilClientController.fxml"));
//                                AnchorPane x = root.load();
//                                FilClientController fc = root.getController();
//                                anchor1.getChildren().setAll(x);
    }

    
    
}
