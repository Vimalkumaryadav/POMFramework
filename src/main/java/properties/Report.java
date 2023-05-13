package properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.model.Media;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.ViewName;

import static constants.Constants.ROOT_DIRECTORY;

import java.io.File;
import java.io.IOException;

import org.jetbrains.annotations.NotNull;

public class Report {

	private ExtentReports extentReports;
	
	public void intitalize() {
		extentReports = new ExtentReports();
		ExtentSparkReporter sparkReporter =  new ExtentSparkReporter(ROOT_DIRECTORY
				+ Properties.getProject().reports() + Properties.getProject().reportName()
				); 

		sparkReporter
			.viewConfigurer()
			.viewOrder()
			.as(new ViewName[] {
					ViewName.DASHBOARD,
					ViewName.TEST,
					ViewName.AUTHOR,
					ViewName.DEVICE,
					ViewName.EXCEPTION,
					ViewName.LOG
			})
			.apply();
		
		try {
			sparkReporter
				.loadXMLConfig(new File( ROOT_DIRECTORY + Properties.getProject().reportConfig()));
		} catch (IOException e) {
			 throw new RuntimeException("Unable to load the configuration xml file for Extent Report");
		}
		
		extentReports.attachReporter(sparkReporter);
	}
	
	public void write() { 
		extentReports.flush();
		System.out.println("Report Name Flused");
	}
	
	public ExtentTest createTestCase(String name) { return extentReports.createTest(name);}
	
	public ExtentTest createTestCase(String name, String description){
		return extentReports.createTest(name, description);
	}
	
	public ExtentTest createTestCase(String name, @NotNull ExtentTest parentTestCase){
		return parentTestCase.createNode(name);
	}
	
	public ExtentTest createTestCase(String name, String description, @NotNull ExtentTest parentTestCase){
		return parentTestCase.createNode(name,description);
	}
	
	public void pass(@NotNull ExtentTest testCase, String message) { testCase.pass(message);}
	
	public void pass(@NotNull ExtentTest testCase, String message, Media media) { testCase.pass(message,media);}
	
	public void pass(@NotNull ExtentTest testCase, Markup markup) { testCase.pass(markup);}
	
	public void fail(@NotNull ExtentTest testCase, String message) { testCase.fail(message);}
	
	public void fail(@NotNull ExtentTest testCase, String message, Media media) { testCase.fail(message,media);}
	
	public void fail(@NotNull ExtentTest testCase, Markup markup) { testCase.fail(markup);}
	
	public void skip(@NotNull ExtentTest testCase, String message) { testCase.skip(message);}
	
	public void skip(@NotNull ExtentTest testCase, String message, Media media) { testCase.skip(message,media);}
	
	public void skip(@NotNull ExtentTest testCase, Markup markup) { testCase.skip(markup);}
	
	public void info(@NotNull ExtentTest testCase, String message) { testCase.info(message);}
	
	public void info(@NotNull ExtentTest testCase, String message, Media media) { testCase.info(message,media);}
	
	public void info(@NotNull ExtentTest testCase, Markup markup) { testCase.info(markup);}
}
