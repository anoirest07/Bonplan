/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entite.DemandeEtablissement;
import Entite.Etablissement;
import Services.ServiceDemandeEtablissement;
import Services.ServiceEtablissement;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author user
 */
public class ValiderAjoutEtabController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField tel;
    @FXML
    private TextField site;
    @FXML
    private TextField horraire;
    @FXML
    private TextField phetab;
    @FXML
    private TextField phpat;
    @FXML
    private TextField adr;
    @FXML
    private TextField cpos;
    @FXML
    private TextArea desc;
    @FXML
    private TextField map;
    @FXML
    private Button validation;
    @FXML
    private Button echec;
    @FXML
    private TextField categ;
    @FXML
    private TextField budget;

    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Ajouter(ActionEvent event) {
        Services.ServiceEtablissement SD = new ServiceEtablissement();
        Etablissement e = new Etablissement();
        
        e.setNom_etablissement(etab.getNom_etab_demande());
        e.setCategorie(etab.getCategorie());
        e.setTelephone_etablissement(etab.getTelephone_etab_demande());
        e.setSite_web(etab.getSite_web_etab_demande());
        e.setHoraire_travail(etab.getHoraire_travail_etab_demande());
        e.setPhoto_etablissement(etab.getPhoto_etab_demande());
        e.setPhoto_patente(etab.getPhoto_patente_etab_demande());
        e.setBudget(Etablissement.Budget.valueOf(etab.getBudget_demande()));
        e.setAdresse_etablissement(etab.getAdresse_etab_demande());
        e.setPosition(etab.getPosition_etab_demande());
        e.setCode_postal(etab.getCode_postal_etab_demande());
        e.setDescription_etablissement(etab.getDescription_etab_demande());
        SD.ajouterEtablissement(e);
    }

    @FXML
    private void Annuler(ActionEvent event) {
    }
    DemandeEtablissement etab ;
    public void setEtab(DemandeEtablissement e){
        this.etab = e ;
        nom.setText(e.getNom_etab_demande());
        categ.setText(Integer.toString(e.getCategorie().getId_categorie()));
        tel.setText(Integer.toString(e.getTelephone_etab_demande()));
        site.setText(e.getSite_web_etab_demande());
        horraire.setText(e.getSite_web_etab_demande());
        budget.setText(e.getBudget_demande().toString());
        phetab.setText(e.getPhoto_etab_demande());
        phpat.setText(e.getPhoto_patente_etab_demande());
        adr.setText(e.getAdresse_etab_demande());
        map.setText(e.getPosition_etab_demande());
        cpos.setText(Integer.toString(e.getCode_postal_etab_demande()));
        desc.setText(e.getDescription_etab_demande());
        
        
        
        
        
        
    }
    
}
