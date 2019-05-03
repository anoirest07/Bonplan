/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bonplan;

import Entite.Produit;
import Presentation.AjouterProduitFXMLController;
import Presentation.OffreController;
import Presentation.ProduitController;
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
public class TestProduit extends Application{
 private Stage primaryStage;
    
           
    @Override
    public void start(Stage Stage) throws IOException {
       
        
     this.primaryStage = Stage ;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(TestProduit.class.getResource("/Presentation/Produit.fxml"));
        AnchorPane serviceindex = (AnchorPane) loader.load();
        ProduitController controller = loader.getController();
        controller.setMainApp(this);
        Scene scene = new Scene(serviceindex);
        Stage.setScene(scene);
        Stage.show();
        
        
    }
    public boolean showServiceEditDialog(Produit p) {
        try {
            FXMLLoader loader2 = new FXMLLoader();
            loader2.setLocation(TestProduit.class.getResource("../Presentation/AjouterOffreFXML.fxml"));
            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            AnchorPane page = (AnchorPane) loader2.load();
            dialogStage.setTitle("Ajouter Produit");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            // Set the person into the controller.
            AjouterProduitFXMLController controller = loader2.getController();            
            controller.setDialogStage(dialogStage);
            controller.setProduit(p);            
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
