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

public abstract class Graph extends JPanel {
    protected int width = 800;
    protected int height = 400;
    protected int padding = 70;
    protected int labelPadding = 25;
    protected Color lineColor;
    protected Color pointColor = new Color(100, 100, 100, 180);
    protected Color gridColor = new Color(200, 200, 200, 200);
    protected static final Stroke GRAPH_STROKE = new BasicStroke(2f);
    protected int pointWidth = 4;
    protected int numberYDivisions = 10;
    protected List<DataPoint> dataPoints;
    
    protected static DataPointProcessor dataPointController;

    //Constructor
    public Graph(List<DataPoint> dataPoints) {
        this.dataPoints = dataPoints;
    }
    
    public Graph(DataPointProcessor dataPointController) {
        this.dataPointController = dataPointController;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        double xScale = ((double) getWidth() - (2 * padding) - labelPadding) / (dataPoints.size() - 1);
        double yScale = ((double) getHeight() - 2 * padding - labelPadding) / (getMaxY() - getMinY());

        List<Point> graphPoints = new ArrayList<>();
        
        for (int i = 0; i < dataPoints.size(); i++) {
            int x1 = (int) (i * xScale + padding + labelPadding);
            int y1 = (int) ((getMaxY() - dataPoints.get(i).y) * yScale + padding);
            graphPoints.add(new Point(x1, y1));
        }

        // draw white background
        g2.setColor(Color.WHITE);
        g2.fillRect(padding + labelPadding, padding, getWidth() - (2 * padding) - labelPadding, getHeight() - 2 * padding - labelPadding);
        g2.setColor(Color.BLACK);

        // create hatch marks and grid lines for y axis. (Representing data)
        for (int i = 0; i < numberYDivisions + 1; i++) {
            int x0 = padding + labelPadding;
            int x1 = pointWidth + padding + labelPadding;
            int y0 = getHeight() - ((i * (getHeight() - padding * 2 - labelPadding)) / numberYDivisions + padding + labelPadding);
            int y1 = y0;
            if (dataPoints.size() > 0) {
                g2.setColor(gridColor);
                g2.drawLine(padding + labelPadding + 1 + pointWidth, y0, getWidth() - padding, y1);
                g2.setColor(Color.BLACK);
                String yLabel = getYLabel(i);
                FontMetrics metrics = g2.getFontMetrics();
                int labelWidth = metrics.stringWidth(yLabel);
                
                //drawString(string to draw, x height, y height)
                g2.drawString(yLabel, x0 - labelWidth - 5, y0 + (metrics.getHeight() / 2) - 3);
            }
            g2.drawLine(x0, y0, x1, y1);
        }

        // and for x axis. (The Days)
        for (int i = 0; i < dataPoints.size(); i++) {
            if (dataPoints.size() > 1) {
                int x0 = i * (getWidth() - padding * 2 - labelPadding) / (dataPoints.size() - 1) + padding + labelPadding;
                int x1 = x0;
                int y0 = getHeight() - padding - labelPadding;
                int y1 = y0 - pointWidth;
                if ((i % ((int) ((dataPoints.size() / 20.0)) + 1)) == 0) {
                    g2.setColor(gridColor);
                    g2.drawLine(x0, getHeight() - padding - labelPadding - 1 - pointWidth, x1, padding);
                    g2.setColor(Color.BLACK);
                    String xLabel = i + " days";
                    FontMetrics metrics = g2.getFontMetrics();
                    int labelWidth = metrics.stringWidth(xLabel);
                    
                
                    //drawString(string to draw, x height, y height)
                    g2.drawString(xLabel, x0 - labelWidth / 2, y0 + metrics.getHeight() + 3);
                    g2.drawLine(x0, y0, x1, y1 - 5);
                }
                else
                	g2.drawLine(x0, y0, x1, y1);
            }
        }

        // create x and y axes 
        g2.drawLine(padding + labelPadding, getHeight() - padding - labelPadding, padding + labelPadding, padding);
        g2.drawLine(padding + labelPadding, getHeight() - padding - labelPadding, getWidth() - padding, getHeight() - padding - labelPadding);
      
        paintGraph(g2,lineColor,pointWidth,pointColor,graphPoints);
    }

//    @Override
//    public Dimension getPreferredSize() {
//        return new Dimension(width, height);
//    }

    protected double getMinY() {
        double minPoint = Double.MAX_VALUE;
        for (DataPoint point : dataPoints) {
            minPoint = Math.min(minPoint, point.y);
        }
        return minPoint;
    }

    protected double getMaxY() {
        double maxPoint = Integer.MIN_VALUE;
        for (DataPoint point : dataPoints) {
            maxPoint = Math.max(maxPoint, point.y);
        }
        return maxPoint;
    }

    public void setdataPoints(List<DataPoint> dataPoints) {
        this.dataPoints = dataPoints;
        invalidate();
        this.repaint();
    }

    public List<DataPoint> getdataPoints() {
        return dataPoints;
    }
    
    abstract String getYLabel(int i);
    
    private static void paintGraph(Graphics2D g2, 
    		Color lineColor, int pointWidth, Color pointColor,
    		List<Point> graphPoints) {
        Stroke oldStroke = g2.getStroke();
        g2.setColor(lineColor);
        g2.setStroke(GRAPH_STROKE);
        
        for (int i = 0; i < graphPoints.size() - 1; i++) {
            int x1 = graphPoints.get(i).x;
            int y1 = graphPoints.get(i).y;
            int x2 = graphPoints.get(i + 1).x;
            int y2 = graphPoints.get(i + 1).y;
            g2.drawLine(x1, y1, x2, y2);
        }

        g2.setStroke(oldStroke);
        g2.setColor(pointColor);
        for (int i = 0; i < graphPoints.size(); i++) {
            int x = graphPoints.get(i).x - pointWidth / 2;
            int y = graphPoints.get(i).y - pointWidth / 2;
            int ovalW = pointWidth;
            int ovalH = pointWidth;
            g2.fillOval(x, y, ovalW, ovalH);
        }
    }
    
    //This is where the points frames and panels are created
    protected static void createAndShowGui() {}
    
    public static void main(String[] args) {
    	  SwingUtilities.invokeLater(new Runnable() {
    	     public void run() {
    	        createAndShowGui();
    	     }
    	  });
    	}
}