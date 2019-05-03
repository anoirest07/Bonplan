/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;



import Config.Connexion;
import Entite.Etablissement;
import Services.ServiceEvaluation;
import Services.ServiceExperience;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;


import javafx.scene.chart.PieChart;


import javax.imageio.ImageIO;
    import javafx.scene.image.WritableImage;
    import java.io.File;
    import java.io.FileOutputStream;
import java.io.IOException;
    import java.io.OutputStream;
    import java.net.URL;
    import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
    import java.util.Date;
    import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.HostServices;
    import javafx.embed.swing.SwingFXUtils;
    import javafx.event.ActionEvent;
import javafx.event.EventHandler;
    import javafx.fxml.FXML;
    import javafx.fxml.Initializable;
    import javafx.scene.SnapshotParameters;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
    import javafx.scene.control.Button;
    import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
    

   
 




/**
 * FXML Controller class
 *
 * @author Hard-System-Info
 */
public class FXMLStatistiquesController implements Initializable {


    Connection con;
    public static int userID;
    public Etablissement rv;
    @FXML
    private Button pdf;
    
        @FXML
    private PieChart pieChart;
        
        Services.ServiceExperience v=new ServiceExperience();
                Services.ServiceEvaluation x=new ServiceEvaluation();

    @FXML
    private Button bt_menu;
    @FXML
    private PieChart piechart2;
    @FXML
    private BarChart<String, Integer> barchart;
    private AnchorPane root;
    @FXML
    private AnchorPane stanch;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try
        {
            //        FXMLStatistiquesController.userID = User.getIdofconnecteduser();
//        lb.setText(String.valueOf(userID));
                loadchart();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLStatistiquesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            moyTotCrit();
            moyEvalTotProp();

        } catch (SQLException ex) {
            Logger.getLogger(FXMLStatistiquesController.class.getName()).log(Level.SEVERE, null, ex);
        }
//nbExp();
        

    }

    public FXMLStatistiquesController() 
    {
        con = Connexion.getInstance().getCon();
    }

    
    
    private void loadchart() throws SQLException 
    {
        String req = "SELECT  ex.date_exp, count( id_exp)  from experience ex , etablissement et where et.id_etablissement =ex.id_etablissement AND et.id=? GROUP by ex.date_exp";
        XYChart.Series<String, Integer> series = new XYChart.Series<String, Integer>();
        PreparedStatement pst = (PreparedStatement) con.prepareStatement(req);
        pst.setInt(1, AuthentificationController.c.getId_user());
        ResultSet rst = pst.executeQuery();
        while (rst.next()) {
            series.getData().add(new XYChart.Data<>(rst.getString(1), rst.getInt(2)));

        }
        
        barchart.getData().add(series);
        barchart.setTitle("Nombre des expériences");

  
    }
         
        

    

    @FXML
    private void onload(ActionEvent event) throws IOException {
        try {
            WritableImage image = pieChart.snapshot(new SnapshotParameters(), null);
            WritableImage image1 = piechart2.snapshot(new SnapshotParameters(), null);
            WritableImage image2 = barchart.snapshot(new SnapshotParameters(), null);
        
// TODO: probably use a file chooser here
            File file = new File("chart.png");
            File file1 = new File("chart1.png");
            File file2 = new File("chart2.png");

            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
            ImageIO.write(SwingFXUtils.fromFXImage(image1, null), "png", file1);
            ImageIO.write(SwingFXUtils.fromFXImage(image2, null), "png", file2);


            OutputStream filex = new FileOutputStream(new File("C:\\Users\\user\\Desktop\\Test.pdf"));

            Document document = new Document();

            PdfWriter.getInstance(document, filex);

            document.open();

            Image img = Image.getInstance("chart.png");
            Image img1 = Image.getInstance("chart1.png");
            Image img2 = Image.getInstance("chart2.png");

            document.add(img);
            document.add(img1);
            document.add(img2);


            document.add(new Paragraph(new Date().toString()));


            document.close();
            filex.close();
                    Desktop.getDesktop().open(new File("C:\\Users\\user\\Desktop\\Test.pdf"));

        } catch (Exception e) {

            e.printStackTrace();

        }

    }
     public void nbExp() {

       // new FadeInUpTransition(commBar).play();
        //new FadeInDowntransition(UserComm).play();
//        final Label caption = new Label("");
//caption.setTextFill(Color.DARKORANGE);
//caption.setStyle("-fx-font: 24 arial;");
//
//for (final PieChart.Data data : pieChart.getData()) {
//    data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED,
//        new EventHandler<MouseEvent>() {
//            @Override public void handle(MouseEvent e) {
//                caption.setTranslateX(e.getSceneX());
//                caption.setTranslateY(e.getSceneY());
//                caption.setText(String.valueOf(data.getPieValue()) + "%");
//             }
//        });
//}


//        pieChart.getData().clear();
//        pieChart.setData(v.nbExp());
//        pieChart.setAnimated(true);
//        pieChart.setVisible(true);
//        pieChart.setTitle("Nombre des expériences sur votre établissement");

    }

       public void moyEvalTotProp() {

       // new FadeInUpTransition(commBar).play();
        //new FadeInDowntransition(UserComm).play();
       
        piechart2.getData().clear();
        piechart2.setData(v.moyEvalTotProp(AuthentificationController.c.getId_user()));
        piechart2.setAnimated(true);
        piechart2.setVisible(true);
        piechart2.setTitle("Moyenne des évaluations");

    }
       
         public void moyTotCrit() throws SQLException {

       // new FadeInUpTransition(commBar).play();
        //new FadeInDowntransition(UserComm).play();
     
        pieChart.getData().clear();
        pieChart.setData(x.moyTotCritProp(AuthentificationController.c.getId_user()));
        pieChart.setAnimated(true);
        pieChart.setVisible(true);
        pieChart.setClockwise(true);
        pieChart.setStartAngle(90);
        pieChart.setTitle("Moyenne des évaluations par critere");
        
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

        System.out.println(" -----------------------------");
        for (Double d :x.moyTotCritEtab(69)) {
            System.out.println(" ********////////"+d);
            
        }
        
                System.out.println(" -----------------------------");

    }

  
     
    
}