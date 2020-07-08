package com.Corona.Forecast.Model;

public class PopulationInfo {
	
	//Attributes
	private boolean isolation; //Not quarantining has the strongest effect on numbers
	private boolean contactTracing;
	private boolean travelRestrictions;
	private boolean hygieneInformationCampaign;
	private boolean centralLocationShudtown;
	private int currentNumberofInfected;
	private int testingPerDay;
	private int populationNumber;
	
	
	public boolean isIsolation() {
		return isolation;
	}
	public void setIsolation(boolean isolation) {
		this.isolation = isolation;
	}
	public boolean isContactTracing() {
		return contactTracing;
	}
	public void setContactTracing(boolean contactTracing) {
		this.contactTracing = contactTracing;
	}
	public boolean isTravelRestrictions() {
		return travelRestrictions;
	}
	public void setTravelRestrictions(boolean travelRestrictions) {
		this.travelRestrictions = travelRestrictions;
	}
	public boolean isHygieneInformationCampaign() {
		return hygieneInformationCampaign;
	}
	public void setHygieneInformationCampaign(boolean hygieneInformationCampaign) {
		this.hygieneInformationCampaign = hygieneInformationCampaign;
	}
	public boolean isCentralLocationShudtown() {
		return centralLocationShudtown;
	}
	public void setCentralLocationShudtown(boolean centralLocationShudtown) {
		this.centralLocationShudtown = centralLocationShudtown;
	}
	public int getCurrentNumberofInfected() {
		return currentNumberofInfected;
	}
	public void setCurrentNumberofInfected(int currentNumberofInfected) {
		if(0 <= currentNumberofInfected && currentNumberofInfected <= getPopulationNumber())
			this.currentNumberofInfected = currentNumberofInfected;
		else if(currentNumberofInfected < 0)
			this.currentNumberofInfected = 0;
		else if(currentNumberofInfected > getPopulationNumber())
			this.currentNumberofInfected = getPopulationNumber();
	}
	public int getTestingPerDay() {
		return testingPerDay;
	}
	public void setTestingPerDay(int testingPerDay) {
		if(0 <= testingPerDay && testingPerDay <= getPopulationNumber())
			this.testingPerDay = testingPerDay;
		else if(testingPerDay < 0)
			this.testingPerDay = 0;
		else if(testingPerDay > getPopulationNumber())
			this.testingPerDay = getPopulationNumber();
	}
	public int getPopulationNumber() {
		return populationNumber;
	}
	public void setPopulationNumber(int populationNumber) {
		if(1 <= populationNumber && populationNumber <= 2000000000)
			this.populationNumber = populationNumber;
		else if(populationNumber < 1)
			this.populationNumber = 1;
		else if(populationNumber > 2000000000)
			this.populationNumber = 2000000000;
	}

}
