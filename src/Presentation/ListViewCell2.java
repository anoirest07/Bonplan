/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entite.Etablissement;
import javafx.scene.control.ListCell;

/**
 *
 * @author Nadia
 */
public class ListViewCell2 extends ListCell<Etablissement>{
    
        @Override
    public void updateItem(Etablissement p, boolean empty)
    {
        super.updateItem(p,empty);
        if(p != null)
        {
            EtabController data = new EtabController();
            data.setInfo(p);
            setGraphic(data.getBox());
        }
    }

    
}
