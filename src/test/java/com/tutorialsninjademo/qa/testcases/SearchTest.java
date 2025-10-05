package com.tutorialsninjademo.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.LoginPage;
import com.tutorialsninja.qa.pages.ProductComparisonPage;
import com.tutorialsninja.qa.pages.ProductDisplayPage;
import com.tutorialsninja.qa.pages.SearchPage;
import com.tutorialsninja.qa.pages.SearchResultPage;

public class SearchTest extends Base {

	public WebDriver driver;
	LoginPage loginPage;
	HomePage homePage;
	SearchResultPage searchResultPage;
	SearchPage searchPage;
	ProductDisplayPage productDisplayPage;
	ProductComparisonPage productComparisonPage;

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

		homePage = new HomePage(driver);
		homePage.enterProductIntoSearchField(dataProp.getProperty("validProduct"));
		homePage.clickOnSearchButton();

		// driver.findElement(By.name("search")).sendKeys(dataProp.getProperty("validProduct"));
		// driver.findElement(By.xpath("(//button[contains(@class,'btn-default')])[1]")).click();

		SearchResultPage searchResultPage = new SearchResultPage(driver);
		Assert.assertTrue(searchResultPage.displayStatusOfValidProduct(), "Valid product HP is not displayed");

	}

	@Test(priority = 2)
	public void verifySearchWithInvalidProduct() {

		homePage = new HomePage(driver);
		homePage.enterProductIntoSearchField(dataProp.getProperty("invalidProduct"));
		homePage.clickOnSearchButton();

//		driver.findElement(By.name("search")).sendKeys(dataProp.getProperty("invalidProduct"));
//		driver.findElement(By.xpath("(//button[contains(@class,'btn-default')])[1]")).click();

		SearchResultPage searchResultPage = new SearchResultPage(driver);
		String actualNoSearchMessage = searchResultPage.retrieveNoProductFoundMessage();
		Assert.assertEquals(actualNoSearchMessage, dataProp.getProperty("invalidProductSearchMessage"),
				"No product in search result message is not displayed");

	}

	@Test(priority = 3, dependsOnMethods = { "verifySearchWithInvalidProduct" })
	public void verifySearchWithoutAnyProduct() {

		homePage = new HomePage(driver);
		homePage.clickOnSearchButton();

//		driver.findElement(By.name("search")).sendKeys(dataProp.getProperty("invalidProduct"));
//		driver.findElement(By.xpath("(//button[contains(@class,'btn-default')])[1]")).click();

		searchResultPage = new SearchResultPage(driver);
		String actualNoSearchMessage = searchResultPage.retrieveNoProductFoundMessage();
		Assert.assertEquals(actualNoSearchMessage, dataProp.getProperty("invalidProductSearchMessage"),
				"No product in search result message is not displayed");

	}

	@Test(priority = 4)
	public void verifySearchAfterLogin() {

		homePage = new HomePage(driver);
		homePage.clickOnMyAccount();
		homePage.selectLoginOption();
		loginPage = new LoginPage(driver);
		loginPage.enterUserName(prop.getProperty("validEmail"));
		loginPage.enterPassword(prop.getProperty("validPassword"));
		loginPage.clickLoginButton();
		homePage.enterProductIntoSearchField(dataProp.getProperty("validProduct"));
		homePage.clickOnSearchButton();
		searchResultPage = new SearchResultPage(driver);
		Assert.assertTrue(searchResultPage.displayStatusOfValidProduct(), "Valid hp product is not displayed");

	}

	@Test(priority = 5)
	public void verifySearchResultingInMultipleProducts() {

		homePage = new HomePage(driver);
		homePage.clickOnMyAccount();
		homePage.selectLoginOption();
		loginPage = new LoginPage(driver);
		loginPage.enterUserName(prop.getProperty("validEmail"));
		loginPage.enterPassword(prop.getProperty("validPassword"));
		loginPage.clickLoginButton();
		homePage.enterProductIntoSearchField(dataProp.getProperty("searchTermForMultipleProducts"));
		homePage.clickOnSearchButton();
		searchResultPage = new SearchResultPage(driver);
		Assert.assertTrue(searchResultPage.getNumberOfProductsDisplayedInSearchResult() > 1);

	}

	@Test(priority = 6)
	public void verifyMultipleProductsInSearchResult() {

		homePage = new HomePage(driver);
		homePage.enterProductIntoSearchField(dataProp.getProperty("searchTermForMultipleProducts"));
		homePage.clickOnSearchButton();
		searchResultPage = new SearchResultPage(driver);
		Assert.assertTrue(searchResultPage.displayStatusOfProductiMacInSearchResult());
		Assert.assertEquals(searchResultPage.retrieveProductMacBookText(),
				dataProp.getProperty("expectedproductMacBookText"), "MacBook text not displayed");
		Assert.assertTrue(searchResultPage.displayStatusOfProductMacBookAir());
		Assert.assertEquals(searchResultPage.retrieveProductMacBookProText(),
				dataProp.getProperty("expectedMacBookProText"), "MacBookPro text not displayed");

	}

	@Test(priority = 7)
	public void verifySearchFieldsPlaceholders() {

		homePage = new HomePage(driver);
		String expectedSearchBoxPlaceholderText = "Search";
		Assert.assertEquals(homePage.getPlaceHolderTextOfSearchBoxField(), expectedSearchBoxPlaceholderText,
				"Expected searchboxPlaceholderText does not match");
		homePage.clickOnSearchButton();
		String expectedSerachCriteriaFieldPlaceholderText = "Keywords";
		searchResultPage = new SearchResultPage(driver);
		Assert.assertEquals(searchResultPage.getPlaceholderTextOfSearchCriteriaField(),
				expectedSerachCriteriaFieldPlaceholderText, "Expected searchCriteriaPlaceholderText does not match");

	}

	@Test(priority = 8)
	public void verifySearchingForProductUsingSearchCriteriaField() {

		homePage = new HomePage(driver);
		homePage.clickOnSearchButton();
		searchPage = new SearchPage(driver);
		searchPage.enterSearchTermIntoSearchCriteriaField(dataProp.getProperty("validProduct"));
		searchPage.clickOnSearchButton();
		Assert.assertTrue(searchPage.isExistingProductDisplayedInSearchResult());

	}

	@Test(priority = 9)
	public void verifySearchinForProductUsingSomeTextInDescription() {

		homePage = new HomePage(driver);
		homePage.clickOnSearchButton();
		searchPage = new SearchPage(driver);
		searchPage.enterSearchTermIntoSearchCriteriaField(dataProp.getProperty("SearchTermText"));
		searchPage.selectSearchInProductDescriptionCheckbox();
		searchPage.clickOnSearchButton();
		searchPage.isProductHavingDescriptionTextDisplayedInSearchResult();
		searchPage.clickOnProductHavingSearchTextInDescription();

	}

	@Test(priority = 10)
	public void verifySearchBySelectingSubCategory() {

		homePage = new HomePage(driver);
		homePage.clickOnSearchButton();
		searchPage = new SearchPage(driver);
		searchPage.enterSearchTermIntoSearchCriteriaField(dataProp.getProperty("existingProductInSubCatagory"));
		searchPage.selectOptionFromCatagoryDropdownField(3);
		searchPage.clickOnSearchButton();
		searchPage.isProductHavingDescriptionTextDisplayedInSearchResult();

	}

	@Test(priority = 11)
	public void verifySearchByUsingParentCatagoryAndSearchInSubcatagoriesOption() {

		homePage = new HomePage(driver);
		homePage.clickOnSearchButton();
		searchPage = new SearchPage(driver);
		searchPage.enterSearchTermIntoSearchCriteriaField(dataProp.getProperty("existingProductInSubCatagory"));
		searchPage.selectOptionFromCatagoryDropdownField(1);
		searchPage.clickOnSearchButton();
		searchResultPage = new SearchResultPage(driver);
		String actualNoSearchMessage = searchResultPage.retrieveNoProductFoundMessage();
		Assert.assertEquals(actualNoSearchMessage, dataProp.getProperty("invalidProductSearchMessage"),
				"No product in search result message is not displayed");
		searchPage.selectInSubcatagoriesCheckBoxField();
		homePage.clickOnSearchButton();
		Assert.assertTrue(searchResultPage.displayStatusOfProductiMacInSearchResult());

	}

	@Test(priority = 12)
	public void verifyProductInSearchResultsInListViewAndGridView() throws InterruptedException {

		homePage = new HomePage(driver);
		homePage.enterProductIntoSearchField(dataProp.getProperty("validProductiMac"));
		homePage.clickOnSearchButton();
		searchPage = new SearchPage(driver);
		searchPage.selectListViewOption();
		searchPage.selectAddToCartOption();
		System.out.println(searchPage.getSuccessMessage());
		Assert.assertTrue(
				searchPage.getSuccessMessage().contains(dataProp.getProperty("expectedMessageSelectAddToCartOption")));
		Thread.sleep(8000);
		searchPage.selectAddToWishListOption();
		Thread.sleep(5000);
		Assert.assertTrue(searchPage.getSuccessMessage()
				.contains(dataProp.getProperty("expectedMessageSelectAddToWishListOption")));
		Thread.sleep(5000);
		searchPage.selectCompareThisProductOption();
		Thread.sleep(5000);
		Assert.assertTrue(searchPage.getSuccessMessage()
				.contains(dataProp.getProperty("expectedMessageSelectCompareThisProductOption")));
		searchPage.selectProductImage();
		productDisplayPage = new ProductDisplayPage(driver);
		Assert.assertTrue(productDisplayPage.didWeNavigateToProuctDisplayPage());

		// From grid view option
		homePage.enterProductIntoSearchField(dataProp.getProperty("validProductiMac"));
		homePage.clickOnSearchButton();
		searchPage = new SearchPage(driver);
		searchPage.selectGridOption();
		;
		searchPage.selectAddToCartOption();
		System.out.println(searchPage.getSuccessMessage());
		Assert.assertTrue(
				searchPage.getSuccessMessage().contains(dataProp.getProperty("expectedMessageSelectAddToCartOption1")));
		Thread.sleep(8000);
		searchPage.selectAddToWishListOption();
		Thread.sleep(5000);
		Assert.assertTrue(searchPage.getSuccessMessage()
				.contains(dataProp.getProperty("expectedMessageSelectAddToWishListOption2")));
		Thread.sleep(5000);
		searchPage.selectCompareThisProductOption();
		Thread.sleep(5000);
		Assert.assertTrue(searchPage.getSuccessMessage()
				.contains(dataProp.getProperty("expectedMessageSelectCompareThisProductOption3")));
		searchPage.selectProductImage();
		productDisplayPage = new ProductDisplayPage(driver);
		Assert.assertTrue(productDisplayPage.didWeNavigateToProuctDisplayPage());

	}

	@Test(priority = 13)
	public void verifyMultipleProductInSearchResultsInListViewAndGridView() {

		homePage = new HomePage(driver);
		homePage.enterProductIntoSearchField(dataProp.getProperty("searchTermForMultipleProductSearch"));
		homePage.clickOnSearchButton();
		searchResultPage = new SearchResultPage(driver);
		Assert.assertTrue(searchResultPage.getNumberOfProductsDisplayedInSearchResult() > 1);
		searchPage = new SearchPage(driver);
		searchPage.selectListViewOption();
		Assert.assertTrue(searchResultPage.getNumberOfProductsDisplayedInSearchResult() > 1);
		searchPage.selectGridOption();
		Assert.assertTrue(searchResultPage.getNumberOfProductsDisplayedInSearchResult() > 1);

	}

	@Test(priority = 14)
	public void verifyNavigationToProductComparePage() {

		homePage = new HomePage(driver);
		homePage.enterProductIntoSearchField(dataProp.getProperty("validProductiMac"));
		homePage.clickOnSearchButton();
		searchPage = new SearchPage(driver);
		searchPage.clickOnProductCompareLink();

		productComparisonPage = new ProductComparisonPage(driver);
		productComparisonPage.didWeNavigateToProductComparisionPage();

	}

}
