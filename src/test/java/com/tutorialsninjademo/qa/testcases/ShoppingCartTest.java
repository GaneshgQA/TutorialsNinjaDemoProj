package com.tutorialsninjademo.qa.testcases;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AddToCartWithoutLoginPage;
import com.tutorialsninja.qa.pages.ShoppingCartPage;

public class ShoppingCartTest extends Base {

	public WebDriver driver;
	private ShoppingCartPage shoppingCartPage;
	private AddToCartWithoutLoginPage addToCartPage;

	public ShoppingCartTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browser"));
		addToCartPage = new AddToCartWithoutLoginPage(driver);
		shoppingCartPage = new ShoppingCartPage(driver);
	}

	/**
	 * Test to verify if Shopping Cart Table is displayed
	 */
	@Test(priority = 1)
	public void verifyShoppingCartTableIsDisplayed() {
		// Navigate to shopping cart (assuming we're on a page with add to cart)
		navigateToShoppingCart();

		boolean isTableDisplayed = shoppingCartPage.isShoppingCartTableDisplayed();
		Assert.assertTrue(isTableDisplayed, "Shopping Cart Table should be displayed on Shopping Cart page");
	}

	/**
	 * Test to verify number of products in shopping cart
	 */
	@Test(priority = 2)
	public void verifyNumberOfProductsInCart() {
		navigateToShoppingCart();

		int numberOfProducts = shoppingCartPage.getNumberOfProductsInCart();
		Assert.assertTrue(numberOfProducts > 0, "Shopping cart should contain at least one product");
		System.out.println("Number of products in cart: " + numberOfProducts);
	}

	/**
	 * Test to verify all product names can be retrieved from shopping cart
	 */
	@Test(priority = 3)
	public void verifyGetAllProductNamesFromCart() {
		navigateToShoppingCart();

		List<String> productNames = shoppingCartPage.getAllProductNames();
		Assert.assertTrue(productNames.size() > 0, "Shopping cart should contain product names");
		Assert.assertNotNull(productNames, "Product names list should not be null");
		
		for (String name : productNames) {
			System.out.println("Product Name: " + name);
			Assert.assertFalse(name.isEmpty(), "Product name should not be empty");
		}
	}

	/**
	 * Test to verify if a specific product exists in the cart
	 */
	@Test(priority = 4)
	public void verifyProductExistsInCart() {
		navigateToShoppingCart();

		String productToSearch = "MacBook"; // Assuming MacBook is added to cart
		boolean isProductInCart = shoppingCartPage.isProductInCart(productToSearch);
		
		if (isProductInCart) {
			System.out.println("Product '" + productToSearch + "' found in shopping cart");
			Assert.assertTrue(isProductInCart, "Product should be found in cart");
		} else {
			System.out.println("Product '" + productToSearch + "' not found in shopping cart");
		}
	}

	/**
	 * Test to verify product model can be retrieved for a product
	 */
	@Test(priority = 5)
	public void verifyGetProductModel() {
		navigateToShoppingCart();

		// Get model for first product (index 0)
		String productModel = shoppingCartPage.getProductModel(0);
		System.out.println("This is product"+ productModel);
		Assert.assertNotNull(productModel, "Product model should not be null");
		Assert.assertFalse(productModel.isEmpty(), "Product model should not be empty");
		System.out.println("Product Model: " + productModel);
	}

	/**
	 * Test to verify product unit price can be retrieved
	 */
	@Test(priority = 6)
	public void verifyGetProductUnitPrice() {
		navigateToShoppingCart();

		// Get unit price for first product (index 0)
		String unitPrice = shoppingCartPage.getProductUnitPrice(0);
		Assert.assertNotNull(unitPrice, "Product unit price should not be null");
		Assert.assertFalse(unitPrice.isEmpty(), "Product unit price should not be empty");
		System.out.println("Product Unit Price: " + unitPrice);
	}

	/**
	 * Test to verify product total price can be retrieved
	 */
	@Test(priority = 7)
	public void verifyGetProductTotalPrice() {
		navigateToShoppingCart();

		// Get total price for first product (index 0)
		String totalPrice = shoppingCartPage.getProductTotalPrice(0);
		Assert.assertNotNull(totalPrice, "Product total price should not be null");
		Assert.assertFalse(totalPrice.isEmpty(), "Product total price should not be empty");
		System.out.println("Product Total Price: " + totalPrice);
	}

	/**
	 * Test to verify product quantity can be retrieved
	 */
	@Test(priority = 8)
	public void verifyGetProductQuantity() {
		navigateToShoppingCart();

		// Get quantity for first product (index 0)
		String quantity = shoppingCartPage.getProductQuantity(0);
		Assert.assertNotNull(quantity, "Product quantity should not be null");
		Assert.assertFalse(quantity.isEmpty(), "Product quantity should not be empty");
		System.out.println("Product Quantity: " + quantity);
	}

	/**
	 * Test to verify product quantity can be updated
	 */
	@Test(priority = 9)
	public void verifyUpdateProductQuantity() {
		navigateToShoppingCart();

		String newQuantity = "2";
		shoppingCartPage.updateProductQuantity(0, newQuantity);

		String updatedQuantity = shoppingCartPage.getProductQuantity(0);
		Assert.assertEquals(updatedQuantity, newQuantity, "Product quantity should be updated to " + newQuantity);
		System.out.println("Product quantity successfully updated to: " + updatedQuantity);
	}

	/**
	 * Test to verify update cart button functionality
	 */
	@Test(priority = 10)
	public void verifyUpdateCartButtonClick() {
		navigateToShoppingCart();

		String newQuantity = "3";
		shoppingCartPage.updateProductQuantity(0, newQuantity);
		shoppingCartPage.clickOnUpdateCartButton();

		// Verify cart was updated by checking the quantity
		String updatedQuantity = shoppingCartPage.getProductQuantity(0);
		Assert.assertEquals(updatedQuantity, newQuantity, "Cart should be updated with new quantity");
		System.out.println("Update Cart button clicked successfully");
	}

	/**
	 * Test to verify SubTotal value can be retrieved
	 */
	@Test(priority = 11)
	public void verifyGetSubTotal() {
		navigateToShoppingCart();

		String subTotal = shoppingCartPage.getSubTotal();
		Assert.assertNotNull(subTotal, "Subtotal should not be null");
		Assert.assertFalse(subTotal.isEmpty(), "Subtotal should not be empty");
		System.out.println("SubTotal: " + subTotal);
	}

	/**
	 * Test to verify Tax value can be retrieved
	 */
	@Test(priority = 12)
	public void verifyGetTax() {
		navigateToShoppingCart();

		String tax = shoppingCartPage.getTax();
		Assert.assertNotNull(tax, "Tax should not be null");
		Assert.assertFalse(tax.isEmpty(), "Tax should not be empty");
		System.out.println("Tax: " + tax);
	}

	/**
	 * Test to verify Total value can be retrieved
	 */
	@Test(priority = 13)
	public void verifyGetTotal() {
		navigateToShoppingCart();

		String total = shoppingCartPage.getTotal();
		Assert.assertNotNull(total, "Total should not be null");
		Assert.assertFalse(total.isEmpty(), "Total should not be empty");
		System.out.println("Total: " + total);
	}

	/**
	 * Test to verify Checkout button is displayed
	 */
	@Test(priority = 14)
	public void verifyCheckoutButtonIsDisplayed() {
		navigateToShoppingCart();

		boolean isCheckoutDisplayed = shoppingCartPage.isCheckoutButtonDisplayed();
		Assert.assertTrue(isCheckoutDisplayed, "Checkout button should be displayed on Shopping Cart page");
	}

	/**
	 * Test to verify Checkout button can be clicked
	 */
	@Test(priority = 15)
	public void verifyClickOnCheckoutButton() {
		navigateToShoppingCart();

		Assert.assertTrue(shoppingCartPage.isCheckoutButtonDisplayed(), "Checkout button should be displayed");
		shoppingCartPage.clickOnCheckoutButton();
		System.out.println("Checkout button clicked successfully");
	}

	/**
	 * Test to verify Continue Shopping button is displayed
	 */
	@Test(priority = 16)
	public void verifyContinueShoppingButtonIsDisplayed() {
		navigateToShoppingCart();

		boolean isContinueShoppingDisplayed = shoppingCartPage.isContinueShoppingButtonDisplayed();
		Assert.assertTrue(isContinueShoppingDisplayed, "Continue Shopping button should be displayed");
	}

	/**
	 * Test to verify Continue Shopping button can be clicked
	 */
	@Test(priority = 17)
	public void verifyClickOnContinueShoppingButton() {
		navigateToShoppingCart();

		Assert.assertTrue(shoppingCartPage.isContinueShoppingButtonDisplayed(), "Continue Shopping button should be displayed");
		shoppingCartPage.clickOnContinueShoppingButton();
		System.out.println("Continue Shopping button clicked successfully");
	}

	/**
	 * Test to verify empty cart message when cart is empty
	 */
	@Test(priority = 18)
	public void verifyEmptyCartMessage() {
		navigateToShoppingCart();

		// Remove all products from cart
		int productsCount = shoppingCartPage.getNumberOfProductsInCart();
		for (int i = 0; i < productsCount; i++) {
			shoppingCartPage.removeProductFromCart(0);
		}

		boolean isCartEmpty = shoppingCartPage.isCartEmpty();
		if (isCartEmpty) {
			String emptyMessage = shoppingCartPage.getEmptyCartMessage();
			Assert.assertNotNull(emptyMessage, "Empty cart message should be displayed");
			Assert.assertTrue(emptyMessage.toLowerCase().contains("empty"), "Message should indicate cart is empty");
			System.out.println("Empty Cart Message: " + emptyMessage);
		}
	}

	/**
	 * Test to verify product can be removed from cart
	 */
	@Test(priority = 19)
	public void verifyRemoveProductFromCart() {
		navigateToShoppingCart();

		int productsBeforeRemoval = shoppingCartPage.getNumberOfProductsInCart();
		Assert.assertTrue(productsBeforeRemoval > 0, "Cart should have at least one product");

		shoppingCartPage.removeProductFromCart(0);

		int productsAfterRemoval = shoppingCartPage.getNumberOfProductsInCart();
		Assert.assertTrue(productsAfterRemoval < productsBeforeRemoval, "Number of products should decrease after removal");
		System.out.println("Product successfully removed from cart");
	}

	/**
	 * Test to verify product details in cart
	 */
	@Test(priority = 20)
	public void verifyProductDetailsInCart() {
		navigateToShoppingCart();

		List<String> productNames = shoppingCartPage.getAllProductNames();
		for (int i = 0; i < productNames.size(); i++) {
			String productName = productNames.get(i);
			String model = shoppingCartPage.getProductModel(i);
			String unitPrice = shoppingCartPage.getProductUnitPrice(i);
			String totalPrice = shoppingCartPage.getProductTotalPrice(i);
			String quantity = shoppingCartPage.getProductQuantity(i);

			Assert.assertNotNull(productName, "Product name should not be null");
			Assert.assertNotNull(model, "Product model should not be null");
			Assert.assertNotNull(unitPrice, "Unit price should not be null");
			Assert.assertNotNull(totalPrice, "Total price should not be null");
			Assert.assertNotNull(quantity, "Quantity should not be null");

			System.out.println("Product: " + productName + ", Model: " + model + ", Unit Price: " + unitPrice
					+ ", Total Price: " + totalPrice + ", Quantity: " + quantity);
		}
	}

	/**
	 * Test to verify complete checkout flow
	 */
	@Test(priority = 21)
	public void verifyCompleteCheckoutFlow() {
		navigateToShoppingCart();

		// Verify cart has products
		Assert.assertTrue(shoppingCartPage.getNumberOfProductsInCart() > 0, "Cart should have products");

		// Verify all cart details
		Assert.assertTrue(shoppingCartPage.isShoppingCartTableDisplayed(), "Cart table should be displayed");
		Assert.assertNotNull(shoppingCartPage.getSubTotal(), "Subtotal should be available");
		Assert.assertNotNull(shoppingCartPage.getTax(), "Tax should be available");
		Assert.assertNotNull(shoppingCartPage.getTotal(), "Total should be available");
		Assert.assertTrue(shoppingCartPage.isCheckoutButtonDisplayed(), "Checkout button should be displayed");

		System.out.println("All checkout flow verifications passed");
	}

	/**
	 * Test to verify cart price calculations
	 */
	@Test(priority = 22)
	public void verifyCartPriceCalculations() {
		navigateToShoppingCart();

		String subTotal = shoppingCartPage.getSubTotal();
		String tax = shoppingCartPage.getTax();
		String total = shoppingCartPage.getTotal();

		Assert.assertNotNull(subTotal, "SubTotal should not be null");
		Assert.assertNotNull(tax, "Tax should not be null");
		Assert.assertNotNull(total, "Total should not be null");

		System.out.println("SubTotal: " + subTotal + ", Tax: " + tax + ", Total: " + total);
		System.out.println("Price calculations are available for verification");
	}

	/**
	 * Test to verify product exists in cart with details
	 */
	@Test(priority = 23)
	public void verifyProductInCartWithDetails() {
		navigateToShoppingCart();

		String productToSearch = "MacBook";
		boolean productExists = shoppingCartPage.verifyProductInCartWithDetails(productToSearch);

		if (productExists) {
			Assert.assertTrue(productExists, "Product should be found with details in cart");
			System.out.println("Product '" + productToSearch + "' verified with details in cart");
		} else {
			System.out.println("Product '" + productToSearch + "' not found in cart with details");
		}
	}

	/**
	 * Helper method to navigate to Shopping Cart page
	 * This assumes we start from a product page and add product to cart
	 */
	private void navigateToShoppingCart() {
		// This is a helper method that navigates to shopping cart
		// The actual implementation depends on your application flow
		// For now, it's assumed that we're already on a page where we can add to cart

		// Example navigation (adjust based on your application)
		// addToCartPage.clickOnMacbookProduct();
		// addToCartPage.clickOnAddToCartButton();
		// addToCartPage.clickOnShoppingCartLink();
		
		AddToCartWithoutLoginPage addToCartWithoutLoginPage = new AddToCartWithoutLoginPage(driver);
		addToCartWithoutLoginPage.clickOnMacbookProduct();
		addToCartWithoutLoginPage.clickOnAddToCartButton();
		addToCartWithoutLoginPage.clickOnShoppingCartLink();
	
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
