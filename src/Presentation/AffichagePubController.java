/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entite.Publicite;
import Services.ServicePublicite;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AffichagePubController implements Initializable {

    @FXML
    private Label descrip;
    Services.ServicePublicite sp= new ServicePublicite();
    private Publicite p ;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    void setDesc(String description_publicite) {
      //To change body of generated methods, choose Tools | Templates.
      descrip.setText(description_publicite);
    }
}
