package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.tutorialsnija.qa.utils.ElementUtils;

public class SiteMapPage {

	WebDriver driver;
	ElementUtils elementUtils;

	public SiteMapPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		elementUtils = new ElementUtils(driver);

	}

	@FindBy(linkText = "Search")
	private WebElement searchLink;

	@FindBy(linkText = "Special Offers")
	private WebElement specialOffersLink;

	public void clickOnSearchLinkOnSiteMapPage() {
		
		elementUtils.clickOnElements(searchLink);

	}

	public void clickOnSpecialOffersLinkOnSiteMapPage() {
		elementUtils.clickOnElements(specialOffersLink);
	}

}
