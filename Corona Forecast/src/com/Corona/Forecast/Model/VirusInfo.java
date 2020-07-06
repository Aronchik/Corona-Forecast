package com.Corona.Forecast.Model;


//This class is implemented with a singleton pattern
public class VirusInfo {
	
	//Single instance is of this class is instatiated here
	private final static VirusInfo virusInfoInstance = new VirusInfo();
	
	//Set by Epidemiologist
	private static double basicReproductiveNumber = 3.8;
	private static boolean vaccineAvailability;
	private static int illnessDuration;
	
	//Private constructor is required in a singleton
	private VirusInfo() {}
	
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
	public boolean isVaccineAvailability() {
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
