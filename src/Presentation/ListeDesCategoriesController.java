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
import javafx.scene.control.Separator;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Nadia
 */
public class ListeDesCategoriesController {

    @FXML
    private AnchorPane container;
    @FXML
    private Label Titre;
    @FXML
    private Separator sep;
    @FXML
    private VBox vbox1;

    /**
     * Initializes the controller class.
     */
   
    public void initialize() {
        // TODO
    
        Services.ServiceCategorie sr = new ServiceCategorie();
        List <Categorie> categ= new ArrayList<>();
        categ=sr.listCategorie();
        for (Categorie c : categ){
                HBox h1= new HBox();
                
                Label lab1= new Label();
    final    Separator sep = new Separator();      
               Button bt1= new Button("Modifier");
               Button bt2= new Button("Supprimer");  
                lab1.setText(c.getNom_categorie());
                 h1.getChildren().add(lab1);
              ServiceCritere sc= new ServiceCritere();
                 VBox v1= new VBox();   
                 List<CriteresEvaluation>criteres=new ArrayList<>();
                 criteres=sc.FindCritereEvalByCateg(c.getId_categorie());
              for(CriteresEvaluation crit:criteres){
                      Label lab2= new Label();
                     
                lab2.setText(crit.getNom_critere_evaluation());
                   v1.getChildren().add(lab2);
          
          }
              h1.setSpacing(50);
            h1.getChildren().add(v1);     
              h1.getChildren().add(bt1);
            h1.getChildren().add(bt2);
         
        vbox1.getChildren().add(h1);
        vbox1.setSpacing(30);
        sep.setMaxWidth(500);
        vbox1.getChildren().add(sep);           
       bt2.setOnAction(new EventHandler<ActionEvent>() {
         
           @Override
                    public void handle(ActionEvent event) {
       
                        sr.supprimerCategorie(c);
                        System.out.println(c);
//                             vbox1.getChildren().remove(sep);
//                            vbox1.getChildren().remove(h1);
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    
            alert.setTitle("Categorie supprimée");
            alert.setHeaderText("La catégorie choisie a été supprimée");
            
            alert.showAndWait();        
                    
                    }
                });
       
        bt1.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
     Parent homePage;
                        try {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifCategorie.fxml"));
                            homePage = loader.load();
                            ModifCategorieController c1 = new ModifCategorieController();
                            c1 = loader.getController();
                            
                            c.setNom_categorie(c.getNom_categorie());
                            c1.setC(c);
                          
                           c1.affich();
                            System.out.println(c.toString());
        Scene homePage_scene = new Scene(homePage);

        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        app_stage.setScene(homePage_scene);

        app_stage.show();
     
                        
                        } catch (IOException ex) {
                            Logger.getLogger(ListeDesCategoriesController.class.getName()).log(Level.SEVERE, null, ex);
                        }

      
                    }
                });
        } 
    
    
    }
 
    
}
