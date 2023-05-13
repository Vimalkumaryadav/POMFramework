package helpers.actions;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.BestClass.office.enums.Condition;
import com.BestClass.office.enums.ExecuteScript;

public class Mouse {

	private final WebDriver lDriver;
	private final Wait wait;
	private JavaScript js;
	
	public Mouse(WebDriver driver) {
		this.lDriver=driver;
		this.wait = new Wait(this.lDriver);
		this.js = new JavaScript(this.lDriver);
	}
	
	public void click(@NotNull WebElement element) {
		wait.until(Condition.clickable,element);
		js.executor(ExecuteScript.highlight,element);
		new Actions(this.lDriver).click(element).perform();
	}
	
	public void clickAndHold(@NotNull WebElement element) {
		wait.until(Condition.clickable,element);
		new Actions(this.lDriver).clickAndHold(element).perform();
	}
	
	public void release(@NotNull WebElement element) {
		wait.until(Condition.clickable,element);
		new Actions(this.lDriver).release(element).perform();
	}
	
	public void rightClick(@NotNull WebElement element) {
		wait.until(Condition.clickable,element);
		new Actions(this.lDriver).contextClick(element).perform();
	}
	
	public void doubleClick(@NotNull WebElement element) {
		wait.until(Condition.clickable,element);
		new Actions(this.lDriver).doubleClick(element).perform();
	}
	
	public void type(String keys) { new Actions (this.lDriver).sendKeys(keys).perform();}
	
	public void dragAndDrop(@NotNull WebElement source, @NotNull WebElement destination) {
		wait.until(Condition.clickable,source);
		wait.until(Condition.clickable,destination);
		new Actions(this.lDriver).dragAndDrop(source, destination).perform();
	}
}
