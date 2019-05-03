/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entite.Etablissement;
import Entite.Offre;
import Entite.OffreProduit;
import Entite.Produit;
import Services.ServiceEtablissement;
import Services.ServiceOffre;
import Services.ServiceOffreProduit;
import Services.ServiceProduit;
import com.jfoenix.controls.JFXButton;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Yassine
 */
public class AjouterOffreFXMLController {
    
    @FXML
    private ImageView picajout;
    @FXML
    private TextArea descajout;
    @FXML
    private Button canoffre;
    @FXML
    private Button ajoutoffre;
    @FXML
    private JFXButton upload_add_offre;
    @FXML
    private DatePicker ddebutoffre;
    @FXML
    private DatePicker dfinoffre;
    private ObservableList<Produit> dataprod;
    private Stage odialogStage;
    private Offre o = new Offre();
    private boolean okClicked = false;
    ServiceOffre sero = new ServiceOffre();
    @FXML
    private Label titre1;
    @FXML
    private TextField ajouttitre;
    @FXML
    private TableView<Produit> table_produit;
    @FXML
    private TableColumn<?, ?> nom_produit;
    @FXML
    private TableColumn<?, ?> prix_produit;
    
    private ObservableList<Produit> data;
    
    ServiceProduit service = new ServiceProduit();
    OffreProduit op = new OffreProduit();
    ServiceOffreProduit serop = new ServiceOffreProduit();
    Produit p;
    @FXML
    private Label titre11;
    @FXML
    private Button add;
    @FXML
    private Button edit;
    @FXML
    private Button delete;
    @FXML
    private AnchorPane anchorProd;
    @FXML
    private ImageView photo2;
    @FXML
    private ChoiceBox<String> chetab;

    /**
     * Initializes the controller class.
     */
    
    public void initialize() {
        // TODO
        List<String> l = new ArrayList<>();
        // l=sp.nouri();
        if (o != null) {
            System.out.println("offre OPENED");
        } else {
            System.out.println("done");
        }
        
       ServiceEtablissement sp = new ServiceEtablissement();       
        l=sp.nouri(AuthentificationController.c.getId_user());
        for (int i=0;i<l.size();i++)
        {
       chetab.getItems().add(l.get(i).toString());
                }
        
        data = FXCollections.observableArrayList();
        List<Produit> ls = service.afficherProduit();
        ls.stream().forEach((Produit j) -> {
            data.add(j);
//            System.out.println(" data PRODUIT >>>>>" + data.toString());
        });
        
        table_produit.setItems(data);
        nom_produit.setCellValueFactory(new PropertyValueFactory<>("nom_produit"));
        prix_produit.setCellValueFactory(new PropertyValueFactory<>("prix_produit"));
        
    }
    
    public void setDialogStage(Stage dialogStage) {
        this.odialogStage = dialogStage;
    }
    
    public void setOffre(Offre o) {
        this.o = o;
        o.setTitre_offre(ajouttitre.getText());
        o.setDescription(descajout.getText());
//                o.setPhoto(picajout.getImage().toString());

        o.setDate_debut(java.sql.Date.valueOf(ddebutoffre.getValue()));
        o.setDate_fin(java.sql.Date.valueOf(dfinoffre.getValue()));
                  ServiceEtablissement sp = new ServiceEtablissement();
         List<String> l=new ArrayList<>();
        l=sp.nouri(AuthentificationController.c.getId_user());
        for (int i=0;i<l.size();i++)
        {//System.out.println(l.get(i).toString());
       chetab.getItems().add(l.get(i).toString());
                }
    }
    
    public boolean isOkClicked() {
        
        return okClicked;
    }

    public String upload(File file) throws FileNotFoundException, IOException {
        BufferedOutputStream stream = null;
        String globalPath = "C:\\wamp64\\www\\image";
        String localPath = "localhost:3306/";
        String fileName = file.getName();
        fileName = fileName.replace(" ", "_");
        try {
            Path p = file.toPath();
            
            byte[] bytes = Files.readAllBytes(p);
            
            File dir = new File(globalPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            // Create the file on server
            File serverFile = new File(dir.getAbsolutePath() + File.separator + fileName);
            stream = new BufferedOutputStream(new FileOutputStream(serverFile));
            stream.write(bytes);
            stream.close();
            return localPath + "/" + fileName;
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
            
            System.out.println(imageFile);
            
            Image image = new Image(imageFile);
            picajout.setImage(image);
            System.out.println("NAAAMMMEEE ::::: >>>>>>>" + selectedFile.getName());            
            o.setPhoto(selectedFile.getName());
            
        } else {
            System.out.println("file doesn't exist");
        }
    }

    @FXML
    private void ajouter(ActionEvent event) {
        p = table_produit.getSelectionModel().getSelectedItem();
       
        if (isInputValid()) {
            System.out.println("OFFRE >>>>>>>>>>>>>> ");
            String k = descajout.getText();
            System.out.println(descajout.getText());
            
            try {
                o.setTitre_offre(ajouttitre.getText());
                System.out.println("111titre111");
                o.setDescription(descajout.getText());
                System.out.println("1111desc111");
              // for(Etablissement e : chetab)
                System.out.println("+++++"+  chetab.getSelectionModel().getSelectedItem()); 
                ServiceEtablissement se= new ServiceEtablissement();
                
                Etablissement e =new Etablissement();
                
                e=se.chercherUnEtablissementParNom(chetab.getSelectionModel().getSelectedItem());
                o.setEtablissement(e);
                
                //              ofre.setPhoto(picajout.getImage().toString());

                o.setDate_debut(java.sql.Date.valueOf(ddebutoffre.getValue()));
                System.out.println("11111date debut111111");
                o.setDate_fin(java.sql.Date.valueOf(dfinoffre.getValue()));
                System.out.println("11111date fin111111");
                 sero.ajouteroffre(o);
            System.out.println("11111ajout offre111111");
                } catch (Exception e) {
                System.out.println("OFFRE ELEMENTS >>>>>>>>> : " + e.getMessage());
            }
            System.out.println("-_-_-_-_-_-_-_-_-"+chetab.getSelectionModel().getSelectedItem().toString());
                
            System.out.println(" oo : " + o.toString());
           
            System.out.println("++++"+o);
            
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
             dataprod = FXCollections.observableArrayList();
       
         dataprod = table_produit.getSelectionModel().getSelectedItems();
        
              
     
        System.out.println("dataprod.size"+dataprod.size());
        //  List<Produit> ls = (List<Produit>) service.NomProduit(p.getId_produit());
          for(int tmp=0;tmp<dataprod.size();tmp++){
          
                      System.out.println("    data OaaaaaaaaaaaaaaaaaE >>>>>" + dataprod.get(tmp).getId_produit());

            try {

                op.setId_offre(sero.getIDD());
                System.out.println("ID Offre Ajouté à OffreProduit: " + sero.getIDD());
                
                op.setId_produit(dataprod.get(tmp).getId_produit());
                System.out.println("ID Produit selectionnédu Table View: " + dataprod.get(tmp).getId_produit());
                
                System.out.println("Insertion dans la base 11111111111");
                serop.ajouteroffreProduit(op);
                System.out.println("Insertion dans la base 22222222222");
            } catch (Exception e) {
                System.out.println("Erreur OFFRE OffreProduit: " + e.getMessage());
            }
            
            
          }
            System.out.println("AAAAAAAA>>>>>>>>> ");

            
                 
            
            
            okClicked = true;
            odialogStage.close();
        }
        
    }
    
    private boolean isInputValid() {
        String errorMessage = "";
        
        if (ajouttitre.getText() == null || ajouttitre.getText().length() == 0) {
            errorMessage += "No valid  Title!\n";
        }
        if (descajout.getText() == null || descajout.getText().length() == 0) {
            errorMessage += "No valid  description!\n";
        }
        if (ddebutoffre.getValue() == null || ddebutoffre.getValue().lengthOfMonth() == 0) {
            errorMessage += "No valid  Starting Date!\n";
        }
        if (dfinoffre.getValue() == null || dfinoffre.getValue().lengthOfMonth() == 0) {
            errorMessage += "No valid  Ending DATE!\n";
        }
        if (picajout.getImage() == null || picajout.getImage().equals(picajout)) {
            errorMessage += "No valid photo, !\n";
        }
        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(odialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);
            
            alert.showAndWait();
            
            return false;
        }
    }
    
    @FXML
    private void Cancel(ActionEvent event) {
        odialogStage.close();
        
    }
    
    private void afficher(MouseEvent event) {
        
         System.out.println(table_produit.getSelectionModel().getSelectedCells());
        System.out.println("/////////");
        System.out.println("" + p.getId_produit());

//        
    }
    
    @FXML
    private void Add(ActionEvent event) throws IOException {
        
        Produit tempService = new Produit();
        boolean okClicked = showServiceAddDialog(tempService);
        if (okClicked) {
            service.ajouterProduit(tempService);
            initialize();
            
        } else {
            System.out.println("not done");
        }
        
    }
    
    @FXML
    private void Edit(ActionEvent event) {
        Produit selectedPerson = table_produit.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            boolean okClicked = showServiceEditDialog(selectedPerson);
            if (okClicked) {
                service.modifierProduit(selectedPerson);
                initialize();
            }
            
        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
//            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Offre Selected");
            alert.setContentText("Please select an Offer from the table.");
            
            alert.showAndWait();
        }
    }
    
    @FXML
    private void Delete(ActionEvent event) {
        int selectedIndex = table_produit.getSelectionModel().getSelectedIndex();
        Produit selectedPerson = table_produit.getSelectionModel().getSelectedItem();
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        if (selectedIndex >= 0) {
            table_produit.getItems().remove(selectedIndex);
            service.deleteProduit(selectedPerson);
        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            // alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Publicite Selected");
            alert.setContentText("Please select a Publicite in the table.");
            alert.showAndWait();
        }
    }
    
    public boolean showServiceEditDialog(Produit o) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierProduitFXML.fxml"));
            Parent root = (Parent) loader.load();
            ModifierProduitFXMLController controller = loader.getController();
            anchorProd.getChildren().clear();
//        anchorProd.set().setRoot(root);
            anchorProd.getChildren().add(root);

            return controller.isOkClicked();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
        
    }
    
    public boolean showServiceAddDialog(Produit p) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterProduitFXML.fxml"));
            Parent root = (Parent) loader.load();
            AjouterProduitFXMLController controller = loader.getController();
            anchorProd.getChildren().clear();
//        anchorProd.set().setRoot(root);
            anchorProd.getChildren().add(root);

            return controller.isOkClicked();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    @FXML
    private void afficheriprod(MouseEvent event) {

           table_produit.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
      
          
        
//        System.out.println(p.getId_produit());
        javafx.scene.image.Image image = new javafx.scene.image.Image("http://localhost/image/" + p.getPhoto_produit(), 350, 350, true, true);

        photo2.setImage(image);
        
        System.out.println("http://localhost/image/" + p.getPhoto_produit());
        
    }
    
}
