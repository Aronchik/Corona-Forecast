package com.Corona.Forecast.Controller;

public class Instruction {
	
	//Attributes
	private String analystInstructions;
	private String epidemiologistInstructions;
	private String loginInstructions;
	
	
	public Instruction()
	{
		analystInstructions = 
				"<html>You are currently logged in as an analyst. <br>"
				+ "Fill in the form with the latest information you<br>"
				+ "have regarding the current state of the situation in your country <br>"
				+ "Choose the information Graph you would like to view <br>"
				+ "and click \"Run\" to view the results.</html>";
		
		epidemiologistInstructions = 
				"<html>You are currently logged in as an epidemiologist. <br>"
				+ "You can update virus characteristics <br>"
				+ "with the latest research information. <br>"
				+ "Fill out the form with new or updated findings and click \"Submit\" <br>"
				+ "to confirm the update.</html>";
		
		loginInstructions = 
				"<html>Welcome to the Corona Forecast Modeling Software. <br>"
				+ "You can Login as an Analyst or an Epidemiologist. <br>"
				+ "As an Analyst you will be able to view the prediction model <br>"
				+ "of the spread of the disease. <br>"
				+ "As an epidemiologist you will be able to update the model with <br>"
				+ "latest research information. <br>"
				+ "Enter your login credentials and click \"Login\" to sign in.</html>";
	}
	
	//Methods
	public String getAnalystInstructions() {
		return analystInstructions;
		}
	public String getEpidemiologistInstructions() {
		return epidemiologistInstructions;
		}
	public String getLoginInstructions() {
		return loginInstructions;
	}

}
