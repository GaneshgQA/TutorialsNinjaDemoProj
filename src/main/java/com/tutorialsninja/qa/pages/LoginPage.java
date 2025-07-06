package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.tutorialsnija.qa.utils.ElementUtils;

public class LoginPage {

	WebDriver driver;
	ElementUtils  elementUtils;

	@FindBy(id = "input-email")
	WebElement userNameField;

	@FindBy(id = "input-password")
	WebElement passwordField;

	@FindBy(xpath = "//input[@value='Login']")
	WebElement loginButton;

	@FindBy(xpath = "//div[contains(@class,'alert-dismissible')]")
	WebElement emailPasswordNoMatchingWarning;

	@FindBy(xpath = "(//div[@class='well']//h2)[2]")
	WebElement returningCustomerTextOnLoginpage;

	@FindBy(xpath = "//a[text()='Continue']")
	private WebElement continueButton;
	
	@FindBy(xpath = "//ul[@class='breadcrumb']//a[text()='Login']")
	private WebElement loginBreadCrumb;
	
	@FindBy(xpath = "(//div[@id='content']//h2)[1]")
	private WebElement loginPageHeading1;

	public LoginPage(WebDriver driver) {

		this.driver = driver;

		PageFactory.initElements(driver, this);
		elementUtils = new ElementUtils(driver);

	}
	
	public String getLoginPageHeading() {
		
		return elementUtils.getTextOfElement(loginPageHeading1);
		
	}

	public void enterUserName(String userName) {

		//userNameField.sendKeys(userName);
		elementUtils.enterTextIntoElement(userNameField, userName);

	}

	public void enterPassword(String password) {

		//passwordField.sendKeys(password);
		elementUtils.enterTextIntoElement(passwordField, password);

	}

	public void clickLoginButton() {

		elementUtils.clickOnElements(loginButton);

	}

	public String retrieveEmailPasswordNotMatchingWarningText() {

		return elementUtils.getTextOfElement(emailPasswordNoMatchingWarning);

	}

	public String retrieveReturningCutomerTextOnLoginPage() {

		return elementUtils.getTextOfElement(returningCustomerTextOnLoginpage);

	}

	public void clickOnContinueButtonOnLoginPage() {

		elementUtils.clickOnElements(continueButton);

	}
	
	public boolean isProperBreadCrumbDisplayed() {
		
		//return loginBreadCrumb.isDisplayed();
		return elementUtils.isElementDisplayed(loginBreadCrumb);
		  
	}
	
}
