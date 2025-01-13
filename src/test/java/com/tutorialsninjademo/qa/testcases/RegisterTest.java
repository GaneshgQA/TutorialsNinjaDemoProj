package com.tutorialsninjademo.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsnija.qa.utils.Utilities;
import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountSuccessPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.RegisterPage;

public class RegisterTest extends Base{

	public WebDriver driver;

	public RegisterTest() {
		
		super();
		
	}
	@BeforeMethod
	public void setUp() {
		
		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browser"));
		HomePage homePage = new HomePage(driver);
		homePage.clickOnMyAccount();
		homePage.clickRegisterOption();
		//driver.findElement(By.xpath("//a[@title='My Account']")).click();
		//driver.findElement(By.linkText("Register")).click();

	}

	@AfterMethod
	public void tearDown() {

		driver.quit();

	}

	@Test(priority = 1)
	public void verifyRegisteringAnAccountWithMandatoryFields() {

		RegisterPage registerPage = new RegisterPage(driver);
		registerPage.enterFirstName(dataProp.getProperty("firstName"));
		registerPage.enterLastName(dataProp.getProperty("lastName"));
		registerPage.enterEmailAddress(Utilities.generateEmailWithTimeStamp());
		registerPage.enterTelephoneNumber(dataProp.getProperty("telephone"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerPage.selectPrivacyPolicy();
		registerPage.clickOnContinueButton();
		
//		driver.findElement(By.id("input-firstname")).sendKeys(dataProp.getProperty("firstName"));
//		driver.findElement(By.id("input-lastname")).sendKeys(dataProp.getProperty("lastName"));
//		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
//		driver.findElement(By.id("input-telephone")).sendKeys(dataProp.getProperty("telephone"));
//		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
//		driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validPassword"));
//		driver.findElement(By.name("agree")).click();
//		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		
		AccountSuccessPage accountSuccessPage = new AccountSuccessPage(driver);
		String actualSuccessMessage = accountSuccessPage.retriveAccountSuccessPageHeading();
		String expectedSuccessMessage = dataProp.getProperty("AccountSuccessfullyCreationMessage");
		Assert.assertEquals(actualSuccessMessage, expectedSuccessMessage,
				"Expected acct success message is not displayed");

	}

	@Test(priority = 2)
	public void registeringAnAccountWithAllField() {

		RegisterPage registerPage = new RegisterPage(driver);
		registerPage.enterFirstName(dataProp.getProperty("firstName"));
		registerPage.enterLastName(dataProp.getProperty("lastName"));
		registerPage.enterEmailAddress(Utilities.generateEmailWithTimeStamp());
		registerPage.enterTelephoneNumber(dataProp.getProperty("telephone"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerPage.selectYesNewsLetterOption();
		registerPage.selectPrivacyPolicy();
		registerPage.clickOnContinueButton();
		
		AccountSuccessPage accountSucessPage = new AccountSuccessPage(driver);
	
//		driver.findElement(By.id("input-firstname")).sendKeys(dataProp.getProperty("firstName"));
//		driver.findElement(By.id("input-lastname")).sendKeys(dataProp.getProperty("lastName"));
//		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
//		driver.findElement(By.id("input-telephone")).sendKeys(dataProp.getProperty("telephone"));
//		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
//		driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validPassword"));
//		driver.findElement(By.xpath("//input[@name='newsletter' and @value='1']")).click();
//		driver.findElement(By.name("agree")).click();
//		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		
		String actualSuccessMessage = accountSucessPage.retriveAccountSuccessPageHeading();
		String expectedSuccessMessage = dataProp.getProperty("AccountSuccessfullyCreationMessage");
		Assert.assertEquals(actualSuccessMessage, expectedSuccessMessage,
				"Expected acct success message is not displayed");

	}

	@Test(priority = 3)
	public void verifyRegisteringAnAccountWithExistingEmailId() {
		
		RegisterPage registerPage = new RegisterPage(driver);
		registerPage.enterFirstName(dataProp.getProperty("firstName"));
		registerPage.enterLastName(dataProp.getProperty("lastName"));
		registerPage.enterEmailAddress(dataProp.getProperty("duplicateEmail"));
		registerPage.enterTelephoneNumber(dataProp.getProperty("telephone"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerPage.selectYesNewsLetterOption();
		registerPage.selectPrivacyPolicy();
		registerPage.clickOnContinueButton();

//		driver.findElement(By.id("input-firstname")).sendKeys(dataProp.getProperty("firstName"));
//		driver.findElement(By.id("input-lastname")).sendKeys(dataProp.getProperty("lastName"));
//		driver.findElement(By.id("input-email")).sendKeys(dataProp.getProperty("duplicateEmail"));
//		driver.findElement(By.id("input-telephone")).sendKeys(dataProp.getProperty("telephone"));
//		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
//		driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validPassword"));
//		driver.findElement(By.xpath("//input[@name='newsletter' and @value='1']")).click();
//		driver.findElement(By.name("agree")).click();
//		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		
		String actualDuplicateEmailWarning = registerPage.retriveDuplicateEmailWarningMessage();
		Assert.assertTrue(actualDuplicateEmailWarning.contains(dataProp.getProperty("dupllicateEmailWarning")),
				"Duplciate email warning message is not displayed");

	}

	@Test(priority = 4)
	public void verifyRegisteringAnAccountWithoutProvidingAnyDetails() {
		
		RegisterPage registerPage = new RegisterPage(driver);
		registerPage.clickOnContinueButton();
		
		//driver.findElement(By.xpath("//input[@value='Continue']")).click();

		String actualPrivacyPolicyWarning = registerPage.retrievePrivacyPolicyWarningMessage();
		Assert.assertTrue(actualPrivacyPolicyWarning.contains(dataProp.getProperty("privacyPolicyWarning")),
				"Privacy policy warning is not displayed");
		String actualFirstNameWarning = registerPage.retrieveFirstNameWarningMessage();
		Assert.assertTrue(actualFirstNameWarning.equals(dataProp.getProperty("fisrtNameWarning")),
				"First name warning message is not displayed");
		String actualLastNameWarning = registerPage.retrieveLastNameWarningMessage();
		Assert.assertTrue(actualLastNameWarning.contains(dataProp.getProperty("lastNameWarning")),
				"Last name warning message is not displayed");
		String actualEmailWarningMessage = registerPage.retriveEmailAddressWarningMessage();
		Assert.assertTrue(actualEmailWarningMessage.contains(dataProp.getProperty("emailAddressWarning")),
				"Actual email warning message is not displayed");
		String actualTelephoneWarningMessage = registerPage.retrievetelephoneNumberWarningMessage();
		Assert.assertTrue(actualTelephoneWarningMessage.contains(dataProp.getProperty("telephoneWarning")),
				"Actual telephone warning message is not displayed");
		String actualPasswordWarningMessage = registerPage.retrievePasswordWarningMessage();
		Assert.assertTrue(actualPasswordWarningMessage.contains(dataProp.getProperty("passwordWarning")),
				"Actual Password warning message is not displayed");

	}
}
