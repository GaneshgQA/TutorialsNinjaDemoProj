package com.tutorialsninja.qa.pages;

import java.util.List;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.tutorialsnija.qa.utils.ElementUtils;

public class SearchResultPage {

	WebDriver driver;
	ElementUtils elementUtils;

	public SearchResultPage(WebDriver driver) {

		this.driver = driver;

		PageFactory.initElements(driver, this);
		elementUtils = new ElementUtils(driver);
	}

	@FindBy(linkText = "HP LP3065")
	private WebElement validSearchProduct;

	@FindBy(xpath = "//div[@id='content']/h2/following-sibling::p")
	private WebElement noProductMessage;

	@FindBy(xpath = "//div[@class='product-thumb']")
	private List<WebElement> numberOfProductsInSearchResult;

	@FindBy(xpath = "//img[@title='iMac']")
	private WebElement productiMac;

	@FindBy(xpath = "//a[text()='MacBook']")
	private WebElement productmacBook;

	@FindBy(xpath = "//img[@title='MacBook Air']")
	private WebElement productMacBookAir;

	@FindBy(xpath = "//a[text()='MacBook Pro']")
	private WebElement productMacBookPro;

	@FindBy(id = "input-search")
	private WebElement searchCriteriaField;

	public String getPlaceholderTextOfSearchCriteriaField() {

		String searchCriteriaFieldPlaceholderText = null;
		try {
			searchCriteriaFieldPlaceholderText = searchCriteriaField.getDomAttribute("placeholder");
		} catch (NoSuchElementException e) {
			searchCriteriaFieldPlaceholderText = null;
		}
		return searchCriteriaFieldPlaceholderText;
	}

	public boolean displayStatusOfProductiMacInSearchResult() {

		return elementUtils.isElementDisplayed(productiMac);
	}

	public boolean displayStatusOfProductMacBookAir() {

		return elementUtils.isElementDisplayed(productMacBookAir);
	}

	public String retrieveProductMacBookText() {

		return elementUtils.getTextOfElement(productmacBook);
	}

	public String retrieveProductMacBookProText() {

		return elementUtils.getTextOfElement(productMacBookPro);
	}

	public int getNumberOfProductsDisplayedInSearchResult() {

		int n = 0;
		try {
			n = numberOfProductsInSearchResult.size();
		} catch (NoSuchElementException e) {
			n = 0;
		}
		return n;
	}

	public boolean displayStatusOfValidProduct() {

		return elementUtils.isElementDisplayed(validSearchProduct);

	}

	public String retrieveNoProductFoundMessage() {

		return elementUtils.getTextOfElement(noProductMessage);

	}
}
