package edu.pitt.is1017.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.pitt.is1017.model.Model;
import edu.pitt.is1017.view.View;

public class Controller {
	
	private View view;
	private Model model;
	private AddButton addButton;
	private DeleteButton deleteButton;
	private UserAddButton userAddButton;
	private UserDeleteButton userDeleteButton;
	
	
	public Controller (View view, Model model){
		this.view=view;
		this.model=model;
		
		this.addButton= new AddButton(this);
		this.view.getBtnAdd().addActionListener(addButton);
		
		this.deleteButton= new DeleteButton(this);
		this.view.getBtnDelete().addActionListener(deleteButton);
		
		this.userAddButton = new UserAddButton(this);
		this.view.getBtnAddUser().addActionListener(userAddButton);
		
		this.userDeleteButton = new UserDeleteButton(this);
		this.view.getBtnDeleteUser().addActionListener(userDeleteButton);
	}


	public View getView() {
		return view;
	}


	public Model getModel() {
		return model;
	}


	public AddButton getAddButton() {
		return addButton;
	}


	public DeleteButton getDeleteButton() {
		return deleteButton;
	}


	public UserAddButton getUserAddButton() {
		return userAddButton;
	}


	public UserDeleteButton getUserDeleteButton() {
		return userDeleteButton;
	}
	
	



}
