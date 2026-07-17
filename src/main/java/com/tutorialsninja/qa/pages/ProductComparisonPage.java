package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.tutorialsnija.qa.utils.ElementUtils;

public class ProductComparisonPage {

	WebDriver driver;
	ElementUtils elementUtils;

	public ProductComparisonPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
		elementUtils = new ElementUtils(driver);
	}

	@FindBy(xpath = "//ul[@class='breadcrumb']//a[text()='Product Comparison']")
	private WebElement productComparisonBreadCrumb;

	@FindBy(xpath = "//a/strong[text()='HP LP3065']")
	private WebElement productDetailsOfHP;

	public boolean didDetailsOfTheProductGotAddedForComparison() {

		return elementUtils.isElementDisplayed(productDetailsOfHP);

	}

	public boolean didWeNavigateToProductComparisionPage() {

		return elementUtils.isElementDisplayed(productComparisonBreadCrumb);

	}
}
