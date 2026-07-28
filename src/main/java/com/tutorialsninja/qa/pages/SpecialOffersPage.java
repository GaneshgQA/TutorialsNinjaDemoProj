package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.tutorialsnija.qa.utils.ElementUtils;

public class SpecialOffersPage {

	WebDriver driver;
	ElementUtils elementUtils;

	// page elements
	@FindBy(xpath = "//h2[normalize-space()='Special Offers']")
	private WebElement specialOffersText;

	@FindBy(xpath = "//ul[contains(@class,'breadcrumb')]/li[last()]")
	private WebElement specialOffersBreadcrumb;

	@FindBy(id = "input-sort")
	private WebElement sortByDropdown;

	@FindBy(xpath = "//button[@data-original-title='Add to Wish List']")
	private WebElement addToWishlistButton;

	@FindBy(css = "div.alert")
	private WebElement alertMessage;

	// constructor of the class
	public SpecialOffersPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		elementUtils = new ElementUtils(driver);
	}

	// returns the page heading text for Special Offers
	public String getSpecialOffersText() {
		return elementUtils.getTextOfElement(specialOffersText);
	}

	// checks whether the Special Offers breadcrumb is visible
	public boolean isSpecialOffersBreadcrumbVisible() {
		return elementUtils.isElementDisplayed(specialOffersBreadcrumb);
	}

	// sorts the results by selecting the last option in the Sort By dropdown (typically descending order)
	public void sortByPriceHighToLow() {
		// try to select the explicit "Price (High > Low)" option; fall back to last option if not present
		if (elementUtils.isElementDisplayed(sortByDropdown) && elementUtils.isElementEnabled(sortByDropdown)) {
			Select select = new Select(sortByDropdown);
			try {
				select.selectByVisibleText("Price (High > Low)");
			} catch (Exception e) {
				int lastIndex = select.getOptions().size() - 1;
				if (lastIndex >= 0) {
					select.selectByIndex(lastIndex);
				}
			}
		}
	}

	// returns the currently selected option text from Sort By dropdown
	public String getSelectedSortByOptionText() {
		if (elementUtils.isElementDisplayed(sortByDropdown)) {
			Select select = new Select(sortByDropdown);
			try {
				return select.getFirstSelectedOption().getText();
			} catch (Exception e) {
				return "";
			}
		}
		return "";
	}

	// clicks on Add to Wish List button for a product
	public void clickAddToWishList() {
		elementUtils.clickOnElements(addToWishlistButton);
	}

	// returns the warning/alert message shown on the page (e.g., asking user to login/create account)
	public String getWarningMessageText() {
		return elementUtils.getTextOfElement(alertMessage);
	}

}
