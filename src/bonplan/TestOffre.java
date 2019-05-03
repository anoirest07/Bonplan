/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bonplan;

import Entite.Offre;
import Presentation.AjouterOffreFXMLController;
import Presentation.OffreController;
import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Yassine
 */
public class TestOffre extends Application{
 private Stage primaryStage;
    
           
    @Override
    public void start(Stage Stage) throws IOException {
       
        
     this.primaryStage = Stage ;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(TestOffre.class.getResource("/Presentation/Offre.fxml"));
        AnchorPane serviceindex = (AnchorPane) loader.load();
        OffreController controller = loader.getController();
        controller.setMainApp(this);
        Scene scene = new Scene(serviceindex);
        Stage.setScene(scene);
        Stage.show();
        
        
    }
    public boolean showServiceEditDialog(Offre o) {
        try {
            FXMLLoader loader2 = new FXMLLoader();
            loader2.setLocation(TestOffre.class.getResource("../Presentation/AjouterOffreFXML.fxml"));
            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            AnchorPane page = (AnchorPane) loader2.load();
            dialogStage.setTitle("Ajouter Offre");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            // Set the person into the controller.
            AjouterOffreFXMLController controller = loader2.getController();            
            controller.setDialogStage(dialogStage);
//            controller.setOffre(o);            
            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();
           // controller.isOkClicked();
            return controller.isOkClicked();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
        
    }
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    public Stage getPrimaryStage() {
        return primaryStage;
    }
}
