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
import com.jfoenix.controls.JFXListView;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.Separator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Nadia
 */
public class AjouterCategorieAdminController {

    @FXML
    private AnchorPane container;
    @FXML
    private Label titre;
    @FXML
    private JFXListView<String> listedemandes;
    @FXML
    private Label nomcat;
    @FXML
    private TextField nom;
    @FXML
    private Label critere;
    @FXML
    private TextField crit1;
    @FXML
    private TextField crit2;
    @FXML
    private TextField crit3;
    @FXML
    private TextField crit4;
    @FXML
    private TextField crit5;
     @FXML
    private Button btn1;
    @FXML
    private ImageView icon1;
    @FXML
    private ImageView icon4;
    @FXML
    private ImageView icon3;
    @FXML
    private ImageView icon6;
    @FXML
    private TextField exist;
    @FXML
    private Label laaaaab;
    @FXML
    private ImageView icon41;
    private ObservableList<String> demandes ;
public ServiceCategorie sc= new ServiceCategorie();
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
            //    ScrollBar s = new ScrollBar();
                Label lab1= new Label();
    final    Separator sep = new Separator();      
               Button bt1= new Button("Modifier");
               Button bt2= new Button("Supprimer"); 
               HBox h2= new HBox();
            h2.getChildren().add(bt1);
             
            h2.getChildren().add(bt2);
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
            h1.getChildren().add(h2);
          
        vbox1.getChildren().add(h1);
        vbox1.setSpacing(30);
        sep.setMaxWidth(500);
        vbox1.getChildren().add(sep); 
           
                vbox1.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;"
        + "-fx-border-width: 2;" + "-fx-border-insets: 5;"
        + "-fx-border-radius: 5;" + "-fx-border-color: grey;");
//        s.setMin(0);
//        s.setOrientation(Orientation.VERTICAL);
//        s.setPrefHeight(180);
//        s.setMax(500);
//        vbox1.getChildren().add(s);
       bt2.setOnAction(new EventHandler<ActionEvent>() {
         
           @Override
                    public void handle(ActionEvent event) {
       
                        sr.supprimerCategorie(c);
                             vbox1.getChildren().remove(sep);
                            vbox1.getChildren().remove(h1);
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

        Stage app_stage = new Stage();

        app_stage.setScene(homePage_scene);

        app_stage.show();
     
                        
                        } catch (IOException ex) {
                            Logger.getLogger(ListeDesCategoriesController.class.getName()).log(Level.SEVERE, null, ex);
                        }

      
                    }
                });
        } 
    
    
    
   demandes = FXCollections.observableArrayList();

                List<Categorie> ls = sc.listDemandesCategorie();
                for(Categorie c :  ls){
             demandes.add(c.getNom_categorie());
                     
                }
 
              listedemandes.setItems(demandes);
        }    
       @FXML
    private void edition(MouseEvent event) {
         if (listedemandes.getSelectionModel().getSelectedItem() != null) 
        {
            exist.setVisible(true);
         
       Categorie c = new Categorie();
       c=sc.afficherCategorieParNom(listedemandes.getSelectionModel().getSelectedItem());
        //    System.out.println(c);        
exist.setText(c.getNom_categorie());
    }

    
}
    @FXML
    private void Ajouter(ActionEvent event) {
  Categorie c= new Categorie();
        if(crit1.getText().isEmpty() || crit2.getText().isEmpty() || crit3.getText().isEmpty() || crit4.getText().isEmpty() || crit5.getText().isEmpty()) 
   { Alert alert = new Alert(Alert.AlertType.ERROR);
    
            alert.setTitle("Erreur critère");
            alert.setHeaderText("Nombre de critères insuffisant");
            alert.setContentText("Veuillez saisir 5 critères d'évaluation");
            alert.showAndWait(); 
        
   }
   else {
          if(nom.getText().isEmpty()){
 
      
      c=  sc.afficherCategorieParNom(exist.getText());
      c.setEnabled(1);
      sc.modifierCategorie(c);
   
          
                  
   }
    else if(exist.getText().isEmpty()){
 
   c.setNom_categorie(nom.getText());
       
      sc.ajouterCategorie(c);}
   
        ServiceCritere scrit= new ServiceCritere();     
        CriteresEvaluation eval= new CriteresEvaluation();
        eval.setNom_critere_evaluation(crit1.getText());
        eval.setCategorie(c);
        scrit.ajoutercritere(eval);  
        CriteresEvaluation eva2= new CriteresEvaluation();
        eva2.setNom_critere_evaluation(crit2.getText());
        eva2.setCategorie(c);
     scrit.ajoutercritere(eva2);     
        CriteresEvaluation eva3= new CriteresEvaluation();
        eva3.setNom_critere_evaluation(crit3.getText());
        eva3.setCategorie(c);
        scrit.ajoutercritere(eva3); 
        CriteresEvaluation eva4= new CriteresEvaluation();
        eva4.setNom_critere_evaluation(crit4.getText());
        eva4.setCategorie(c);
        scrit.ajoutercritere(eva4);
        CriteresEvaluation eva5= new CriteresEvaluation();
        eva5.setNom_critere_evaluation(crit5.getText());
        eva5.setCategorie(c);
        scrit.ajoutercritere(eva5);        
                  Alert alertaj = new Alert(Alert.AlertType.CONFIRMATION);
    
            alertaj.setTitle("Ajout catégorie");
            alertaj.setHeaderText("Catégorie ajoutée");
            alertaj.setContentText(" Critères d'évaluation ajoutés");
            alertaj.showAndWait(); 
         
     initialize();
     //remove and add in vbox
     
        
        }
          
    
    }
    
    
//    private void redirection(ActionEvent event) throws IOException {
//   Parent homePage = FXMLLoader.load(getClass().getResource("ListeDesCategories.fxml"));
//
//        Scene homePage_scene = new Scene(homePage);
//
//        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//
//        app_stage.setScene(homePage_scene);
//
//        app_stage.show();
//    
//    }


}