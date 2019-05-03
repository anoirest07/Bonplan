/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Config.Connexion;
import Entite.Produit;
import IServices.IServiceProduit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author malek
 */
public class ServiceProduit implements IServiceProduit {

    public Connection cnx;

    public ServiceProduit() {
        this.cnx = Connexion.getInstance().getCon();
    }

    @Override
    public void ajouterProduit(Produit prod) {
        String req = "insert into produit (nom_produit,photo_produit ,prix_produit,id_etablissement) values (?,?,?,?)";
        try {
            PreparedStatement st1 = cnx.prepareStatement(req, PreparedStatement.RETURN_GENERATED_KEYS);

            st1.setString(1, prod.getNom_produit());
            st1.setString(2, prod.getPhoto_produit());

            st1.setDouble(3, prod.getPrix_produit());
           ServiceEtablissement sc = new ServiceEtablissement();
             st1.setInt(4,sc.chercherEtablissement(prod.getEtablissement().getId_etablissement()).getId_etablissement());

            st1.executeUpdate();

            ResultSet result = st1.getGeneratedKeys();
            int id = 0;
            while (result.next()) {
                id = result.getInt(1);
                prod.setId_produit(result.getInt(1));
                System.out.println("id ajout" + id);

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void deleteProduit(Produit p) {

        String req = "delete from produit where id_produit = ?";
        try {
            PreparedStatement st2 = Connexion.getInstance().getCon().prepareStatement(req);
            st2.setInt(1, p.getId_produit());
            st2.executeUpdate();

        } catch (Exception e) {
            System.out.println("msg: " + e.getMessage());
        }
    }

    @Override
    public void modifierProduit(Produit p) {

        try {
            String req = "UPDATE produit SET nom_produit=? ,prix_produit=? , photo_produit=? where id_produit=?";
            PreparedStatement st3 = Connexion.getInstance().getCon().prepareStatement(req);

            st3.setString(1, p.getNom_produit());
            st3.setDouble(2, p.getPrix_produit());
            st3.setString(3, p.getPhoto_produit());
            st3.setInt(4, p.getId_produit());
            st3.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("errueur dans la requete update");

        }

    }

    @Override
    public Produit chercherProduit(String nom_produit) {
        Produit g = new Produit();
        try {
            String select = "SELECT * FROM produit WHERE nom_produit = '" + nom_produit + "' ";
            Statement statement1 = cnx.createStatement();
            ResultSet result = statement1.executeQuery(select);

            while (result.next()) {
                g.setId_produit(result.getInt("id_produit"));
                g.setNom_produit(result.getString("nom_produit"));
                g.setPhoto_produit(result.getString("photo_produit"));
                g.setPrix_produit(result.getDouble("prix_produit"));

            }
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
            System.err.println("SQLSTATE: " + e.getSQLState());
            System.err.println("VnedorError: " + e.getErrorCode());
        }
        return g;
    }

    @Override
    public List<Produit> afficherProduit() {
        List<Produit> produits = new ArrayList<>();
        try {
            Statement stm = Connexion.getInstance().getCon().createStatement();
            ResultSet rest
                    = stm.executeQuery("select * from produit p, etablissement e  where p.id_etablissement=e.id_etablissement ");
            while (rest.next()) {
                Produit prod = new Produit();
                prod.setId_produit(rest.getInt("id_produit"));
                prod.setNom_produit(rest.getString("nom_produit"));
                prod.setPhoto_produit(rest.getString("photo_produit"));
                prod.setPrix_produit(rest.getDouble("prix_produit"));
                        ServiceEtablissement sc = new ServiceEtablissement();
                  prod.setEtablissement(sc.chercherEtablissement(rest.getInt("id_etablissement")));
               
                produits.add(prod);

            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceOffre.class.getName()).log(Level.SEVERE, null, ex);
        }
        return produits;
    }

    
    public List<Produit> afficherALLProduits() {
        List<Produit> produits = new ArrayList<>();
        try {
            Statement stm = Connexion.getInstance().getCon().createStatement();
            ResultSet rest
                    = stm.executeQuery("SELECT * from `produit`");
            while (rest.next()) {
                Produit prod = new Produit();
                prod.setId_produit(rest.getInt("id_produit"));
                prod.setNom_produit(rest.getString("nom_produit"));
                prod.setPhoto_produit(rest.getString("photo_produit"));
                prod.setPrix_produit(rest.getDouble("prix_produit"));
                        ServiceEtablissement sc = new ServiceEtablissement();
                  prod.setEtablissement(sc.chercherEtablissement(rest.getInt("id_etablissement")));
               
                produits.add(prod);

            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceOffre.class.getName()).log(Level.SEVERE, null, ex);
        }
        return produits;
    }

}
