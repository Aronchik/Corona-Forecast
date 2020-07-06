package com.Corona.Forecast.Driver;

import javax.swing.SwingUtilities;

import com.Corona.Forecast.Model.*;
import com.Corona.Forecast.View.*;
import com.Corona.Forecast.Controller.*;

public class MVCDriver {
	
    public static void main(String[] args) {
    	SystemLogin loginScreen = new SystemLogin();
    	
    	loginScreen.main(null);
     }
}