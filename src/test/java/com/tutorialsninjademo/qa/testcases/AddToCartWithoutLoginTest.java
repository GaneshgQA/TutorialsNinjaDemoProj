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


	@Test(priority = 1,groups = "regression" )
	public void verifyAddingMacbookToCartWithoutLogin() {
		Assert.assertTrue(addToCartWithoutLoginPage.isMacbookProductDisplayed(),
				"MacBook product is not displayed on the home page");
		Assert.assertTrue(addToCartWithoutLoginPage.isAddToCartButtonDisplayed(),
				"Add to Cart button is not displayed");
		addToCartWithoutLoginPage.clickOnAddToCartButton();

		// Page object method now handles verification overlay wait automatically
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
		// Click on Add to Cart button (overlay wait handled automatically)
		addToCartWithoutLoginPage.clickOnAddToCartButton();

		// Verify success message is displayed
		Assert.assertTrue(addToCartWithoutLoginPage.isSuccessMessageDisplayed(),
				"Success message is not displayed after adding product to cart");

		// Get and verify success message (overlay wait handled automatically)
		String successMessage = addToCartWithoutLoginPage.getTextOfSuccessMessage();
		Assert.assertNotNull(successMessage, "Success message is null");
		System.out.println("Success Message: " + successMessage);

		// Click on Shopping Cart link to navigate to cart (overlay wait handled automatically)
		addToCartWithoutLoginPage.clickOnShoppingCartLink();

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
	 * Test to verify iPhone product can be added to cart without login
	 */
	@Test(priority = 3)
	public void verifyAddingIPhoneToCartWithoutLogin() {
		Assert.assertTrue(addToCartWithoutLoginPage.isIPhoneDisplayed(),
				"iPhone product is not displayed on the home page");
		Assert.assertTrue(addToCartWithoutLoginPage.isAddToCartButtonDisplayed(),
				"Add to Cart button is not displayed");
		
		// Click iPhone product (overlay wait handled automatically)
		addToCartWithoutLoginPage.clickOnIPhone();

		// Click Add to Cart button (overlay wait handled automatically)
		addToCartWithoutLoginPage.clickOnAddToCartButtonAfterClickingOnProduct();

		Assert.assertTrue(addToCartWithoutLoginPage.isSuccessMessageDisplayed(),
				"Success message is not displayed after adding iPhone to cart");

		String successMessage = addToCartWithoutLoginPage.getTextOfSuccessMessage();
		Assert.assertNotNull(successMessage, "Success message is null");
		Assert.assertTrue(successMessage.length() > 0, "Success message is empty");
		System.out.println("Success Message: " + successMessage);
		
		Assert.assertTrue(addToCartWithoutLoginPage.isShoppingCartLinkDisplayed(),
				"Shopping Cart link is not displayed after adding iPhone to cart");
	}

	/**
	 * Test to verify Apple Cinema 30 product can be added to cart without login
	 */
	@Test(priority = 4)
	public void verifyAddingAppleCinema30ToCartWithoutLogin() {
		Assert.assertTrue(addToCartWithoutLoginPage.isAppleCinema30Displayed(),
				"Apple Cinema 30 product is not displayed on the home page");
		Assert.assertTrue(addToCartWithoutLoginPage.isAddToCartButtonDisplayed(),
				"Add to Cart button is not displayed");
		
		// Click Apple Cinema 30 product (overlay wait handled automatically)
		addToCartWithoutLoginPage.clickOnAddToCartButtonAfterClickingOnProduct();

		// Click Add to Cart button (overlay wait handled automatically)
		addToCartWithoutLoginPage.clickOnAddToCartButton();

		Assert.assertTrue(addToCartWithoutLoginPage.isSuccessMessageDisplayed(),
				"Success message is not displayed after adding Apple Cinema 30 to cart");

		String successMessage = addToCartWithoutLoginPage.getTextOfSuccessMessage();
		Assert.assertNotNull(successMessage, "Success message is null");
		Assert.assertTrue(successMessage.length() > 0, "Success message is empty");
		System.out.println("Success Message: " + successMessage);
		
		Assert.assertTrue(addToCartWithoutLoginPage.isShoppingCartLinkDisplayed(),
				"Shopping Cart link is not displayed after adding Apple Cinema 30 to cart");
	}

	/**
	 * Test to verify Canon EOS 5D product can be added to cart without login
	 * @throws InterruptedException 
	 */
	@Test(priority = 5, enabled = false)
	public void verifyAddingCanonEOS5DToCartWithoutLogin() throws InterruptedException {
		Assert.assertTrue(addToCartWithoutLoginPage.isCanonEOS5DDisplayed(),
				"Canon EOS 5D product is not displayed on the home page");
		Assert.assertTrue(addToCartWithoutLoginPage.isAddToCartButtonDisplayed(),
				"Add to Cart button is not displayed");
		
		// Click Canon EOS 5D product (overlay wait handled automatically)
		addToCartWithoutLoginPage.clickOnCanonEOS5D();
		Thread.sleep(5000);	// Click Add to Cart button (overlay wait handled automatically)
		addToCartWithoutLoginPage.clickOnAddToCartButtonAfterClickingOnProduct();

		Assert.assertTrue(addToCartWithoutLoginPage.isSuccessMessageDisplayed(),
				"Success message is not displayed after adding Canon EOS 5D to cart");

		String successMessage = addToCartWithoutLoginPage.getTextOfSuccessMessage();
		Assert.assertNotNull(successMessage, "Success message is null");
		Assert.assertTrue(successMessage.length() > 0, "Success message is empty");
		System.out.println("Success Message: " + successMessage);
		
		Assert.assertTrue(addToCartWithoutLoginPage.isShoppingCartLinkDisplayed(),
				"Shopping Cart link is not displayed after adding Canon EOS 5D to cart");
	}

}
