/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entite.Publicite;
import Services.ServicePublicite;
import com.itextpdf.text.PageSize;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AccueilClientController implements Initializable {

    @FXML
    private Button bt_menu;

   private ObservableList<Publicite> data ;
ServicePublicite service = new ServicePublicite();
    @FXML
    private Pagination slide;
    private List<Publicite> ls;
   
    private int PagSize=0;
    @FXML
    private ImageView imagespace;
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
         slide.getStyleClass().add(Pagination.STYLE_CLASS_BULLET);
     
   
}
      public ImageView LoadPub(int pageIndex) {
        ImageView box = new ImageView();
        Publicite P = ls.get(pageIndex);
        //Image I = new Image("/Presentation/"+P.getPhoto_publicite());
        javafx.scene.image.Image image = new javafx.scene.image.Image("http://localhost/image/"+P.getPhoto_publicite());
        imagespace.setImage(image);
        return imagespace;
      }
}