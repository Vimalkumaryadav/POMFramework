package com.BestClass.office;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import properties.Properties;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import properties.Report;

public abstract class BaseTest {

	protected WebDriver lBrowser;
	protected static Report report;
	protected ExtentTest parentTestCase;
	protected ExtentTest testCase;

	@BeforeTest
	public void initReport() {
		report = new Report();
		report.intitalize();
	}

	@BeforeClass
	public void setUp() {
		lBrowser = BrowserSupplier.launch(Properties.getProject().browser());
		parentTestCase = report.createTestCase(this.getClass().getName());
	}

	@AfterMethod
	public void after(@NotNull ITestResult result) {
		if (report == null) {
			System.out.println("One of the report objects is null.");
			return;
		}else if(testCase == null) {
			System.out.println("One of the testcase objects is null.");
			return;
		}else if(result.getMethod() == null) {
			System.out.println("One of the getMethod objects is null.");
			return;
		}

		switch (result.getStatus()) {
		case ITestResult.SUCCESS:
			report.pass(testCase, MarkupHelper.createLabel(
					"This is a logging event for:" + result.getName() + ", and it passed!", ExtentColor.GREEN));
			break;
		case ITestResult.FAILURE:
			report.fail(testCase, MarkupHelper.createLabel(
					"This is a logging event for:" + result.getName() + ", and it failed!", ExtentColor.RED));
			break;
		case ITestResult.SKIP:
			report.skip(testCase, MarkupHelper.createLabel(
					"This is a logging event for:" + result.getName() + ", and it skipped!", ExtentColor.YELLOW));
			break;
		}
	}

	/*
	 * @AfterMethod public void after(@NotNull ITestResult result) {
	 * switch(result.getStatus()) { case ITestResult.SUCCESS: report.pass(testCase,
	 * MarkupHelper .createLabel("This is a logging event for:" +
	 * result.getName()+", and it passed!", ExtentColor.GREEN)); break; case
	 * ITestResult.FAILURE: report.fail(testCase, MarkupHelper
	 * .createLabel("This is a logging event for:" +
	 * result.getName()+", and it failed!", ExtentColor.RED)); break; case
	 * ITestResult.SKIP: report.skip(testCase, MarkupHelper
	 * .createLabel("This is a logging event for:" +
	 * result.getName()+", and it skipped!", ExtentColor.YELLOW)); break; } }
	 */

	@AfterClass
	public void tearDown() {
		report.write();
	}
}
