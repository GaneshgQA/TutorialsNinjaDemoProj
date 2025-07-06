package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.tutorialsnija.qa.utils.ElementUtils;

public class AccountSuccessPage {

	WebDriver driver;
	ElementUtils elementUtils;

	@FindBy(xpath = "//div[@id='content']/p[1]")
	private WebElement accountSuccessPageHeading;

	@FindBy(xpath = "//div[@id='content']/h1/following-sibling::p[1]")
	private WebElement accountSuccessPageHeading1;

	public AccountSuccessPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
		elementUtils = new ElementUtils(driver);
	}

	public String retriveAccountSuccessPageHeading() {

		return elementUtils.getTextOfElement(accountSuccessPageHeading);
	}

	public String retrieveAccountSuccessPageHeading1() {

		return elementUtils.getTextOfElement(accountSuccessPageHeading1);
	}
}
