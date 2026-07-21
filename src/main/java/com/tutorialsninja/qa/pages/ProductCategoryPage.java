package com.tutorialsninja.qa.pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.tutorialsnija.qa.utils.ElementUtils;

public class ProductCategoryPage extends ProductDisplayPage {

	WebDriver driver;
	ElementUtils elementUtils;
	WebDriverWait wait;

	public ProductCategoryPage(WebDriver driver) {

		super(driver);

		this.driver = driver;
		PageFactory.initElements(driver, this);
		elementUtils = new ElementUtils(driver);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	}

	@FindBy(xpath = "//div[contains(@class,'product-layout product-list')]//h4//a")
	private List<WebElement> productListInProductCatagoryPage;

	
	public List<String> getNameOfTheProductInProductCatagoryPage() {

		wait.until(ExpectedConditions.visibilityOfAllElements(productListInProductCatagoryPage));

		List<String> productNames = new ArrayList<>();

		for (WebElement productList : productListInProductCatagoryPage) {

			String text = elementUtils.getTextOfElement(productList);
			System.out.println(text);
			productNames.add(text);
		}

		return productNames;

	}

}
