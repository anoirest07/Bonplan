/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entite.Etablissement;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author Nadia
 */
public class EtabController implements Initializable {

    @FXML
    private HBox hbox;
    @FXML
    private ImageView img;
    @FXML
    private Label nom;
    @FXML
    private Label tel;
    @FXML
    private Label description;
    @FXML
    private Label categ;
 private ListView<Etablissement> listview;
 public Etablissement e = new Etablissement();
    /**
     * Initializes the controller class.
     */
  public EtabController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("etab.fxml"));
            fxmlLoader.setController(this);
        try
        {
            fxmlLoader.load();
        
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    javafx.scene.image.Image image = new javafx.scene.image.Image("http://localhost/image/"+e.getPhoto_etablissement());
        img.setImage(image);        


    }    

    public void setInfo(Etablissement string)
    {
        nom.setText(string.getNom_etablissement());
        description.setText(string.getDescription_etablissement());
        
     
        tel.setText(""+string.getTelephone_etablissement());
        categ.setText(string.getCategorie().getNom_categorie());
        
        
        //img.setImage(null);
        Image image = new Image("http://localhost/image/"+string.getPhoto_etablissement());
        img.setImage(image);
  
    }

    public HBox getBox()
    {
        return hbox;
    }

       
    
}
