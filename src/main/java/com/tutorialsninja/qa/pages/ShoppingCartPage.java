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
	@FindBy(xpath = "//div[@class='table-responsive']//table")
	private WebElement shoppingCartTable;
	////table[@class='table table-bordered']
	
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
	@FindBy(xpath = "//input[contains(@name,'quantity')]")
	private List<WebElement> quantityFields;
	////input[@name='quantity']
	
	// Product unit price
	@FindBy(xpath = "//table[@class='table table-bordered']//tbody//tr//td[5]")
	private List<WebElement> unitPrices;
	
	// Product total price
	@FindBy(xpath = "//table[@class='table table-bordered']//tbody//tr//td[5]")
	private List<WebElement> totalPrices;
	
	// Remove product button
	@FindBy(xpath = "(//span[@class='input-group-btn']//button)[3]")
	private List<WebElement> removeButtons;
	////button[@type='submit']//i[@class='fa fa-times']
	
	// Subtotal
	@FindBy(xpath = "(//table[@class='table table-bordered']//tr//td[2])[7]")
	private WebElement subTotal;
	////th[text()='Sub-Total']/following::td[1]
	
	// Tax
	@FindBy(xpath = "(//table[@class='table table-bordered']//tr//td[2])[8]")
	private WebElement tax;
	////th[text()='Tax']/following::td[1]
	
	// Total
	@FindBy(xpath = "(//table[@class='table table-bordered']//tr[4]//td[2])[2]")
	private WebElement total;
	////th[text()='Total']/following::td[1]
	
	// Checkout button
	@FindBy(xpath = "//a[@class='btn btn-primary']")
	private WebElement checkoutButton;
	
	// Continue Shopping button
	@FindBy(xpath = "//a[contains(@href, 'home')][@class='btn btn-default']")
	private WebElement continueShoppingButton;
	
	// Empty cart message
	@FindBy(xpath = "//div[@id='content']//p")
	private WebElement emptyCartMessage;
	////p[text()='Your shopping cart is empty!']
	
	// Update cart button
	@FindBy(xpath = "//button[@type='submit'][@class='btn btn-primary']")
	private WebElement updateCartButton;
	////button[@type='submit'][contains(text(), 'Update')]
	
	// Action Methods
	
	/**
	 * Verify if Shopping Cart Table is displayed
	 * @return true if cart table is displayed, false otherwise
	 */
	public boolean isShoppingCartTableDisplayed() {
		elementUtils.waitForPageLoad(15);
		elementUtils.waitForElementToBeVisible(shoppingCartTable, 15);
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
		elementUtils.waitForPageLoad(15);
		List<String> names = new java.util.ArrayList<>();
		for (WebElement element : productNames) {
			try {
				String name = elementUtils.getTextOfElementWithRetry(element, 3);
				if (!name.isEmpty()) {
					names.add(name);
				}
			} catch (Exception e) {
				// Skip element if retrieval fails
			}
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
			try {
				return elementUtils.getTextOfElementWithRetry(productModels.get(index), 3);
			} catch (Exception e) {
				return null;
			}
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
			try {
				return elementUtils.getTextOfElementWithRetry(unitPrices.get(index), 3);
			} catch (Exception e) {
				return null;
			}
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
			try {
				return elementUtils.getTextOfElementWithRetry(totalPrices.get(index), 3);
			} catch (Exception e) {
				return null;
			}
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
		elementUtils.waitForElementToBeClickable(updateCartButton, 15);
		elementUtils.clickOnElementWithRetry(updateCartButton, 3);
	}
	
	/**
	 * Remove a product from cart by index
	 * @param index Index of the product to remove (0-based)
	 */
	public void removeProductFromCart(int index) {
		if (index < removeButtons.size()) {
			elementUtils.clickOnElementWithRetry(removeButtons.get(index), 3);
			// Wait for page to refresh after removal
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
			// Wait for page load after removal
			elementUtils.waitForPageLoad(15);
		}
	}
	
	/**
	 * Remove all products from cart with proper wait handling
	 * This method handles DOM refresh after each removal
	 */
	public void removeAllProductsFromCart() {
		// Keep removing the first product until cart is empty or no more products
		int maxAttempts = 50; // Prevent infinite loop
		int attempts = 0;
		
		while (attempts < maxAttempts) {
			try {
				int productsCount = getNumberOfProductsInCart();
				if (productsCount <= 0) {
					// Cart is empty
					break;
				}
				
				// Always remove the first product (index 0)
				if (removeButtons != null && removeButtons.size() > 0) {
					elementUtils.clickOnElementWithRetry(removeButtons.get(0), 3);
					// Wait for page to refresh
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						Thread.currentThread().interrupt();
					}
					elementUtils.waitForPageLoad(15);
				} else {
					// No more remove buttons, cart might be empty
					break;
				}
			} catch (Exception e) {
				// If any error occurs, break the loop
				System.out.println("Error during removal: " + e.getMessage());
				break;
			}
			attempts++;
		}
	}
	
	/**
	 * Get Subtotal value
	 * @return Subtotal text
	 */
	public String getSubTotal() {
		try {
			elementUtils.waitForElementToBeVisible(subTotal, 10);
			return elementUtils.getTextOfElementWithRetry(subTotal, 3);
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * Get Tax value
	 * @return Tax text
	 */
	public String getTax() {
		try {
			elementUtils.waitForElementToBeVisible(tax, 10);
			return elementUtils.getTextOfElementWithRetry(tax, 3);
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * Get Total value
	 * @return Total text
	 */
	public String getTotal() {
		try {
			elementUtils.waitForElementToBeVisible(total, 10);
			return elementUtils.getTextOfElementWithRetry(total, 3);
		} catch (Exception e) {
			return null;
		}
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
		elementUtils.waitForElementToBeClickable(checkoutButton, 15);
		elementUtils.clickOnElementWithRetry(checkoutButton, 3);
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
		elementUtils.waitForElementToBeClickable(continueShoppingButton, 15);
		elementUtils.clickOnElementWithRetry(continueShoppingButton, 3);
	}
	
	/**
	 * Verify if Shopping Cart is empty
	 * @return true if cart is empty, false otherwise
	 */
	public boolean isCartEmpty() {
		try {
			// Wait up to 10 seconds for the empty message to appear
			elementUtils.waitForElementToBeVisible(emptyCartMessage, 10);
			return elementUtils.isElementDisplayed(emptyCartMessage);
		} catch (Exception e) {
			// Element not found or not visible, cart is not empty
			return false;
		}
	}
	
	/**
	 * Get the empty cart message text
	 * @return Empty cart message
	 */
	public String getEmptyCartMessage() {
		try {
			elementUtils.waitForElementToBeVisible(emptyCartMessage, 10);
			return elementUtils.getTextOfElementWithRetry(emptyCartMessage, 3);
		} catch (Exception e) {
			return null;
		}
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
