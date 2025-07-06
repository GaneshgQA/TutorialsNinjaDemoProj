package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.tutorialsnija.qa.utils.ElementUtils;

public class SearchPage {

	WebDriver driver;
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
	
	public void selectOptionFromCatagoryDropdownField(int indexNumber) {
		
		Select select = new Select(catagoryDropDownField);
		select.selectByIndex(indexNumber);
		
	}

	public void enterSearchTermIntoSearchCriteriaField(String product) {

		//searchCriteriaField.sendKeys(product);
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
		
		return productHavingSearchTextInDescription.isDisplayed();
		
	}

}
