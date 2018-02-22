/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bonplan;

import Entite.Publicite;
import Presentation.AjouterPubliciteController;
import Presentation.PubliciteController;
import Services.Facebook;
import com.restfb.types.Message.Share;
import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author user
 */
public class TestPublicite extends Application {
    private Stage primaryStage;
    
           
    @Override
    public void start(Stage Stage) throws IOException {
       
        
    /* this.primaryStage = Stage ;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../Presentation/Publicite.fxml"));
        AnchorPane serviceindex = (AnchorPane) loader.load();
        PubliciteController controller = loader.getController();
        Scene scene = new Scene(serviceindex);
        Stage.setScene(scene);
        Stage.show();*/
        Facebook s = new Facebook();
    s.partager();
        
        
    }

    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
