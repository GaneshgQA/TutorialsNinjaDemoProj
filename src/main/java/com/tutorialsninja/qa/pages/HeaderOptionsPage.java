package com.tutorialsninja.qa.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.tutorialsnija.qa.utils.ElementUtils;

public class HeaderOptionsPage {

	WebDriver driver;
	ElementUtils elementUtils;

	public HeaderOptionsPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
		elementUtils = new ElementUtils(driver);
	}

	@FindBy(xpath = "//a/i[@class='fa fa-phone']")
	private WebElement phoneIconOption;

	@FindBy(xpath = "//span[contains(text(),'Wish List')]")
	private WebElement wishListOption;

	@FindBy(xpath = "//span[text()='Shopping Cart']")
	private WebElement shoppingCartOption;

	@FindBy(xpath = "//span[text()='Checkout']")
	private WebElement checkOutOption;

	@FindBy(xpath = "//button[@class='btn btn-default btn-lg']")
	private WebElement searchButton;

	@FindBy(xpath = "//ul[@class='breadcrumb']//a[text()='Account']")
	private WebElement accountBreadCrumb;

	@FindBy(xpath = "//ul[@class='breadcrumb']//i[@class='fa fa-home']")
	private WebElement homeBreadCrumb;

	// ul[@class='breadcrumb']//i[@class='fa fa-home']

	@FindBy(xpath = "//span[text()='My Account']")
	private WebElement myAccountDropMenu;

	@FindBy(xpath = "//ul[@class='dropdown-menu dropdown-menu-right']//li//a[text()='Logout']")
	private WebElement logOutOption;

	public void selectLogOutOption() {

		elementUtils.clickOnElements(logOutOption);

	}

	public void selectMyAccountDropMenu() {

		elementUtils.clickOnElements(myAccountDropMenu);

	}

	public boolean isLogoutOptionAvailable() {

		return elementUtils.isElementDisplayed(logOutOption);
	}

	public void clickOnHomeBreadCrumb() {

		/*
		 * WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		 * wait.until(ExpectedConditions.elementToBeClickable(homeBreadCrumb));
		 * homeBreadCrumb.click();
		 */

		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", homeBreadCrumb);
		
		/*
		 * Actions actions = new Actions(driver); Thread.sleep(5000);
		 * actions.moveToElement(homeBreadCrumb).click().perform();
		 */

		// elementUtils.clickOnElements(homeBreadCrumb);
	}
}
