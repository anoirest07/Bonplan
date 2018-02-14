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
public class DemandePub {
    private int id_demande_pub;
    private String  description_demande_pub;
    private String  photo_demande_pub;
    private Etablissement etablissement;

    public DemandePub() {
    }

    public DemandePub(int id_demande_pub, String description_demande_pub, String photo_demande_pub, Etablissement etablissement) {
        this.id_demande_pub = id_demande_pub;
        this.description_demande_pub = description_demande_pub;
        this.photo_demande_pub = photo_demande_pub;
        this.etablissement = etablissement;
    }

    public int getId_demande_pub() {
        return id_demande_pub;
    }

    public String getDescription_demande_pub() {
        return description_demande_pub;
    }

    public String getPhoto_demande_pub() {
        return photo_demande_pub;
    }

    public Etablissement getEtablissement() {
        return etablissement;
    }

    public void setId_demande_pub(int id_demande_pub) {
        this.id_demande_pub = id_demande_pub;
    }

    public void setDescription_demande_pub(String description_demande_pub) {
        this.description_demande_pub = description_demande_pub;
    }

    public void setPhoto_demande_pub(String photo_demande_pub) {
        this.photo_demande_pub = photo_demande_pub;
    }

    public void setEtablissement(Etablissement etablissement) {
        this.etablissement = etablissement;
    }

    @Override
    public String toString() {
        return "DemandePub{" + "id_demande_pub=" + id_demande_pub + ", description_demande_pub=" + description_demande_pub + ", photo_demande_pub=" + photo_demande_pub + ", etablissement=" + etablissement + '}';
    }

    
    
    
                    
                    
}
