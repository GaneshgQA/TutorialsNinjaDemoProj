package com.tutorialsninjademo.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.HeaderOptionsPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.ProductCategoryPage;
import com.tutorialsninja.qa.pages.ProductComparisonPage;
import com.tutorialsninja.qa.pages.ProductDisplayPage;
import com.tutorialsninja.qa.pages.SearchPage;

public class ProductCompareTest extends Base {

	WebDriver driver;
	HomePage homePage;
	SearchPage searchPage;
	ProductDisplayPage productDisplayPage;
	ProductComparisonPage productComparisonPage;
	HeaderOptionsPage headerOptionsPage;
	ProductCategoryPage productCategoryPage;

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
		productDisplayPage.selectCompareThisProductOption();
		System.out.println(productDisplayPage.getSuccessMessageForCompareThisProductOption());
		String expectedProductComparisonSuccessMessage = "Success: You have added HP LP3065 to your product comparison!";
		//Assert.assertEquals(productDisplayPage.getSuccessMessageForCompareThisProductOption(),
			//	expectedProductComparisonSuccessMessage);
		
		String actualMessage = productDisplayPage.getSuccessMessageForCompareThisProductOption();
		
		Assert.assertTrue(actualMessage.contains(expectedProductComparisonSuccessMessage));
		//System.out.println(productDisplayPage.getSuccessMessageForCompareThisProductOption());
		productDisplayPage.clickOnProductComparisonLinkInSuccessMessage();
		productComparisonPage = new ProductComparisonPage(driver);
		productComparisonPage.didWeNavigateToProductComparisionPage();
		productComparisonPage.didDetailsOfTheProductGotAddedForComparison();
		
	}
	
	@Test(priority = 2)
	public void verifyAddingProductForcomparisonFromSearchPageListView() {
		
		homePage = new HomePage(driver);
		homePage.enterProductIntoSearchField(dataProp.getProperty("validProduct"));
		homePage.clickOnSearchButton();
		searchPage = new SearchPage(driver);
		searchPage.selectListViewOption();
		searchPage.clickOnProductDisplayedInSearchResultUsingName();
		productDisplayPage = new ProductDisplayPage(driver);
		String expectedToolTip = "Compare this Product";	
		Assert.assertEquals(productDisplayPage.getToolTipForCompareThisProductOption(), expectedToolTip);
		productDisplayPage.selectCompareThisProductOption();
		System.out.println(productDisplayPage.getSuccessMessageForCompareThisProductOption());
		String expectedProductComparisonSuccessMessage = "Success: You have added HP LP3065 to your product comparison!";
		String actualMessage = productDisplayPage.getSuccessMessageForCompareThisProductOption();
		Assert.assertTrue(actualMessage.contains(expectedProductComparisonSuccessMessage));
		productDisplayPage.clickOnProductComparisonLinkInSuccessMessage();
		productComparisonPage = new ProductComparisonPage(driver);
		productComparisonPage.didWeNavigateToProductComparisionPage();
		productComparisonPage.didDetailsOfTheProductGotAddedForComparison();
		
	}
 
	@Test(priority = 3)
	public void verifyAddingProductForcomparisonFromSearchPageGridView() {
		
		homePage = new HomePage(driver);
		homePage.enterProductIntoSearchField(dataProp.getProperty("validProduct"));
		homePage.clickOnSearchButton();
		searchPage = new SearchPage(driver);
		searchPage.selectGridOption();
		searchPage.clickOnProductDisplayedInSearchResultUsingName();
		productDisplayPage = new ProductDisplayPage(driver);
		String expectedToolTip = "Compare this Product";	
		Assert.assertEquals(productDisplayPage.getToolTipForCompareThisProductOption(), expectedToolTip);
		productDisplayPage.selectCompareThisProductOption();
		System.out.println(productDisplayPage.getSuccessMessageForCompareThisProductOption());
		String expectedProductComparisonSuccessMessage = "Success: You have added HP LP3065 to your product comparison!";
		String actualMessage = productDisplayPage.getSuccessMessageForCompareThisProductOption();
		Assert.assertTrue(actualMessage.contains(expectedProductComparisonSuccessMessage));
		productDisplayPage.clickOnProductComparisonLinkInSuccessMessage();
		productComparisonPage = new ProductComparisonPage(driver);
		productComparisonPage.didWeNavigateToProductComparisionPage();
		productComparisonPage.didDetailsOfTheProductGotAddedForComparison();
		
	}
	
	@Test(priority = 4)
	public void verifyAddingProductForComparisonFromCatagoryPage() throws InterruptedException {
		
		headerOptionsPage = new HeaderOptionsPage(driver);
		headerOptionsPage.clickOnDesktopsMenuOption();
		headerOptionsPage.clickOnShowAllDesktopsOption();
		searchPage = new SearchPage(driver);
		//searchPage.selectGridOption();
		searchPage.clickOnProductDisplayedInSearchResultUsingName();
		productCategoryPage = new ProductCategoryPage(driver);
		String expectedToolTip = "Compare this Product";
		Assert.assertEquals(productCategoryPage.getToolTipForCompareThisProductOption(), expectedToolTip);
		productCategoryPage.selectCompareThisProductOption();
		System.out.println(productCategoryPage.getSuccessMessageForCompareThisProductOption());
		String expectedProductComparisonSuccessMessage = "Success: You have added HP LP3065 to your product comparison!";
		String actualMessage = productCategoryPage.getSuccessMessageForCompareThisProductOption();
		Assert.assertTrue(actualMessage.contains(expectedProductComparisonSuccessMessage));
		//System.out.println(productCategoryPage.getSuccessMessageForCompareThisProductOption());
		productCategoryPage.clickOnProductComparisonLinkInSuccessMessage();
		productComparisonPage = new ProductComparisonPage(driver);
		productComparisonPage.didWeNavigateToProductComparisionPage();
		productComparisonPage.didDetailsOfTheProductGotAddedForComparison();
		
		
		
	}

}
