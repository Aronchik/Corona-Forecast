package com.Corona.Forecast.Controller;

import com.Corona.Forecast.Model.*;

public class VirusInfoUpdateProcessor {
	
	//Attributes
	private VirusInfo virusInfo = VirusInfo.getVirusInfoInstance();
	
	public VirusInfo getVirusInfoDataForm() {
		return virusInfo;
	}
	
	public void updateBasicReproductiveNumber(double basicReproductiveNumber) {
		virusInfo.setBasicReproductiveNumber(basicReproductiveNumber);
	}
	public void updateVaccineInfo(boolean vaccineAvailability) {
		virusInfo.setVaccineAvailability(vaccineAvailability);
	}
	public void updateIllnessDuration(int illnessDuration) {
		virusInfo.setIllnessDuration(illnessDuration);
	}
}
