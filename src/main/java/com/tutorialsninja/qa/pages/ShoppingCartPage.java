package com.tutorialsninja.qa.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.tutorialsnija.qa.utils.ElementUtils;

public class ShoppingCartPage {

	WebDriver driver;
	ElementUtils elementUtils;
	
	public ShoppingCartPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		elementUtils = new ElementUtils(driver);
	}
	
	// Web Elements for Shopping Cart Page
	@FindBy(xpath = "//table[@class='table table-bordered']")
	private WebElement shoppingCartTable;
	
	// Shopping Cart table rows (products)
	@FindBy(xpath = "//table[@class='table table-bordered']//tbody//tr")
	private List<WebElement> cartTableRows;
	
	// Product name in cart table
	@FindBy(xpath = "(//table[@class='table table-bordered']//td//a)[2]")
	private List<WebElement> productNames;
	
	////table[@class='table table-bordered']//td//a
	// Product model in cart table
	@FindBy(xpath = "//*[@id=\"content\"]/form/div/table/tbody/tr/td[3]")
	private List<WebElement> productModels;
	
	////table[@class='table table-bordered']//tbody//tr//td[2]
	// Product quantity field
	@FindBy(xpath = "//input[@name='quantity']")
	private List<WebElement> quantityFields;
	
	// Product unit price
	@FindBy(xpath = "//table[@class='table table-bordered']//tbody//tr//td[4]")
	private List<WebElement> unitPrices;
	
	// Product total price
	@FindBy(xpath = "//table[@class='table table-bordered']//tbody//tr//td[5]")
	private List<WebElement> totalPrices;
	
	// Remove product button
	@FindBy(xpath = "//button[@type='submit']//i[@class='fa fa-times']")
	private List<WebElement> removeButtons;
	
	// Subtotal
	@FindBy(xpath = "//th[text()='Sub-Total']/following::td[1]")
	private WebElement subTotal;
	
	// Tax
	@FindBy(xpath = "//th[text()='Tax']/following::td[1]")
	private WebElement tax;
	
	// Total
	@FindBy(xpath = "//th[text()='Total']/following::td[1]")
	private WebElement total;
	
	// Checkout button
	@FindBy(xpath = "//a[@class='btn btn-primary']")
	private WebElement checkoutButton;
	
	// Continue Shopping button
	@FindBy(xpath = "//a[contains(@href, 'home')][@class='btn btn-default']")
	private WebElement continueShoppingButton;
	
	// Empty cart message
	@FindBy(xpath = "//p[text()='Your shopping cart is empty!']")
	private WebElement emptyCartMessage;
	
	// Update cart button
	@FindBy(xpath = "//button[@type='submit'][contains(text(), 'Update')]")
	private WebElement updateCartButton;
	
	// Action Methods
	
	/**
	 * Verify if Shopping Cart Table is displayed
	 * @return true if cart table is displayed, false otherwise
	 */
	public boolean isShoppingCartTableDisplayed() {
		return elementUtils.isElementDisplayed(shoppingCartTable);
	}
	
	/**
	 * Get the number of products in the shopping cart
	 * @return Number of products in cart
	 */
	public int getNumberOfProductsInCart() {
		return cartTableRows.size();
	}
	
	/**
	 * Get all product names in the shopping cart
	 * @return List of product names
	 */
	public List<String> getAllProductNames() {
		List<String> names = new java.util.ArrayList<>();
		for (WebElement element : productNames) {
			names.add(elementUtils.getTextOfElement(element));
		}
		return names;
	}
	
	/**
	 * Verify if a specific product exists in the cart by product name
	 * @param productName Name of the product to verify
	 * @return true if product is found in cart, false otherwise
	 */
	public boolean isProductInCart(String productName) {
		List<String> products = getAllProductNames();
		return products.stream().anyMatch(product -> product.trim().equals(productName.trim()));
	}
	
	/**
	 * Get product model for a specific product by index
	 * @param index Index of the product (0-based)
	 * @return Product model text
	 */
	public String getProductModel(int index) {
		if (index < productModels.size()) {
			return elementUtils.getTextOfElement(productModels.get(index));
		}
		return null;
	}
	
	/**
	 * Get product unit price for a specific product by index
	 * @param index Index of the product (0-based)
	 * @return Unit price text
	 */
	public String getProductUnitPrice(int index) {
		if (index < unitPrices.size()) {
			return elementUtils.getTextOfElement(unitPrices.get(index));
		}
		return null;
	}
	
	/**
	 * Get product total price for a specific product by index
	 * @param index Index of the product (0-based)
	 * @return Total price text
	 */
	public String getProductTotalPrice(int index) {
		if (index < totalPrices.size()) {
			return elementUtils.getTextOfElement(totalPrices.get(index));
		}
		return null;
	}
	
	/**
	 * Update quantity for a specific product
	 * @param index Index of the product (0-based)
	 * @param quantity New quantity to set
	 */
	public void updateProductQuantity(int index, String quantity) {
		if (index < quantityFields.size()) {
			elementUtils.enterTextIntoElement(quantityFields.get(index), quantity);
		}
	}
	
	/**
	 * Get quantity for a specific product
	 * @param index Index of the product (0-based)
	 * @return Current quantity value
	 */
	public String getProductQuantity(int index) {
		if (index < quantityFields.size()) {
			return quantityFields.get(index).getAttribute("value");
		}
		return null;
	}
	
	/**
	 * Click on Update Cart button to apply quantity changes
	 */
	public void clickOnUpdateCartButton() {
		elementUtils.clickOnElements(updateCartButton);
	}
	
	/**
	 * Remove a product from cart by index
	 * @param index Index of the product to remove (0-based)
	 */
	public void removeProductFromCart(int index) {
		if (index < removeButtons.size()) {
			elementUtils.clickOnElements(removeButtons.get(index));
		}
	}
	
	/**
	 * Get Subtotal value
	 * @return Subtotal text
	 */
	public String getSubTotal() {
		return elementUtils.getTextOfElement(subTotal);
	}
	
	/**
	 * Get Tax value
	 * @return Tax text
	 */
	public String getTax() {
		return elementUtils.getTextOfElement(tax);
	}
	
	/**
	 * Get Total value
	 * @return Total text
	 */
	public String getTotal() {
		return elementUtils.getTextOfElement(total);
	}
	
	/**
	 * Verify if Checkout button is displayed
	 * @return true if checkout button is displayed, false otherwise
	 */
	public boolean isCheckoutButtonDisplayed() {
		return elementUtils.isElementDisplayed(checkoutButton);
	}
	
	/**
	 * Click on Checkout button to proceed with checkout
	 */
	public void clickOnCheckoutButton() {
		elementUtils.clickOnElements(checkoutButton);
	}
	
	/**
	 * Verify if Continue Shopping button is displayed
	 * @return true if continue shopping button is displayed, false otherwise
	 */
	public boolean isContinueShoppingButtonDisplayed() {
		return elementUtils.isElementDisplayed(continueShoppingButton);
	}
	
	/**
	 * Click on Continue Shopping button
	 */
	public void clickOnContinueShoppingButton() {
		elementUtils.clickOnElements(continueShoppingButton);
	}
	
	/**
	 * Verify if Shopping Cart is empty
	 * @return true if cart is empty, false otherwise
	 */
	public boolean isCartEmpty() {
		return elementUtils.isElementDisplayed(emptyCartMessage);
	}
	
	/**
	 * Get the empty cart message text
	 * @return Empty cart message
	 */
	public String getEmptyCartMessage() {
		return elementUtils.getTextOfElement(emptyCartMessage);
	}
	
	/**
	 * Verify if a specific product exists in cart and get its details
	 * @param productName Name of the product to search
	 * @return true if product found, false otherwise
	 */
	public boolean verifyProductInCartWithDetails(String productName) {
		List<String> products = getAllProductNames();
		for (int i = 0; i < products.size(); i++) {
			if (products.get(i).trim().equals(productName.trim())) {
				return true;
			}
		}
		return false;
	}
	
}
