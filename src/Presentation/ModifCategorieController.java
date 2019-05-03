/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entite.Categorie;
import Entite.CriteresEvaluation;
import Services.ServiceCategorie;
import Services.ServiceCritere;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Nadia
 */
public class ModifCategorieController {

    @FXML
    private AnchorPane container;
    @FXML
    private  JFXTextField nomcateg;
    @FXML
    private Label categorie;
    @FXML
    private Label criteres;
    private Categorie c ; 
    private VBox vbox1;
    @FXML
    private JFXTextField lab1;
    @FXML
    private JFXTextField lab2;
    @FXML
    private JFXTextField lab3;
    @FXML
    private JFXTextField lab4;
    @FXML
    private JFXTextField lab5;
    @FXML
    private JFXButton bt1;
private Stage dialogStage;
    /**
     * Initializes the controller class.
     */
    
    public void initialize() {
        // TODO

    }   

    public void setC(Categorie c) {
        this.c = c;
    }
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void affich(){
        nomcateg.setText(c.getNom_categorie());
       
         Services.ServiceCritere s= new ServiceCritere();
         List<CriteresEvaluation> test = new ArrayList<>();
    test=s.FindCritereEvalByCateg(c.getId_categorie());
for(CriteresEvaluation crit:test)
   
{                     
    lab1.setText(test.get(0).getNom_critere_evaluation());
  lab2.setText(test.get(1).getNom_critere_evaluation());
  lab3.setText(test.get(2).getNom_critere_evaluation());
   lab4.setText(test.get(3).getNom_critere_evaluation());
  lab5.setText(test.get(4).getNom_critere_evaluation());
    
}
    }
         
           
    @FXML
    private void enregistrer(ActionEvent event) throws IOException {
          ServiceCategorie scat= new ServiceCategorie();
                    c.setNom_categorie(nomcateg.getText());
                     scat.modifierCategorie(c);
           ServiceCritere se= new ServiceCritere();
          List<CriteresEvaluation> lo=new ArrayList<>();
          lo= se.FindCritereEvalByCateg(c.getId_categorie());
          System.out.println("Prjjjjjjjj"+lo);      
         
          lo.get(0).setNom_critere_evaluation(lab1.getText());
             
              lo.get(1).setNom_critere_evaluation(lab2.getText());
                        lo.get(2).setNom_critere_evaluation(lab3.getText());
                        lo.get(3).setNom_critere_evaluation(lab4.getText());
                        lo.get(4).setNom_critere_evaluation(lab5.getText());
                for(CriteresEvaluation cr :lo){
                        se.editcritere(cr);
                 initialize();}
                 Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    
            alert.setTitle("Categorie modifiée");
            alert.setHeaderText("La catégorie choisie a été modifiée");
            alert.setContentText("Votre modification a été enregistrée");
            alert.showAndWait();
            alert.close();
           
                //       System.out.println(cr);
//                     FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterCategorieAdmin.fxml"));
//                     AnchorPane ancho = loader.load();
//                     container
//                     container.getChildren().add(ancho);
//                     
                     
                
                  
    }

 

    
    
}
