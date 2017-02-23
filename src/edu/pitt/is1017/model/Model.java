package edu.pitt.is1017.model;

import java.util.HashMap;
import java.util.Vector;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;

import java.sql.*;

public class Model {
	
	
	
	private Vector<ListItem> todoList;
	private Vector<User> userList;
	private HashMap<ListItem,User> usertodoHash;
	
	
	
	
	public Model(){
		todoList= new Vector<ListItem>();
		userList = new Vector<User>();
		usertodoHash = new HashMap<ListItem,User>();
		
	}
	
	
	
	
	public void addListItem(String text, int id, Timestamp timeStamp){
		todoList.addElement(new ListItem(text, id, timeStamp));
	}
	
	public void addUser(String firstName, String lastName, int id){
		userList.addElement(new User(firstName, lastName, id));
	}
	
	public void addHashEntry(ListItem li, User u){
		usertodoHash.put(li, u );
	}
	
	public int addUserDB(String fname, String lname){
		int dataID=-1;
		
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
			System.out.println("Connecting to database...");
			
			Connection connection = DriverManager.getConnection("jdbc:mysql://sis-teach-01.sis.pitt.edu:3306/alg209is1017","alg209is1017","alg209@pitt.edu");
			
			System.out.println(connection);
			
	        System.out.println("Creating statement...");
	        
	        

	        
			Statement statement = connection.createStatement();
			String insert = "INSERT INTO user(firstName,lastName) VALUES('"+fname+"','"+lname+"')";
		
			System.out.println(insert);
			PreparedStatement stmt = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
			stmt.executeUpdate();
			
			ResultSet rs = stmt.getGeneratedKeys();
	           while(rs.next()){
	            dataID = rs.getInt(1);
	            System.out.println(dataID);
	}
	           
	           
	         
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		return dataID;
		
	}
	
	public int addDatabaseItem(String text){
		int dataID=-1;
		
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
			System.out.println("Connecting to database...");
			
			Connection connection = DriverManager.getConnection("jdbc:mysql://sis-teach-01.sis.pitt.edu:3306/alg209is1017","alg209is1017","alg209@pitt.edu");
			
			System.out.println(connection);
			
	        System.out.println("Creating statement...");
	        
	        

	        
			Statement statement = connection.createStatement();
			String insert = "INSERT INTO todoList(todoList) VALUES('"+text+"')";
		
			System.out.println(insert);
			PreparedStatement stmt = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
			stmt.executeUpdate();
			
			ResultSet rs = stmt.getGeneratedKeys();
	           while(rs.next()){
	            dataID = rs.getInt(1);
	            System.out.println(dataID);
	}
	           
	           
	         
			
			
			/*
			String query = "CREATE TABLE ";
			Statement statement =  connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			*/
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		return dataID;
		
	}
	
	

	
	public void deleteListItem(int i){
		todoList.removeElement(i);
	}
	
	public void deleteUserItem(int i){
		userList.removeElement(i);
	}
	/*
	public void deleteHashEntry(ListItem li,User u){
		usertodoHash.remove(li);
	}
	*/
	
	public void deleteUser(int i){
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
			System.out.println("Connecting to database...");
			
			Connection connection = DriverManager.getConnection("jdbc:mysql://sis-teach-01.sis.pitt.edu:3306/alg209is1017","alg209is1017","alg209@pitt.edu");
			
			System.out.println(connection);
			
	        System.out.println("Creating statement...");
	    
	        String delete = "DELETE FROM user WHERE id=("+(i)+")";
	        PreparedStatement stmt = connection.prepareStatement(delete, Statement.RETURN_GENERATED_KEYS);
			stmt.executeUpdate();
			
			Statement statement = connection.createStatement();

			

			String query  = "SELECT * FROM user";
			statement.executeQuery(query);
			
			
			statement.executeUpdate(delete);
			System.out.println(delete);
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}	
	
	public void deleteDatabaseItem(int i){
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
			System.out.println("Connecting to database...");
			
			Connection connection = DriverManager.getConnection("jdbc:mysql://sis-teach-01.sis.pitt.edu:3306/alg209is1017","alg209is1017","alg209@pitt.edu");
			
			System.out.println(connection);
			
	        System.out.println("Creating statement...");
	    
	        String delete = "DELETE FROM todoList WHERE id=("+(i)+")";
	        PreparedStatement stmt = connection.prepareStatement(delete, Statement.RETURN_GENERATED_KEYS);
			stmt.executeUpdate();
			
			Statement statement = connection.createStatement();

			

			String query  = "SELECT * FROM todoList";
			statement.executeQuery(query);
			
			
			statement.executeUpdate(delete);
			System.out.println(delete);
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static java.sql.Timestamp getCurrentTimeStamp() {

		java.util.Date today = new java.util.Date();
		return new java.sql.Timestamp(today.getTime());

	}
	
	
	
	public Vector<ListItem> getList(){
		return todoList;
	}

	public Vector<User> getUserList() {
		return userList;
	}

	public HashMap<ListItem,User> getUsertodoHash() {
		return usertodoHash;
	}

	public HashMap<ListItem, User> deleteHashEntry(ListItem selected) {
		usertodoHash.remove(selected);
		return null;
	}
	


}
