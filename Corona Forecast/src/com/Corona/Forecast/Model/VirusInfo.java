package com.Corona.Forecast.Model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.List;

//This class is implemented with a singleton pattern
public class VirusInfo implements Serializable{
	
	//Single instance is of this class is instatiated here
	private final static VirusInfo virusInfoInstance = new VirusInfo();
	
	//Set by Epidemiologist
	private double basicReproductiveNumber;
	private boolean vaccineAvailability;
	private int illnessDuration;
	
	//Private constructor is required in a singleton
	private VirusInfo() {
		ObjectInputStream inputStream =  null;
		
		try {
		String filename = "Virus Information.txt";
		inputStream = new ObjectInputStream(new FileInputStream(filename));
		
		this.basicReproductiveNumber = inputStream.readDouble();
		this.vaccineAvailability = inputStream.readBoolean();
		this.illnessDuration = inputStream.readInt();
		
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	//As a part of the singleton pattern a getter is used to return the instance
	public static VirusInfo  getVirusInfoInstance() {
		return virusInfoInstance;
	}
	
	public double getBasicReproductiveNumber() {
		return basicReproductiveNumber;
	}
	public void setBasicReproductiveNumber(double basicReproductiveNumber) {
		this.basicReproductiveNumber = basicReproductiveNumber;
	}
	public boolean getVaccineAvailability() {
		return vaccineAvailability;
	}
	public void setVaccineAvailability(boolean vaccineAvailability) {
		this.vaccineAvailability = vaccineAvailability;
	}
	public int getIllnessDuration() {
		return illnessDuration;
	}
	public void setIllnessDuration(int illnessDuration) {
		this.illnessDuration = illnessDuration;
	}
	
}
