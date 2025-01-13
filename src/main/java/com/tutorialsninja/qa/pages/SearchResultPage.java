package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchResultPage {
	
	WebDriver driver;
	
	@FindBy(linkText = "HP LP3065")
	private WebElement validSearchProduct;
	
	@FindBy(xpath = "//div[@id='content']/h2/following-sibling::p")
	private WebElement noProductMessage;
	
	
	public SearchResultPage(WebDriver driver) {
		
		this.driver = driver;
		
		PageFactory.initElements(driver, this);
		
	}
	
	public boolean displayStatusOfValidProduct() {
		
		boolean displayStatus = validSearchProduct.isDisplayed();
		
		return displayStatus;
		
	}
	
	public String retrieveNoProductFoundMessage() {
		
		String noProductMessageText = noProductMessage.getText();
		
		return noProductMessageText;
		
	}
}
