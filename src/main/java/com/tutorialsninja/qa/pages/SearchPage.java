package com.tutorialsninja.qa.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.tutorialsnija.qa.utils.ElementUtils;

public class SearchPage {

	public WebDriver driver;
	ElementUtils elementUtils;

	public SearchPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
		elementUtils = new ElementUtils(driver);
	}

	@FindBy(id = "input-search")
	private WebElement searchCriteriaField;

	@FindBy(id = "button-search")
	private WebElement searchButton;

	@FindBy(linkText = "HP LP3065")
	private WebElement hpProduct;

	@FindBy(id = "description")
	private WebElement searchInProductDescriptionCheckbox;

	@FindBy(linkText = "iMac")
	private WebElement productHavingSearchTextInDescription;

	@FindBy(xpath = "//select[@name='category_id']")
	private WebElement catagoryDropDownField;

	@FindBy(name = "sub_category")
	private WebElement searchInSubcatagoriesCheckboxField;

	@FindBy(id = "list-view")
	private WebElement listViewOption;

	@FindBy(xpath = "//span[text()='Add to Cart']")
	private WebElement addToCartOption;

	@FindBy(xpath = "//div[contains(@class,'alert alert-success alert-dismissible')]")
	private WebElement successMessage;

	@FindBy(xpath = "//button[@*='Add to Wish List']")
	private WebElement addToWishListOption;

	@FindBy(xpath = "//button[@*='Compare this Product']")
	private WebElement compareThisProductOption;

	@FindBy(xpath = "//div[@class='product-thumb']")
	private List<WebElement> numberOfProucts;

	@FindBy(xpath = "//div[@class='product-thumb']//img")
	private WebElement productImage;

	@FindBy(id = "grid-view")
	private WebElement gridOptin;

	@FindBy(id = "compare-total")
	private WebElement productCompareLink;

	@FindBy(linkText = "Search")
	private WebElement searchBreadCrumbLink;

	public void selectSearchBreadCrumbOption() {

		elementUtils.clickOnElements(searchBreadCrumbLink);
	}

	public void clickOnProductCompareLink() {

		elementUtils.clickOnElements(productCompareLink);

	}

	public void selectGridOption() {

		elementUtils.clickOnElements(gridOptin);
	}

	public void selectiMacProductUsingName() {

		elementUtils.clickOnElements(productHavingSearchTextInDescription);
	}

	public void selectProductImage() {

		elementUtils.clickOnElements(productImage);
	}

	public void selectCompareThisProductOption() {

		elementUtils.clickOnElements(compareThisProductOption);
	}

	public void selectAddToWishListOption() {

		elementUtils.clickOnElements(addToWishListOption);
	}

	public String getSuccessMessage() {

		return elementUtils.getTextOfElement(successMessage);

	}

	public void selectAddToCartOption() {

		elementUtils.clickOnElements(addToCartOption);
	}

	public void selectListViewOption() {

		elementUtils.clickOnElements(listViewOption);
	}

	public void selectInSubcatagoriesCheckBoxField() {

		elementUtils.clickOnElements(searchInSubcatagoriesCheckboxField);

	}

	public void selectOptionFromCatagoryDropdownField(int indexNumber) {

		Select select = new Select(catagoryDropDownField);
		select.selectByIndex(indexNumber);

	}

	public void enterSearchTermIntoSearchCriteriaField(String product) {

		// searchCriteriaField.sendKeys(product);
		elementUtils.enterTextIntoElement(searchCriteriaField, product);

	}

	public void clickOnSearchButton() {

		elementUtils.clickOnElements(searchButton);

	}

	public boolean isExistingProductDisplayedInSearchResult() {

		boolean productDisplayStatus = elementUtils.isElementDisplayed(hpProduct);
		return productDisplayStatus;

	}

	public void selectSearchInProductDescriptionCheckbox() {

		elementUtils.clickOnElements(searchInProductDescriptionCheckbox);

	}

	public void clickOnProductHavingSearchTextInDescription() {

		elementUtils.clickOnElements(productHavingSearchTextInDescription);

	}

	public boolean isProductHavingDescriptionTextDisplayedInSearchResult() {

		// return productHavingSearchTextInDescription.isDisplayed();

		return elementUtils.isElementDisplayed(productHavingSearchTextInDescription);

	}

	public boolean didWeNavigateToSearchPage() {

		return elementUtils.isElementDisplayed(searchBreadCrumbLink);

	}

	public void searchForAProductUsingKeyboardKeys(String productName) {
		

			elementUtils.pressKeyMultipleTimes(driver, "TAB", 8);
			elementUtils.enterTextIntoFieldUsingKeyboardKeys(driver, productName);
			elementUtils.pressKeyboardKey("TAB");
			elementUtils.pressKeyboardKey("ENTER");
			//elementUtils.pressKeyMultipleTimes(driver, "TAB", 21);

	}
	
	public void searchUsingSearchCriteriaFieldInSearchResultPage(String productName) {
		
		elementUtils.pressKeyMultipleTimes(driver, "TAB", 21);
		elementUtils.pressKeyboardKey("BACK_SPACE");
		elementUtils.enterTextIntoFieldUsingKeyboardKeys(driver, productName);
		elementUtils.pressKeyMultipleTimes(driver, "TAB", 3);
		elementUtils.pressKeyboardKey("ENTER");
		
	}
	
	public void verifySearchingByCatagoryUsingKeyboardKeys() {
		
		elementUtils.pressKeyMultipleTimes(driver, "TAB", 22);
		elementUtils.pressKeyboardKey("ARROW_DOWN");
		elementUtils.pressKeyMultipleTimes(driver, "TAB", 3);
		elementUtils.pressKeyboardKey("ENTER");
		
	}
	
	public void verifySearchingInSubCatagoryUsingKeyboardKeys() {
		
		elementUtils.pressKeyMultipleTimes(driver, "TAB", 23);
		elementUtils.pressKeyboardKey("SPACE");
		elementUtils.pressKeyMultipleTimes(driver, "TAB", 2);
		elementUtils.pressKeyboardKey("ENTER");
		
	}
	
	public void verifySearchingInProductDescriptionUsingKeyboardKeys(String productDescription) {
		
		elementUtils.pressKeyMultipleTimes(driver, "TAB", 21);
		elementUtils.pressKeyboardKey("BACK_SPACE");
		elementUtils.enterTextIntoFieldUsingKeyboardKeys(driver, productDescription);
		elementUtils.pressKeyMultipleTimes(driver, "TAB", 3);
		elementUtils.pressKeyboardKey("SPACE");
		elementUtils.pressKeyMultipleTimes(driver, "TAB", 1);
		elementUtils.pressKeyboardKey("ENTER");
			
	}
	
	public void verifySearchingInListViewUsingKeyboardKeys() {
		
		elementUtils.pressKeyMultipleTimes(driver, "TAB", 26);
		elementUtils.pressKeyboardKey("ENTER");
		//elementUtils.enterTextIntoFieldUsingKeyboardKeys(driver, productDescription);
		//elementUtils.pressKeyMultipleTimes(driver, "TAB", 3);
		//elementUtils.pressKeyboardKey("SPACE");
		//elementUtils.pressKeyMultipleTimes(driver, "TAB", 1);
		//elementUtils.pressKeyboardKey("ENTER");
			
	}
	
	public void verifySearchingInGridViewUsingKeyboardKeys() {
		
		elementUtils.pressKeyMultipleTimes(driver, "TAB", 1);
		elementUtils.pressKeyboardKey("ENTER");
		
	}
	
	public void verifyNavigatingToProductComparePageUsingKeyboardKeys() {
		
		elementUtils.pressKeyMultipleTimes(driver, "TAB",1);
		elementUtils.pressKeyboardKey("ENTER");
		
	}
	
	public void verifySortInSearchPageUsingKeyboardKeys() {
		
		elementUtils.pressKeyMultipleTimes(driver, "TAB", 1);
		elementUtils.pressKeyboardKey("ARROW_DOWN");
		
	}
	
	public void verifyProductCountInSearchPageUsingKeyboardKeys() {
		
		elementUtils.pressKeyMultipleTimes(driver, "TAB", 30);
		elementUtils.pressKeyboardKey("ARROW_DOWN");
		
	}
}
