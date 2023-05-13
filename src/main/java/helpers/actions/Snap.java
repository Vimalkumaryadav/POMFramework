package helpers.actions;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;

import com.assertthat.selenium_shutterbug.core.Capture;
import com.assertthat.selenium_shutterbug.core.Shutterbug;

import static constants.Constants.*;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class Snap {

	private final WebDriver lDriver;
	
	private final String snapShotPath = ROOT_DIRECTORY + "\\results\\reports\\snaps\\";
	
	public Snap(WebDriver driver) { this.lDriver = driver;}

	private File sourceImage;
	private File destinationImage;
	
	public String viewableArea(String fileName) throws IOException {
		this.sourceImage = ((TakesScreenshot) this.lDriver).getScreenshotAs(OutputType.FILE);
		this.destinationImage = new File(ROOT_DIRECTORY + "\\results\\reports\\snaps\\"+fileName);
		FileHandler.copy(this.sourceImage, this.destinationImage);
		return this.snapShotPath + fileName;
	}
	
	public String element(@NotNull WebElement element, String fileName) throws IOException{
		this.sourceImage=element.getScreenshotAs(OutputType.FILE);
		this.destinationImage= new File(ROOT_DIRECTORY + "\\results\\reports\\snaps\\"+fileName);
		FileHandler.copy(this.sourceImage, this.destinationImage);
		return this.snapShotPath + fileName;
	}
	
	public String fullPage(String fileName) throws IOException{
		this.sourceImage= ((FirefoxDriver) this.lDriver).getFullPageScreenshotAs(OutputType.FILE);
		this.destinationImage= new File(ROOT_DIRECTORY + "\\results\\reports\\snaps\\"+fileName);
		FileHandler.copy(this.sourceImage, this.destinationImage);
		return this.snapShotPath + fileName;
	}
	
	public void shutterViewablearea(String fileName) {
		Shutterbug.shootPage(this.lDriver,Capture.VIEWPORT)
		.withTitle(new Date().toString())
		.withName(fileName)
		.save(this.snapShotPath);
	}
	
	public void shutterFullPage(String fileName) {
		Shutterbug.shootPage(this.lDriver,Capture.FULL_SCROLL)
		.withTitle(new Date().toString())
		.withName(fileName)
		.save(this.snapShotPath);
	}
	
	public void shutterElement(WebElement element, String fileName) {
		Shutterbug.shootElement(this.lDriver, element)
		.withTitle(new Date().toString())
		.withName(fileName)
		.save(this.snapShotPath);
	}
	
	public void compare(String imagePath,double deviation) throws IOException{
		Shutterbug
			.shootPage(this.lDriver)
			.equals(imagePath,deviation);
	}
	
	public void compate(String imagePath, double deviation, WebElement element) throws IOException{
		Shutterbug
			.shootElement(this.lDriver, element)
			.equals(imagePath,deviation);
	}
}
