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
	@FindBy(xpath = "(//div[@class='row']//div//h4//a)[1]")
	private WebElement macbookProduct;
	////a[text()='MacBook']
	
	@FindBy(xpath = "(//div[@class='row']//div//h4//a)[2]")
	private WebElement iPhone;
	
	@FindBy(xpath = "(//div[@class='row']//div//h4//a)[3]")
	private WebElement AppleCinema30;
	
	@FindBy(xpath = "(//div[@class='row']//div//h4//a)[4]")
	private WebElement CanonEOS5D;
	
	// Web Element for Add to Cart button
	@FindBy(xpath = "//button[contains(@onclick, 'cart.add')]")
	private WebElement addToCartButton;
	////button[contains(@onclick, 'cart.add')]
	
	@FindBy(xpath = "//button[@id='button-cart']")
	private WebElement addToCartButtonAfterClickingOnProduct;
	
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
	
	public void clickOnAddToCartButtonAfterClickingOnProduct() {
		elementUtils.clickOnElements(addToCartButtonAfterClickingOnProduct);
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
	
	/**
	 * Verify if iPhone product is displayed
	 * @return true if iPhone is displayed, false otherwise
	 */
	public boolean isIPhoneDisplayed() {
		return elementUtils.isElementDisplayed(iPhone);
	}
	
	/**
	 * Click on iPhone product
	 */
	public void clickOnIPhone() {
		elementUtils.clickOnElements(iPhone);
	}
	
	/**
	 * Verify if Apple Cinema 30 product is displayed
	 * @return true if Apple Cinema 30 is displayed, false otherwise
	 */
	public boolean isAppleCinema30Displayed() {
		return elementUtils.isElementDisplayed(AppleCinema30);
	}
	
	/**
	 * Click on Apple Cinema 30 product
	 */
	public void clickOnAppleCinema30() {
		elementUtils.clickOnElements(AppleCinema30);
	}
	
	/**
	 * Verify if Canon EOS 5D product is displayed
	 * @return true if Canon EOS 5D is displayed, false otherwise
	 */
	public boolean isCanonEOS5DDisplayed() {
		return elementUtils.isElementDisplayed(CanonEOS5D);
	}
	
	/**
	 * Click on Canon EOS 5D product
	 */
	public void clickOnCanonEOS5D() {
		elementUtils.clickOnElements(CanonEOS5D);
	}
	
}
