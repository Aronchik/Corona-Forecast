package com.Corona.Forecast.Model;

import java.io.Serializable;

public class User implements Serializable{
	
	//Attributes
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	
	//Methods
	public User(String username, String password)
	{
		this.setUsername(username);
		this.setPassword(password);
	}
	public User createUser(String username, String password) 
	{
		User newUser = new User(username, password);
		
		return newUser;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
