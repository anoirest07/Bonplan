/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entite.Categorie;
import Entite.CriteresEvaluation;
import Entite.Etablissement;
import Entite.Evaluation;
import Entite.Experience;
import Entite.Utilisateur;
import Services.ServiceCategorie;
import Services.ServiceCritere;
import Services.ServiceEtablissement;
import Services.ServiceEvaluation;
import Services.ServiceExperience;
import Services.ServiceUtilisateur;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class AjoutExpClientController  {

    @FXML
    private JFXComboBox<String> combo1;
    @FXML
    private JFXTextField etab;
    @FXML
    private JFXButton btnajout;
  

    @FXML
    private JFXTextArea desc;
    @FXML
    private ImageView img;
    
    private Stage dialogStage;
    private Experience x;
    private String ImageFile;
    
    @FXML
    private JFXButton up;
    @FXML
    private Rating r1;
    @FXML
    private Rating r5;
    @FXML
    private Rating r4;
    @FXML
    private Rating r2;
    @FXML
    private Rating r3;
    @FXML
    private Label l1;
    @FXML
    private Label l2;
    @FXML
    private Label l3;
    @FXML
    private Label l4;
    @FXML
    private Label l5;
    @FXML
    private AnchorPane anchorpane2;
    /**
     * Initializes the controller class.
     */
    
    public void initialize() {
//        vrat.getChildren().clear();
       
        
        ServiceCategorie sc = new ServiceCategorie();
        List<Categorie> listecat = new ArrayList<>();
        listecat= sc.listCategorie();
        for (Categorie c: listecat)
        {
            combo1.getItems().add(c.getNom_categorie());
        }    
        
        combo1.setOnAction((event) -> {
             final Categorie c11 = sc.afficherCategorieParNom(combo1.getSelectionModel().getSelectedItem());
       ServiceCritere scrit = new ServiceCritere();
        List<CriteresEvaluation> listcrit = new ArrayList<>();
        listcrit = scrit.Rechcritere(c11.getId_categorie());
        
        
          

         /*A VERIFIER METHODE HORS COMBO ON ACTION */   
           List<String> etabs2 = new ArrayList<>();
            etabs2.clear();
ServiceEtablissement setab = new ServiceEtablissement();
        for (String s : setab.listnomEtablissement(c11.getId_categorie()))
        {
            etabs2.add(s);
            System.out.println(" /////////");
            System.out.println(s);
            System.out.println(" /////////");
        }
                TextFields.bindAutoCompletion(etab,etabs2);

  l1.setText(listcrit.get(0).getNom_critere_evaluation());
  l2.setText(listcrit.get(1).getNom_critere_evaluation());
  l3.setText(listcrit.get(2).getNom_critere_evaluation());
  l4.setText(listcrit.get(3).getNom_critere_evaluation());
  l5.setText(listcrit.get(4).getNom_critere_evaluation());
 
        
        });
          
    }    
    
//    public void setDialogStage(Stage dialogStage) {
//        this.dialogStage = dialogStage;
//    }
//    
//     public void setExperience(Experience x) {
//            this.x =x ;
//            desc.setText(x.getDescription_experience());
//        
////        photo.setImage(s.getPhoto_publicite().to);
//        
//       // System.out.println(s.toString());
//    }
//     
//     public boolean isOkClicked() {
//
//            return okClicked;
//        }
//    
    
Services.ServiceExperience sxp = new ServiceExperience();
    Experience exp=new Experience();
 
    
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
            img.setImage(image);
            exp.setPreuve(selectedFile.getName());
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
 
     
        public String sendSms() {
		try {
			// Construct data
			String apiKey = "apikey=" + "uqKEaKSaHVU-t9hQp792oVVjjyqDSnUJ5m4qL4W4sA	";
			String message = "&message=" + "Vous avez recu une mauvaise évaluation sur votre établissement";
			String sender = "&sender=" +"BonPlan";
			String numbers = "&numbers=" + "0021650189147";
			
			// Send data
			HttpURLConnection conn = (HttpURLConnection) new URL("https://api.txtlocal.com/send/?").openConnection();
			String data = apiKey + numbers + message + sender;
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
			conn.getOutputStream().write(data.getBytes("UTF-8"));
			final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			final StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = rd.readLine()) != null) {
				stringBuffer.append(line);
			}
			rd.close();
			
                        System.out.println(" HELLOOOOOOO");
			return stringBuffer.toString();
		} catch (Exception e) {
			System.out.println("Error SMS "+e);
                                                System.out.println(" BYYYYYE");

			return "Error "+e;
		}
	} 

    @FXML
    private void ajouter(ActionEvent event) {
         
 
   if(combo1.getSelectionModel().getSelectedItem().isEmpty() || etab.getText().isEmpty() || /*img. ||*/ desc.getText().isEmpty() ) 
   { Alert alert = new Alert(Alert.AlertType.ERROR);
    
            alert.setTitle("Erreur!");
            alert.setHeaderText("Veuillez remplir tous les champs");
            alert.setContentText("Veuillez remplir tous les champs");
            alert.showAndWait(); 
        
   }
   
   else if (r1.getRating() == 0|| r2.getRating()==0 ||r3.getRating() ==0||r4.getRating() ==0||r5.getRating() ==0)
   {Alert alert = new Alert(Alert.AlertType.ERROR);
    
            alert.setTitle("Erreur!");
            alert.setHeaderText("Veuillez evaluer tous les critères");
            alert.setContentText("Veuillez eveluer tous les criteres");
            alert.showAndWait(); 
            
        }
 
    else {
       
       
       try {
           ServiceEtablissement setab = new ServiceEtablissement();
           Etablissement inter = new Etablissement();
           
           ServiceCategorie sc=new ServiceCategorie();
           Categorie c = new Categorie();
           c=sc.afficherCategorieParNom(combo1.getSelectionModel().getSelectedItem());
           
           inter = setab.chercherEtablissementidcat(c.getId_categorie());
           
           System.out.println(inter.getNom_etablissement());
           exp.setEtablissement(inter);
           exp.setDescription_experience(desc.getText());
           exp.setDate_exp(Date.valueOf(LocalDate.now()));
           Services.ServiceUtilisateur su = new ServiceUtilisateur();
           Utilisateur u =su.getUserById(AuthentificationController.c.getId_user());
           System.out.println("youssef"+u.getNom());
           exp.setUtilisateur(u);
           System.out.println(u.getNom());
           
//       System.out.println("TTTTTTTTTTTTTTTTTTTTTTTTT");
//       System.out.println(inter);

sxp.ajouterexperience(exp);

ServiceCritere scrit = new ServiceCritere();
List<CriteresEvaluation> listcrit = new ArrayList<>();
listcrit=scrit.Rechcritere(c.getId_categorie());

//       for (CriteresEvaluation crit : listcrit) {
//           System.out.println(crit);
//           
//       }
//
ServiceEvaluation seval = new ServiceEvaluation();
Evaluation ev1 = new Evaluation();
ev1.setExperience(exp);
ev1.setCritere_evaluation(listcrit.get(0));
ev1.setNote(r1.getRating());

Evaluation ev2 = new Evaluation();
ev2.setExperience(exp);
ev2.setCritere_evaluation(listcrit.get(1));
ev2.setNote(r2.getRating());

Evaluation ev3 = new Evaluation();
ev3.setExperience(exp);
ev3.setCritere_evaluation(listcrit.get(2));
ev3.setNote(r3.getRating());

Evaluation ev4 = new Evaluation();
ev4.setExperience(exp);
ev4.setCritere_evaluation(listcrit.get(3));
ev4.setNote(r4.getRating());

Evaluation ev5 = new Evaluation();
ev5.setExperience(exp);
ev5.setCritere_evaluation(listcrit.get(4));
ev5.setNote(r5.getRating());

seval.ajouterevaluation(ev1);
seval.ajouterevaluation(ev2);
seval.ajouterevaluation(ev3);
seval.ajouterevaluation(ev4);
seval.ajouterevaluation(ev5);


       if ((ev1.getNote()<3) || (ev2.getNote()<3)||(ev3.getNote()<3)||(ev4.getNote()<3)||(ev5.getNote()<3))
           
       {
       
      sendSms();
       
       
       
       }
System.out.println(exp.getId_exp());


System.out.println("MOY"+ seval.moyTotExp(exp.getId_exp()));
Alert alertaj = new Alert(Alert.AlertType.CONFIRMATION);
alertaj.setTitle("Ajout experience");
alertaj.setHeaderText("Eperience ajoutée");
alertaj.setContentText(" Experience ajoutés");
alertaj.showAndWait();
FXMLLoader loader = new FXMLLoader(getClass().getResource("FilClient.fxml"));
Parent root = (Parent) loader.load();
FilClientController controller = loader.getController();

anchorpane2.getChildren().clear();

anchorpane2.getChildren().add(root);



// ServiceCategorie sc= new ServiceCategorie();
//        Categorie c= new Categorie();
//   c.setNom_categorie(nom.getText());
//       
//      sc.ajouterCategorie(c);
//
//        ServiceCritere scrit= new ServiceCritere();     
//        CriteresEvaluation eval= new CriteresEvaluation();
//        eval.setNom_critere_evaluation(crit1.getText());
//        eval.setCategorie(c);
//        scrit.ajoutercritere(eval);  
//        CriteresEvaluation eva2= new CriteresEvaluation();
//        eva2.setNom_critere_evaluation(crit2.getText());
//        eva2.setCategorie(c);
//     scrit.ajoutercritere(eva2);     
//        CriteresEvaluation eva3= new CriteresEvaluation();
//        eva3.setNom_critere_evaluation(crit3.getText());
//        eva3.setCategorie(c);
//        scrit.ajoutercritere(eva3); 
//        CriteresEvaluation eva4= new CriteresEvaluation();
//        eva4.setNom_critere_evaluation(crit4.getText());
//        eva4.setCategorie(c);
//        scrit.ajoutercritere(eva4);
//        CriteresEvaluation eva5= new CriteresEvaluation();
//        eva5.setNom_critere_evaluation(crit5.getText());
//        eva5.setCategorie(c);
//        scrit.ajoutercritere(eva5);
//                  
//    }
       } catch (IOException ex) {
           Logger.getLogger(AjoutExpClientController.class.getName()).log(Level.SEVERE, null, ex);
       }
    }

    }
    }

//    @FXML
//    private void njarab(KeyEvent event) {
//        ServiceEtablissement se = new ServiceEtablissement();
//        Etablissement e = se.chercherEtab(etab.getText(), combo1.getValue());
//        etab.setText(e.getNom_etablissement());
//    }

   

    
    
    
    

