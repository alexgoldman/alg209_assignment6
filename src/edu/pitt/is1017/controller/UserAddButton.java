package edu.pitt.is1017.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;

import javax.swing.JOptionPane;

public class UserAddButton implements ActionListener{
	
	private Controller controller;
	
	public UserAddButton(Controller controller){
		this.controller = controller;
	}




	public void actionPerformed(ActionEvent e) {
		String enteredFName = controller.getView().getFirstName().getText();
		String enteredLName = controller.getView().getLastName().getText();
		//Checks if name fields are empty
		if((enteredFName.isEmpty() || enteredLName.isEmpty())){
			
		}	else if((!(enteredFName.isEmpty())&&(!(enteredLName.isEmpty())))){
			
			//Adds user to gui view
			controller.getView().getUserModel().addElement(""+enteredFName+" "+ enteredLName);
			
			//Adds user to database
			int id = controller.getModel().addUserDB(enteredFName, enteredLName);
		
			//Adds user to vector
			controller.getModel().addUser(enteredFName, enteredLName, id);
		}
		
	}
}
	
