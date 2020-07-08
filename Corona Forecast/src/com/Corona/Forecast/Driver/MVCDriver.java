package com.Corona.Forecast.Driver;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;

import com.Corona.Forecast.Model.*;
import com.Corona.Forecast.View.*;
import com.Corona.Forecast.Controller.*;

public class MVCDriver {
	
    public static void main(String[] args) {
    	SystemLogin loginScreen = new SystemLogin();
    	
    	List<User> validUserInfo =  new ArrayList<User>();
    	List<User> validAnalysts =  new ArrayList<User>();
    	List<User> validEpidemiologists = new ArrayList<User>();
    	
		ObjectOutputStream outputStream =  null;
		User analyst = new User("Analyst","APass");
		User epidemiologist =  new User("Epidemiologist","EPass");
		User admin = new User("root","admin");
		
		validUserInfo.add(analyst);
		validUserInfo.add(epidemiologist);
		validUserInfo.add(admin);
		
		validAnalysts.add(analyst);
		validEpidemiologists.add(epidemiologist);
		
		
		try {
		String filename = "Valid Users.txt";
		outputStream = new ObjectOutputStream(new FileOutputStream(filename));
		outputStream.writeObject(validUserInfo);
		
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			try {
				outputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		try {
		String filename = "Valid Analysts.txt";
		outputStream = new ObjectOutputStream(new FileOutputStream(filename));
		outputStream.writeObject(validAnalysts);
		
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			try {
				outputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		try {
		String filename = "Valid Epidemiologists.txt";
		outputStream = new ObjectOutputStream(new FileOutputStream(filename));
		outputStream.writeObject(validEpidemiologists);
		
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			try {
				outputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		try {
		String filename = "Virus Information.txt";
		outputStream = new ObjectOutputStream(new FileOutputStream(filename));
		outputStream.writeDouble(3.8);
		outputStream.writeBoolean(true);
		outputStream.writeInt(20);
		
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			try {
				outputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
    	
    	loginScreen.main(null);
     }
}