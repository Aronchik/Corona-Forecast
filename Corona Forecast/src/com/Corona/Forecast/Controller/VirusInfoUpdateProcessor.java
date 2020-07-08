package com.Corona.Forecast.Controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import com.Corona.Forecast.Model.*;

public class VirusInfoUpdateProcessor {
	
	//Attributes
	private VirusInfo virusInfo = VirusInfo.getVirusInfoInstance();
	
	public VirusInfo getVirusInfoDataForm() {
		return virusInfo;
	}
	
	public void updateVirusInformation(double basicReproductiveNumber, boolean vaccineAvailability, int illnessDuration) {
		updateBasicReproductiveNumber(basicReproductiveNumber);
		updateVaccineInfo(vaccineAvailability);
		updateIllnessDuration(illnessDuration);
		
		ObjectOutputStream outputStream =  null;
		
		try {
		String filename = "Virus Information.txt";
		outputStream = new ObjectOutputStream(new FileOutputStream(filename));
		outputStream.writeDouble(basicReproductiveNumber);
		outputStream.writeBoolean(vaccineAvailability);
		outputStream.writeInt(illnessDuration);
		
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			try {
				outputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void updateBasicReproductiveNumber(double basicReproductiveNumber) {
		virusInfo.setBasicReproductiveNumber(basicReproductiveNumber);
	}
	private void updateVaccineInfo(boolean vaccineAvailability) {
		virusInfo.setVaccineAvailability(vaccineAvailability);
	}
	private void updateIllnessDuration(int illnessDuration) {
		virusInfo.setIllnessDuration(illnessDuration);
	}
}
