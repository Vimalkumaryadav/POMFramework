package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.BestClass.office.BasePage;
import com.BestClass.office.enums.Condition;
import com.BestClass.office.enums.Is;

public class LoginPage extends BasePage{
	
	//Constructor
	public LoginPage(WebDriver driver) { super(driver);}

	//Locators
	By login = By.xpath("//button[text()=' Login ']");
	By username1=dynamicLocator("input","name","username");
	//By userName = By.name("username");
	By password1=dynamicLocator("input","name","password");
	//By passWord = By.name("password");
	By logout = By.xpath("//a[text()='Logout']");
	By profile = By.xpath("//img[@alt='profile picture' and @class='oxd-userdropdown-img']");
	By profileButton = dynamicLocator("img","alt","profile picture");
	
	public void fillLoginDetails(String username,String password) {
		objWait.until(Condition.presenceOf,username1);
		element.type(username, find.element(username1));
		objWait.until(Condition.presenceOf,password1);
		element.type(password, find.element(password1));
		element.click(find.element(login));
		objWait.until(Condition.scriptTimeout, 10);
	}
	
	public void goTo(String url) {
		browser.maximize();
		browser.getTo(url);
	}
	
	public void logOut() {
		objWait.until(Condition.presenceOf,profile);
		element.click(find.element(profile));
		objWait.until(Condition.presenceOf,logout);
		element.click(find.element(logout));
		objWait.until(Condition.scriptTimeout, 5);
		browser.closeAll();
	}
	
	public static By dynamicLocator(String tagName, String attributeName, String attributeValue) {
        // Generate an XPath locator based on tag name, attribute name, and attribute value
        String xpathLocator = "//" + tagName + "[@" + attributeName + "='" + attributeValue + "']";
        return By.xpath(xpathLocator);
    }
	
	@Override
	protected boolean hasItLoaded()
	{
		return check.whether(login,Is.enabled);
	}
}
