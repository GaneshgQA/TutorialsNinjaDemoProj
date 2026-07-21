package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.tutorialsnija.qa.utils.ElementUtils;

public class ProductCategoryPage extends ProductDisplayPage {

	WebDriver driver;
	ElementUtils elementUtils;

	public ProductCategoryPage(WebDriver driver) {
		
		super(driver);

		this.driver = driver;
		PageFactory.initElements(driver, this);
		elementUtils = new ElementUtils(driver);

	}

	
}
