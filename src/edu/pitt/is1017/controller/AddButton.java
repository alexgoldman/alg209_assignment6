package edu.pitt.is1017.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import edu.pitt.is1017.model.ListItem;
import edu.pitt.is1017.model.User;

public class AddButton implements ActionListener{
	
	private Controller controller;
	
	public AddButton(Controller controller){
		this.controller = controller;
	}
	
	public void actionPerformed(ActionEvent e) {
		String enteredText = controller.getView().getItemArea().getText();
		Timestamp time = controller.getModel().getCurrentTimeStamp();
		Boolean userSelected = controller.getView().getUserList().isSelectionEmpty();
		Boolean itemSelected = controller.getView().getTreeList().isSelectionEmpty();
		//Makes sure a user is selected and text is entered
		if(enteredText.isEmpty() || userSelected==true || itemSelected==true){
			System.out.println("Error");
		} else {
			//Adds todo text to the gui view
			controller.getView().getListModel().addElement(enteredText);
			
			//Sets i to items database ID and stores item in database
			int i= controller.getModel().addDatabaseItem(enteredText);
			
			
			
			//New gui adder with the JTree
			DefaultMutableTreeNode rootTest = controller.getView().getRootNode();
			DefaultMutableTreeNode textTest = new DefaultMutableTreeNode(enteredText);
			int childCount = controller.getView().getRootNode().getChildCount();
			DefaultTreeModel modelTest = controller.getView().getTreeModel();
			controller.getView().getTreeModel().insertNodeInto(new DefaultMutableTreeNode(enteredText), controller.getView().getRootNode(), controller.getView().getRootNode().getChildCount());
			
			DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) controller.getView().getTreeList().getSelectionPath().getLastPathComponent();
			
			DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(enteredText);
			selectedNode.add(newNode);
			
			//Adds item to ListItem vector
			controller.getModel().addListItem(enteredText,i,time);
			
			//Resets textfield
			controller.getView().getItemArea().setText(null);
				
			//Gets all the users from the user vector
			Vector<User> u = controller.getModel().getUserList();
			
			Vector<ListItem> item = controller.getModel().getList();
			
			ListItem newItem = item.lastElement();
			
			
			
			//Initializes user id to the selected position
			int userID= controller.getView().getUserList().getSelectedIndex();
			int vectorID=0;
			
			Iterator<User> iter = u.iterator();
			while(iter.hasNext()){
				User usr = iter.next();
				
				//Checks if the selected user matches up with the user position in the vector
				//Sets the selected user if it does
			      if(userID==vectorID )
			      {
			    	User selectedUser = usr;
			    	System.out.println(selectedUser);
			    	controller.getModel().addHashEntry(newItem,selectedUser);
			        vectorID++;
			      } else vectorID++;
			}
			
			//User selectedUser = controller.getView().getUserList().getSelectedValue();
						
			
			
			
			
			
			HashMap<ListItem,User> test = controller.getModel().getUsertodoHash();
			for(Map.Entry kv: test.entrySet()){
				System.out.println(kv);
			}
		}
		
	}

}
