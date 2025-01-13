package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {

	WebDriver driver;

	@FindBy(id = "input-firstname")
	private WebElement firstNameField;

	@FindBy(id = "input-lastname")
	private WebElement lastNameField;

	@FindBy(id = "input-email")
	private WebElement emailField;

	@FindBy(id = "input-telephone")
	private WebElement telehoneField;

	@FindBy(id = "input-password")
	private WebElement passwordField;

	@FindBy(id = "input-confirm")
	private WebElement confirmPasswordField;

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
	
	public RegisterPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	public void enterFirstName(String firstNameText) {

		firstNameField.sendKeys(firstNameText);

	}

	public void enterLastName(String lastNameText) {

		lastNameField.sendKeys(lastNameText);

	}

	public void enterEmailAddress(String emailAddressText) {

		emailField.sendKeys(emailAddressText);

	}

	public void enterTelephoneNumber(String telephoneText) {

		telehoneField.sendKeys(telephoneText);

	}

	public void enterPassword(String passwordText) {

		passwordField.sendKeys(passwordText);

	}

	public void enterConfirmPassword(String confirmPasswordText) {

		confirmPasswordField.sendKeys(confirmPasswordText);

	}

	public void selectPrivacyPolicy() {

		privacyPolicyCheckBoxField.click();

	}

	public void clickOnContinueButton() {

		continueButton.click();

	}
	
	public void selectYesNewsLetterOption() {
		
		yesNewLetterOption.click();
		
	}
	
	public String retriveDuplicateEmailWarningMessage() {
		
		String duplicateEmailWarningText = duplicateEmailAddressWarningMessage.getText();
		
		return duplicateEmailWarningText;
		
	}
	
	public String retrievePrivacyPolicyWarningMessage() {
		
		String privacyPolicyWarningText = privacyPolicyWarningMessage.getText();
		
		return privacyPolicyWarningText;
	}
	
	public String retrieveFirstNameWarningMessage() {
		
		String firstNameWarningMessageText = firstNameWarningMessage.getText();
		
		return firstNameWarningMessageText;
		
	}
	
	public String retrieveLastNameWarningMessage() {
		
		String lastNameWarningMessageText = lastNameWarningMessage.getText();
		
		return lastNameWarningMessageText;
		
	}

	public String retriveEmailAddressWarningMessage() {
		
		String emailAddressWarningMessageText = emailAddressWarningMessage.getText();
		
		return emailAddressWarningMessageText;
		
	}
	
	public String retrievetelephoneNumberWarningMessage() {
		
		String telephoneNumberWarningMessageText = telephoneNumberWarningMessage.getText();
		
		return telephoneNumberWarningMessageText;
		
	}
	
	public String retrievePasswordWarningMessage() {
		
		String passwordWarningMessageText = passwordWarningMessage.getText();
		
		return passwordWarningMessageText;
	}
}
