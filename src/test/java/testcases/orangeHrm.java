package testcases;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;

import org.jetbrains.annotations.NotNull;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import com.BestClass.office.BaseTest;
import com.BestClass.office.BrowserSupplier;

import annotations.Extent;
import pages.LoginPage;
import properties.Properties;
import testdata.Json;

public class orangeHrm extends BaseTest {

	protected WebDriver lBrowser;
	public LoginPage objLoginPage;

	@Extent(name = "Excel", description = "TC-01: Login into application", author = { "Vimal" }, category = {
			"sanity" }, device = "local system")

	@BeforeTest
	public void initialSetUp() {
		lBrowser = BrowserSupplier.launch(Properties.getProject().browser());
		objLoginPage = new LoginPage(lBrowser);
	}

	@Test(dataProvider = "Basic_Login_Flow")
	public void OrangeHRM(@NotNull Map<String, String> inputData) throws Exception {
		 testCase = report.createTestCase(inputData.get("Test_Scenario"), report.createTestCase(inputData.get("Test_Scenario")));
		 testCase.assignCategory(inputData.get("Module"));
		 testCase.assignAuthor(inputData.get("Author"));
		 testCase.assignDevice(inputData.get("device"));
		 try {
			objLoginPage.goTo(Properties.getProject().OrangeHRM());
			objLoginPage.fillLoginDetails(inputData.get("username"), Properties.getProject().OrangeHRMPwd());
			objLoginPage.logOut();
			report.write();
		} catch (Exception e) {
			testCase.fail("Test Case Failed due the following exception: " + e.getMessage());
			report.write();
			throw new Exception(e);
		}
	}

	@DataProvider(name = "Basic_Login_Flow")
	private @NotNull Iterator<Object[]> getJson() throws IOException, ParseException {
		Json json = new Json();
		json.load("./resources/testdata/TestHRMflow.json");
		return json.getMap("Test HRM flow");
	}
}
