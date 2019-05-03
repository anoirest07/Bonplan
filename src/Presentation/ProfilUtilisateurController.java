
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entite.Client;
import Entite.Etablissement;
import static Presentation.AuthentificationController.c;
import Services.ServiceClient;
import Services.ServiceEtablissement;
import Services.ServiceFavoris;
import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author malek
 */
public class ProfilUtilisateurController implements Initializable {

    @FXML
    private Label nom;
    @FXML
    private Label ville;
   
    @FXML
    private ListView<CustomThing> listeV;
    @FXML
    private ImageView imgfav;
    @FXML
    private ListView<CustomThing> listVetab;
    @FXML
    private ImageView imagetabl;
    @FXML
    private Label prenom;
    @FXML
    private Label email;
    @FXML
    private ImageView imageProfil;
    @FXML
    private JFXButton deconn;
    String pathString;
    @FXML
    private Button changePhoto;
   

  

    @FXML
    private void onclickedPhoto(MouseEvent event) {
    }

    @FXML
    private void onclickedchange(ActionEvent event) throws IOException {
         FileChooser fileChooser = new FileChooser();
fileChooser.setTitle("Choisir une image pour votre profil");
        Window stage = null;
        File file;
 file=fileChooser.showOpenDialog(stage);
      pathString ="src/"+file.getName();
 Image image = new Image("file:"+file.getPath());
    
       Path pth= file.toPath();
     
      File resourcesDirectory = new File("src/"+file.getName());
    Files.deleteIfExists(resourcesDirectory.toPath());
        Files.copy(pth, resourcesDirectory.toPath(), StandardCopyOption.COPY_ATTRIBUTES);
imageProfil.setImage(image);
    }

   

 
    /**
     * Initializes the controller class.
     */
    /////////////
     private static class CustomThing {
         private int id_Et;
         private String img;
         private String nom;
         private String adresse;
         private String photo_pat;
         private String siteweb;
         private String horaire;
         private int telf;
         

        public CustomThing(int id_Et, String img, String nom, String adresse ) {
            this.id_Et = id_Et;
            this.img = img;
            this.nom = nom;
            this.adresse = adresse;
        }

        public CustomThing(int id_Et,String img, String nom, String adresse , String siteweb, String horaire, int telf ) {
            
            this.id_Et = id_Et;
            this.img=img;
            this.nom = nom;
            this.adresse = adresse;
          
            this.siteweb = siteweb;
            this.horaire = horaire;
            this.telf = telf;
           
        }

       

        
        
        
        public int getId_Et() {
            return id_Et;
        }

        public void setId_Et(int id_Et) {
            this.id_Et = id_Et;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getNom() {
            return nom;
        }

        public void setNom(String nom) {
            this.nom = nom;
        }

        public String getAdresse() {
            return adresse;
        }

        public void setAdresse(String adresse) {
            this.adresse = adresse;
        }

        public String getPhoto_pat() {
            return photo_pat;
        }

        public void setHoraire(String horaire) {
            this.horaire = horaire;
        }

        public String getHoraire() {
            return horaire;
        }

        public String getSiteweb() {
            return siteweb;
        }

        public void setSiteweb(String siteweb) {
            this.siteweb = siteweb;
        }

        public int getTelf() {
            return telf;
        }

        public void setTelf(int telf) {
            this.telf = telf;
        }
         
        
     }
    //////////////////
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
      
        
        ///////////////
        List<Etablissement> rs ;
         ServiceFavoris ps = new ServiceFavoris();
         rs = ps.listfavorisEtabl(AuthentificationController.c.getId_user());
         
         ObservableList<CustomThing> data = FXCollections.observableArrayList();
        for(Etablissement e : rs) 
        {
         data.addAll(new CustomThing(e.getId_etablissement(), e.getPhoto_etablissement()
                 , e.getNom_etablissement(), e.getAdresse_etablissement()));
        }
        
        listeV.getItems().addAll(data);
        listeV.setCellFactory(new Callback<ListView<CustomThing>, ListCell<CustomThing>>() {

            @Override
            public ListCell<CustomThing> call(ListView<CustomThing> arg0) {
                return new ListCell<CustomThing>() {

                    @Override
                    protected void updateItem(CustomThing item, boolean bln) {
                        super.updateItem(item, bln);
                        if (item != null) {
                        
                                 
                            VBox vBox = new VBox(new Text(item.getNom()), new Text(item.getAdresse()) , new Text(item.getImg()) 
                                    );
                            vBox.setSpacing(4);
                           Image image1 = new Image("file:src/" + item.getImg());
                            ImageView imv =new ImageView(image1);
                            imv.setFitHeight(130);
                            imv.setFitWidth(130);
                            
                            HBox hBox = new HBox(imv, vBox);
                            hBox.setSpacing(10);
                            
                            
                            setGraphic(hBox);
                          
                    
                        }else{
                         
        setText(null);
        setGraphic(null);
  
                        }
                    }

                };
            }

        });
        ///////////////////
        ServiceClient sessionservice = new ServiceClient();
        // Client a = sessionservice.getClientById(c.getId_user());
         
        
        nom.setText(c.getNom());
        prenom.setText(c.getPrenom());
        email.setText(c.getEmail());
        ville.setText(c.getVille());
        Image image = new Image(c.getPhoto_user()); 
        imageProfil.setImage(image); 
        System.out.println(c.getPhoto_user());
        
        
         ///////////////////////
     List<Etablissement> le =new ArrayList<>() ;
     
         ServiceEtablissement pe = new ServiceEtablissement();
//         le = pe.listEtablissement(c.getId_user());
         
         ObservableList<CustomThing> obs = FXCollections.observableArrayList();
        for(Etablissement e : le) 
        {
         obs.addAll(new CustomThing(e.getId_etablissement(), e.getPhoto_etablissement()
                 , e.getNom_etablissement(), e.getAdresse_etablissement()));
        }
        
        listVetab.getItems().addAll(obs);
        listVetab.setCellFactory(new Callback<ListView<CustomThing>, ListCell<CustomThing>>() {

            @Override
            public ListCell<CustomThing> call(ListView<CustomThing> arg0) {
                return new ListCell<CustomThing>() {

                    @Override
                    protected void updateItem(CustomThing item, boolean bln) {
                        super.updateItem(item, bln);
                        if (item != null) {
                        
                                 
                            VBox vBox = new VBox(new Text(item.getNom()), new Text(item.getAdresse()) ,
                            new Text (item.getHoraire()) , new Text (item.getSiteweb()) , new Text(String.valueOf(item.getTelf()))  , new Text(item.getImg()) );
                            vBox.setSpacing(4);
                            Image  image  = new Image("file:src/" + item.getImg()); 
                            ImageView imv =new ImageView(image);
                            imv.setFitHeight(130);
                            imv.setFitWidth(130);
                            
                            HBox hBox = new HBox(imv, vBox);
                            hBox.setSpacing(10);
                            
                            
                            setGraphic(hBox);
                          
                    
                        }else{
                         
        setText(null);
        setGraphic(null);
  
                        }
                    }

                };
            }

        });
    
    
        
        
        
    }    
    
    
    
    
}
