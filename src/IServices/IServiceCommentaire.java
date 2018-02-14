/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Entite.Commentaire;
import java.util.List;

/**
 *
 * @author amine
 */
public interface IServiceCommentaire {
        public void ajoutercritere(Commentaire e); 
         public void editcommentaire(Commentaire cs);
          public void Deletecommentaire(Commentaire ce);
          public Commentaire Findcommentaire(int id_commentaire);
           public List<Commentaire> listcommentaire();



    
}
