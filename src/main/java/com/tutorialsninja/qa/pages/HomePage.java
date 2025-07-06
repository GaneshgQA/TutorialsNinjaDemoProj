package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.tutorialsnija.qa.utils.ElementUtils;

public class HomePage {

	WebDriver driver;
	ElementUtils elementUtils;

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
		elementUtils = new ElementUtils(driver);
	}

	// Actions

	public String getPlaceHolderTextOfSearchBoxField() {

		return elementUtils.getPlaceHolderTextOfElement(searchField);

	}

	public void clickOnMyAccount() {

		elementUtils.clickOnElements(myAccountDropmenu);
	}

	public void selectLoginOption() {

		elementUtils.clickOnElements(loginOption);

	}

	public void clickRegisterOption() {

		elementUtils.clickOnElements(registerOption);

	}

	public void enterProductIntoSearchField(String productSearchText) {

		//searchField.sendKeys(productSearchText);
		elementUtils.enterTextIntoElement(searchField,productSearchText);

	}

	public void clickOnSearchButton() {

		elementUtils.clickOnElements(searchButton);
		

	}
}
