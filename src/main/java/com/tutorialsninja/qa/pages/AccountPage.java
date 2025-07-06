package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.tutorialsnija.qa.utils.ElementUtils;

public class AccountPage {

	WebDriver driver;
	ElementUtils elementUtils;

	public AccountPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
		elementUtils = new ElementUtils(driver);
	}

	@FindBy(linkText = "Edit your account information")
	private WebElement editYourAccountInfoOption;

	@FindBy(linkText = "Subscribe / unsubscribe to newsletter")
	private WebElement subscribeUnsubscribeNewLetterOption;

	@FindBy(xpath = "//a[@class='list-group-item'][text()='Logout']")
	private WebElement rightColumnLogoutOption;

	@FindBy(linkText = "Change your password")
	private WebElement changeYourPassword;

	public boolean verifyEditYourAccountInfoLabel() {

		boolean displayStatus = editYourAccountInfoOption.isDisplayed();

		return displayStatus;

	}

	public void clickOnRightColumnLogoutOption() {

		elementUtils.clickOnElements(rightColumnLogoutOption);

	}

	public boolean isRightColumnLogoutOptionAvailable() {
		
		return elementUtils.isElementDisplayed(rightColumnLogoutOption);
		
	}

}
