package com.framework.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadDataFromPropFile {
	
	//This class will be used to maintain all methods related to read test data from property file
	public static Properties readProperties(String filename) {
		Properties prop = null;
		try {
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\Config\\"+filename);//to read file
			prop =new Properties();
			prop.load(fis); 
			
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		
		return prop;
		
		
	}

}
