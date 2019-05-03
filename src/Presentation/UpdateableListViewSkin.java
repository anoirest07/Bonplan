/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import com.sun.javafx.scene.control.skin.ListViewSkin;
import javafx.scene.control.ListView;

/**
 *
 * @author Nidhal
 */
public class UpdateableListViewSkin <T> extends ListViewSkin<T> {
    
    public UpdateableListViewSkin(ListView<T> arg0) {
		super(arg0);
	}
	
	public void refresh() {
		super.flow.rebuildCells();
	}
	
	@SuppressWarnings("unchecked")
    static <T> UpdateableListViewSkin<T> cast(Object obj) {
        return (UpdateableListViewSkin<T>)obj;
    }

    
}
