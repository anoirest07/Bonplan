/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import  Entite.Evenement;
import Services.ServiceEvenement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static java.time.temporal.TemporalQueries.localDate;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import Services.ServiceInteresser;
import java.sql.SQLException;
/**
 * FXML Controller class
 *
 * @author amine
 */
public class EvenController implements Initializable {
  private ListView<Evenement> listview;
    @FXML
    private ImageView img;
    @FXML
    private Label nom;
    @FXML
    private Label date;
    @FXML
    private Label description;
    @FXML
    private HBox hbox;
    
public Evenement e = new Evenement();
 
    @FXML
    private Label nbparticip;
    /**
     * Initializes the controller class.
     */
    public EvenController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("even.fxml"));
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
    ServiceInteresser si = new  ServiceInteresser();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
javafx.scene.image.Image image = new javafx.scene.image.Image("http://localhost/image/"+e.getPhoto());
        img.setImage(image);        
img.setFitHeight(130);
img.setFitWidth(130);
    }    

    public void setInfo(Evenement string) throws SQLException
    {
        nom.setText(string.getNom_evenement());
        description.setText(string.getDescription());
       
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
String formattedString = string.getDate_evenement().format(formatter);
        date.setText(formattedString);
        //img.setImage(null);
        Image image = new Image("http://localhost/image/"+string.getPhoto());
        img.setImage(image);
        System.out.println(si.GetinteresserByevenementid(string));
        int a =0;
        a=si.GetinteresserByevenementid(string);
   nbparticip.setText(""+a);
    }

    public HBox getBox()
    {
        return hbox;
    }


 
}
