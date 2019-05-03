/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Config.Connexion;
import Entite.Client;
import Entite.Utilisateur;
import Services.ServiceClient;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
//import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.InternetHeaders;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;
import javax.activation.DataSource;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

/**
 * FXML Controller class
 *
 * @author malek
 */
public class NewInscriptionController implements Initializable {

   
    @FXML
    private JFXTextField pren;
    @FXML
    private JFXTextField mail;
   
    @FXML
    private JFXTextField pseudo;
    @FXML
    private JFXTextField pass;
    @FXML
    private JFXButton confInscr;
    @FXML
    private Label outputnom;
    @FXML
    private Label outputprenom;
    @FXML
    private Label outputmail;
    @FXML
    private Label outputpseudo;
    @FXML
    private Label outputpasswd;
    public boolean valide1,valide2,valide3,valide4,valide5;
    @FXML
    private JFXTextField Nom;
    @FXML
    private JFXTextField reg;
    @FXML
    private JFXTextField vil;
   
    @FXML
    private JFXComboBox<String> role;
    @FXML
    private ImageView imageProfil;
    
     int a;
     File pDir ;
    File pfile ;
    String lien ;
    @FXML
    private JFXButton back;
    @FXML
    private Label msgValid;
    

    /**
     * Initializes the controller class.
     */
     
     
    
    
     
       public void verifierEmail(){
        Connexion ds =Connexion.getInstance();
        String Email = mail.getText().toString();       
         ServiceClient uService = new ServiceClient();
         Client u=uService.getClientByEmail(Email);
         
         if (u!=null)
         {outputmail.setStyle("-fx-text-fill: red;");
             outputmail.setText("Adresse déja utilisée");
         valide1=false;}
         else{
             outputmail.setText("Adresse valide");
             outputmail.setStyle("-fx-text-fill: #58e524;");
              valide1=true;
             System.out.println(valide1);
         }
         
        if(!(isValidEmailAddress(Email))){
             {outputmail.setStyle("-fx-text-fill: red;");
             outputmail.setText("Entrer une format valide");
             valide1=false;
             System.out.println(valide1);
             } 
         }
             
       }
    
     
     
    public static boolean isValidEmailAddress(String email) {
   boolean result = true;
   try {
       InternetAddress emailAddr = new InternetAddress(email);
      emailAddr.validate();
   } catch (AddressException ex) {
      result = false;
   }
   return result;
       
       
       } 
       
       
       
        public void verifierNom(){
         
             if(Nom.getText().toString().equals("")){
                 outputnom.setStyle("-fx-text-fill: red;");
                 outputnom.setText("champ Obligatoire");
                 valide2=false;
             }
             else{
                 outputnom.setText("");
                 valide2=true;}
             
         }
        
        
        public void verifierPrenom(){
             if(pren.getText().toString().equals("")){
                 outputprenom.setStyle("-fx-text-fill: red;");
                 outputprenom.setText("champ Obligatoire");
                 valide3=false;
             }
             else{
                 outputprenom.setText("");
             valide3=true;}
         }
        
        
        public void verifierPseudo(){
              Connexion ds =Connexion.getInstance();
        String Pseudo = pseudo.getText().toString();       
         ServiceClient uService = new ServiceClient();
         Client u=uService.getClientByPseudo(Pseudo);
         
         if (u!=null)
         {outputpseudo.setStyle("-fx-text-fill: red;");
             outputpseudo.setText("Pseudo déja utilisée");
         valide4=false;}
         else{
             outputpseudo.setText("Pseudo valide");
             outputpseudo.setStyle("-fx-text-fill: #58e524;");
             valide4=true;
         }
         if (pseudo.equals("")){
             outputpseudo.setStyle("-fx-text-fill: red;");
             outputpseudo.setText("champs Obligatoire");
         valide4=false;   
         }
             
         
         }
        
          public void verifierMotDePasse(){
           if(pass.getText().toString().equals("")){
               outputpasswd.setStyle("-fx-text-fill: red;");
                 outputpasswd.setText("champ Obligatoire");
                 valide5=false;
             }
           else{
                 outputpasswd.setText("");  
                 valide5=true;}
         }
       
       
       
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        role.getItems().setAll("Client","Proprietaire");
        
         a = (int)( Math.random()*( 300000 - 2 + 1 ) ) + 2; 
        pDir = new File("src/Media/Club"+a+".jpg");
        lien = "Media/Club"+a+".jpg" ;
        imageProfil.setImage(new Image("file:src/Media/avatar.png"));
        
        verifierEmail();
        verifierMotDePasse();
        verifierNom();
        verifierPrenom();
        verifierPseudo();
                }    

    @FXML
    private void onclickedConfirmer(ActionEvent event) {
       Connexion ds =Connexion.getInstance();
        copier( pfile,pDir) ;
        
      verifierPseudo();   
     verifierMotDePasse();
     verifierEmail();
     verifierNom();
     verifierPrenom();
    
        
   
      
        
    
    
    if(valide1==true&&valide3==true&&valide4==true&&valide2==true&&valide5==true){
        ServiceClient uService = new ServiceClient();
       uService.addClient(Nom.getText(),pren.getText(),mail.getText(),reg.getText(),role.getSelectionModel().getSelectedItem(),pseudo.getText(),pass.getText(),vil.getText(),lien);
    } else{
        msgValid.setOpacity(1);
        msgValid.setText("veilliez valider tout les champs");
        outputmail.setOpacity(1);
        outputpseudo.setOpacity(1);
        outputpasswd.setOpacity(1);
        outputprenom.setOpacity(1);
        outputnom.setOpacity(1);
    }
       
              
         
      
}

    @FXML
    private void onclickedPhoto(MouseEvent event) throws MalformedURLException {
        
         FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle( "Select image.." );
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter( "JPG", "*.jpg" ),
            new FileChooser.ExtensionFilter( "PNG", "*.png" ),
            new FileChooser.ExtensionFilter( "BMP", "*.bmp" )
        );
        Window stage = null;
        pfile = fileChooser.showOpenDialog(stage);

/* - draw image */
    
        if ( pfile != null )
        {      
            Image image = new Image( pfile.toURI().toURL().toExternalForm() );
            imageProfil.setImage(image);  
        }
    }
    
    public static boolean copier(File source, File dest) { 
        try (InputStream sourceFile = new java.io.FileInputStream(source);  
        OutputStream destinationFile = new FileOutputStream(dest)) { 
        // Lecture par segment de 0.5Mo  
            byte buffer[] = new byte[512 * 1024]; 
            int nbLecture; 
            while ((nbLecture = sourceFile.read(buffer)) != -1){ 
                destinationFile.write(buffer, 0, nbLecture); 
            } 
        }catch (IOException e){ 
            e.printStackTrace(); 
            return false; // Erreur 
        } 
        return true; // Résultat OK   
    }
    

    @FXML
    private void onclickedBack(ActionEvent event) throws IOException {
        Parent test2_parent = FXMLLoader.load(getClass().getResource("Authentification_1.fxml"));
         Scene test2_scene = new Scene(test2_parent);
        Stage test1_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        test1_stage.setScene(test2_scene);
        test1_stage.show();
    }

   
}
