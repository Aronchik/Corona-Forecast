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

public class InfectedGraph extends Graph{
	
	//Attributes
	//private List<Point> infectedDP;
	
	public InfectedGraph(List<DataPoint> dataPoints) {
		super(dataPoints);
		// TODO Auto-generated constructor stub
		
		//Red: Color(255,0,0,180)
		lineColor = new Color(255,0,0,180);
	}
	
	public InfectedGraph(DataPointProcessor dataPointController) {
		super(dataPointController);
		// TODO Auto-generated constructor stub
		
		//Red: Color(255,0,0,180)
		lineColor = new Color(255,0,0,180);
	}

	//Methods
	public void showInfectedGraph() {}
	
    String getYLabel(int i) {
    	return (long)(((long) ((getMinY() + (getMaxY() - getMinY()) * ((i * 1.0) / numberYDivisions)) * 100)) / 100.0) + "";
    }
	
    //This is where the points frames and panels are created
	public static void createAndShowGui() {
    	
    	//Create Data points list
        List<DataPoint> dataPoints = dataPointController.requestGraphPoints("Infected");
        
        //Creating GUI panel object instance
        Graph mainPanel = new InfectedGraph(dataPoints);
        
        //Changing resolution of window
        mainPanel.setPreferredSize(new Dimension(1500,800));
        JFrame frame = new JFrame("Infected");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(mainPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
	}
}
