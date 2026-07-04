package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.tutorialsnija.qa.utils.ElementUtils;

public class FooterOptions {

	WebDriver driver;
	ElementUtils elementUtils;

	public FooterOptions(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
		elementUtils = new ElementUtils(driver);

	}

	@FindBy(linkText = "Site Map")
	private WebElement siteMapLink;

	public void clickOnSiteMapLink() {

		elementUtils.clickOnElements(siteMapLink);

	}

}
