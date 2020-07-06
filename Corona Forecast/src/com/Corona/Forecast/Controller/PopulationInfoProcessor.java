package com.Corona.Forecast.Controller;

import com.Corona.Forecast.Model.*;

public class PopulationInfoProcessor {
	
	private PopulationInfo populationInfoDataForm = new PopulationInfo();
	
	
	public PopulationInfo getPopulationInfoDataForm() {
		return populationInfoDataForm;
	}
	
	public boolean IsolationStatus() {
		return populationInfoDataForm.isIsolation();
	}
	public void setIsolationStatus(boolean isolationStatus) {
		this.populationInfoDataForm.setIsolation(isolationStatus);
	}
	public boolean ContactTracingStatus() {
		return populationInfoDataForm.isContactTracing();
	}
	public void setContactTracingStatus(boolean contactTracingStatus) {
		this.populationInfoDataForm.setContactTracing(contactTracingStatus);
	}
	public boolean TravelRestrictionsStatus() {
		return populationInfoDataForm.isTravelRestrictions();
	}
	public void setTravelRestrictionsStatus(boolean travelRestrictionsStatus) {
		this.populationInfoDataForm.setTravelRestrictions(travelRestrictionsStatus);
	}
	public boolean HygieneInformationCampaignStatus() {
		return populationInfoDataForm.isHygieneInformationCampaign();
	}
	public void setHygieneInformationCampaignStatus(boolean hygieneInformationCampaignStatus) {
		this.populationInfoDataForm.setHygieneInformationCampaign(hygieneInformationCampaignStatus);
	}
	public boolean isCentralLocationShudtownStatus() {
		return populationInfoDataForm.isCentralLocationShudtown();
	}
	public void setCentralLocationShudtownStatus(boolean centralLocationShudtownStatus) {
		this.populationInfoDataForm.setCentralLocationShudtown(centralLocationShudtownStatus);
	}
	public int getCurrentlyInfected() {
		return populationInfoDataForm.getCurrentNumberofInfected();
	}
	public void setCurrentlyInfected(int currentlyInfected) {
		this.populationInfoDataForm.setCurrentNumberofInfected(currentlyInfected);
	}
	public int getTestsPerDay() {
		return populationInfoDataForm.getTestingPerDay();
	}
	public void setTestsPerDay(int testsPerDay) {
		this.populationInfoDataForm.setTestingPerDay(testsPerDay);
	}
	public int getTotalPopulation() {
		return populationInfoDataForm.getPopulationNumber();
	}
	public void setTotalPopulation(int totalPopulation) {
		this.populationInfoDataForm.setPopulationNumber(totalPopulation);
	}

}
