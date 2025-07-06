package com.tutorialsninjademo.qa.testcases;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsnija.qa.utils.Utilities;
import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountSuccessPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.RegisterPage;

public class RegisterTest extends Base {

	public WebDriver driver;

	RegisterPage registerPage;

	AccountSuccessPage accountSuccessPage;

	public RegisterTest() {

		super();

	}

	@BeforeMethod
	public void setUp() {

		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browser"));
		HomePage homePage = new HomePage(driver);
		homePage.clickOnMyAccount();
		homePage.clickRegisterOption();
		// driver.findElement(By.xpath("//a[@title='My Account']")).click();
		// driver.findElement(By.linkText("Register")).click();

	}

	@AfterMethod
	public void tearDown() {

		driver.quit();

	}

	@Test(priority = 1)
	public void verifyRegisteringAnAccountWithMandatoryFields() {

		registerPage = new RegisterPage(driver);
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

		accountSuccessPage = new AccountSuccessPage(driver);
		String actualSuccessMessage = accountSuccessPage.retriveAccountSuccessPageHeading();
		String expectedSuccessMessage = dataProp.getProperty("AccountSuccessfullyCreationMessage");
		Assert.assertEquals(actualSuccessMessage, expectedSuccessMessage,
				"Expected acct success message is not displayed");

	}

	@Test(priority = 2)
	public void registeringAnAccountWithAllField() {

		registerPage = new RegisterPage(driver);
		registerPage.enterFirstName(dataProp.getProperty("firstName"));
		registerPage.enterLastName(dataProp.getProperty("lastName"));
		registerPage.enterEmailAddress(Utilities.generateEmailWithTimeStamp());
		registerPage.enterTelephoneNumber(dataProp.getProperty("telephone"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerPage.selectYesNewsLetterOption();
		registerPage.selectPrivacyPolicy();
		registerPage.clickOnContinueButton();

		accountSuccessPage = new AccountSuccessPage(driver);

//		driver.findElement(By.id("input-firstname")).sendKeys(dataProp.getProperty("firstName"));
//		driver.findElement(By.id("input-lastname")).sendKeys(dataProp.getProperty("lastName"));
//		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
//		driver.findElement(By.id("input-telephone")).sendKeys(dataProp.getProperty("telephone"));
//		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
//		driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validPassword"));
//		driver.findElement(By.xpath("//input[@name='newsletter' and @value='1']")).click();
//		driver.findElement(By.name("agree")).click();
//		driver.findElement(By.xpath("//input[@value='Continue']")).click();

		String actualSuccessMessage = accountSuccessPage.retriveAccountSuccessPageHeading();
		String expectedSuccessMessage = dataProp.getProperty("AccountSuccessfullyCreationMessage");
		Assert.assertEquals(actualSuccessMessage, expectedSuccessMessage,
				"Expected acct success message is not displayed");

	}

	@Test(priority = 3)
	public void verifyRegisteringAnAccountWithExistingEmailId() {

		registerPage = new RegisterPage(driver);
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

		registerPage = new RegisterPage(driver);
		registerPage.clickOnContinueButton();

		// driver.findElement(By.xpath("//input[@value='Continue']")).click();

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

	@Test(priority = 5)
	public void verifyRegisteringAnAccountWithInvalidEmail() {

		String browserName = prop.getProperty("browser");
		registerPage = new RegisterPage(driver);
		registerPage.enterFirstName(dataProp.getProperty("firstName"));
		registerPage.enterLastName(dataProp.getProperty("lastName"));
		registerPage.enterEmailAddress(dataProp.getProperty("invalidEmail"));
		registerPage.enterTelephoneNumber(dataProp.getProperty("telephone"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerPage.selectPrivacyPolicy();
		registerPage.clickOnContinueButton();

		if (browserName.equals("chrome")) {
			System.out.println(registerPage.emailField.getDomProperty("validationMessage"));
			Assert.assertEquals(registerPage.emailField.getDomProperty("validationMessage"),
					"Please include an '@' in the email address. 'ganesh' is missing an '@'.");

		} else if (browserName.equals("firefox")) {

			System.out.println(registerPage.emailField.getDomProperty("validationMessage"));
			Assert.assertEquals(registerPage.emailField.getDomProperty("validationMessage"),
					"Please enter an email address.");

		} else if (browserName.equals("edge")) {

			System.out.println(registerPage.emailField.getDomProperty("validationMessage"));
			Assert.assertEquals(registerPage.emailField.getDomProperty("validationMessage"),
					"Please include an '@' in the email address. 'ganesh' is missing an '@'.");

		}
	}

	@Test(priority = 6)
	public void registeringAnAccountUsingKeyboardKeys() {

		registerPage = new RegisterPage(driver);

		Actions action = new Actions(driver);
		for (int i = 1; i <= 23; i++) {

			action.sendKeys(Keys.TAB).perform();
		}

		action.sendKeys(dataProp.getProperty("firstName")).pause(Duration.ofSeconds(2)).sendKeys(Keys.TAB).build()
				.perform();
		action.sendKeys(dataProp.getProperty("lastName")).pause(Duration.ofSeconds(2)).sendKeys(Keys.TAB).build()
				.perform();
		action.sendKeys(Utilities.generateEmailWithTimeStamp()).pause(Duration.ofSeconds(2)).sendKeys(Keys.TAB).build()
				.perform();
		action.sendKeys(dataProp.getProperty("telephone")).pause(Duration.ofSeconds(2)).sendKeys(Keys.TAB).build()
				.perform();
		// action.sendKeys(dataProp.getProperty(Utilities.generateEmailWithTimeStamp())).pause(Duration.ofSeconds(2)).sendKeys(Keys.TAB).build().perform();
		action.sendKeys(prop.getProperty("validPassword")).pause(Duration.ofSeconds(2)).sendKeys(Keys.TAB).build()
				.perform();
		action.sendKeys(prop.getProperty("validPassword")).pause(Duration.ofSeconds(2)).sendKeys(Keys.TAB).build()
				.perform();
		action.sendKeys(Keys.ARROW_LEFT).pause(Duration.ofSeconds(2)).sendKeys(Keys.TAB).pause(Duration.ofSeconds(2))
				.sendKeys(Keys.TAB).sendKeys(Keys.SPACE).build().perform();
		action.sendKeys(Keys.ENTER).perform();

		accountSuccessPage = new AccountSuccessPage(driver);
		Assert.assertTrue(driver.findElement(By.linkText("Logout")).isDisplayed());
		String ActualAcctSuccessCreationMessage = accountSuccessPage.retrieveAccountSuccessPageHeading1();
		// String expectedAcctSuccessMessage = "Congratulations! Your new account has
		// been successfully created!";
		Assert.assertTrue(ActualAcctSuccessCreationMessage.contains(dataProp.getProperty("acctSuccessPageHeading1")),
				"Expected acct success message not displayed");
		// Assert.assertEquals(ActualAcctSuccessCreationMessage,
		// expectedAcctSuccessMessage,"Expected acct success message not displayed");
	}
	
	@Test(priority = 7)
	public void verifyBreadCrumbPageHeadingTitleAndURLOfRegisterPage() {
		
		registerPage = new RegisterPage(driver);
		Assert.assertTrue(registerPage.isProperRegisterPageBreadCrumbDisplayed());
		Assert.assertEquals(getPageTitle(driver), dataProp.getProperty("registerPageTitle"));
		Assert.assertEquals(getPageURL(driver), dataProp.getProperty("registerPageURL"));
		Assert.assertEquals(registerPage.getRegitserPageHeading(), dataProp.getProperty("registerPageTitle"));
		
	}
}
