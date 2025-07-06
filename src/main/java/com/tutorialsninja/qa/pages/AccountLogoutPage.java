package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.tutorialsnija.qa.utils.ElementUtils;

public class AccountLogoutPage {
	
	public ElementUtils elementUtils;
	
	WebDriver driver;
	
	public AccountLogoutPage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
		elementUtils = new ElementUtils(driver);
	}

	@FindBy(xpath = "//ul[@class='breadcrumb']//li//a[text()='Logout']")
	private WebElement accountLogoutBreadCrumb;
	
	@FindBy(xpath = "//div[@id='content']//h1")
	private WebElement accountLogoutHeading;
	
	@FindBy(xpath = "//a[@class='btn btn-primary'][text()='Continue']")
	private WebElement accoutLogoutPageContinueButton;
	
	@FindBy(xpath = "//div[@id='content']//p[1]")
	private WebElement accountLogoutMessage;
	
	public String retrieveAccountLogOutHeadingText() {
		
		return elementUtils.getTextOfElement(accountLogoutHeading);
		
	}
	
	public void clickOnAccontLogoutPageContinueButton() {
		
		elementUtils.clickOnElements(accoutLogoutPageContinueButton);	
			
	}
	
	public boolean verifyIsAccountLogoutBreadCrumbDisplayed() {
		
		return elementUtils.isElementDisplayed(accountLogoutBreadCrumb);
		
	}
	
	public String retrieveAccountLogoutMessageText() {
		
		return elementUtils.getTextOfElement(accountLogoutMessage);
		
	}
	
}
