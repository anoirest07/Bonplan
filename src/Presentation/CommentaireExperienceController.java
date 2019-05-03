/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entite.Commentaire;
import Entite.Experience;
import Entite.Test;
import Entite.Utilisateur;
import static Presentation.AuthentificationController.c;
import Services.ServiceCommentaire;
import Services.ServiceExperience;
import com.jfoenix.controls.JFXTextArea;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author malek
 */
public class CommentaireExperienceController implements Initializable {
    
    @FXML
    private ListView<CustomThing> listComm;
    @FXML
    private Button ajout;
    @FXML
    private TextArea commentaireArea;
    @FXML
    private JFXTextArea nouvComm;
    @FXML
    private Button modif;
    Commentaire commentaire = new Commentaire();

    @FXML
    private void onclickedModifier(ActionEvent event) {
      CustomThing selectedPerson = listComm.getSelectionModel().getSelectedItem();
       
        ServiceCommentaire cs = new ServiceCommentaire();
       
        commentaire.setCommentaire(nouvComm.getText());
       
        try
        {
          
        cs.editcommentaire(commentaire);
        
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Commentaire modifié");
            alert.setHeaderText("Commentaire modifié avec succès");
            alert.setContentText("Commentaire "+nouvComm.getText()+" modifié avec succès");
            alert.showAndWait();
            
             listComm.getItems().clear();
                    UpdateableListViewSkin<CustomThing> skin = new UpdateableListViewSkin<>(this.listComm);
        this.listComm.setSkin(skin);
        ((UpdateableListViewSkin) listComm.getSkin()).refresh();
        
        List<Test> rs = null ;
         ServiceCommentaire ps = new ServiceCommentaire();
        try {
            rs = ps.listcommentaire(AuthentificationController.c.getId_user());
        } catch (SQLException ex) {
            Logger.getLogger(CommentaireExperienceController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         ObservableList<CustomThing> data = FXCollections.observableArrayList();
        for(Test e : rs) 
        {
         data.addAll(new CustomThing( e.getId_commentaire(),e.getCommen() ,e.getUtilisateur(), e.getNom(),e.getPrenom(),e.getPhoto_user()
                 ));
        }
        
        listComm.getItems().addAll(data);
        listComm.setCellFactory(new Callback<ListView<CustomThing>, ListCell<CustomThing>>() {

            @Override
            public ListCell<CustomThing> call(ListView<CustomThing> arg0) {
                return new ListCell<CustomThing>() {

                    @Override
                    protected void updateItem(CustomThing item, boolean bln) {
                        super.updateItem(item, bln);
                        if (item != null) {
                            
                            Button btn = new Button("supprimer");
                             btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                listComm.getItems().clear();
                
                UpdateableListViewSkin<CustomThing> skin = new UpdateableListViewSkin<>(listComm);
        listComm.setSkin(skin);
       ((UpdateableListViewSkin) listComm.getSkin()).refresh();
       ServiceCommentaire s = new ServiceCommentaire();
     //  s.Deletecommentaire(item.);
       
       
       List<Test> rss = null ;
         ServiceCommentaire ps = new ServiceCommentaire();
                try {
                    rss = ps.listcommentaire(3);
                } catch (SQLException ex) {
                    Logger.getLogger(CommentaireExperienceController.class.getName()).log(Level.SEVERE, null, ex);
                }
         
         
        for(Test e : rss) 
        {
         listComm.getItems().add(new CustomThing( e.getId_commentaire(),e.getCommen() ,e.getUtilisateur() , e.getNom(),e.getPrenom(),e.getPhoto_user()
                 ));
        }
       
            }
        });
                            
                            
                            VBox vBox ;
                           
                 vBox = new VBox( new Text(item.getNom()) , new Text(item.getPreno()) ,new Text(item.getComm())
                                );
                            vBox.setSpacing(4);
                            Image  image  = new Image("file:src/" + item.getPhoto_user());
                            ImageView imv =new ImageView(image);
                            imv.setFitHeight(130);
                            imv.setFitWidth(130);
                            
                            HBox hBox = new HBox(imv,vBox);
                            hBox.setSpacing(10);
                            
                            
                           setGraphic(hBox);
                          
                    
                        }else{
                         
        setText(null);
        setGraphic(null);
  
                        }
                    }

                };
            }

        });
      
        }
        catch (Exception e)
                {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Commentaire non modifié");
                alert.setHeaderText("Echec de la modification de l'Commentaire");
                alert.setContentText("Echec de la modification de l'Commentaire "+nouvComm.getText());
                alert.showAndWait();
                }
    
        nouvComm.setText("");
        
    }

    /**
     * Initializes the controller class.
     */
    
     private static class CustomThing {
         private int id_comm;
         private String comm;
         private Utilisateur utilisateur;
         private String nom;
         private String preno;
         private String photo_user;

        public CustomThing(int id_comm, String comm, Utilisateur utilisateur, String nom, String preno, String photo_user) {
            this.id_comm = id_comm;
            this.comm = comm;
            this.utilisateur = utilisateur;
            this.nom = nom;
            this.preno = preno;
            this.photo_user = photo_user;
        }

        

       
        public CustomThing(){
            
        }

        public int getId_comm() {
            return id_comm;
        }

        public void setId_comm(int id_comm) {
            this.id_comm = id_comm;
        }

        public String getComm() {
            return comm;
        }

        public void setComm(String comm) {
            this.comm = comm;
        }

        public Utilisateur getUtilisateur() {
            return utilisateur;
        }

        public void setUtilisateur(Utilisateur utilisateur) {
            this.utilisateur = utilisateur;
        }

        public String getNom() {
            return nom;
        }

        public void setNom(String nom) {
            this.nom = nom;
        }

        public String getPreno() {
            return preno;
        }

        public void setPreno(String preno) {
            this.preno = preno;
        }

        public String getPhoto_user() {
            return photo_user;
        }

        public void setPhoto_user(String photo_user) {
            this.photo_user = photo_user;
        }

   

     
         
         
         
     }
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        UpdateableListViewSkin<CustomThing> skin = new UpdateableListViewSkin<>(this.listComm);
        this.listComm.setSkin(skin);
        ((UpdateableListViewSkin) listComm.getSkin()).refresh();
        
        List<Test> rs = null ;
         ServiceCommentaire ps = new ServiceCommentaire();
         
        try {
            Experience ex = new Experience();
            rs = ps.listcommentaire(17);
            System.out.println("+++++"+rs);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(CommentaireExperienceController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         ObservableList<CustomThing> data = FXCollections.observableArrayList();
        for(Test e : rs) 
        {
         data.addAll(new CustomThing( e.getId_commentaire(),e.getCommen() ,e.getUtilisateur() , e.getNom(),e.getPrenom(),e.getPhoto_user()
                 ));
        }
        
        listComm.getItems().addAll(data);
        listComm.setCellFactory(new Callback<ListView<CustomThing>, ListCell<CustomThing>>() {

            @Override
            public ListCell<CustomThing> call(ListView<CustomThing> arg0) {
                return new ListCell<CustomThing>() {

                    @Override
                    protected void updateItem(CustomThing item, boolean bln) {
                        super.updateItem(item, bln);
                        if (item != null) {
                            
                            Button btn = new Button("supprimer");
                             btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                listComm.getItems().clear();
                
                UpdateableListViewSkin<CustomThing> skin = new UpdateableListViewSkin<>(listComm);
        listComm.setSkin(skin);
       ((UpdateableListViewSkin) listComm.getSkin()).refresh();
       ServiceCommentaire s = new ServiceCommentaire();
       s.Deletecommentaire(item.getId_comm());
       
       
       List<Test> rss = null ;
         ServiceCommentaire ps = new ServiceCommentaire();
                try {
                    rss = ps.listcommentaire(3);
                } catch (SQLException ex) {
                    Logger.getLogger(CommentaireExperienceController.class.getName()).log(Level.SEVERE, null, ex);
                }
         
         
        for(Test e : rss) 
        {
         listComm.getItems().add(new CustomThing( e.getId_commentaire(),e.getCommen() ,e.getUtilisateur() , e.getNom(),e.getPrenom(),e.getPhoto_user()
                 ));
        }
       
            }
        });
                            
                            
                            VBox vBox ;
                           if(item.getUtilisateur().getId_user()==c.getId_user()){     
                            vBox = new VBox( new Text(item.getNom()) , new Text(item.getPreno()) ,new Text(item.getComm()) 
                                  ,btn  );}
                else{ vBox = new VBox( new Text(item.getNom()) , new Text(item.getPreno()) ,new Text(item.getComm())
                                );}
                            vBox.setSpacing(4);
                            Image  image  = new Image("file:src/" + item.getPhoto_user());
                            ImageView imv =new ImageView(image);
                            imv.setFitHeight(130);
                            imv.setFitWidth(130);
                            
                            HBox hBox = new HBox(imv,vBox);
                            hBox.setSpacing(10);
                            
                            
                           setGraphic(hBox);
                          
                    
                        }else{
                         
        setText(null);
        setGraphic(null);
  
                        }
                    }

                };
            }

        });
        
        //////////////////////////////
    /*    ServiceExperience sessionservice = new ServiceExperience();
        Experience e = sessionservice.getExperienceById(c.getId_user());
        descArea.setText(e.getDescription_experience());
        Image  image  = new Image("http://127.0.0.1/img/im", true); 
        imagExp.setImage(image); */
        
        ///////////////////////////////////
        
        
        
        
        
        
        
        
        
    }    

  /*  private void onClicked_Experience(ActionEvent event) {
         Commentaire sess= new Commentaire();
         
         ServiceCommentaire sessionservice = new ServiceCommentaire();
         sessionservice.ajoutercommentaire(sess, 3, 3);
    }*/

    @FXML
    private void onAjoutClicked(ActionEvent event) {
      
       UpdateableListViewSkin<CustomThing> skin = new UpdateableListViewSkin<>(this.listComm);
        this.listComm.setSkin(skin);
        ((UpdateableListViewSkin) listComm.getSkin()).refresh();
        
        List<Test> rs = null ;
         ServiceCommentaire ps = new ServiceCommentaire();
        try {
            rs = ps.listcommentaire(3);
        } catch (SQLException ex) {
            Logger.getLogger(CommentaireExperienceController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         ObservableList<CustomThing> data = FXCollections.observableArrayList();
        for(Test e : rs) 
        {
         data.addAll(new CustomThing( e.getId_commentaire(),e.getCommen() ,e.getUtilisateur() , e.getNom(),e.getPrenom(),e.getPhoto_user()
                 ));
        }
        
        listComm.getItems().addAll(data);
         /***/
        Commentaire a = new Commentaire();
       ServiceCommentaire s = new ServiceCommentaire();
       a.setCommentaire(commentaireArea.getText());
       s.ajoutercommentaire(a);
        /***/
        listComm.setCellFactory(new Callback<ListView<CustomThing>, ListCell<CustomThing>>() {
           @Override
           public ListCell<CustomThing> call(ListView<CustomThing> arg0) {
                return new ListCell<CustomThing>() {

                    @Override
                    protected void updateItem(CustomThing item, boolean bln) {
                        super.updateItem(item, bln);
                        if (item != null) {
                        
                                
                                                 
                
                UpdateableListViewSkin<CustomThing> skin = new UpdateableListViewSkin<>(listComm);
        listComm.setSkin(skin);
       ((UpdateableListViewSkin) listComm.getSkin()).refresh();
     
       
       
     List<Test> rss = null ;
        // ServiceCommentaire ps = new ServiceCommentaire();
                try {
                    rss = s.listcommentaire(3);
                } catch (SQLException ex) {
                    Logger.getLogger(CommentaireExperienceController.class.getName()).log(Level.SEVERE, null, ex);
                }
         
         
        for(Test e : rss) 
        {
         listComm.getItems().add(new CustomThing( e.getId_commentaire(),e.getCommen() ,e.getUtilisateur() , e.getNom(),e.getPrenom(),e.getPhoto_user()
                 ));
        }
                                     
                                     
                                 
                            VBox vBox = new VBox( new Text(item.getNom()) , new Text(item.getPreno()) ,new Text(item.getComm()) 
                             );
                            vBox.setSpacing(4);
                            Image  image  = new Image("file:src/"+ item.getPhoto_user()); 
                            ImageView imv =new ImageView(image);
                            imv.setFitHeight(130);
                            imv.setFitWidth(130);
                            
                            HBox hBox = new HBox(imv, vBox);
                            hBox.setSpacing(10);
                            
                            
                            setGraphic(hBox);
                          
                    
                        }else{
                         
        setText(null);
        setGraphic(null);
  
                        }
                    }

                };
           }
        
    });
    commentaireArea.setText("");
   
}
}
    
    
    

    

