package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	WebDriver driver;

	// Objects
	@FindBy(xpath = "//a[@title='My Account']")
	private WebElement myAccountDropmenu;

	@FindBy(linkText = "Login")
	private WebElement loginOption;

	@FindBy(linkText = "Register")
	private WebElement registerOption;
	
	@FindBy(name = "search")
	private WebElement searchField;
	
	@FindBy(xpath = "//button[contains(@class,'btn-default')][1]")
	private WebElement searchButton;

	public HomePage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	// Actions

	public void clickOnMyAccount() {

		myAccountDropmenu.click();

	}

	public void selectLoginOption() {

		loginOption.click();

	}

	public void clickRegisterOption() {

		registerOption.click();

	}
	
	public void enterProductIntoSearchField(String productSearchText) {
		
		searchField.sendKeys(productSearchText);
		
	}
	
	public void clickOnSearchButton() {
		
		searchButton.click();
		
	}
}
