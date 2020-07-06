package com.Corona.Forecast.Controller;

public class DataPoint {
	public int x; 
	public int y; 
	 
	 public DataPoint()
	 {
		 x=0;
		 y=0;
	 }
	 
	 public DataPoint(int x, int y)
	 {
		 this.x=x;
		 this.y=y;
	 }

	public DataPoint(DataPoint other) {
		this.x = other.x;
		this.y = other.y;
	}
}