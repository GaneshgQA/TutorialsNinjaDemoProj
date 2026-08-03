package com.tutorialsnija.qa.utils;

import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

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
			try {
				element.click();
			} catch (org.openqa.selenium.ElementClickInterceptedException e) {
				// If element is intercepted by another element, scroll into view and try again
				scrollToElement(element);
				try {
					element.click();
				} catch (org.openqa.selenium.ElementClickInterceptedException e2) {
					// If still intercepted, use JavaScriptExecutor to click
					org.openqa.selenium.JavascriptExecutor executor = (org.openqa.selenium.JavascriptExecutor) driver;
					executor.executeScript("arguments[0].click();", element);
				}
			}
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

	public void selectOptionInDropDownFieldUsingOptionText(WebElement element, String optionText) {

		if (isElementDisplayed(element) && isElementEnabled(element)) {

			Select select = new Select(element);
			select.selectByContainsVisibleText(optionText);

		}
	}

	public WebDriver pressKeyMultipleTimes(WebDriver driver, String key, int numberOfTimes) {

		Actions action = new Actions(driver);

		for (int i = 0; i < numberOfTimes; i++) {

			action.sendKeys(Keys.valueOf(key)).perform();

		}

		return driver;

	}

	public WebDriver enterTextIntoFieldUsingKeyboardKeys(WebDriver driver, String text) {

		Actions action = new Actions(driver);
		action.sendKeys(text).perform();
		return driver;
	}

	public void leftMouseClick() {

		Actions action = new Actions(driver);
		action.contextClick().perform();
	}

	public void rightMouseClick() {

		Actions action = new Actions(driver);
		action.contextClick().perform();
	}

	public void pressAndHoldKeyboardKey(String key) {

		Actions action = new Actions(driver);
		action.keyDown(Keys.valueOf(key)).perform();
	}

	public void pressKeyboardKey(String key) {

		Actions action = new Actions(driver);
		action.sendKeys(Keys.valueOf(key)).build().perform();
	}

	public void releaseKeyboardKey(String key) {

		Actions action = new Actions(driver);
		action.keyUp(Keys.valueOf(key)).perform();
	}

	public void pastingTextIntoField(WebElement element) {

		if (isElementDisplayed(element) && isElementEnabled(element)) {
			Actions action = new Actions(driver);
			action.keyDown(Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL).perform();
		}
	}

	public void copyTextFromElement(WebElement element) {

		if (isElementDisplayed(element) && isElementEnabled(element)) {
			Actions action = new Actions(driver);
			action.keyDown(Keys.CONTROL).sendKeys("c").keyUp(Keys.CONTROL).build().perform();
		}
	}

	public void scrollToElement(WebElement element) {
		if (isElementDisplayed(element)) {
			Actions action = new Actions(driver);
			action.scrollToElement(element).perform();
		}
	}

	public String getToolTip(WebElement element) {
		String toolTipText = null;
		if (isElementDisplayed(element)) {
			
			toolTipText = element.getDomAttribute("data-original-title");

		}
		return toolTipText;

	}
}