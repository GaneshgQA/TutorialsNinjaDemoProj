package com.tutorialsnija.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter {

	public static ExtentReports generateExtentReport() {

		ExtentReports extentReports = new ExtentReports();
		File extentReportFile = new File(
				System.getProperty("user.dir") + "\\test-output\\ExtentReport\\extentReport.html");
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReportFile);

		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setReportName("Ganesh Demo Test");
		sparkReporter.config().setDocumentTitle("Ganesh Test");
		sparkReporter.config().setTimeStampFormat("dd/mm/yyyy hh:mm:ss");

		extentReports.attachReporter(sparkReporter);

		Properties configProp = new Properties();
		File configFile = new File(System.getProperty("user.dir")
				+ "\\src\\main\\java\\com\\tutorialsninja\\qa\\config\\config.properties");
		try {
			FileInputStream fis = new FileInputStream(configFile);
			configProp.load(fis);
		} catch (Exception e) {

			e.printStackTrace();
		}
		extentReports.setSystemInfo("App URL", configProp.getProperty("url"));
		extentReports.setSystemInfo("Browser Name", configProp.getProperty("browser"));
		extentReports.setSystemInfo("User Name", configProp.getProperty("validEmail"));
		extentReports.setSystemInfo("Password", configProp.getProperty("validPassword"));
		extentReports.setSystemInfo("Operating system", System.getProperty("os.name"));
		extentReports.setSystemInfo("User Name", System.getProperty("user.name"));
		extentReports.setSystemInfo("Java Version", System.getProperty("java.version"));
		
		return extentReports;
	}

}
