package com.Corona.Forecast.Model;

import com.Corona.Forecast.Controller.*;
import java.util.ArrayList;
import java.util.List;

public class ForecastAlgorithm {
	
	//Attributes
	private int days; //Amount of DP to return
	private double basicReproductiveNumber; //R0 Entered by epidemiologist
	private double effectiveReproductiveNumber; //Calculated
	private double avgNoOfExposures; //E or B
	private double probabilityOfInfection; //P (0<P<1)
	private double recoveryPercentagePerDay; //in percent
	private PopulationInfo infoForm = new PopulationInfo();
	private VirusInfo virusInformation = VirusInfo.getVirusInfoInstance();
	private enum GraphType{
		INFECTION_RATE,INFECTED,ICU,DECEASED,RECOVERED
	}
	
	//SIR model attributes
	private int totalPopulation; //Total Population
	private int susceptible; //Starts with total population number minus infected
	private int infectious; //Starts with initial number of cases
	private int recovered; //Starts with 0
	
	//Class Constructor must receive the population info and also the epidemiologist info
	public ForecastAlgorithm(PopulationInfo informationForm, VirusInfo virusInformation, int days) {
		this.infoForm = informationForm;
		this.basicReproductiveNumber = virusInformation.getBasicReproductiveNumber();
		
		
		if(days >= 3 && days <= 180)
			this.days = days;
		else if(days < 3)
			this.days = 3;
		else
			this.days = 180;
		
		//Initializing Algorithm values from the population form
		totalPopulation = infoForm.getPopulationNumber();
		infectious = infoForm.getCurrentNumberofInfected();
		susceptible = infoForm.getPopulationNumber() - infectious;
		recovered = 0;
		
		//Default values according to statistics
		avgNoOfExposures = 15;
		probabilityOfInfection = 0.2; 
		recoveryPercentagePerDay = 0.8;
	}
	
	//R (effectiveReproductiveNumber) needs to be calculated for each i (day that passes)
	//If R>1 growth is exponential and disease is epidemic
	//if R=1 growth is linear and disease is endemic
	//if R<1 growth is declining and spread of disease is slowing
	
	

	//Methods
	
	//Calculate P
	//Goes down when people follow proper Hygiene practices
	//Calculated according to effective reproductive number
	private void calculateProbabilityOfInfection() {
		
		//Proper Hygiene among the population lowers the spread by 15%
		if(infoForm.isHygieneInformationCampaign())
			probabilityOfInfection*=0.85;
	}
	

	//Goes down when people stop gathering
	private void calculateAverageNumberOfExposures() {
		
		//Isolation has 15% effectiveness against the spread
		if(infoForm.isIsolation())
			avgNoOfExposures*=0.85;
		
		//Contact tracing has 10% effectiveness against the spread
		if(infoForm.isContactTracing())
			avgNoOfExposures*=0.9;
		
		//Travel restrictions have 10% effectiveness against the spread
		if(infoForm.isTravelRestrictions())
			avgNoOfExposures*=0.9;
		
		//Central location Shutdown has 15% effectiveness against the spread
		if(infoForm.isCentralLocationShudtown())
			avgNoOfExposures*=0.85;
	}
	
	//Depends on probability of infection
	//Also depends on the average number of exposures per day
	private void calculateEffectiveReproductiveNumber() {
		calculateProbabilityOfInfection();
		calculateAverageNumberOfExposures();
		effectiveReproductiveNumber = avgNoOfExposures*probabilityOfInfection;
	}
	
	private List<DataPoint> ISRModelAlgorithm(GraphType typeOfGraph)
	{
		List<DataPoint> infectionRateDP = new ArrayList<DataPoint>();
		List<DataPoint> infectedDP = new ArrayList<DataPoint>();
		List<DataPoint> ICUDP = new ArrayList<DataPoint>();
		List<DataPoint> deceasedDP = new ArrayList<DataPoint>();
		List<DataPoint> recoveredDP = new ArrayList<DataPoint>();
		
		//Calculate the Effective Reproductive Number using the Population Info Form
		//calculateEffectiveReproductiveNumber();
		
		
        //How many data points are in the graph (max x value)
        int maxDataPoints = days;
        
        //Maximum Value the function can take (max y value)
        //int maxScore = 100;
       
        DataPoint infectedRate = new DataPoint(0,0);
        DataPoint infected = new DataPoint(0,infectious);
        DataPoint icu = new DataPoint(0,0);
        DataPoint deceased = new DataPoint(0,0);
        DataPoint recovered = new DataPoint(0,0);
        
        
    	//temp.x = i;
    	//(Math.sin(0.2*i/3.14)) * maxScore;
    	//(1 + avgNoOfExposures*probabilityOfInfection)*temp.y;
    	//temp.y = (Math.sin(0.2*i/3.14)) * 100;
    	//infectedDP.add(new DataPoint(temp));

    	infectionRateDP.add(new DataPoint(infectedRate));
    	infectedDP.add(new DataPoint(infected));
    	ICUDP.add(new DataPoint(icu));
    	deceasedDP.add(new DataPoint(deceased));
    	recoveredDP.add(new DataPoint(recovered));    
    	
    	calculateEffectiveReproductiveNumber();
    	
    	int infectedToday;
    	int recoveredToday;
    	int deceasedToday;
    	int icuToday;
        
        //This where the data points are added
        for (int i = 1; i < maxDataPoints; i++) 
        {
        	infectedRate.x = infected.x = icu.x = deceased.x = recovered.x = i;

        	//effectiveReproductiveNumber = 1.2; //max = 3.8, min = less than one
        	
        	//Infections increase exponentially until herd immunity achieved which is 75% of the population infected
        	if(infected.y <= totalPopulation*0.75){
        		if(i%14 == 0) {
        			infectedToday = (int) (infected.y*effectiveReproductiveNumber);
        			infected.y += infectedToday;
        			susceptible = susceptible - (int)infectedToday;
        			//infectious = infected.y;
        		}
        		else {
        			infectedToday = 1000;
        			infected.y += infectedToday;
        			susceptible = susceptible - (int)infectedToday;
        		}
        			
        	}
        	else
        		infectedToday = 0;
        	
        	
        	if (i > 14 ) {
        		//30% chance of recovery in icu
        		deceasedToday = (int) (icu.y*0.70);
        		deceased.y += deceasedToday; 
        		
        		//Some amount recovers
        		recoveredToday = (int) (infected.y*recoveryPercentagePerDay);
        		if(recovered.y + recoveredToday <= totalPopulation*0.90)
        			recovered.y += recoveredToday;
        		
        		//5% critical condition
        		icuToday = (int) (infected.y*0.05 - deceasedToday);
            	icu.y += icuToday;
            	
            	infected.y -= (int) (icuToday + deceasedToday + recoveredToday);
            	
            	//Is calculated by dividing the amount of infected today by tests per day
            	infectedRate.y = infectedToday/infoForm.getTestingPerDay();
        	}
  
        	infectionRateDP.add(new DataPoint(infectedRate));
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
	
	//Getters-Setters

}
