package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	WebDriver driver;

	@FindBy(id = "input-email")
	WebElement userNameField;

	@FindBy(id = "input-password")
	WebElement passwordField;

	@FindBy(xpath = "//input[@value='Login']")
	WebElement loginButton;
	
	@FindBy(xpath = "//div[contains(@class,'alert-dismissible')]")
	WebElement emailPasswordNoMatchingWarning;

	public LoginPage(WebDriver driver) {

		this.driver = driver;

		PageFactory.initElements(driver, this);

	}

	public void enterUserName(String userName) {

		userNameField.sendKeys(userName);

	}

	public void enterPassword(String password) {

		passwordField.sendKeys(password);

	}

	public void clickLoginButton() {

		loginButton.click();

	}
	
	public String retrieveEmailPasswordNotMatchingWarningText() {
		
		String warningText = emailPasswordNoMatchingWarning.getText();
		return warningText;
		
	}
}
