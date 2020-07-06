package com.Corona.Forecast.Model;

import java.util.ArrayList;
import java.util.List;

public class ValidUsers {
	private List<User> validUserInfo =  new ArrayList<User>();
	private List<User> validAnalysts =  new ArrayList<User>();
	private List<User> validEpidemiologists = new ArrayList<User>();
	
	public ValidUsers()
	{
		User analyst = new User("Analyst","APass");
		User epidemiologist =  new User("Epidemiologist","EPass");
		User admin = new User("root","admin");
		
		validUserInfo.add(analyst);
		validUserInfo.add(epidemiologist);
		validUserInfo.add(admin);
		
		validAnalysts.add(analyst);
		validEpidemiologists.add(epidemiologist);
	}
	
	public boolean isUserValid(String username, String password) {
		for (User usr : validUserInfo) {
			if(username.equals(usr.getUsername()) && password.equals(usr.getPassword())) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isValidAnalyst(String username, String password) {
		for (User usr : validAnalysts) {
			if(username.equals(usr.getUsername()) && password.equals(usr.getPassword())) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isValidEpidemiologist(String username, String password) {
		for (User usr : validEpidemiologists) {
			if(username.equals(usr.getUsername()) && password.equals(usr.getPassword())) {
				return true;
			}
		}
		return false;
	}
	
	
}
