/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Moianoir;

import Entite.Publicite;
import Services.ServicePublicite;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author user
 */
public class FXMLController implements Initializable {

    @FXML
    private ListView<Publicite> description;
    @FXML
    private ImageView photo2;
    private ObservableList<Publicite> data ;
    ServicePublicite service = new ServicePublicite();
    
    
    @FXML
    private Label label1;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         data = FXCollections.observableArrayList();
        List<Publicite> ls = service.afficherpublicite();
        ls.stream().forEach((j)->{
            data.add(j);
        });
        
        description.setItems(data);
        description.getSelectionModel().selectFirst();
        Publicite e=description.getSelectionModel().getSelectedItem();
        javafx.scene.image.Image image = new javafx.scene.image.Image(getClass().getResource(e.getPhoto_publicite())
               .toExternalForm());
        photo2.setImage(image);
        // TODO
    }    
    
    @FXML
    public void afficher()
    {
        
        Publicite e=description.getSelectionModel().getSelectedItem();
       // photo2.setViewport(e.getPhoto_publicite());
       //label1.setText(e.getPhoto_publicite());
       // System.out.println(e.getPhoto_publicite());
      // Image image = new Image(e.getPhoto_publicite());
       javafx.scene.image.Image image = new javafx.scene.image.Image(getClass().getResource(e.getPhoto_publicite())
               .toExternalForm());
      //  ImageView photo2 = new ImageView(image);
       
    photo2.setImage(image);
        
      
      
    }
    
}
