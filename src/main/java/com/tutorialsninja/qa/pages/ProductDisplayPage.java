package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.tutorialsnija.qa.utils.ElementUtils;

public class ProductDisplayPage {
	
	WebDriver driver;
	ElementUtils elementUtils;
	
	public ProductDisplayPage(WebDriver driver) {
		
		this.driver = driver;
		elementUtils = new ElementUtils(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//ul[@class='thumbnails']")
	private WebElement thumbNailsSection;
	
	
	public boolean didWeNavigateToProuctDisplayPage() {
		
		return elementUtils.isElementDisplayed(thumbNailsSection);
		
	}

}
