package helpers.actions;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.BestClass.office.enums.Condition;

public class KeyBoard {

	private final WebDriver lDriver;
	private final Wait wait;
	
	public KeyBoard(WebDriver driver) {
		this.lDriver = driver;
		this.wait = new Wait(this.lDriver);
	}
	
	public void type(String keys, @NotNull WebElement element) {
		wait.until(Condition.clickable, element);
		new Actions(this.lDriver).click(element)
		.sendKeys(Keys.chord(keys)).build().perform();
	}
	
	public void upperCase(String text) {
		new Actions(this.lDriver).keyDown(Keys.SHIFT)
		.keyDown(Keys.chord(text))
		.build().perform();
	}
	
	public void selectAll() {
		new Actions(this.lDriver).keyDown(Keys.CONTROL)
		.sendKeys("a")
		.keyUp(Keys.CONTROL)
		.build().perform();
	}
	
	public void cut() {
		new Actions(this.lDriver).clickAndHold()
		.sendKeys("x")
		.keyUp(Keys.CONTROL)
		.build().perform();
	}
	
	public void copy() {
		new Actions(this.lDriver).clickAndHold()
		.keyDown(Keys.CONTROL)
		.sendKeys("c")
		.keyUp(Keys.CONTROL)
		.build().perform();
	}
	
	public void paste() {
		new Actions(this.lDriver).clickAndHold()
		.sendKeys("v")
		.keyUp(Keys.CONTROL)
		.build().perform();
	}
	
}
