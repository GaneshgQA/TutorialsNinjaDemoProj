package com.tutorialsninjademo.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.FooterOptions;
import com.tutorialsninja.qa.pages.SiteMapPage;

public class SiteMapPageTest extends Base {

	public WebDriver driver;

	public SiteMapPageTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {

		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browser"));
	}
	
	@Test(priority = 1)
	public void testClickOnSpecialOffersLink() {
		
		// Navigate to SiteMap page via footer
		FooterOptions footerOptions = new FooterOptions(driver);
		footerOptions.clickOnSiteMapLink();
		
		// Create SiteMapPage object and click on Special Offers link
		SiteMapPage siteMapPage = new SiteMapPage(driver);
		siteMapPage.clickOnSpecialOffersLinkOnSiteMapPage();
		
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	

}