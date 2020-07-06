package com.Corona.Forecast.Controller;

import com.Corona.Forecast.Model.*;
import java.util.List;
import com.Corona.Forecast.Model.*;


//This Processor acts as an adapter between external requests and the system algorithm
public class DataPointProcessor {
	
	//Attributes
	private PopulationInfoProcessor populationInfoForm;
	private List<DataPoint>	processedGraphDP;
	ForecastAlgorithm algo;
	VirusInfo virus;
	
	//Constructor
	 public DataPointProcessor(PopulationInfoProcessor populationInfoForm, VirusInfoUpdateProcessor virus, int days) {
		this.populationInfoForm = populationInfoForm;
		this.algo =  new ForecastAlgorithm(populationInfoForm.getPopulationInfoDataForm(), virus.getVirusInfoDataForm(), days);
	}
	
	//Methods
	 public List<DataPoint> requestGraphPoints(String typeOfData) {
		 if(typeOfData.equals("Infection Rate"))
			 return processInfectionRateDP();
		 else if(typeOfData.equals("Infected"))
				 return processInfectedDP();
		 else if(typeOfData.equals("ICU"))
			 return processICUDP();
		 else if(typeOfData.equals("Deceased"))
			 return processDeceasedDP();
		 else if(typeOfData.equals("Recovered"))
			 return processRecoveredDP();
		 return null;
	 }
	 
	 
	public List<DataPoint> processInfectionRateDP() { 
		processedGraphDP = algo.calculateInfectionRateDataPoints();
		return processedGraphDP;
		};
	public List<DataPoint> processInfectedDP() { 
		processedGraphDP =  algo.calculateInfectedDataPoints();
		return processedGraphDP;
		}
	public List<DataPoint> processICUDP() { 
		processedGraphDP =  algo.calculateICUDataPoints();
		return processedGraphDP;
		}
	public List<DataPoint> processDeceasedDP() { 
		processedGraphDP = algo.calculateDeceasedDataPoints();
		return processedGraphDP;
		}
	public List<DataPoint> processRecoveredDP() { 
		processedGraphDP = algo.calculateRecoveredDataPoints();
		return processedGraphDP;
		}
}
