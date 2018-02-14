/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Entite.Etablissement;
import java.util.List;

/**
 *
 * @author user
 */
public interface IServiceEtablissement {
    
    public void ajouterEtablissement(Etablissement e);
    public void modifierEtablissement(Etablissement e);
    public void supprimerEtablissement(Etablissement e);
    public Etablissement chercherEtablissement(int id);
    public List<Etablissement> listEtablissement();
    
}
