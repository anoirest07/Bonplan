/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entite.Evenement;
import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author amine
 */
public class EvenadmController implements Initializable {

    @FXML
    private HBox hbox;
    @FXML
    private ImageView img;
    @FXML
    private Label nom;
    @FXML
    private Label date;
    @FXML
    private Label description;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("evenadm.fxml"));
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
   public void setInfo(Evenement string)
    {
        nom.setText(string.getNom_evenement());
        description.setText(string.getDescription());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
String formattedString = string.getDate_evenement().format(formatter);
        date.setText(formattedString);
        //img.setImage(null);
        //Image image = new Image("http://localhost/"+string.getPhoto());
        //img.setImage(image);
    }

    public HBox getBox()
    {
        return hbox;
    } 
}
