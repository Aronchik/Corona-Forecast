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
    	form.setTotalPopulation(8000000);
    	form.setCurrentlyInfected(20);
    	form.setHygieneInformationCampaignStatus(false);
    	form.setIsolationStatus(false);
    	form.setTestsPerDay(10000);
    	form.setTravelRestrictionsStatus(false);
    	
    	DataPointProcessor processor = new DataPointProcessor(form,virusInfo, 180);
    	
    	InfectedGraph inf = new InfectedGraph(processor);
    	inf.createAndShowGui();
    	
    	ICUGraph icu = new ICUGraph(processor);
    	icu.createAndShowGui();
    	
    	DeceasedGraph deceased = new DeceasedGraph(processor);
    	deceased.createAndShowGui();
    	
    	InfectionRateGraph infectionRate = new InfectionRateGraph(processor);
    	infectionRate.createAndShowGui();
    	
    	RecoveredGraph recovered = new RecoveredGraph(processor);
    	recovered.createAndShowGui();
	}
}
