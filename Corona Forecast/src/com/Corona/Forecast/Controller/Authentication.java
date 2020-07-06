package com.Corona.Forecast.Controller;

import java.util.List;

import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import com.Corona.Forecast.Model.*;
import com.Corona.Forecast.View.*;

public class Authentication 
{
	//Attributes
	ValidUsers users =  new ValidUsers();
	private String validated;
	private String validatedAnalyst;
	private String validatedEpidemiologist;
	private String rejected;
	
	public Authentication()
	{
		users = new ValidUsers();
		validated = new String("Login Successful");
		validatedAnalyst = new String("Login Successful\nYou are logged in as an Analyst");
		validatedEpidemiologist = new String("Login Successful\nYou are logged in as an Epidemiologist");
		rejected =  new String("Login credentials are Invalid");
	}
	

	//Methods
	public int Login(String username, String password) {
		if(users.isUserValid(username,password) == false)
			return 0;
		if(users.isValidAnalyst(username, password))
			return 1;
		if(users.isValidEpidemiologist(username, password))
			return 2;
		if(users.isUserValid(username, password) == true)
			return 3;
		return -1;
	}
		
	public String ShowAttemptResults(int isValid) {
		if(isValid == 1)
			return validatedAnalyst;
		else if(isValid == 2)
			return validatedEpidemiologist;
		else if(isValid == 3)
			return validated;
		else
			return rejected;
	}
}

