/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Config.Connexion;
import Entite.Admin;
import Entite.Client;
import Entite.Utilisateur;
import Services.ServiceAdmin;
import Services.ServiceClient;
import Services.ServiceUtilisateur;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * FXML Controller class
 *
 * @author malek
 */
public class AuthentificationController implements Initializable {

    @FXML
    private AnchorPane bigAnchor;
    @FXML
    private AnchorPane ancho1;
    @FXML
    private Label SignIn;
    @FXML
    private AnchorPane anchro2;
    @FXML
    private Label keep;
    @FXML
    private JFXTextField pseudo;
    @FXML
    private JFXPasswordField passwd;
    @FXML
    private JFXButton log;
    @FXML
    private JFXButton sgUp;
    @FXML
    private Label output;
    @FXML
    private JFXButton forgotPass;
    @FXML
    private JFXButton admin;
    public static Client c;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    

    
    @FXML
    private void onclickedProfilUser(ActionEvent event) {
          Connexion ds =Connexion.getInstance();
         System.out.println("DS:\n"+ds+"\n connexion etablie");
         String loginI = pseudo.getText().toString();
         String passwordI = passwd.getText().toString();
         System.out.println("loginI="+loginI);
         System.out.println("passwordI="+passwordI);
         
              ServiceClient uService = new ServiceClient();
            
              
         c=uService.getClientByLoginPassword(loginI, passwordI);
         // u.setId_user(c.getId_user());
          System.out.println(c);
          
          if (c!=null && c.getRoles().equals("Client")){
              output.setStyle("o-fx-text-fill: #58e524;");
              output.setText("Bienvenue "+c.getNom());
            //  msg.setText("Redirection en cours ");
            // loading.setOpacity(1);
              
        Timeline timeline = new Timeline(new KeyFrame(
        Duration.millis(3500),
        ae -> {   try {
            Stage stage = (Stage) log.getScene().getWindow();
    // do what you have to do
            stage.close();
            Stage window = new Stage();
            window.centerOnScreen();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("AcceuilClient.fxml")));
            window.setScene(scene);  
             window.show();
                  } catch (IOException ex) {
                      Logger.getLogger(AuthentificationController.class.getName()).log(Level.SEVERE, null, ex);
                  }
            
                    }));
           timeline.play(); 
          }
          else if(c != null && c.getRoles().equals("Administrateur")){
              output.setStyle("o-fx-text-fill: #58e524;");
              output.setText("Bienvenue "+c.getNom());
            //  msg.setText("Redirection en cours ");
            // loading.setOpacity(1);
              
        Timeline timeline = new Timeline(new KeyFrame(
        Duration.millis(3500),
        ae -> {   try {
            Stage stage = (Stage) log.getScene().getWindow();
    // do what you have to do
            stage.close();
            Stage window = new Stage();
            window.centerOnScreen();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("AccueilAdmin1.fxml")));
            window.setScene(scene);  
             window.show();
                  } catch (IOException ex) {
                      Logger.getLogger(AuthentificationController.class.getName()).log(Level.SEVERE, null, ex);
                  }
            
                    }));
           timeline.play(); 
          } else if(c != null && c.getRoles().equals("Proprietaire")){
              output.setStyle("o-fx-text-fill: #58e524;");
              output.setText("Bienvenue "+c.getNom());
            //  msg.setText("Redirection en cours ");
            // loading.setOpacity(1);
              
        Timeline timeline = new Timeline(new KeyFrame(
        Duration.millis(3500),
        ae -> {   try {
            Stage stage = (Stage) log.getScene().getWindow();
    // do what you have to do
            stage.close();
            Stage window = new Stage();
            window.centerOnScreen();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("AccueilProp.fxml")));
            window.setScene(scene);  
             window.show();
                  } catch (IOException ex) {
                      Logger.getLogger(AuthentificationController.class.getName()).log(Level.SEVERE, null, ex);
                  }
            
                    }));
           timeline.play(); 
              
          }
          else {
              output.setStyle("-fx-text-fill:  #93858a;"); 
              output.setText("Veuillez vérifier vos informations!");
              output.setOpacity(1);
        Timeline timeline = new Timeline(new KeyFrame(
        Duration.millis(1000),
        ae -> {   
            pseudo.setStyle("-fx-text-inner-color:white");
            passwd.setStyle("-fx-text-inner-color:white");
                    }));
           timeline.play();
           passwd.setStyle("-fx-text-inner-color:red");
              pseudo.setStyle("-fx-text-inner-color:red"); 
                        
          }
         
        
          
    }
   
    @FXML
    private void onclickedInscription(ActionEvent event) throws IOException {
        Parent test2_parent = FXMLLoader.load(getClass().getResource("newInscription.fxml"));
         Scene test2_scene = new Scene(test2_parent);
        Stage test1_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        test1_stage.setScene(test2_scene);
        test1_stage.show();
    }

    @FXML
    private void onclickedRecupererPass(ActionEvent event) throws AddressException, MessagingException, IOException {
        Parent test2_parent = FXMLLoader.load(getClass().getResource("MailCheck.fxml"));
         Scene test2_scene = new Scene(test2_parent);
        Stage test1_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        test1_stage.setScene(test2_scene);
        test1_stage.show();
        
    }

    @FXML
    private void onclickedEspaceAdmin(ActionEvent event) throws IOException {
        Parent test2_parent = FXMLLoader.load(getClass().getResource("LoginAdmin.fxml"));
         Scene test2_scene = new Scene(test2_parent);
        Stage test1_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        test1_stage.setScene(test2_scene);
        test1_stage.show();
        
    }

}
