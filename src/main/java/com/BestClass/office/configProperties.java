package com.BestClass.office;

import java.io.IOException;
import java.io.FileReader;
import java.util.Properties;

import org.apache.commons.configuration.PropertiesConfiguration;

public class configProperties {

	private static String environmentName;
	
	/*
	 * @value("${xEncv}") public void setEnvironmentName(String env) { environment = env;}
	 */
	
	private static String propFileName = "src/main/resources/application-"+ environmentName +".properties";
	
	private static String propFileName_Test = "src/main/resources/application-"+ System.getenv("spring.profiles.active") +".properties";
	
	private static String xEnv;
	
	public static String getxEnv() { return xEnv;}
	
	public static void setxEnv(String xEnv) { configProperties.xEnv = xEnv;}
	
	public configProperties() {
		propFileName_Test = "src/main/resources/application-"+ environmentName +".properties";
		System.out.println("environment name:" + environmentName);
	}
	
	public static String getPropValues_TestApplication(String label) throws IOException{
		FileReader reader =null;
		String value="";
		try {
			Properties prop = new Properties();
			String propFileName = propFileName_Test;
			reader = new FileReader(propFileName);
			prop.load(reader);
			value=prop.getProperty(label);
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		}finally {
			if(reader!=null) {
				reader.close();
			}
		}
		return value;
	}
	
	public static String getPropValues(String label) {
		String value="";
		try {
			System.out.println(environmentName);
			String activeProFile1 = System.getenv("spring.profiles.active");
			System.out.println("Properties files: " +activeProFile1);
			propFileName = "src/main/resources/application-"+ environmentName +".properties";
			
			PropertiesConfiguration properties = new PropertiesConfiguration(propFileName);
			value=properties.getProperties(label).toString().trim();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return value;
	}
	
	public static String getPropValues(String propertyFileName,String label) {
		String value="";
		try {
			PropertiesConfiguration properties = new PropertiesConfiguration("src/main/resources/"+propertyFileName+".properties");
			value=properties.getProperty(label).toString().trim();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return value;
	}
}
