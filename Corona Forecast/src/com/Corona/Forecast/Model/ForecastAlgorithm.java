package com.Corona.Forecast.Model;

import com.Corona.Forecast.Controller.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class ForecastAlgorithm {
	
	//Attributes
	private int days; //Amount of DP to return
	private double basicReproductiveNumber; //R0 Entered by epidemiologist
	private double effectiveReproductiveNumber; //Calculated
	private double avgNoOfExposures; //E or B
	private double probabilityOfInfection; //P (0<P<1)
	private double recoveryPercentage; //in percent
	private PopulationInfo infoForm = new PopulationInfo();
	private double normalizationFactor = 0.244;
	private VirusInfo virusInformation = VirusInfo.getVirusInfoInstance();
	private enum GraphType{
		INFECTION_RATE,INFECTED,ICU,DECEASED,RECOVERED
	}
	
	//SIR model attributes
	private int totalPopulation; //Total Population
	private int susceptible; //Starts with total population number minus infected
	private int infectious; //Starts with initial number of cases
	private int removed; //Starts with 0
	
	//Class Constructor must receive the population info and also the epidemiologist info
	public ForecastAlgorithm(PopulationInfo informationForm, VirusInfo virusInformation, int days) {
		this.infoForm = informationForm;
		this.basicReproductiveNumber = virusInformation.getBasicReproductiveNumber();
		
		if(virusInformation.getVaccineAvailability())
			JOptionPane.showMessageDialog(null, "Please note that a vaccine is available", "Vaccine", JOptionPane.INFORMATION_MESSAGE);
		
		if(days >= 3 && days <= 180)
			this.days = days;
		else if(days < 3)
			this.days = 3;
		else
			this.days = 180;
		
		resetValues();
	}
	
	private void resetValues()
	{
		
		//Initializing Algorithm values from the population form
		totalPopulation = infoForm.getPopulationNumber();
		infectious = infoForm.getCurrentNumberofInfected();
		susceptible = infoForm.getPopulationNumber() - infectious;
		removed = 1;
		
		//Default values according to statistics
		avgNoOfExposures = 12;
		probabilityOfInfection = 0.2; 
		recoveryPercentage = 0.2;
	}
	
	//R (effectiveReproductiveNumber) needs to be calculated for each i (day that passes)
	//If R>1 growth is exponential and disease is epidemic
	//if R=1 growth is linear and disease is endemic
	//if R<1 growth is declining and spread of disease is slowing
	
	

	//Methods
	
	//Calculate P
	//Goes down when people follow proper Hygiene practices
	private void calculateProbabilityOfInfection() {
		
		//Proper Hygiene among the population lowers the spread by 40%
		if(infoForm.isHygieneInformationCampaign())
			probabilityOfInfection*=0.60;
	}
	

	//Goes down when people stop gathering
	private void calculateAverageNumberOfExposures() {
		
		//Isolation has 50% effectiveness against the spread
		if(infoForm.isIsolation())
			avgNoOfExposures*=0.5;
		
		//Contact tracing has 30% effectiveness against the spread
		if(infoForm.isContactTracing())
			avgNoOfExposures*=0.7;
		
		//Travel restrictions have 20% effectiveness against the spread
		if(infoForm.isTravelRestrictions())
			avgNoOfExposures*=0.8;
		
		//Central location Shutdown has 30% effectiveness against the spread
		if(infoForm.isCentralLocationShudtown())
			avgNoOfExposures*=0.7;
	}
	
	//Depends on probability of infection
	//Also depends on the average number of exposures per day
	private void calculateEffectiveReproductiveNumber() {
		calculateProbabilityOfInfection();
		calculateAverageNumberOfExposures();
		effectiveReproductiveNumber = virusInformation.getBasicReproductiveNumber()*avgNoOfExposures*probabilityOfInfection;
	}
	
	private List<DataPoint> ISRModelAlgorithm(GraphType typeOfGraph)
	{
		List<DataPoint> infectionRateDP = new ArrayList<DataPoint>();
		List<DataPoint> infectedDP = new ArrayList<DataPoint>();
		List<DataPoint> ICUDP = new ArrayList<DataPoint>();
		List<DataPoint> deceasedDP = new ArrayList<DataPoint>();
		List<DataPoint> recoveredDP = new ArrayList<DataPoint>();
		
		resetValues();
		
		
        //How many data points are in the graph (max x value)
        int maxDataPoints = days;
        
       
        DataPoint infectionRate = new DataPoint(0,0);
        DataPoint infected = new DataPoint(0,infectious);
        DataPoint icu = new DataPoint(0,0);
        DataPoint deceased = new DataPoint(0,0);
        DataPoint recovered = new DataPoint(0,0);

    	infectionRateDP.add(new DataPoint(infectionRate));
    	infectedDP.add(new DataPoint(infected));
    	ICUDP.add(new DataPoint(icu));
    	deceasedDP.add(new DataPoint(deceased));
    	recoveredDP.add(new DataPoint(recovered));    
    	
    	//Calculate the Effective Reproductive Number using the Population Info Form
    	calculateEffectiveReproductiveNumber();
    	

        
        //This where the data points are added
        for (int i = 1; i < maxDataPoints; i++) 
        {
        	double newInfected = 0;
        	double newRecovered = 0;
        	double newDeceased = 0;
        	double newICU = 0;
        	
        	infectionRate.x = infected.x = icu.x = deceased.x = recovered.x = i;
        	
        	//Infections increase exponentially until herd immunity achieved which is about 75% of the population infected
        	newInfected = infected.y*effectiveReproductiveNumber*(susceptible*normalizationFactor/totalPopulation*normalizationFactor);
        	infected.y += (int) newInfected;
        	infectious += newInfected;
        	susceptible -= newInfected;
    		
        	
        	//Some amount begin recover from the virus after about 20 days
        	if(i > virusInformation.getIllnessDuration())
        	{
        		//Out of the infected about 5% will need hospitalization in the ICU
        		newICU = infected.y*0.05;
        		icu.y += newICU;
        		
        		//Out of those hospitalized in the ICU about 20% will die
        		newDeceased = icu.y*0.2;
        		deceased.y += newDeceased;
        		icu.y -= newDeceased;
        		removed += newDeceased;
        		
        		
        		//Some amount recovers from the disease
	        	newRecovered = infected.y*recoveryPercentage;
	        	newRecovered += icu.y*0.1;
	        	icu.y -= icu.y*0.1;
	    		recovered.y += newRecovered;
	    		infected.y -= newRecovered;
	    		infectious -= newRecovered;
	    		removed += newRecovered;
        	}

  
        	//Infection Rate graph calculating what percentage of the total tests are likely to be positive
        	infectionRate.y = (700.0*infectious/totalPopulation);
        	
        	infectionRateDP.add(new DataPoint(infectionRate));
        	infectedDP.add(new DataPoint(infected));
        	ICUDP.add(new DataPoint(icu));
        	deceasedDP.add(new DataPoint(deceased));
        	recoveredDP.add(new DataPoint(recovered));        	
        }
        
        //Return type is based on DP request from controller
        switch(typeOfGraph)
        {
        case INFECTION_RATE:
        	return infectionRateDP;
        case INFECTED:
        	return infectedDP;
        case ICU:
        	return ICUDP;
        case DECEASED:
        	return deceasedDP;
        case RECOVERED:
        	return recoveredDP;
        }      
        
        //In case of error
        return new ArrayList<DataPoint>();
	}
	
	
	//Interfacing with controller unit
	public List<DataPoint> calculateInfectionRateDataPoints() {
		
		List<DataPoint> infectionRateList = new ArrayList<DataPoint>();
		
		infectionRateList = ISRModelAlgorithm(GraphType.INFECTION_RATE);
		
		return infectionRateList;
	}
	
	
	public List<DataPoint> calculateInfectedDataPoints() {
		
		List<DataPoint> infectedList = new ArrayList<DataPoint>();
		
		infectedList = ISRModelAlgorithm(GraphType.INFECTED);
		
		return infectedList;
	}
	
	
	public List<DataPoint> calculateRecoveredDataPoints() {
		
		List<DataPoint> recoveredList = new ArrayList<DataPoint>();
		
		recoveredList = ISRModelAlgorithm(GraphType.RECOVERED);
		
		return recoveredList;
	}
	
	
	public List<DataPoint> calculateICUDataPoints() {
		
		List<DataPoint> icuList = new ArrayList<DataPoint>();
		
		icuList = ISRModelAlgorithm(GraphType.ICU);
		
		return icuList;
	}
	
	
	public List<DataPoint> calculateDeceasedDataPoints() {
		
		List<DataPoint> deceasedList = new ArrayList<DataPoint>();
		
		deceasedList = ISRModelAlgorithm(GraphType.DECEASED);
		
		return deceasedList;
	}

}
