package edu.pitt.is1017.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import edu.pitt.is1017.model.ListItem;
import edu.pitt.is1017.model.User;



public class UserDeleteButton implements ActionListener {
	
	private Controller controller;
	

	public UserDeleteButton(Controller controller){
		
		this.controller=controller;
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		int i = controller.getView().getUserList().getSelectedIndex();
		int itemNumber = 0;
		int d=0;
		int modelID=0;
		int loopNumber=0;
		
		
		if(controller.getView().getUserList().getSelectedValue()==null){
			controller.getView().getBtnDeleteUser().setEnabled(false);
			controller.getView().getBtnDeleteUser().setEnabled(true);
		} else {
			//Gets list of users
			HashMap<ListItem,User> itemUserIdentifier = controller.getModel().getUsertodoHash();
			//Iterates through the users and deletes them and their associated items from the hashmap
			 Iterator it = itemUserIdentifier.entrySet().iterator();
			    while (it.hasNext()) {
			        Map.Entry pair = (Map.Entry)it.next();
			        if(pair.getValue().toString().equals(controller.getView().getUserList().getSelectedValue().toString())){
			        	//System.out.println(pair.getKey() + " Key = Value " + pair.getValue());
			        	it.remove(); // avoids a ConcurrentModificationExceptions
			        	System.out.println(pair.getKey().toString());
				        itemUserIdentifier.remove(pair.getKey(),pair.getValue());
				        
			        }
			        
					
					
					
				}
			    
			    
		        Vector<ListItem> item = controller.getModel().getList();
				//Loops through the list of items
				Iterator<ListItem> iterL = item.iterator();
				for(loopNumber=0;loopNumber<item.size()+1;loopNumber++){
					ListItem lis = item.elementAt(itemNumber);
					System.out.println("List iterator: " + lis.toString());
					
					//Selection is found
				      if(!(itemUserIdentifier.containsKey(lis)))
				      {
				    	  	
				    	  	
				    	  	//Removes item from database
				    	  	System.out.println(lis.getid());
				    	  	d=lis.getid();
							controller.getModel().deleteDatabaseItem(d);
							System.out.println("Removing from view");
							//Removes item from view
							controller.getView().getListModel().removeElementAt(itemNumber);
							ListItem selected = lis;
							System.out.println("Removing from vector");
							//Removes item from list vector
							controller.getModel().deleteListItem(itemNumber);
							item.remove(itemNumber);
							
							
				        
				      } else {
				      		itemNumber++;
				      }
				}
				
			        
			        
			        
			    
			
			//Gets list of users
			Vector<User> user = controller.getModel().getUserList();
			
			//Loops through users and finds the selected one
			Iterator<User> iter = user.iterator();
			while(iter.hasNext()){
				User usr = iter.next();
				System.out.println("User iterator");
				//Selection found
			      if(i==modelID )
			      {
			    	d=usr.getId();
			        iter.remove();
			        modelID++;
			      } else modelID++;
			}
			
			
			//Deletes user from database
			controller.getModel().deleteUser(d);
			//Deletes user from view
			controller.getView().getUserModel().removeElementAt(i);
			//Deletes user from user vector
			controller.getModel().deleteUserItem(i);
			
			HashMap<ListItem,User> test = controller.getModel().getUsertodoHash();
			for(Map.Entry kv: test.entrySet()){
				System.out.println(kv);
			}
			
			
		}
	
		
		
		
		
		
	}
}

