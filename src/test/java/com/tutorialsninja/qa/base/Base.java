package com.tutorialsninja.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.tutorialsnija.qa.utils.Utilities;

public class Base {

	WebDriver driver;
	public Properties prop;
	public Properties dataProp;

	public Base() {

		prop = new Properties();
		File propFile = new File(System.getProperty("user.dir")
				+ "\\src\\main\\java\\com\\tutorialsninja\\qa\\config\\config.properties");

		dataProp = new Properties();
		File dataPropFile = new File(System.getProperty("user.dir")
				+ "\\src\\main\\java\\com\\tutorialsninja\\qa\\testdata\\testdata.properties");

		try {
			
			FileInputStream fis2 = new FileInputStream(dataPropFile);
			dataProp.load(fis2);
		
		} catch (Exception e) {

			e.printStackTrace();

		}

		try {

			FileInputStream fis = new FileInputStream(propFile);
			prop.load(fis);

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public WebDriver initializeBrowserAndOpenApplicationURL(String browserName) {

		if (browserName.equals("chrome")) {

			driver = new ChromeDriver();

		} else if (browserName.equals("firefox")) {

			driver = new FirefoxDriver();

		} else if (browserName.equals("edge")) {

			driver = new EdgeDriver();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICITE_WAIT_TIME));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGE_LOAD_TIME));
		driver.get(prop.getProperty("url"));

		return driver;

	}

}
