/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entite.Publicite;
import Services.ServiceEtablissement;
import Services.ServicePublicite;
import ch.qos.logback.core.util.Loader;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author user
 */
public class ModifierPubliciteController  {

    @FXML
    private TextField titre;
    @FXML
    private TextArea desc;
    @FXML
    private ImageView pic;
    @FXML
    private ImageView pic1;
    @FXML
    private ChoiceBox chb;
    Publicite s;
     private boolean okClicked = false;
      private Stage dialogStage;
    /**
     * Initializes the controller class.
     */
    
    public void initialize() {
        
//        titre.setText(s.getTitre());
//        desc.setText(s.getDescription_publicite());
//        desc.setWrapText(true);
//        Image img = new Image("http://localhost/image/"+s.getPhoto_publicite());
//        System.out.println("---------"+s.getPhoto_publicite()+"---------");
//        pic.setImage(img);
//          ServiceEtablissement sp = new ServiceEtablissement();
//         List<String> l=new ArrayList<>();
//        l=sp.nouri(AuthentificationController.c.getId_user());
//        for (int i=0;i<l.size();i++)
//        {
//       chb.getItems().add(l.get(i).toString());
//                }
    }    

    @FXML
    private void uploadPic(ActionEvent event) throws IOException {
         FileChooser fc = new FileChooser();
        File selectedFile = fc.showOpenDialog(null);
        if (selectedFile != null) {
           
            upload(selectedFile);
            String imageFile = selectedFile.toURI().toURL().toString();
            System.err.println(selectedFile.getName());

            Image image = new Image(imageFile);
            pic.setImage(image);
            s.setPhoto_publicite(selectedFile.getName());
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
        
    }}
    
     public String upload(File file) throws FileNotFoundException, IOException {
        BufferedOutputStream stream = null;
        String globalPath="C:\\wamp\\www\\image";
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
    
    
    public void setPublicite(Publicite s) {
        this.s =s ;
        titre.setText(s.getTitre());
        desc.setText(s.getDescription_publicite());
        desc.setWrapText(true);
        Image img = new Image("http://localhost/image/"+s.getPhoto_publicite());
        pic.setImage(img);
          ServiceEtablissement sp = new ServiceEtablissement();
         List<String> l=new ArrayList<>();
        l=sp.nouri(AuthentificationController.c.getId_user());
        for (int i=0;i<l.size();i++)
        {
       chb.getItems().add(l.get(i).toString());
                }
    }
    
    
     private void afficher() {
       titre.setText(s.getTitre());
        desc.setText(s.getDescription_publicite());
       
        System.out.println("http://localhost/image/"+s.getPhoto_publicite());
    
       javafx.scene.image.Image image = new javafx.scene.image.Image("http://localhost/image/"+s.getPhoto_publicite());
        pic.setImage(image);
    }
    
      @FXML
    void update() {
      
            ServicePublicite sp = new ServicePublicite();
            Publicite p = new Publicite();
            p.setTitre(titre.getText());
            p.setDescription_publicite(desc.getText());
            p.setPhoto_publicite(s.getPhoto_publicite());
            p.setId_publicite(s.getId_publicite()); 
            
            sp.modifierpublicite(p);

            Stage s = (Stage )pic.getScene().getWindow();
            s.close();
            afficher();
            
     
            
            
    }
//    public void afficher(){
//   titre.setText(s.getTitre());
//   desc.setText(s.getDescription_publicite());
//
////        ddebutoffre.setValue(java.sql.Date.valueOf(o.getDate_debut()));
//    javafx.scene.image.Image image = new javafx.scene.image.Image("http://localhost/image/" + s.getPhoto_publicite());
//    pic.setImage(image);
//    }
 
    @FXML
    private void Retour(ActionEvent event) {
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
}
