 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entite;

/**
 *
 * @author admin
 */
public class Utilisateur {
    private int id_user;
    private String nom_user;

    public Utilisateur(int id_user, String nom_user) {
        this.id_user = id_user;
        this.nom_user = nom_user;
    }

    

    public Utilisateur() {
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getNom_user() {
        return nom_user;
    }

    public void setNom_user(String nom_user) {
        this.nom_user = nom_user;
    }
            
           
    
    
}
