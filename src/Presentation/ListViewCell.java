/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entite.Evenement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.ListCell;

/**
 *
 * @author elbrh
 */
public class ListViewCell extends ListCell<Evenement>{
    
        @Override
    public void updateItem(Evenement p, boolean empty)
    {
        super.updateItem(p,empty);
        if(p != null)
        {
            EvenController data = new EvenController();
            try {
                data.setInfo(p);
            } catch (SQLException ex) {
                Logger.getLogger(ListViewCell.class.getName()).log(Level.SEVERE, null, ex);
            }
            setGraphic(data.getBox());
        }
    }


}
