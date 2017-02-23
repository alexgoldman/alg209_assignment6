package edu.pitt.is1017.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import edu.pitt.is1017.model.ListItem;
import edu.pitt.is1017.model.User;



public class DeleteButton implements ActionListener {
	
	private Controller controller;
	
	public DeleteButton(Controller controller){
		
		this.controller=controller;
		
	}
	
	public void actionPerformed(ActionEvent e) {
		int i = controller.getView().getItemList().getSelectedIndex();
		int modelID = 0;
		int d=0;
		
		
		
		if(controller.getView().getItemList().getSelectedValue()==null){
			controller.getView().getBtnDelete().setEnabled(false);
			controller.getView().getBtnDelete().setEnabled(true);
		}
		else{
		//Gets vector of all items	
		Vector<ListItem> item = controller.getModel().getList();
		//Loops through the list of items
		Iterator<ListItem> iter = item.iterator();
		while(iter.hasNext()){
			ListItem lis = iter.next();
			System.out.println(i);
			
			System.out.println(modelID);
			//Selection is found
		      if(i==modelID )
		      {
		    	ListItem selected = lis;
		    	//Removes the item from the hashmap
		    	HashMap<ListItem,User> hasher = controller.getModel().deleteHashEntry(selected);
		    	d=lis.getid();
		        iter.remove();
		        modelID++;
		      } else modelID++;
		}
		//Removes item from database
		controller.getModel().deleteDatabaseItem(d);
		//Removes item from view
		controller.getView().getListModel().removeElementAt(i);
		//Removes item from list vector
		controller.getModel().deleteListItem(i);
		}
	}

}
