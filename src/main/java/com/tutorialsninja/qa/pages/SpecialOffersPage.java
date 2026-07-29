package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import java.util.List;
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

	// 'Compare this product' button (appears per product)
	@FindBy(xpath = "//button[@data-original-title='Compare this Product']")
	private WebElement compareThisProductButton;

	// link inside the success alert that navigates to Product Comparison page
	@FindBy(xpath = "//div[contains(@class,'alert')]//a[text()='product comparison']")
	private WebElement productComparisonLink;

	// Product Comparison page heading
	@FindBy(xpath = "//h1[normalize-space()='Product Comparison']")
	private WebElement productComparisonHeading;

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
	public void sortSpecialOffersProductListUsingSortBy(String sortOption) {
		// try to select the explicit "Price (High > Low)" option; fall back to last option if not present
		if (elementUtils.isElementDisplayed(sortByDropdown) && elementUtils.isElementEnabled(sortByDropdown)) {
			Select select = new Select(sortByDropdown);
			try {
				select.selectByVisibleText(sortOption);
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

	// clicks the 'Compare this Product' button for a product
	public void clickCompareThisProduct() {
		elementUtils.clickOnElements(compareThisProductButton);
	}

	// clicks the 'Compare this Product' button for a specific product identified by its name
	public void selectCompareThisProductUsingName(String productName) {

		// find product containers on the page (supports both thumb and list layouts)
		List<WebElement> products = driver.findElements(By.xpath("//div[contains(@class,'product-thumb') or contains(@class,'product-layout')]"));
		for (WebElement product : products) {
			try {
				WebElement nameElement = product.findElement(By.xpath(".//h4//a | .//div[@class='caption']/h4/a"));
				String name = nameElement.getText().trim();
				if (name.equalsIgnoreCase(productName)) {
					// try common compare button variants inside the product container
					WebElement compareBtn = null;
					try {
						compareBtn = product.findElement(By.xpath(".//button[contains(@onclick,'compare') or @data-original-title='Compare this Product' or @*='Compare this Product']"));
					} catch (Exception e) {
						// ignore and continue
					}
					if (compareBtn != null) {
						compareBtn.click();
					}
					break;
				}
			} catch (Exception e) {
				// continue searching other products
			}
		}
	}

	// returns the success message text shown after adding product to comparison (e.g., "Success: You have added ...")
	public String getSuccessMessageText() {
		return elementUtils.getTextOfElement(alertMessage);
	}

	// clicks the 'product comparison' link inside the success alert to navigate to comparison page
	public void clickProductComparisonLink() {
		elementUtils.clickOnElements(productComparisonLink);
	}

	// verifies whether the Product Comparison heading is visible on the page
	public boolean isProductComparisonHeadingVisible() {
		return elementUtils.isElementDisplayed(productComparisonHeading);
	}

}
