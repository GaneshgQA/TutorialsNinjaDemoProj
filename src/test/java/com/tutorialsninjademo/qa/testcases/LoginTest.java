package com.tutorialsninjademo.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialsnija.qa.utils.Utilities;
import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.LoginPage;
import com.tutorialsninja.qa.pages.RegisterPage;

public class LoginTest extends Base {

	public WebDriver driver;

	LoginPage loginPage;
	RegisterPage registerPage;

	public LoginTest() {

		super();

	}

	@BeforeMethod
	public void setUp() {

		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browser"));
		HomePage homePage = new HomePage(driver);
		homePage.clickOnMyAccount();
		homePage.selectLoginOption();

	}

	@AfterMethod
	public void tearDown() {

		driver.quit();

	}

	@Test(priority = 1, dataProvider = "ValidCredentialSupplier")
	public void verifyLoginWithValidCredentials(String email, String password) {

		loginPage = new LoginPage(driver);
		loginPage.enterUserName(email);
		loginPage.enterPassword(password);
		loginPage.clickLoginButton();
//		driver.findElement(By.id("input-email")).sendKeys(email);
//		driver.findElement(By.id("input-password")).sendKeys(password);
//		driver.findElement(By.xpath("//input[@value='Login']")).click();

		AccountPage accoutPage = new AccountPage(driver);
		Assert.assertTrue(accoutPage.verifyEditYourAccountInfoLabel(), "Edit your acct info text is not displayed");

	}

	@DataProvider(name = "ValidCredentialSupplier")
	public Object[][] supplyTestData() {

		Object[][] data = Utilities.getTestDataFromExcel("Login");

		return data;

	}

	@Test(priority = 2)
	public void verifyLoginWithInvalidCredentials() {

		loginPage = new LoginPage(driver);
		loginPage.enterUserName(Utilities.generateEmailWithTimeStamp());
		loginPage.enterPassword(dataProp.getProperty("invalidPassword"));
		loginPage.clickLoginButton();

//		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
//		driver.findElement(By.id("input-password")).sendKeys(dataProp.getProperty("invalidPassword"));
//		driver.findElement(By.xpath("//input[@value='Login']")).click();
		String actualWarningMessage = loginPage.retrieveEmailPasswordNotMatchingWarningText();
		String expectedWarningMessage = dataProp.getProperty("emailPasswordNoMatching");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),
				"Expected warning message is not displayed");

	}

	@Test(priority = 3)
	public void verifyLoginWithInvalidEmailAndValidPassword() {

		loginPage = new LoginPage(driver);
		loginPage.enterUserName(Utilities.generateEmailWithTimeStamp());
		loginPage.enterPassword(prop.getProperty("validPassword"));
		loginPage.clickLoginButton();

//		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
//		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
//		driver.findElement(By.xpath("//input[@value='Login']")).click();

		String actualWarningMessage = loginPage.retrieveEmailPasswordNotMatchingWarningText();
		String expectedWarningMessage = dataProp.getProperty("emailPasswordNoMatching");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),
				"Expected warning message is not displayed");

	}

	@Test(priority = 4)
	public void verifyLoginWithValidEmailAndInvalidPassword() {

		loginPage = new LoginPage(driver);
		loginPage.enterUserName(prop.getProperty("validEmail"));
		loginPage.enterPassword(dataProp.getProperty("invalidPassword"));
		loginPage.clickLoginButton();

//		driver.findElement(By.id("input-email")).sendKeys(prop.getProperty("validEmail"));
//		driver.findElement(By.id("input-password")).sendKeys(dataProp.getProperty("invalidPassword"));
//		driver.findElement(By.xpath("//input[@value='Login']")).click();
		String actualWarningMessage = loginPage.retrieveEmailPasswordNotMatchingWarningText();
		String expectedWarningMessage = dataProp.getProperty("emailPasswordNoMatching");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),
				"Expected warning message is not displayed");

	}

	@Test(priority = 5)
	public void verifyLoginWithoutProvidingCredentials() {

		loginPage = new LoginPage(driver);
		loginPage.clickLoginButton();
		String actualWarningMessage = loginPage.retrieveEmailPasswordNotMatchingWarningText();
		String expectedWarningMessage = dataProp.getProperty("emailPasswordNoMatching");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),
				"Expected warning message is not displayed");

	}

	@Test(priority = 6)
	public void verifyDifferentWaysOfNavigatingToLoginpage() {

		loginPage = new LoginPage(driver);
//		 Assert.assertTrue(loginPage.retrieveReturningCutomerTextOnLoginPage().equals(dataProp.getProperty("expectedReturningCustomerTextOnLoginPage")),
//		 "Returning customer text not found on login Page");
		Assert.assertEquals(loginPage.retrieveReturningCutomerTextOnLoginPage(),
				dataProp.getProperty("expectedReturningCustomerTextOnLoginPage"),
				"Returning customer text not found on login Page1");

		loginPage.clickOnContinueButtonOnLoginPage();

		registerPage = new RegisterPage(driver);

		registerPage.clickOnLoginPageLink();
		Assert.assertEquals(loginPage.retrieveReturningCutomerTextOnLoginPage(),
				dataProp.getProperty("expectedReturningCustomerTextOnLoginPage"),
				"Returning customer text not found on login Page2");

		loginPage.clickOnContinueButtonOnLoginPage();

		registerPage.clickOnRightSideLoginOption();

		Assert.assertEquals(loginPage.retrieveReturningCutomerTextOnLoginPage(),
				dataProp.getProperty("expectedReturningCustomerTextOnLoginPage"),
				"Returning customer text not found on login Page3");

	}
	
	@Test(priority = 7)
	public void verifyBreadCrumbPageHeadingTitleAndPageURLOfLoginPage() {
		
		loginPage = new LoginPage(driver);
		Assert.assertTrue(loginPage.isProperBreadCrumbDisplayed());
		Assert.assertEquals(getPageTitle(driver), dataProp.getProperty("loginPageTitle"));
		Assert.assertEquals(getPageURL(driver), dataProp.getProperty("loginPageURL"));
		Assert.assertEquals(loginPage.getLoginPageHeading(), dataProp.getProperty("registerPageHeading"));
		
	}
	
	@Test(priority = 8)
	public void verifyUIOfLoginPage() {
		
		Utilities.takeScreenshot(driver, "\\Screenshot\\actualLoginPageUI.png");
		
		Assert.assertFalse(Utilities.compareTwoScreenshot(
			System.getProperty("user.dir")+ "\\Screenshot\\actualLoginPageUI.png", 
			System.getProperty("user.dir")+ "\\Screenshot\\expectedLoginPageUI.png"));
		
	}

}
