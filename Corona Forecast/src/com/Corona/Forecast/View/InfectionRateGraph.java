package com.Corona.Forecast.View;

import com.Corona.Forecast.Controller.*;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class InfectionRateGraph extends Graph{
	
	public InfectionRateGraph(List<DataPoint> dataPoints) {
		super(dataPoints);
		
		//Blue: Color(0,0,255,180)
		lineColor = new Color(0,0,255,180);
	}
	
	public InfectionRateGraph(DataPointProcessor dataPointController) {
		super(dataPointController);
		
		//Blue: Color(0,0,255,180)
		lineColor = new Color(0,0,255,180);
	}

	//Methods
	public void showInfectionRateGraph() {}
	
    String getYLabel(int i) {
    	String yLabel;
    	double numYLabel =(double)(((double) ((getMinY() + (getMaxY() - getMinY()) * ((i * 1.0) / numberYDivisions)) * 100)) / 100.0);
    	
    	if(numYLabel > 0)
    		yLabel = numYLabel + "";
    	else
    		yLabel = 0 + "";
    	
    	yLabel = yLabel.substring(0, Math.min(yLabel.length(), 4)) + "%";
    	return yLabel;
    }
	
    //This is where the points frames and panels are created
	public static void createAndShowGui() {
    	
    	//Create Data points list
        List<DataPoint> dataPoints = dataPointController.requestGraphPoints("Infection Rate");
        
        //Creating GUI panel object instance
        Graph mainPanel = new InfectionRateGraph(dataPoints);
        
        //Changing resolution of window
        mainPanel.setPreferredSize(new Dimension(1500,800));
        JFrame frame = new JFrame("Infection Rate");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(mainPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
