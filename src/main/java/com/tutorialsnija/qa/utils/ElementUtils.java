package com.tutorialsnija.qa.utils;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ElementUtils {

	WebDriver driver;

	public ElementUtils(WebDriver driver) {

		this.driver = driver;
	}

	public boolean isElementDisplayed(WebElement element) {
		boolean b = false;
		try {
			b = element.isDisplayed();
		} catch (NoSuchElementException e) {
			b = false;
		}
		return b;
	}

	public boolean isElementEnabled(WebElement element) {
		boolean b = false;
		try {
			b = element.isEnabled();
		} catch (NoSuchElementException e) {
			b = false;
		}
		return b;
	}

	public void clickOnElements(WebElement element) {
		if (isElementDisplayed(element) && isElementEnabled(element)) {
			element.click();
		}
	}

	public String getTextOfElement(WebElement element) {

		String text = "";
		try {
			text = element.getText();
		} catch (NoSuchElementException e) {
			text = "";
		} catch (Exception e) {
			text = "";
		}
		return text;
	}

	public void enterTextIntoElement(WebElement element, String searchText) {
		if (isElementDisplayed(element) && isElementEnabled(element)) {
			element.clear();
			element.sendKeys(searchText);
		}
	}
	
	public String getPlaceHolderTextOfElement(WebElement element) {
		
		String searchBoxFieldPlaceHolderText = null;
		try {

			searchBoxFieldPlaceHolderText = element.getDomAttribute("placeholder");

		} catch (NoSuchElementException e) {

			searchBoxFieldPlaceHolderText = null;
		}

		return searchBoxFieldPlaceHolderText;
	}
}
