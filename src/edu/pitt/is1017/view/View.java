package edu.pitt.is1017.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.TextField;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import edu.pitt.is1017.controller.Controller;

public class View {
	
	
	 private JTextField itemArea;
	 
	 private JTextField firstName;
	 
	 private JTextField lastName;
	 
	 private JLabel lblUser;
	 
	 private JLabel lblFirstName;
	 
	 private JLabel lblLastName;
	 
	 private JLabel lblTodo;

	 private JFrame frmMain;

	 private JButton btnAdd;
	 
	 private JButton btnDelete;
	 
	 private JButton btnAddUser;
	 
	 private JButton btnDeleteUser;

	 private DefaultListModel listModel;
	 
	 private DefaultListModel userModel;
	 
	 private DefaultTreeModel treeModel;

	 private JList itemList;
	 
	 private JList userList;
	 
	 private Controller controller;
	 
	 private JTree tree;
	 
	 private DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root node");
	 
	 private DefaultMutableTreeNode newRoot;
	 
	 private JScrollPane scroller;
	
	public View(){
		
		
		 frmMain = new JFrame();
		 frmMain.setLayout(null);
	     frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	     frmMain.setSize(900, 350);
	     
	     itemArea = new JTextField();
	     itemArea.setBounds(435, 110, 120, 30);
	     
	     lblUser = new JLabel("Enter new user or select existing");
	     lblUser.setBounds(20,20, 240,30);
	     
	     lblFirstName = new JLabel("First name");
	     lblFirstName.setBounds(20,80, 240,30);
	     
	     lblLastName = new JLabel("Last name");
	     lblLastName.setBounds(20,135, 240,30);
	     
	     lblTodo = new JLabel("Item Area");
	     lblTodo.setBounds(435,80, 240,30);
	     
	     firstName = new JTextField();
	     firstName.setBounds(20, 110, 120, 30);
	     
	     lastName = new JTextField();
	     lastName.setBounds(20, 160, 120, 30);
	     
	     btnAdd = new JButton ("Add");
	     btnAdd.setBounds(580, 75, 80, 30);
	     
	     btnDelete = new JButton("Delete");
	     btnDelete.setBounds(580, 150, 80, 30);
	     
	     btnAddUser = new JButton ("Add");
	     btnAddUser.setBounds(155, 75, 80, 30);
	     
	     btnDeleteUser = new JButton("Delete");
	     btnDeleteUser.setBounds(155, 150, 80, 30);
	     
	     tree = new JTree();
	     tree.setBounds(700,30,150,250);
	     tree.setVisible(true);
	     tree.setBorder(BorderFactory.createLineBorder(Color.black));
	     
	    
	     
	     treeModel = new DefaultTreeModel(root);
	     tree.setModel(treeModel);
	     
		
		
		root = (DefaultMutableTreeNode) treeModel.getRoot();
	     
		scroller = new JScrollPane(tree,
	    	      JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
	    	      JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		scroller.setPreferredSize(new Dimension(150, 250));
		
		frmMain.add(scroller, BorderLayout.CENTER);
	    scroller.setBorder(BorderFactory.createLineBorder(Color.black));
	    scroller.setBounds(700,30,150,250);
	    
	    scroller.setVisible(true);
		
	     itemList = new JList();
	     itemList.setBounds(700, 30, 150, 250);
	     itemList.setVisible(false);
	     
	     userList = new JList();
	     userList.setBounds(275, 30, 150, 250);
	     userList.setVisible(true);
	     
	     userModel = new DefaultListModel();
	     userList.setModel(userModel);
	     userList.setBorder(BorderFactory.createLineBorder(Color.black));
	     
	     listModel = new DefaultListModel();
	     itemList.setModel(listModel);
	     itemList.setBorder(BorderFactory.createLineBorder(Color.black));

	     frmMain.add(itemArea);
	     frmMain.add(firstName);
	     frmMain.add(btnAdd);
	     frmMain.add(btnDelete);
	     frmMain.add(itemList);
	     frmMain.add(userList);
	     frmMain.add(btnAddUser);
	     frmMain.add(btnDeleteUser);
	     frmMain.add(btnDeleteUser);
	     frmMain.add(lblUser);
	     frmMain.add(lblFirstName);
	     frmMain.add(lblLastName);
	     frmMain.add(lblTodo);
	     frmMain.add(lastName);
	     //frmMain.add(tree);
	     frmMain.add(scroller);
	     
	     frmMain.setVisible(true);
		
	}
	
	public void fillDB(){
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			System.out.println("Initializing db...");
			
			Connection connection = DriverManager.getConnection("jdbc:mysql://sis-teach-01.sis.pitt.edu:3306/alg209is1017","alg209is1017","alg209@pitt.edu");
			
			System.out.println(connection);
			
	        System.out.println("Creating statement...");
	        
	        

	        
			Statement statement = connection.createStatement();
			String insert = "SELECT todoList FROM todoList";
		
			System.out.println(insert);
			PreparedStatement stmt = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
			stmt.executeQuery();
			
			ResultSet rs = stmt.getResultSet();
	           while(rs.next()){
	        	 DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(rs.getString(1));
	   			root.add(newNode);
	   		
	}
	           tree.setModel(treeModel);
	           
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
	}



	public JButton getBtnAdd() {
		return btnAdd;
	}



	public JButton getBtnDelete() {
		return btnDelete;
	}



	public JTextField getItemArea() {
		return itemArea;
	}



	public DefaultListModel getListModel() {
		return listModel;
	}
	
	public DefaultTreeModel getTreeModel() {
		return treeModel;
	}

	public DefaultMutableTreeNode getRootNode() {
		return root;
	}

	public JList getItemList() {
		return itemList;
	}
	
	public JTree getTreeList() {
		return tree;
	}



	public JTextField getFirstName() {
		return firstName;
	}



	public JTextField getLastName() {
		return lastName;
	}



	public DefaultListModel getUserModel() {
		return userModel;
	}



	public void setFirstName(JTextField firstName) {
		this.firstName = firstName;
	}



	public JList getUserList() {
		return userList;
	}



	public JButton getBtnAddUser() {
		return btnAddUser;
	}



	public JButton getBtnDeleteUser() {
		return btnDeleteUser;
	}
	


}
