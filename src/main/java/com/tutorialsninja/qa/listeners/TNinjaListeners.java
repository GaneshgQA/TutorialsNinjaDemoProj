package com.tutorialsninja.qa.listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tutorialsnija.qa.utils.ExtentReporter;
import com.tutorialsnija.qa.utils.Utilities;

public class TNinjaListeners implements ITestListener {
	
	//private static final String WebDriver = null;
	ExtentReports extentReport;
	ExtentTest extentTest;

	@Override
	public void onStart(ITestContext context) {

		extentReport = ExtentReporter.generateExtentReport();

	}

	@Override
	public void onTestStart(ITestResult result) {

		String testName = result.getName();
		extentTest = extentReport.createTest(testName);
		extentTest.log(Status.INFO, testName+ " Started executing");
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {

		String testName = result.getName();
		extentTest.log(Status.PASS, testName+ "Got successfully executed");
	
	}

	@Override
	public void onTestFailure(ITestResult result) {
	
		String testName = result.getName();
		WebDriver driver = null;
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		 * File srcScreenshot =
		 * ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE); String
		 * destinationScreenshotPath =
		 * System.getProperty("user.dir")+"\\Screenshot\\"+testName+".png"; try {
		 * FileHandler.copy(srcScreenshot, new File(destinationScreenshotPath)); } catch
		 * (IOException e) { // TODO Auto-generated catch block e.printStackTrace(); }
		 */
		String destinationScreenshotPath = Utilities.captureScreenshot(driver, result.getName());
		
		extentTest.addScreenCaptureFromPath(destinationScreenshotPath);
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.FAIL, testName + " Got failed");
		//System.out.println(result.getThrowable());
		//System.out.println(testName + " Got failed");
	}

	@Override
	public void onTestSkipped(ITestResult result) {

		String testName = result.getName();
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.SKIP, testName + " Got skipped");
	//	System.out.println(testName + " Got skipped");
		//System.out.println(result.getThrowable());

	}

	@Override
	public void onFinish(ITestContext context) {
		
		extentReport.flush();
		//System.out.println("Finished executing Project test");
		
		String pathOfExtentReport = System.getProperty("user.dir")+"\\test-output\\ExtentReport\\extentReport.html";
		File extentReportFile = new File(pathOfExtentReport);
		try {
			Desktop.getDesktop().browse(extentReportFile.toURI());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
