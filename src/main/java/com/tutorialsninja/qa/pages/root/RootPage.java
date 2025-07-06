package com.tutorialsninja.qa.pages.root;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RootPage {

	public WebDriver driver;

	public RootPage(WebDriver driver) {

		this.driver = driver;

	}

	public WebDriver getDriver() {

		return driver;

	}

	public String getDomAttributeOfElement(WebElement element, String attribute) {

		String text = null;
		try {

			text = element.getDomAttribute(attribute);

		} catch (NoSuchElementException e) {

			text = null;

		} catch (Exception e) {

			text = null;
		}
		return text;
	}

}
