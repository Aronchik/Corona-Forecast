package com.Corona.Forecast.Driver;

import javax.swing.SwingUtilities;

import com.Corona.Forecast.Model.*;
import com.Corona.Forecast.View.*;
import com.Corona.Forecast.Controller.*;


public class ForecastAlgorithmTest2 {
	
	public static void main(String[] args) {
    	PopulationInfoProcessor form = new PopulationInfoProcessor();
    	VirusInfoUpdateProcessor virusInfo = new VirusInfoUpdateProcessor();
    	
    	form.setCentralLocationShudtownStatus(false);
    	form.setContactTracingStatus(false);
    	form.setCurrentlyInfected(2);
    	form.setHygieneInformationCampaignStatus(false);
    	form.setIsolationStatus(false);
    	form.setTestsPerDay(10000);
    	form.setTotalPopulation(8000000);
    	form.setTravelRestrictionsStatus(false);
    	
    	DataPointProcessor processor = new DataPointProcessor(form,virusInfo, 100);
    	
    	InfectedGraph inf = new InfectedGraph(processor);
    	ICUGraph icu = new ICUGraph(processor);
    	DeceasedGraph deceased = new DeceasedGraph(processor);
    	InfectionRateGraph infectionRate = new InfectionRateGraph(processor);
    	RecoveredGraph recovered = new RecoveredGraph(processor);
    	
    	inf.createAndShowGui();
    	//icu.createAndShowGui();
    	//deceased.createAndShowGui();
    	//infectionRate.createAndShowGui();
    	//recovered.createAndShowGui();
	}
}
