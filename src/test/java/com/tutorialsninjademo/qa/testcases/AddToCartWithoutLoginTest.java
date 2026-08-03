package com.tutorialsninjademo.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AddToCartWithoutLoginPage;
import com.tutorialsninja.qa.pages.HomePage;

public class AddToCartWithoutLoginTest extends Base {

	WebDriver driver;
	AddToCartWithoutLoginPage addToCartWithoutLoginPage;
	HomePage homePage;

	public AddToCartWithoutLoginTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browser"));
		homePage = new HomePage(driver);
		addToCartWithoutLoginPage = new AddToCartWithoutLoginPage(driver);
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}


	@Test(priority = 1)
	public void verifyAddingMacbookToCartWithoutLogin() {
		Assert.assertTrue(addToCartWithoutLoginPage.isMacbookProductDisplayed(),
				"MacBook product is not displayed on the home page");
		Assert.assertTrue(addToCartWithoutLoginPage.isAddToCartButtonDisplayed(),
				"Add to Cart button is not displayed");
		addToCartWithoutLoginPage.clickOnAddToCartButton();

		// Wait a moment for the success message to appear
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Assert.assertTrue(addToCartWithoutLoginPage.isSuccessMessageDisplayed(),
				"Success message is not displayed after adding product to cart");

		String successMessage = addToCartWithoutLoginPage.getTextOfSuccessMessage();
		Assert.assertNotNull(successMessage, "Success message is null");
		Assert.assertTrue(successMessage.length() > 0, "Success message is empty");
		System.out.println("Success Message: " + successMessage);
		
		Assert.assertTrue(addToCartWithoutLoginPage.isShoppingCartLinkDisplayed(),
				"Shopping Cart link is not displayed after adding product to cart");
	}
	
	@Test(priority = 2)
	public void verifyAddToCartAndClickOnShoppingCartLinkWithoutLogin() {
		// Click on Add to Cart button
		addToCartWithoutLoginPage.clickOnAddToCartButton();

		// Wait for success message
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// Verify success message is displayed
		Assert.assertTrue(addToCartWithoutLoginPage.isSuccessMessageDisplayed(),
				"Success message is not displayed after adding product to cart");

		// Get and verify success message
		String successMessage = addToCartWithoutLoginPage.getTextOfSuccessMessage();
		Assert.assertNotNull(successMessage, "Success message is null");
		System.out.println("Success Message: " + successMessage);

		// Click on Shopping Cart link to navigate to cart
		addToCartWithoutLoginPage.clickOnShoppingCartLink();

		// Wait for page to load
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// Verify navigation to shopping cart page
		String pageTitle = getPageTitle(driver);
		System.out.println("Page Title: " + pageTitle);
		Assert.assertTrue(pageTitle.contains("Shopping Cart") || pageTitle.contains("cart"),
				"Failed to navigate to shopping cart page");
		//AddToCartWithoutLoginPage addToCartWithoutLoginPage = new AddToCartWithoutLoginPage(driver);
		Assert.assertTrue(addToCartWithoutLoginPage.isShoppingCartBreadcrumbDisplayed(),
				"Shopping Cart breadcrumb is not displayed on the shopping cart page");
	}

	/**
	 * Test to verify MacBook product can be searched and added to cart
	 */

}
