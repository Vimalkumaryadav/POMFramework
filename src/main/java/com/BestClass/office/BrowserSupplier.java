package com.BestClass.office;

import java.util.HashMap;
import java.util.function.Supplier;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.BestClass.office.enums.Browsers;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserSupplier {

	public static final HashMap<Browsers, Supplier<WebDriver>> map = new HashMap<>();
	
	public static final Supplier<WebDriver> chrome=() ->{
		ChromeOptions co= new ChromeOptions();
		co.addArguments("--remote-allow-origins=*");
	    WebDriverManager.chromedriver().setup();
	    //WebDriver driver = new ChromeDriver(co);
		/*System.setProperty("webdriver.chrome.driver", "./resources/drivers/chromedriver.exe");
		 * WebDriverManager.chromedriver().setup();
		 * HashMap<String,Object> chromePrefs = new HashMap<>();
		 * chromePrefs.put("plugins.always_open_pdf_externally", true); ChromeOptions
		 * opt = new ChromeOptions(); opt.setExperimentalOption("prefs", chromePrefs);
		 * DesiredCapabilities cap = new DesiredCapabilities();
		 * cap.setCapability(ChromeOptions.CAPABILITY, opt);
		 */
		  return new ChromeDriver(co);
	};
	
	public static final Supplier<WebDriver> chromeIncog=() ->{
		System.setProperty("webdriver.chrome.driver", "Driver Location");
		/*
		 * ChromeOptions chromeOpt = new ChromeOptions();
		 * chromeOpt.addArguments("--incognito"); DesiredCapabilities desCap = new
		 * DesiredCapabilities(); desCap.setCapability(ChromeOptions.CAPABILITY,
		 * chromeOpt);
		 */
		return new ChromeDriver();
	};
	
	public static final Supplier<WebDriver> firefox=() ->{
		WebDriverManager.firefoxdriver().setup();
		return new FirefoxDriver();
	};
	
	public static final Supplier<WebDriver> edge=() ->{
		WebDriverManager.edgedriver().setup();
		return new EdgeDriver();
	};
	
	public static final Supplier<WebDriver> headlessChrome=() ->{
		WebDriverManager.chromedriver().setup();
		ChromeOptions options =  new ChromeOptions();
		options.setHeadless(true);
		return new ChromeDriver(options);
	};
	
	public static final Supplier<WebDriver> headlessFirefox=() ->{
		WebDriverManager.firefoxdriver().setup();
		FirefoxOptions options =  new FirefoxOptions();
		options.setHeadless(true);
		return new FirefoxDriver(options);
	};
	
	static{
		map.put(Browsers.chrome,chrome);
		map.put(Browsers.chromeIncog,chromeIncog);
		map.put(Browsers.firefox,firefox);
		map.put(Browsers.edge,edge);
		map.put(Browsers.headlessChrome,headlessChrome);
		map.put(Browsers.headlessFirefox,headlessFirefox);
	}
	
	public static WebDriver launch(Browsers browserType) {
		ThreadLocal<WebDriver> browser = ThreadLocal.withInitial(map.get(browserType));
		return browser.get();
	}
	
}
