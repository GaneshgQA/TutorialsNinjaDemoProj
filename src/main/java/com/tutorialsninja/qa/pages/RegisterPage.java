package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.tutorialsnija.qa.utils.ElementUtils;

public class RegisterPage {

	WebDriver driver;

	ElementUtils elementUtils;

	@FindBy(id = "input-firstname")
	private WebElement firstNameField;

	@FindBy(id = "input-lastname")
	private WebElement lastNameField;

	@FindBy(id = "input-email")
	public WebElement emailField;

	@FindBy(id = "input-telephone")
	private WebElement telehoneField;

	@FindBy(id = "input-password")
	private WebElement passwordField;

	@FindBy(id = "input-confirm")
	public WebElement confirmPasswordField;

	@FindBy(name = "agree")
	private WebElement privacyPolicyCheckBoxField;

	@FindBy(xpath = "//input[@value='Continue']")
	private WebElement continueButton;

	@FindBy(xpath = "//input[@name='newsletter' and @value='1']")
	private WebElement yesNewLetterOption;

	@FindBy(xpath = "//div[contains(@class,'alert-dismissible')]")
	private WebElement duplicateEmailAddressWarningMessage;

	@FindBy(xpath = "//div[contains(@class,'alert-dismissible')]")
	private WebElement privacyPolicyWarningMessage;

	@FindBy(xpath = "//input[@id='input-firstname']/following-sibling::div")
	private WebElement firstNameWarningMessage;

	@FindBy(xpath = "//input[@id='input-lastname']/following-sibling::div")
	private WebElement lastNameWarningMessage;

	@FindBy(xpath = "//input[@id='input-email']/following-sibling::div")
	private WebElement emailAddressWarningMessage;

	@FindBy(xpath = "//input[@id='input-telephone']/following-sibling::div")
	private WebElement telephoneNumberWarningMessage;

	@FindBy(xpath = "//input[@id='input-password']/following-sibling::div")
	private WebElement passwordWarningMessage;

	@FindBy(xpath = "//a[text()='login page']")
	private WebElement loginPagelink;

	@FindBy(xpath = "(//a[text()='Login'])[2]")
	private WebElement rightSideLoginOption;

	@FindBy(xpath = "//ul[@class='breadcrumb']//a[text()='Register']")
	private WebElement registerPageBreadCrumb;

	@FindBy(xpath = "//div[@id='content']/h1")
	private WebElement registerPageHeading;

	public RegisterPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
		elementUtils = new ElementUtils(driver);
	}

	public String getRegitserPageHeading() {

		return registerPageHeading.getText();

	}

	public boolean isProperRegisterPageBreadCrumbDisplayed() {

		return elementUtils.isElementDisplayed(registerPageBreadCrumb);

	}

	public void enterFirstName(String firstNameText) {

		//firstNameField.sendKeys(firstNameText);
		elementUtils.enterTextIntoElement(firstNameField, firstNameText);

	}

	public void enterLastName(String lastNameText) {

		//lastNameField.sendKeys(lastNameText);
		elementUtils.enterTextIntoElement(lastNameField, lastNameText);

	}

	public void enterEmailAddress(String emailAddressText) {

		//emailField.sendKeys(emailAddressText);
		elementUtils.enterTextIntoElement(emailField, emailAddressText);

	}

	public void enterTelephoneNumber(String telephoneText) {

		//telehoneField.sendKeys(telephoneText);
		elementUtils.enterTextIntoElement(telehoneField, telephoneText);

	}

	public void enterPassword(String passwordText) {

		//passwordField.sendKeys(passwordText);
		elementUtils.enterTextIntoElement(passwordField, passwordText);

	}

	public void enterConfirmPassword(String confirmPasswordText) {

		//confirmPasswordField.sendKeys(confirmPasswordText);
		elementUtils.enterTextIntoElement(confirmPasswordField, confirmPasswordText);

	}

	public void selectPrivacyPolicy() {

		elementUtils.clickOnElements(privacyPolicyCheckBoxField);

	}

	public void clickOnContinueButton() {

		elementUtils.clickOnElements(continueButton);
	}

	public void selectYesNewsLetterOption() {

		elementUtils.clickOnElements(yesNewLetterOption);
	}

	public String retriveDuplicateEmailWarningMessage() {

		return elementUtils.getTextOfElement(duplicateEmailAddressWarningMessage);
	}

	public String retrievePrivacyPolicyWarningMessage() {

		return elementUtils.getTextOfElement(privacyPolicyWarningMessage);
	}

	public String retrieveFirstNameWarningMessage() {

		return elementUtils.getTextOfElement(firstNameWarningMessage);
	}

	public String retrieveLastNameWarningMessage() {

		return elementUtils.getTextOfElement(lastNameWarningMessage);
	}

	public String retriveEmailAddressWarningMessage() {

		return elementUtils.getTextOfElement(emailAddressWarningMessage);
	}

	public String retrievetelephoneNumberWarningMessage() {

		return elementUtils.getTextOfElement(telephoneNumberWarningMessage);
	}

	public String retrievePasswordWarningMessage() {

		return elementUtils.getTextOfElement(passwordWarningMessage);
	}

	public void clickOnLoginPageLink() {

		elementUtils.clickOnElements(loginPagelink);
	}

	public void clickOnRightSideLoginOption() {

		elementUtils.clickOnElements(rightSideLoginOption);
	}
}
