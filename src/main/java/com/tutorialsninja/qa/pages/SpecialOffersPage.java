package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.tutorialsnija.qa.utils.ElementUtils;

public class SpecialOffersPage {

	WebDriver driver;
	ElementUtils elementUtils;
	WebDriverWait wait;
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

	@FindBy(xpath = "//div[contains(@class,'product-thumb') or contains(@class,'product-layout')]")
	private List<WebElement> productContainers;

	// 'Add to Cart' buttons (one per product)
	@FindBy(xpath = "//button[@data-original-title='Add to Cart' or contains(@onclick,'cart.add')]")
	private List<WebElement> addToCartButtons;

	// link inside the success alert that navigates to Product Comparison page
	@FindBy(xpath = "//div[contains(@class,'alert')]//a[text()='product comparison']")
	private WebElement productComparisonLink;
	
	////div[contains(@class,'alert')]//a[text()='product comparison']

	// Product Comparison page heading
	@FindBy(xpath = "//h1[normalize-space()='Product Comparison']")
	private WebElement productComparisonHeading;

	// Product details page heading (appears on product information page)
	@FindBy(xpath = "//div[@id='content']//h1")
	private WebElement productHeading;

	// constructor of the class
	public SpecialOffersPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		elementUtils = new ElementUtils(driver);
		// initialize WebDriverWait with a default timeout of 10 seconds
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	}

	// Explicit WebDriverWait methods
	// waits for the Sort By dropdown to be visible
	public void waitForSortByDropdownToBeVisible() {
		// reuse the wait instance created in the constructor
		wait.until(ExpectedConditions.elementToBeClickable(sortByDropdown));
	}

	// waits for the Add to Wish List button to be visible
	public void waitForAddToWishListButtonToBeVisible() {
		// reuse the wait instance created in the constructor
		wait.until(ExpectedConditions.elementToBeClickable(addToWishlistButton));
	}

	// waits for the Compare this Product button to be visible
	public void waitForCompareThisProductButtonToBeVisible() {
		// reuse the wait instance created in the constructor
		wait.until(ExpectedConditions.elementToBeClickable(compareThisProductButton));
	}

	// waits for the alert message to be visible
	public void waitForAlertMessageToBeVisible() {
		// reuse the wait instance created in the constructor
		wait.until(ExpectedConditions.elementToBeClickable(alertMessage));
	}

	// waits for the product comparison link (inside alert) to be visible
	public void waitForProductComparisonLinkToBeVisible() {
		// reuse the wait instance created in the constructor
		wait.until(ExpectedConditions.elementToBeClickable(productComparisonLink));
	}

	// waits for the Product Comparison page heading to be visible
	public void waitForProductComparisonHeadingToBeVisible() {
		// reuse the wait instance created in the constructor
		// The <h1> heading is not expected to be clickable; wait for visibility instead
		wait.until(ExpectedConditions.visibilityOf(productComparisonHeading));
	}

	// waits for products to be visible on the page
	public void waitForProductsToBeVisible() {
			// reuse the wait instance created in the constructor
			if (productContainers != null && !productContainers.isEmpty()) {
				wait.until(ExpectedConditions.elementToBeClickable(productContainers.get(0)));
			} else {
				// fallback: wait for any product container by locator
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class,'product-thumb') or contains(@class,'product-layout')]")));
			}
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
				// scroll the link into view and click using JS (avoids ElementClickInterceptedException
				// when other elements overlap or the link is not interactable by Selenium native click)
				JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", productComparisonLink);

				try {
					// try waiting until link is clickable first
					// reuse the wait instance created in the constructor
					wait.until(ExpectedConditions.elementToBeClickable(productComparisonLink));
					// prefer JS click which is more robust when overlays interfere
					jsExecutor.executeScript("arguments[0].click();", productComparisonLink);
				} catch (Exception e) {
					// fallback to JS click without waiting
					jsExecutor.executeScript("arguments[0].click();", productComparisonLink);
				}
	}

	// clicks the 'Add to Cart' button for the first product on the Special Offers page
	public void clickAddToCart() {
		if (addToCartButtons != null && !addToCartButtons.isEmpty()) {
			elementUtils.clickOnElements(addToCartButtons.get(0));
		}
	}

	// Clicks the Add to Cart button for a specific product by its name and then opens the product information page
	public void addToCartAndOpenProductInfoByName(String productName) {

		List<WebElement> products = driver.findElements(By.xpath("//div[contains(@class,'product-thumb') or contains(@class,'product-layout')]"));
		for (WebElement product : products) {
			try {
				WebElement nameElement = null;
				try {
					nameElement = product.findElement(By.xpath(".//h4//a | .//div[@class='caption']/h4/a"));
				} catch (Exception e) {
					// try alternate heading structure
					nameElement = product.findElement(By.xpath(".//h2//a | .//h3//a"));
				}
				String name = nameElement.getText().trim();
				if (name.equalsIgnoreCase(productName)) {
					// click Add to Cart inside the same product container
					try {
						WebElement addBtn = product.findElement(By.xpath(".//button[@data-original-title='Add to Cart' or contains(@onclick,'cart.add')]")).isDisplayed() ? product.findElement(By.xpath(".//button[@data-original-title='Add to Cart' or contains(@onclick,'cart.add')]") ) : null;
						if (addBtn != null) {
							// use JS click to be robust
							JavascriptExecutor js = (JavascriptExecutor) driver;
							js.executeScript("arguments[0].click();", addBtn);
						}
					} catch (Exception e) {
						// ignore add to cart click failure and continue
					}
					// after attempting to add to cart, navigate to product information page by clicking the product name
					try {
						JavascriptExecutor js = (JavascriptExecutor) driver;
						js.executeScript("arguments[0].scrollIntoView(true);", nameElement);
						js.executeScript("arguments[0].click();", nameElement);
					} catch (Exception e) {
						try {
							nameElement.click();
						} catch (Exception ex) {
							// ignore and continue
						}
					}
					break;
				}
			} catch (Exception e) {
				// continue searching other products
			}
		}
	}

	// waits for the product details page heading to be visible
	public void waitForProductHeadingToBeVisible() {
		// reuse the wait instance created in the constructor
		wait.until(ExpectedConditions.visibilityOf(productHeading));
	}

	// returns the product details page heading text
	public String getProductHeadingText() {
		return elementUtils.getTextOfElement(productHeading);
	}

	// checks whether the product details page heading matches the expected text (case-insensitive)
	public boolean isProductHeadingEqualTo(String expectedHeading) {
		String actual = getProductHeadingText();
		if (actual == null) return false;
		return actual.trim().equalsIgnoreCase(expectedHeading.trim());
	}

	// verifies whether the Product Comparison heading is visible on the page
	public boolean isProductComparisonHeadingVisible() {
		return elementUtils.isElementDisplayed(productComparisonHeading);
	}

}
