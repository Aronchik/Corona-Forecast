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


public class RecoveredGraph extends Graph {
	
	//Attributes
	//private List<Point> recoveredDP;
	
	public RecoveredGraph(List<DataPoint> dataPoints) {
		super(dataPoints);
		// TODO Auto-generated constructor stub
		
		//Green: Color(0,204,0,180)
		lineColor = new Color(0,204,0,180);
	}
	
	public RecoveredGraph(DataPointProcessor dataPointController) {
		super(dataPointController);
		// TODO Auto-generated constructor stub
		
		//Green: Color(0,204,0,180)
		lineColor = new Color(0,204,0,180);
	}

	//Methods
	public void showRecoveredGraph() {}
	
    //This is where the points frames and panels are created
	public static void createAndShowGui() {
    	
    	//Create Data points list
        List<DataPoint> dataPoints = dataPointController.requestGraphPoints("Recovered");
        
        //Creating GUI panel object instance
        Graph mainPanel = new RecoveredGraph(dataPoints);
        
        //Changing resolution of window
        mainPanel.setPreferredSize(new Dimension(1500,800));
        JFrame frame = new JFrame("Recovered");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(mainPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
	}

}
