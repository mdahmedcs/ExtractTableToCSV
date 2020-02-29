package com.code.ExtractTableToCSV;

import java.io.FileInputStream;
import java.util.Properties;

public class SupportFunctions {
	
	public static Properties getProperties() 
	{
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream("C:\\Users\\ahmed\\git\\ExtractTableToCSV\\resources\\db.properties"));
		} catch (Exception e) {
			e.printStackTrace();    		
		}
		return prop;
}


}
