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
	
	//Attributes
	//private List<Point> infectionRateGraphDP;
	
	public InfectionRateGraph(List<DataPoint> dataPoints) {
		super(dataPoints);
		// TODO Auto-generated constructor stub
		
		//Blue: Color(0,0,255,180)
		lineColor = new Color(0,0,255,180);
	}
	
	public InfectionRateGraph(DataPointProcessor dataPointController) {
		super(dataPointController);
		// TODO Auto-generated constructor stub
		
		//Blue: Color(0,0,255,180)
		lineColor = new Color(0,0,255,180);
	}

	//Methods
	public void showInfectionRateGraph() {}
	
    //This is where the points frames and panels are created
	public static void createAndShowGui() {
    	
    	//Create Data points list
        List<DataPoint> dataPoints = dataPointController.requestGraphPoints("Infection Rate");
        
        //Creating GUI panel object instance
        Graph mainPanel = new InfectionRateGraph(dataPoints);
        
        //Changing resolution of window
        mainPanel.setPreferredSize(new Dimension(1500,800));
        JFrame frame = new JFrame("Infections Per Day");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(mainPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
