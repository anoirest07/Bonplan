/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entite;

import java.sql.Date;

/**
 *
 * @author admin
 */
public class Publicite 
{
    private int id_publicite;
    private String description_publicite;
    private String photo_publicite;
    private int enabled;
    private Etablissement etablissement;
    
    public Publicite() 
    {
    }

    public Publicite(String description_publicite, String photo_publicite, int enabled, Etablissement etablissement) {
        this.description_publicite = description_publicite;
        this.photo_publicite = photo_publicite;
        this.enabled = enabled;
        this.etablissement = etablissement;
    }

    

    
    
    public int getId_publicite() {
        return id_publicite;
    }

    public void setId_publicite(int id_publicite) {
        this.id_publicite = id_publicite;
    }

    public String getDescription_publicite() {
        return description_publicite;
    }

    public String getPhoto_publicite() {
        return photo_publicite;
    }

    public Etablissement getEtablissement() {
        return etablissement;
    }

    public void setDescription_publicite(String description_publicite) {
        this.description_publicite = description_publicite;
    }

    public void setPhoto_publicite(String photo_publicite) {
        this.photo_publicite = photo_publicite;
    }

    public void setEtablissement(Etablissement etablissement) {
        this.etablissement = etablissement;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }
    

    @Override
    public String toString() {
        return description_publicite ;
    
    
}
}