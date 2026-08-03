package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.tutorialsnija.qa.utils.ElementUtils;

public class AddToCartWithoutLoginPage {

	WebDriver driver;
	ElementUtils elementUtils;
	
	public AddToCartWithoutLoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		elementUtils = new ElementUtils(driver);
	}
	
	// Web Elements for MacBook product
	@FindBy(xpath = "//a[text()='MacBook']")
	private WebElement macbookProduct;
	
	// Web Element for Add to Cart button
	@FindBy(xpath = "//button[contains(@onclick, 'cart.add')]")
	private WebElement addToCartButton;
	
	// Web Element for success message
	@FindBy(xpath = "//div[contains(@class, 'alert alert-success alert-dismissible')]")
	private WebElement successMessage;
	
	// Web Element for shopping cart link
	@FindBy(xpath = "//span[text()='Shopping Cart']")
	private WebElement shoppingCartLink;
	
	@FindBy(xpath = "//a[text()='Shopping Cart']")
	private WebElement shoppingCartBreadcrumb;
	// Action Methods
	
	public boolean isShoppingCartBreadcrumbDisplayed() {
		return elementUtils.isElementDisplayed(shoppingCartBreadcrumb);
	}
	/**
	 * Check if MacBook product is available and then click on Add to Cart button
	 */
	public void clickOnMacbookProduct() {
		if (isMacbookProductDisplayed()) {
			elementUtils.clickOnElements(addToCartButton);
		}
	}
	
	/**
	 * Click on Add to Cart button to add MacBook to cart
	 */
	public void clickOnAddToCartButton() {
		elementUtils.clickOnElements(addToCartButton);
	}
	
	/**
	 * Get the success message text after adding product to cart
	 * @return Success message text
	 */
	public String getTextOfSuccessMessage() {
		return elementUtils.getTextOfElement(successMessage);
	}
	
	/**
	 * Click on Shopping Cart link to navigate to shopping cart page
	 */
	public void clickOnShoppingCartLink() {
		
		elementUtils.clickOnElements(shoppingCartLink);
	}
	
	/**
	 * Verify if MacBook product is displayed
	 * @return true if MacBook is displayed, false otherwise
	 */
	public boolean isMacbookProductDisplayed() {
		return elementUtils.isElementDisplayed(macbookProduct);
	}
	
	/**
	 * Verify if Add to Cart button is displayed
	 * @return true if Add to Cart button is displayed, false otherwise
	 */
	public boolean isAddToCartButtonDisplayed() {
		return elementUtils.isElementDisplayed(addToCartButton);
	}
	
	/**
	 * Verify if success message is displayed
	 * @return true if success message is displayed, false otherwise
	 */
	public boolean isSuccessMessageDisplayed() {
		return elementUtils.isElementDisplayed(successMessage);
	}
	
	/**
	 * Verify if Shopping Cart link is displayed
	 * @return true if Shopping Cart link is displayed, false otherwise
	 */
	public boolean isShoppingCartLinkDisplayed() {
		return elementUtils.isElementDisplayed(shoppingCartLink);
	}
	
}
