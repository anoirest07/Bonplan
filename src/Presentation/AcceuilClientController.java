/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entite.Publicite;
import Services.ServicePublicite;
import Services.ServiceStatPublicite;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.sound.midi.Soundbank;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AcceuilClientController implements Initializable {

    @FXML
    private Button bt_menu;
    @FXML
    private Pagination slide;
    @FXML
    private ImageView imagespace;
    private int PagSize = 0;
     private ObservableList<Publicite> data;
    ServicePublicite service = new ServicePublicite();
    private List<Publicite> ls;
    @FXML
    private JFXButton profilclient;
    @FXML
    private Button idEtbalissement;
    @FXML
    private Button idOffre;
    @FXML
    private Button idevenment;
    @FXML
    private Button filAct;
    @FXML
    private AnchorPane anchorpane1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         data = FXCollections.observableArrayList();
        ls = service.afficherPhoto();
        PagSize = ls.size();
        slide.setPageCount(PagSize);
        slide.setPageFactory(new Callback<Integer, Node>() {
            @Override
            public Node call(Integer pageIndex) {
                return LoadPub(pageIndex);
            }

        });
        
        /*new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //parcours  me3 tsawer
                    //
                    
                    Thread.sleep(5000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(AccueilClientController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }).start();*/
        

        
        slide.getStyleClass().add(Pagination.STYLE_CLASS_BULLET);

    }
        // TODO
         public ImageView LoadPub(int pageIndex) {
        ImageView box = new ImageView();
        Publicite P = ls.get(pageIndex);
        //Image I = new Image("/Presentation/"+P.getPhoto_publicite());
        javafx.scene.image.Image image = new javafx.scene.image.Image("http://localhost/image/" + P.getPhoto_publicite());
        imagespace.setImage(image);
        //     System.out.println("++++"+image);
           //  System.out.println(" ooooooooo"+P.toString());
        imagespace.onMouseClickedProperty().setValue(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                
                try {
                   
                    ServicePublicite ps = new ServicePublicite();
             //       System.out.println(" *******"+ps.afficherpubliciteClick(P.getPhoto_publicite()));
                   
     Publicite pub=  ps.afficherpubliciteClick(P.getPhoto_publicite());
   //   System.out.println("   TTTTTT"+pub); 
                    pub.setNbre_click((pub.getNbre_click())+1);
                    ps.modifierpublicite(pub);

                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource(
                            "AffichagePub.fxml"));
                    AnchorPane root = (AnchorPane) loader.load();
                    AffichagePubController controller = loader.getController();
                    controller.setDesc(P.getDescription_publicite());
                    Scene scene = new Scene(root);
                    
                    Stage stage = new Stage();
                    stage.setTitle("Description de Publicite");
                    stage.setScene(scene);
                    stage.showAndWait();
                } catch (IOException ex) {
                    Logger.getLogger(AcceuilClientController.class.getName()).log(Level.SEVERE, null, ex);
                }
         
                
            }
        });
        return imagespace;
    }

    

    @FXML
    private void OnclickedProfil(ActionEvent event) throws IOException {
//        Parent test2_parent = FXMLLoader.load(getClass().getResource("Profil Utilisateur.fxml"));
//         Scene test2_scene = new Scene(test2_parent);
//        Stage test1_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        test1_stage.setScene(test2_scene);
//        test1_stage.show();
FXMLLoader loader = new FXMLLoader(getClass().getResource("Profil Utilisateur.fxml"));
Parent root = (Parent) loader.load();
 ProfilUtilisateurController controller = loader.getController();
 Scene scene = new Scene(root);
      Stage stage = new Stage();
      stage.setScene(scene);
      stage.showAndWait(); 
    }

    @FXML
    private void logOut(MouseEvent event) throws IOException {
        
        Parent test2_parent = FXMLLoader.load(getClass().getResource("Authentification_1.fxml"));
         Scene test2_scene = new Scene(test2_parent);
        Stage test1_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        test1_stage.setScene(test2_scene);
        test1_stage.show();
    }

    @FXML
    private void OnclickedEtab(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ListeDesEtablissements.fxml"));
        Parent root = (Parent) loader.load();
        ListeDesEtablissementsController controller = loader.getController();
        anchorpane1.getChildren().clear();
        //anchor.set().setRoot(root);
    anchorpane1.getChildren().add(root);
        
    }

    @FXML
    private void OnClickedOffre(ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherOffreClientFXML.fxml"));
        Parent root = (Parent) loader.load();
       // EvenementclientController controller = loader.getController();
        anchorpane1.getChildren().clear();
        //anchor.set().setRoot(root);
    anchorpane1.getChildren().add(root);
        
    }

    @FXML
    private void OnClickedEven(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("EvenementClient.fxml"));
        Parent root = (Parent) loader.load();
       // EvenementclientController controller = loader.getController();
        anchorpane1.getChildren().clear();
        //anchor.set().setRoot(root);
    anchorpane1.getChildren().add(root);
    }

    @FXML
    private void OnClickedFil(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FilClient.fxml"));
        Parent root = (Parent) loader.load();
        //FilClientController controller = loader.getController();
        anchorpane1.getChildren().clear();
        //anchor.set().setRoot(root);
    anchorpane1.getChildren().add(root);
        
    }

    @FXML
    private void OnclickAccueil(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("AcceuilClient.fxml"));
        Parent root = (Parent) loader.load();
        //FilClientController controller = loader.getController();
        anchorpane1.getChildren().clear();
        //anchor.set().setRoot(root);
    anchorpane1.getChildren().add(root);
        
        
    }

    @FXML
    private void ouvrePub(MouseEvent event) {
    }
    
}
