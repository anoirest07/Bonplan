/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Config.Connexion;
import Entite.Admin;
import Services.ServiceAdmin;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
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
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author malek
 */
public class LoginAdminController implements Initializable {

    @FXML
    private JFXTextField pseud;
    @FXML
    private JFXPasswordField pass;
    @FXML
    private JFXButton back;
    @FXML
    private JFXButton log;
    @FXML
    private Label output;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onclickedAuthentification(ActionEvent event) throws IOException {
        
         Parent test2_parent = FXMLLoader.load(getClass().getResource("Authentification_1.fxml"));
         Scene test2_scene = new Scene(test2_parent);
        Stage test1_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        test1_stage.setScene(test2_scene);
        test1_stage.show();
        
    }

    @FXML
    private void onclickedEspaceAdmin(ActionEvent event) {
        Connexion ds =Connexion.getInstance();
         System.out.println("DS:\n"+ds+"\n connexion etablie");
         String loginI = pseud.getText().toString();
         String passwordI = pass.getText().toString();
         System.out.println("loginI="+loginI);
         System.out.println("passwordI="+passwordI);
        
         ServiceAdmin aService = new ServiceAdmin();
          Admin a= aService.getAdminByLoginPassword(loginI, passwordI);
          if (a!=null){
              output.setStyle("o-fx-text-fill: #58e524;");
              output.setText("bienvenue "+a.getNom());
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
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("accueilAdmin1.fxml")));
            window.setScene(scene);  
             window.show();
                  } catch (IOException ex) {
                      Logger.getLogger(AuthentificationController.class.getName()).log(Level.SEVERE, null, ex);
                  }
            
                    }));
           timeline.play(); 
          }
          else {
              output.setStyle("-fx-text-fill: #ffffff;"); 
              output.setText("veuillez verifier vos informations");
        Timeline timeline = new Timeline(new KeyFrame(
        Duration.millis(1000),
        ae -> {   
            pseud.setStyle("-fx-text-inner-color:white");
            pass.setStyle("-fx-text-inner-color:white");
                    }));
           timeline.play();
           pass.setStyle("-fx-text-inner-color:red");
              pseud.setStyle("-fx-text-inner-color:red"); 
                        
          }
        
        
    }
    
}
