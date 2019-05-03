/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entite.Utilisateur;
import Services.ServicePublicite;
import Services.ServiceUtilisateur;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author user
 */
public class StatistiquePubController implements Initializable {

    @FXML
    private PieChart piechart;
    @FXML
    private AnchorPane stanch;
           Services.ServicePublicite sp = new ServicePublicite();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
                    StatPub();

        
    
    }    
    

    
    
    
       public void StatPub() {
//Services.ServiceUtilisateur SU = new ServiceUtilisateur();
//           Utilisateur u = new Utilisateur();
           //u.setId_user(AuthentificationController.c.getId_user());
       // new FadeInUpTransition(commBar).play();
        //new FadeInDowntransition(UserComm).play();
       
        piechart.getData().clear();
      piechart.setData(sp.StatPublicite(AuthentificationController.c.getId_user()));
        piechart.setAnimated(true);
        piechart.setVisible(true);
        piechart.setTitle("Nombre de clicks par publicité");
           
        
 final Label caption = new Label("");
//        caption.setTextFill(Paint.valueOf(Color.MAGENTA.toString()));

        caption.setStyle("-fx-font: 15 arial;");
 
        for (final PieChart.Data data : piechart.getData()) {
            data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    caption.setTranslateX(e.getSceneX());
                    caption.setTranslateY(e.getSceneY());
 
                    caption.setText(String.valueOf(data.getPieValue()));
                }
            });
           }
        
        AnchorPane root = new AnchorPane();
        root.getChildren().addAll(piechart, caption);
        stanch.getChildren().add(root);

    }
}
