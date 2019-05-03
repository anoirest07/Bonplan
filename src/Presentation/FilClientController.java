/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entite.CriteresEvaluation;
import Entite.Evaluation;
import Entite.Experience;
import Services.ServiceCritere;
import Services.ServiceEvaluation;
import Services.ServiceExperience;
import com.jfoenix.controls.JFXButton;
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
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class FilClientController implements Initializable {

    @FXML
    private VBox vbox;
    @FXML
    private AnchorPane anchor1;

    private Rating r5;
    private Rating r4;
    private Rating r2;
    private Rating r3;
    private Rating rmoy;
    @FXML
    private JFXButton ajout;


        
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServiceExperience sxp = new ServiceExperience();
        List<Experience> exps = new ArrayList<>();
                
        exps=sxp.afficherexperience();
        
        for (Experience exp : exps){
           
            
                HBox h1= new HBox();
                HBox h2= new HBox();
                VBox v1= new VBox();
                HBox h3= new HBox();
                VBox vev = new VBox();
                VBox vcrit = new VBox();
                HBox heval = new HBox();
                
                
                VBox vpane = new VBox();
                
                
                
                heval.setSpacing(30);
                
                
                h1.setSpacing(20);
                h2.setSpacing(50);
                v1.setSpacing(25);
                v1.setPrefWidth(1200);
////                h2.setPrefHeight(h2.USE_COMPUTED_SIZE);
//                v1.setPrefHeight(v1.USE_COMPUTED_SIZE);
vbox.setSpacing(50);
h3.setSpacing(100);
h3.setAlignment(Pos.CENTER);

Label lab1= new Label();
Label lab2= new Label();
Label lab3= new Label();
Label lab4= new Label();
Label lab5= new Label();
Label lab6= new Label();
Label lab7= new Label();
Label lab8= new Label();
Label ldate= new Label();


ServiceCritere scrit = new ServiceCritere();
List<CriteresEvaluation> listcrit = new ArrayList<>();
listcrit = scrit.Rechcritere(exp.getEtablissement().getCategorie().getId_categorie());

Label l1= new Label (listcrit.get(0).getNom_critere_evaluation());
Label l2 = new Label(listcrit.get(1).getNom_critere_evaluation());
Label l3= new Label(listcrit.get(2).getNom_critere_evaluation());
Label l4 = new Label(listcrit.get(3).getNom_critere_evaluation());
Label l5= new Label(listcrit.get(4).getNom_critere_evaluation());

ImageView img1= new ImageView();
ImageView img2= new ImageView();

final Separator separator = new Separator();
final Separator separator1 = new Separator();
final Separator separator2 = new Separator();
final Separator separator3 = new Separator();

//              lab5.setMaxHeight(350);
//              lab5.setMaxWidth(650);

lab5.setWrapText(true);

lab5.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;"
        + "-fx-border-width: 2;" + "-fx-border-insets: 10;"
        + "-fx-border-radius: 5;" + "-fx-border-color: red;");


lab1.setText(exp.getUtilisateur().getNom());                

lab2.setText(exp.getEtablissement().getNom_etablissement());
lab3.setText(exp.getEtablissement().getCategorie().getNom_categorie());
lab4.setText(exp.getEtablissement().getAdresse_etablissement());
lab5.setText(exp.getDescription_experience());
lab6.setText(" -- ");
lab7.setText(" -- ");
lab8.setText(" IMAGE ETABLISSEMENT ");
ldate.setText(exp.getDate_exp().toString());

System.out.println("  ddddddddddddddd   "+exp.getDate_exp());

lab1.setFont(javafx.scene.text.Font.font("COURIER", 20));
lab2.setFont(javafx.scene.text.Font.font("COURIER", 20));
lab3.setFont(javafx.scene.text.Font.font("COURIER", 20));
lab4.setFont(javafx.scene.text.Font.font("COURIER", 20));



javafx.scene.image.Image img = new javafx.scene.image.Image("http://localhost/image/"+exp.getPreuve(),550,350, true, true);
img1.setImage(img);

javafx.scene.image.Image imgg = new javafx.scene.image.Image("http://localhost/image/"+exp.getEtablissement().getPhoto_etablissement(),80,80, true, true);
img2.setImage(imgg);

h1.getChildren().add(img2);
//              h1.getChildren().add(lab8);
h1.getChildren().add(lab2);
h1.getChildren().add(lab6);
h1.getChildren().add(lab3);
h1.getChildren().add(lab7);
h1.getChildren().add(lab4);                

//                h2.getChildren().add(lab9);
h2.getChildren().add(img1);
//                h2.getChildren().add(lab10);
h2.getChildren().add(lab5);

v1.getChildren().add(lab1);
v1.getChildren().add(ldate);
v1.getChildren().add(separator1);
v1.getChildren().add(h1);
v1.getChildren().add(separator2);

v1.getChildren().add(h2);
vbox.getChildren().add(v1);



//                separator.setAlignment(Pos.CENTER_LEFT);
//separator.setMaxWidth(150);
//
//separator.setMaxHeight(10);    
//vbox.getChildren().add(separator);
//

vbox.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;"
        + "-fx-border-width: 2;" + "-fx-border-insets: 5;"
        + "-fx-border-radius: 5;" + "-fx-border-color: blue;");

v1.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;"
        + "-fx-border-width: 2;" + "-fx-border-insets: 5;"
        + "-fx-border-radius: 5;" + "-fx-border-color: grey;");

vpane.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;"
        + "-fx-border-width: 2;" + "-fx-border-insets: 5;"
        + "-fx-border-radius: 5;" + "-fx-border-color: green;");

heval.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;"
        + "-fx-border-width: 2;" + "-fx-border-insets: 5;"
        + "-fx-border-radius: 5;" + "-fx-border-color: pink;");



Services.ServiceEvaluation sev = new ServiceEvaluation();
List<Evaluation> listeval = new ArrayList<>();
listeval=sev.listEvaluationExp(exp.getId_exp());



Rating  r1= new Rating();
r1.setRating(listeval.get(0).getNote());
r1.setDisable(true);

Rating  r2= new Rating();
r2.setRating(listeval.get(1).getNote());
r2.setDisable(true);

Rating  r3= new Rating();
r3.setRating(listeval.get(2).getNote());
r3.setDisable(true);

Rating  r4= new Rating();
r4.setRating(listeval.get(3).getNote());
r4.setDisable(true);

Rating  r5= new Rating();
r5.setRating(listeval.get(4).getNote());
r5.setDisable(true);

Rating rmoy = new Rating();
rmoy.setRating(sev.moyTotExp(exp.getId_exp()));


vev.getChildren().add(rmoy);
vev.getChildren().add(r1);
vev.getChildren().add(r2);
vev.getChildren().add(r3);
vev.getChildren().add(r4);
vev.getChildren().add(r5);


vcrit.setSpacing(30);
vcrit.getChildren().add(l1);
vcrit.getChildren().add(l2);
vcrit.getChildren().add(l3);
vcrit.getChildren().add(l4);
vcrit.getChildren().add(l5);

heval.getChildren().add(vcrit);
heval.getChildren().add(vev);
heval.getChildren().add(vpane);


AnchorPane ancom = new AnchorPane();
vpane.getChildren().add(ancom);
//FXMLLoader loader = new FXMLLoader(getClass().getResource("CommentaireExperience"));
//Parent root = (Parent) loader.load();
//CommentaireExperienceController controller = loader.getController();
//ancom.getChildren().clear();
////anchor.set().setRoot(root);
//ancom.getChildren().add(root);
Parent root;
         FXMLLoader loader = new FXMLLoader(getClass().getResource("CommentaireExperience.fxml"));
            try {
                root  = loader.load();
                ancom.getChildren().clear();
            ancom.getChildren().add(root);
            } catch (IOException ex) {
                Logger.getLogger(CommentaireExperienceController.class.getName()).log(Level.SEVERE, null, ex);
            }
            





vev.setAlignment(Pos.CENTER);
v1.getChildren().add(separator);

v1.getChildren().add(heval);

JFXButton modif = new JFXButton("Modifier");
JFXButton supp = new JFXButton("Supprimer");
modif.setButtonType(JFXButton.ButtonType.RAISED);
supp.setButtonType(JFXButton.ButtonType.RAISED);
modif.setFont(javafx.scene.text.Font.font(15));
supp.setFont(javafx.scene.text.Font.font(15));


if (exp.getUtilisateur().getId_user()== AuthentificationController.c.getId_user()) /* id user connecté pour ajouter mod supp à ses exp seulement */
{
    h3.getChildren().add(modif);
    h3.getChildren().add(supp);
    v1.getChildren().add(separator3);
    v1.getChildren().add(h3);
}

modif.setOnAction(new EventHandler<ActionEvent>() {
    @Override
    public void handle(ActionEvent event) {
        try {
            FXMLLoader root = new FXMLLoader(getClass().getResource("ModifExpCli.fxml"));
            AnchorPane x = root.load();
            ModifExpCliController mexp = root.getController();
            anchor1.getChildren().setAll(x);
            mexp.setExperience(exp);
            mexp.afficher();
            
        } catch (IOException ex) {
            Logger.getLogger(FilClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
});

supp.setOnAction(new EventHandler<ActionEvent>() {
    @Override
    public void handle(ActionEvent event) {
        sxp.supprimerexperience(exp);
        vbox.getChildren().remove(separator);
        vbox.getChildren().remove(v1);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        
        alert.setTitle("Expérience supprimée");
        alert.setHeaderText("L'expérience choisie a été supprimée");
        alert.showAndWait();
        
    }
});   

    }   
             
    
}

    @FXML
    private void ajoutexp(ActionEvent event) throws IOException {
        
        FXMLLoader root = new FXMLLoader(getClass().getResource("AjoutExpClient.fxml"));
        ScrollPane x = root.load();
        AjoutExpClientController aexp = root.getController();
        anchor1.getChildren().setAll(x);


    }
    
}