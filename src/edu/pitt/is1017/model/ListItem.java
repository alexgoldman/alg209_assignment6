package edu.pitt.is1017.model;

import java.sql.*;

public class ListItem {
	
	private String description;
	private int id;
	private Timestamp timeStamp;
	public ListItem(String description, int id, Timestamp timeStamp){
		this.id=id;
		this.description=description;
		this.timeStamp=timeStamp;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getid() {
		return id;
	}

	public void setid(int id) {
		id = id;
	}
	
	public String toString(){
		return description;
	}

}
