package com.tutorialsninjademo.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.ProductDisplayPage;
import com.tutorialsninja.qa.pages.SearchPage;

public class ProductCompareTest extends Base {

	WebDriver driver;
	HomePage homePage;
	SearchPage searchPage;
	ProductDisplayPage productDisplayPage;

	public ProductCompareTest() {

		super();
	}

	@AfterMethod
	public void tearDown() {

		driver.quit();
	}

	@BeforeMethod
	public void setUp() {

		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browser"));
	}

	@Test(priority = 1)
	public void verifyAddingProductForComparisonFromProductDisplayPage() {

		homePage = new HomePage(driver);
		homePage.enterProductIntoSearchField(dataProp.getProperty("validProduct"));
		homePage.clickOnSearchButton();
		searchPage = new SearchPage(driver);
		searchPage.clickOnProductDisplayedInSearchResultUsingName();
		productDisplayPage = new ProductDisplayPage(driver);
		String expectedToolTip = "Compare this Product";
		Assert.assertEquals(productDisplayPage.getToolTipForCompareThisProductOption(), expectedToolTip);
		

	}

}
