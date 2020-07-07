package com.Corona.Forecast.Driver;

import static org.junit.jupiter.api.Assertions.*;

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
        int maxPoint = Integer.MIN_VALUE;
        for (DataPoint point : dataPoints) {
            maxPoint = Math.max(maxPoint, point.y);
        }
        return maxPoint;
    }

	@Test
	void happyCaseTest() {
    	PopulationInfoProcessor form = new PopulationInfoProcessor();
    	VirusInfoUpdateProcessor virusInfo = new VirusInfoUpdateProcessor();
    	
    	form.setCentralLocationShudtownStatus(true);
    	form.setContactTracingStatus(true);
    	form.setCurrentlyInfected(2);
    	form.setHygieneInformationCampaignStatus(true);
    	form.setIsolationStatus(true);
    	form.setTestsPerDay(10000);
    	form.setTotalPopulation(8000000);
    	form.setTravelRestrictionsStatus(true);
    	
    	DataPointProcessor processor = new DataPointProcessor(form,virusInfo, 100);
    	
		List<DataPoint> infectionRateDP = processor.processInfectionRateDP();
		assertEquals(0,getMinY(infectionRateDP));
		assertEquals(1,getMaxY(infectionRateDP));
		
		List<DataPoint> infectedDP = processor.processInfectedDP();
		assertEquals(2,getMinY(infectedDP));
		assertEquals(32210,getMaxY(infectedDP));
		
		List<DataPoint> ICUDP = processor.processICUDP();
		assertEquals(0,getMinY(ICUDP));
		assertEquals(1021,getMaxY(ICUDP));
		
		List<DataPoint> deceasedDP = processor.processDeceasedDP();
		assertEquals(0,getMinY(deceasedDP));
		assertEquals(4577,getMaxY(deceasedDP));
		
		List<DataPoint> recoveredDP = processor.processRecoveredDP();
		assertEquals(0,getMinY(recoveredDP));
		assertEquals(72572,getMaxY(recoveredDP));
		
	}

}
