/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Config.Connexion;
import Entite.Etablissement;
import Services.ServiceEtablissement;
import com.itextpdf.text.Document;

import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXButton;
import com.mysql.jdbc.PreparedStatement;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author user
 */
public class DemandesEtablissementAdminController implements Initializable {

    @FXML
    private ScrollPane scroll;
    @FXML
    private VBox vbox1;
    @FXML
    private PieChart pieChart;
    @FXML
    private BarChart<String, Integer> barchart;
    @FXML
    private JFXButton pdf;
    private  Connection con;
    ServiceEtablissement t=new ServiceEtablissement();
    @FXML
    private AnchorPane stanch;

    /**
     * Initializes the controller class.
     */
    public DemandesEtablissementAdminController(){
        con=Connexion.getInstance().getCon();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
     // TODO
            try {
            loadchart2();
        } catch (SQLException ex) {
            Logger.getLogger(DemandesEtablissementAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
 Services.ServiceEtablissement sr = new ServiceEtablissement();
        List <Etablissement> etab= new ArrayList<>();
        etab=sr.listDemandesEtablissement();
        for (Etablissement e : etab){
        
       
            HBox h1= new HBox();
                VBox v1= new VBox();
                Label lab1= new Label();
                Label lab2= new Label();
                Label lab3= new Label();
                 final    Separator sep = new Separator();      
               Button bt1= new Button("Traiter");
                lab1.setText(e.getNom_etablissement());
                lab2.setText(e.getAdresse_etablissement());
                lab3.setText(e.getDescription_etablissement());
               v1.getChildren().add(lab1);
                v1.getChildren().add(lab2);
                v1.getChildren().add(lab3);
              
         v1.setSpacing(20);
         ImageView image = new ImageView();
         javafx.scene.image.Image img = new javafx.scene.image.Image("http://localhost/image/"+e.getPhoto_etablissement(),350,350, true, true);
        
        image.setImage(img);
               
                 h1.setSpacing(130);
                h1.getChildren().add(image);
             
            h1.getChildren().add(v1);
               h1.getChildren().add(bt1);
         vbox1.setSpacing(60);
        sep.setMaxWidth(600);
          
            vbox1.getChildren().add(h1);
        vbox1.getChildren().add(sep);
      
        bt1.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
     Parent homePage;
                        try {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("EtablissementAdmin.fxml"));
                            homePage  = loader.load();
                           EtablissementAdminController ctr = new EtablissementAdminController();
                  
                            ctr = loader.getController();
                            e.setNom_etablissement(e.getNom_etablissement());               
                            e.setAdresse_etablissement(e.getAdresse_etablissement());
                            e.setCode_postal(e.getCode_postal());
                            e.setDescription_etablissement(e.getDescription_etablissement());
                            e.setBudget(e.getBudget());
                            e.setHoraire_travail(e.getHoraire_travail());
//                            e.setPhoto_etablissement(e.getPhoto_etablissement());
//                            e.setPhoto_patente(e.getPhoto_patente());
                            e.setSite_web(e.getSite_web());
                            e.setTelephone_etablissement(e.getTelephone_etablissement());
                            e.setCategorie(e.getCategorie());
                          
                              ImageView image1 = new ImageView();
         javafx.scene.image.Image img = new javafx.scene.image.Image("http://localhost/image/"+e.getPhoto_etablissement(),350,350, true, true);
        
        image.setImage(img);
                  ImageView image2 = new ImageView();
         javafx.scene.image.Image img1 = new javafx.scene.image.Image("http://localhost/image/"+e.getPhoto_patente(),350,350, true, true);
        
        image.setImage(img1);
               
                            ctr.setE(e);
                          ctr.affich();
                            
                          
                          
//        Scene homePage_scene = new Scene(homePage);
//
//        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//
//        app_stage.setScene(homePage_scene);
//
//        app_stage.show();
        Stage stage = new Stage();
         
         Scene scene = new Scene(homePage);
         stage.setScene(scene);
         stage.show();
         
     
                        
                        } catch (IOException ex) {
                            Logger.getLogger(DemandesEtablissementAdminController.class.getName()).log(Level.SEVERE, null, ex);
                        }

      
                    }
                });

        
        }    
    
     loadchart();
    }   
    
     private void loadchart() {
      
          StatEtablissementParCategorie();
     }
        private void loadchart2() throws SQLException {
        String req = "SELECT u.prenom, count( id_etablissement)  from user u , etablissement e where u.id =e.id GROUP by u.id";
        XYChart.Series<String, Integer> series = new XYChart.Series<String, Integer>();
       
        
        
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(req);
            ResultSet rst = pst.executeQuery();
            while (rst.next()) {
                series.getData().add(new XYChart.Data<>(rst.getString(1), rst.getInt(2)));
            }
            barchart.getData().add(series);
  barchart.setTitle("Nombre d'établissement par propriétaire");
  
         
    }    

    @FXML
    private void onload(ActionEvent event) {
         try {
            WritableImage image = pieChart.snapshot(new SnapshotParameters(), null);

            WritableImage image2 = barchart.snapshot(new SnapshotParameters(), null);
            
            File file = new File("chart.png");
            File file3 = new File("chart1.png");

            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
            ImageIO.write(SwingFXUtils.fromFXImage(image2, null), "png", file3);

            OutputStream file2 = new FileOutputStream(new File("C:\\Users\\user\\Desktop\\Statistique.pdf"));

            Document document = new Document();

            PdfWriter.getInstance(document, file2);

            document.open();

            Image img = Image.getInstance("chart.png");
            Image img2 = Image.getInstance("chart1.png");
            document.add(img);
            document.add(img2);
            document.add(new Paragraph(new Date().toString()));

            document.close();

            file2.close();
            
                    Desktop.getDesktop().open(new File("C:\\Users\\user\\Desktop\\Statistique.pdf"));

        } catch (Exception e) {

            e.printStackTrace();

        }
    }
     public void StatEtablissementParCategorie() {

        
        pieChart.getData().clear();
        pieChart.setData(t.StatEtablissementParCategorie());
        pieChart.setAnimated(true);
        pieChart.setVisible(true);
        pieChart.setTitle("Nombre d'établissement par Catégorie");
        
         final Label caption = new Label("");
//        caption.setTextFill(Paint.valueOf(Color.MAGENTA.toString()));

        caption.setStyle("-fx-font: 15 arial;");
 
        for (final PieChart.Data data : pieChart.getData()) {
            data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    caption.setTranslateX(e.getSceneX());
                    caption.setTranslateY(e.getSceneY());
 
                    caption.setText(String.valueOf(data.getPieValue()));
                }
            });
           }
        
        AnchorPane root = new AnchorPane();
        root.getChildren().addAll(pieChart, caption);
        stanch.getChildren().add(root);

    }
    
}
