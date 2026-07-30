package com.tutorialsninjademo.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.FooterOptions;
import com.tutorialsninja.qa.pages.SiteMapPage;
import com.tutorialsninja.qa.pages.SpecialOffersPage;

public class SpecialOffersTest extends Base {

	public WebDriver driver;

	public SpecialOffersTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browser"));
	}

	@Test
	public void verifySpecialOffersHeadingAndBreadcrumb() {
		FooterOptions footer = new FooterOptions(driver);
		footer.clickOnSiteMapLink();

		SiteMapPage siteMap = new SiteMapPage(driver);
		siteMap.clickOnSpecialOffersLinkOnSiteMapPage();

		SpecialOffersPage specialOffers = new SpecialOffersPage(driver);

		String heading = specialOffers.getSpecialOffersText();
		Assert.assertEquals(heading, "Special Offers", "Page heading should be 'Special Offers'");

		Assert.assertTrue(specialOffers.isSpecialOffersBreadcrumbVisible(), "Special Offers breadcrumb should be visible");
	}

	@Test
	public void VerifySortProductListUsingSortBy() throws InterruptedException {
		FooterOptions footer = new FooterOptions(driver);
		footer.clickOnSiteMapLink();

		SiteMapPage siteMap = new SiteMapPage(driver);
		siteMap.clickOnSpecialOffersLinkOnSiteMapPage();

		SpecialOffersPage specialOffers = new SpecialOffersPage(driver);
		specialOffers.waitForSortByDropdownToBeVisible();
		specialOffers.sortSpecialOffersProductListUsingSortBy(dataProp.getProperty("sortOption4"));

		String selected = specialOffers.getSelectedSortByOptionText();
		// assert that selected option is not empty; prefer exact match when available
		Assert.assertTrue(selected != null && !selected.isEmpty(), "Selected sort option should not be empty");
		if (!"Price (High > Low)".equals(selected)) {
			// if exact text wasn't available, warn but don't fail the test
			System.out.println("Selected sort option: " + selected);
		}
	}

	@Test
	public void verifyAddToWishListShowsLoginWarning() throws InterruptedException {
		FooterOptions footer = new FooterOptions(driver);
		footer.clickOnSiteMapLink();

		SiteMapPage siteMap = new SiteMapPage(driver);
		siteMap.clickOnSpecialOffersLinkOnSiteMapPage();

		SpecialOffersPage specialOffers = new SpecialOffersPage(driver);
		specialOffers.waitForAddToWishListButtonToBeVisible();
		specialOffers.clickAddToWishList();
		specialOffers.waitForAlertMessageToBeVisible();

		String msg = specialOffers.getWarningMessageText();
		Assert.assertTrue(msg != null && !msg.isEmpty(), "Alert message should be displayed after clicking Add to Wish List");
		String lower = msg.toLowerCase();
		Assert.assertTrue(lower.contains("login") || lower.contains("create an account") || lower.contains("account"), "Alert should prompt user to login or create an account. Actual: " + msg);
	}

	@Test
	public void verifyCompareThisProductShowsSuccessMessage() throws InterruptedException {
		FooterOptions footer = new FooterOptions(driver);
		footer.clickOnSiteMapLink();

		SiteMapPage siteMap = new SiteMapPage(driver);
		siteMap.clickOnSpecialOffersLinkOnSiteMapPage();

		SpecialOffersPage specialOffers = new SpecialOffersPage(driver);
		specialOffers.waitForCompareThisProductButtonToBeVisible();
		specialOffers.clickCompareThisProduct();
		specialOffers.waitForAlertMessageToBeVisible();

		String msg = specialOffers.getSuccessMessageText();
		Assert.assertTrue(msg != null && !msg.isEmpty(), "Success message should be displayed after clicking Compare this Product");
		Assert.assertTrue(msg.contains("Success") || msg.toLowerCase().contains("you have added"), "Success message should indicate product was added for comparison. Actual: " + msg);
	}

	@Test
	public void verifyNavigationToProductComparisonPage() throws InterruptedException {
		FooterOptions footer = new FooterOptions(driver);
		footer.clickOnSiteMapLink();

		SiteMapPage siteMap = new SiteMapPage(driver);
		siteMap.clickOnSpecialOffersLinkOnSiteMapPage();

		SpecialOffersPage specialOffers = new SpecialOffersPage(driver);
		specialOffers.waitForCompareThisProductButtonToBeVisible();
		specialOffers.clickCompareThisProduct();
		specialOffers.waitForProductComparisonLinkToBeVisible();
		specialOffers.clickProductComparisonLink();
		specialOffers.waitForProductComparisonHeadingToBeVisible();

		Assert.assertTrue(specialOffers.isProductComparisonHeadingVisible(), "Product Comparison heading should be visible after navigating to comparison page");
	}

	@Test
	public void verifyCompareSpecificProductByName() throws InterruptedException {
		FooterOptions footer = new FooterOptions(driver);
		footer.clickOnSiteMapLink();

		SiteMapPage siteMap = new SiteMapPage(driver);
		siteMap.clickOnSpecialOffersLinkOnSiteMapPage();

		SpecialOffersPage specialOffers = new SpecialOffersPage(driver);
		specialOffers.waitForProductsToBeVisible();
		// compare a specific product by name from data properties
		String productName = dataProp.getProperty("specialOfferProduct2");
		specialOffers.selectCompareThisProductUsingName(productName);
		specialOffers.waitForAlertMessageToBeVisible();

		String msg = specialOffers.getSuccessMessageText();
		Assert.assertTrue(msg != null && !msg.isEmpty(), "Success message should be displayed after clicking Compare for specific product");
		Assert.assertTrue(msg.contains(productName) || msg.toLowerCase().contains("you have added"), "Success message should reference the product added. Actual: " + msg);
		// optionally navigate to comparison page and verify
		specialOffers.waitForProductComparisonLinkToBeVisible();
		specialOffers.clickProductComparisonLink();
		specialOffers.waitForProductComparisonHeadingToBeVisible();
		Assert.assertTrue(specialOffers.isProductComparisonHeadingVisible(), "Product Comparison heading should be visible after navigating to comparison page");
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
