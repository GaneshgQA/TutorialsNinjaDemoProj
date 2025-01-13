package com.tutorialsninjademo.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.SearchResultPage;

public class SearchTest extends Base {

	public WebDriver driver;

	public SearchTest() {

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
	public void verifySearchWithValidProduct() {

		HomePage homePage = new HomePage(driver);
		homePage.enterProductIntoSearchField(dataProp.getProperty("validProduct"));
		homePage.clickOnSearchButton();

		// driver.findElement(By.name("search")).sendKeys(dataProp.getProperty("validProduct"));
		// driver.findElement(By.xpath("(//button[contains(@class,'btn-default')])[1]")).click();

		SearchResultPage searchResultPage = new SearchResultPage(driver);
		Assert.assertTrue(searchResultPage.displayStatusOfValidProduct(), "Valid product HP is not displayed");

	}

	@Test(priority = 2)
	public void verifySearchWithInvalidProduct() {

		HomePage homePage = new HomePage(driver);
		homePage.enterProductIntoSearchField(dataProp.getProperty("invalidProduct"));
		homePage.clickOnSearchButton();

//		driver.findElement(By.name("search")).sendKeys(dataProp.getProperty("invalidProduct"));
//		driver.findElement(By.xpath("(//button[contains(@class,'btn-default')])[1]")).click();

		SearchResultPage searchResultPage = new SearchResultPage(driver);
		String actualNoSearchMessage = searchResultPage.retrieveNoProductFoundMessage();
		Assert.assertEquals(actualNoSearchMessage, dataProp.getProperty("invalidProductSearchMessage"),
				"No product in search result message is not displayed");

	}

	@Test(priority = 3 , dependsOnMethods = {"verifySearchWithInvalidProduct"})
	public void verifySearchWithoutAnyProduct() {

		HomePage homePage = new HomePage(driver);
		homePage.clickOnSearchButton();

//		driver.findElement(By.name("search")).sendKeys(dataProp.getProperty("invalidProduct"));
//		driver.findElement(By.xpath("(//button[contains(@class,'btn-default')])[1]")).click();

		SearchResultPage searchResultPage = new SearchResultPage(driver);
		String actualNoSearchMessage = searchResultPage.retrieveNoProductFoundMessage();
		Assert.assertEquals(actualNoSearchMessage, dataProp.getProperty("invalidProductSearchMessage"),
				"No product in search result message is not displayed");

	}
}
