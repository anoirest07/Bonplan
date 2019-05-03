/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entite.Offre;
import Entite.OffreProduit;
import Entite.Produit;
import Services.ServiceOffre;
import Services.ServiceOffreProduit;
import Services.ServiceProduit;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Yassine
 */
public class AfficherOffreClientFXMLController{

    @FXML
    private ImageView photo2;
    @FXML
    private Label label_description;
    @FXML
    private TableView<Offre> table_offre;
    @FXML
    private TableColumn<Offre, ?> titre_offre;
    @FXML
    private TableColumn<Offre, ?> debut_offre;
    @FXML
    private TableColumn<Offre, ?> fin_offre;
    @FXML
    private Label produitsoff;
OffreProduit opp=new OffreProduit();
    ServiceOffreProduit serop =new ServiceOffreProduit();
       private ObservableList<Offre> data;

    ServiceOffre service = new ServiceOffre();
Produit pp= new Produit();
ServiceProduit serviceprod= new ServiceProduit();
  
    /**
     * Initializes the controller class.
     */
    
    public void initialize() {
        // TODO
         data = FXCollections.observableArrayList();
        List<Offre> ls = service.afficherALLoffres();
        ls.stream().forEach((Offre j) -> {
            data.add(j);
            System.out.println("    data OFFRE >>>>>" + data.toString());
        });

        table_offre.setItems(data);
        titre_offre.setCellValueFactory(new PropertyValueFactory<>("titre_offre"));
        debut_offre.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
        fin_offre.setCellValueFactory(new PropertyValueFactory<>("date_fin"));

    }    

    @FXML
    private void afficher(MouseEvent event) {
        String ch="";
        Offre e = table_offre.getSelectionModel().getSelectedItem();

        List<OffreProduit> lsop = serop.recupOffreSelect(e.getId_offre());
        List<Produit> lsp = serviceprod.afficherProduit();
 
        System.out.println("NNNNNNNNNNNNNNNNNNNNNNNNN");
        for (int i = 0; i < lsp.size(); i++) {
                for (int j = 0; j < lsop.size(); j++) {
                    if (lsp.get(i).getId_produit() == lsop.get(j).getId_produit()) {
                        ch +=" ~ "+ lsp.get(i).getNom_produit() + " ~ ";
                        
                    }
                }
            }
        System.out.println("cchh = " + ch);
        System.out.println("YYYYYYYYYYYYYYY");
        produitsoff.setText(ch);
        
        label_description.setWrapText(true);
        label_description.setText(e.getDescription());
        System.out.println("DESCRIPTION OFFRE >>>>>" + e.getDescription());

        javafx.scene.image.Image image = new javafx.scene.image.Image("http://localhost/image/" + e.getPhoto(), 350, 350, true, true);
        //  ImageView photo2 = new ImageView(image);

        photo2.setImage(image);

        System.out.println("http://localhost/image/" + e.getPhoto());
    }
    
}
