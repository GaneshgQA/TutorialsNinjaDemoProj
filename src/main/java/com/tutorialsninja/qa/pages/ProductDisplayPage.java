package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.tutorialsnija.qa.utils.ElementUtils;

public class ProductDisplayPage {

	WebDriver driver;
	ElementUtils elementUtils;

	public ProductDisplayPage(WebDriver driver) {

		this.driver = driver;
		elementUtils = new ElementUtils(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//ul[@class='thumbnails']")
	private WebElement thumbNailsSection;

	@FindBy(xpath = "//button[contains(@onclick,'compare')]")
	private WebElement compareThisProduct;

	@FindBy(xpath = "//div[@class='alert alert-success alert-dismissible']")
	private WebElement successMessage;

	@FindBy(xpath = "//a[text()='product comparison']")
	private WebElement productComparisonLinkInSuccessMessage;

	public void clickOnProductComparisonLinkInSuccessMessage() {

		elementUtils.clickOnElements(productComparisonLinkInSuccessMessage);

	}

	public String getSuccessMessageForCompareThisProductOption() {

		return elementUtils.getTextOfElement(successMessage);

	}

	public void selectCompareThisProductOption() {

		elementUtils.clickOnElements(compareThisProduct);

	}

	public String getToolTipForCompareThisProductOption() {

		return elementUtils.getToolTip(compareThisProduct);

	}

	public boolean didWeNavigateToProuctDisplayPage() {

		return elementUtils.isElementDisplayed(thumbNailsSection);

	}

}
