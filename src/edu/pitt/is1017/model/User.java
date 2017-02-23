package edu.pitt.is1017.model;

public class User {
	
	private int id;
	private String firstName;
	private String lastName;
	
	public User(String firstName, String lastName, int id){
		this.firstName=firstName;
		this.lastName=lastName;
		this.id=id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public int getId() {
		return id;
	}
	
	public String toString(){
		return (firstName+" "+lastName);
		
	}

}
