package com.BestClass.office;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebDriverConfig {
	
	private final String browserVersion;
	
	public WebDriverConfig() throws IOException{
		browserVersion =configProperties.getPropValues_TestApplication("browser.version");
	}
	
	private WebDriver edge() {
		WebDriverManager
		.edgedriver()
		.setup();
		return new EdgeDriver();
	}
	private WebDriver firefox() {
		WebDriverManager
		.firefoxdriver()
		.setup();
		return new FirefoxDriver();
	}
	private WebDriver chrome() {
		WebDriverManager
		.chromedriver()
		.browserVersion(this.browserVersion)
		.setup();
		return new ChromeDriver();
	}

}
