package com.Corona.Forecast.Driver;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.Corona.Forecast.Controller.DataPoint;
import com.Corona.Forecast.Controller.DataPointProcessor;
import com.Corona.Forecast.Controller.PopulationInfoProcessor;
import com.Corona.Forecast.Controller.VirusInfoUpdateProcessor;
import com.Corona.Forecast.View.DeceasedGraph;
import com.Corona.Forecast.View.ICUGraph;
import com.Corona.Forecast.View.InfectedGraph;
import com.Corona.Forecast.View.InfectionRateGraph;
import com.Corona.Forecast.View.RecoveredGraph;

class AlgorithmHappyCaseTest {
	
    private double getMinY(List<DataPoint> dataPoints) {
        double minPoint = Double.MAX_VALUE;
        for (DataPoint point : dataPoints) {
            minPoint = Math.min(minPoint, point.y);
        }
        return minPoint;
    }
    
    private double getMaxY(List<DataPoint> dataPoints) {
        double maxPoint = Integer.MIN_VALUE;
        for (DataPoint point : dataPoints) {
            maxPoint = Math.max(maxPoint, point.y);
        }
        return maxPoint;
    }
    
    private void resetVirusInfo() {
    	ObjectOutputStream outputStream =  null;
    	
		try {
		String filename = "Virus Information.txt";
		outputStream = new ObjectOutputStream(new FileOutputStream(filename));
		outputStream.writeDouble(3.8);
		outputStream.writeBoolean(false);
		outputStream.writeInt(20);
		
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
    
    @Test 
    void happyNegTest() {
    	PopulationInfoProcessor form = new PopulationInfoProcessor();
    	VirusInfoUpdateProcessor virusInfo = new VirusInfoUpdateProcessor();
    	
    	resetVirusInfo();
    	
    	form.setCentralLocationShudtownStatus(true);
    	form.setContactTracingStatus(true);
    	form.setTotalPopulation(-50000);
    	form.setCurrentlyInfected(-400);
    	form.setHygieneInformationCampaignStatus(true);
    	form.setIsolationStatus(true);
    	form.setTestsPerDay(-4000);
    	form.setTravelRestrictionsStatus(true);
    	
    	DataPointProcessor processor = new DataPointProcessor(form,virusInfo, 100);
    	
		List<DataPoint> infectionRateDP = processor.processInfectionRateDP();
		assertEquals(0,getMinY(infectionRateDP));
		assertEquals(0,getMaxY(infectionRateDP));
		
		List<DataPoint> infectedDP = processor.processInfectedDP();
		assertEquals(0,getMinY(infectedDP));
		assertEquals(0,getMaxY(infectedDP));
		
		List<DataPoint> ICUDP = processor.processICUDP();
		assertEquals(0,getMinY(ICUDP));
		assertEquals(0,getMaxY(ICUDP));
		
		List<DataPoint> deceasedDP = processor.processDeceasedDP();
		assertEquals(0,getMinY(deceasedDP));
		assertEquals(0,getMaxY(deceasedDP));
		
		List<DataPoint> recoveredDP = processor.processRecoveredDP();
		assertEquals(0,getMinY(recoveredDP));
		assertEquals(0,getMaxY(recoveredDP));
    	
    }
    
    @Test 
    void happyZeroTest() {
    	PopulationInfoProcessor form = new PopulationInfoProcessor();
    	VirusInfoUpdateProcessor virusInfo = new VirusInfoUpdateProcessor();
    	
    	resetVirusInfo();
    	
    	form.setCentralLocationShudtownStatus(true);
    	form.setContactTracingStatus(true);
    	form.setTotalPopulation(0);
    	form.setCurrentlyInfected(0);
    	form.setHygieneInformationCampaignStatus(true);
    	form.setIsolationStatus(true);
    	form.setTestsPerDay(10000);
    	form.setTravelRestrictionsStatus(true);
    	
    	DataPointProcessor processor = new DataPointProcessor(form,virusInfo, 100);
    	
		List<DataPoint> infectionRateDP = processor.processInfectionRateDP();
		assertEquals(0,getMinY(infectionRateDP));
		assertEquals(0,getMaxY(infectionRateDP));
		
		List<DataPoint> infectedDP = processor.processInfectedDP();
		assertEquals(0,getMinY(infectedDP));
		assertEquals(0,getMaxY(infectedDP));
		
		List<DataPoint> ICUDP = processor.processICUDP();
		assertEquals(0,getMinY(ICUDP));
		assertEquals(0,getMaxY(ICUDP));
		
		List<DataPoint> deceasedDP = processor.processDeceasedDP();
		assertEquals(0,getMinY(deceasedDP));
		assertEquals(0,getMaxY(deceasedDP));
		
		List<DataPoint> recoveredDP = processor.processRecoveredDP();
		assertEquals(0,getMinY(recoveredDP));
		assertEquals(0,getMaxY(recoveredDP));
    	
    }

	@Test
	void happyMaxTest() {
    	PopulationInfoProcessor form = new PopulationInfoProcessor();
    	VirusInfoUpdateProcessor virusInfo = new VirusInfoUpdateProcessor();
    	
    	resetVirusInfo();
    	
    	form.setCentralLocationShudtownStatus(true);
    	form.setContactTracingStatus(true);
    	form.setTotalPopulation(2100000000);
    	form.setCurrentlyInfected(2100000000);
    	form.setHygieneInformationCampaignStatus(true);
    	form.setIsolationStatus(true);
    	form.setTestsPerDay(2100000000);
    	form.setTravelRestrictionsStatus(true);
    	
    	DataPointProcessor processor = new DataPointProcessor(form,virusInfo, 10000);
    	
    	assertEquals(2000000000,form.getTotalPopulation());
    	assertEquals(2000000000,form.getCurrentlyInfected());
    	assertEquals(2000000000,form.getTestsPerDay());
	}
}
