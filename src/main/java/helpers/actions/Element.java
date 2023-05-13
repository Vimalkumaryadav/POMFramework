package helpers.actions;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebElement;

public class Element {

	public void clear(@NotNull WebElement element) { element.clear();}
	
	public void click(@NotNull WebElement element) { element.click();}
	
	public void type(String text, @NotNull WebElement element) { element.sendKeys(text);}
	
	public void submit(@NotNull WebElement element) { element.submit();}
}
