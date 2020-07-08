package com.Corona.Forecast.Model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ValidUsers {
	private List<User> validUserInfo = new ArrayList<User>();
	private List<User> validAnalysts = new ArrayList<User>();
	private List<User> validEpidemiologists = new ArrayList<User>();
	
	
	public boolean isUserValid(String username, String password) {
		
		ObjectInputStream inputStream =  null;
		
		try {
		String filename = "Valid Users.txt";
		inputStream = new ObjectInputStream(new FileInputStream(filename));
		
		validUserInfo = (List<User>) inputStream.readObject();
		
		} catch(IOException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		for (User usr : validUserInfo) {
			if(username.equals(usr.getUsername()) && password.equals(usr.getPassword())) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isValidAnalyst(String username, String password) {		
		ObjectInputStream inputStream =  null;
		
		try {
		String filename = "Valid Analysts.txt";
		inputStream = new ObjectInputStream(new FileInputStream(filename));
		
		validAnalysts = (List<User>) inputStream.readObject();
		
		} catch(IOException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		for (User usr : validAnalysts) {
			if(username.equals(usr.getUsername()) && password.equals(usr.getPassword())) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isValidEpidemiologist(String username, String password) {
		
		ObjectInputStream inputStream =  null;
		
		try {
		String filename = "Valid Epidemiologists.txt";
		inputStream = new ObjectInputStream(new FileInputStream(filename));
		
		validEpidemiologists = (List<User>) inputStream.readObject();
		
		} catch(IOException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		for (User usr : validEpidemiologists) {
			if(username.equals(usr.getUsername()) && password.equals(usr.getPassword())) {
				return true;
			}
		}
		return false;
	}
	
	
}
