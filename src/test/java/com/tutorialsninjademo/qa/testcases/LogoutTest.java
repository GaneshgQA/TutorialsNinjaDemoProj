package com.tutorialsninjademo.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountLogoutPage;
import com.tutorialsninja.qa.pages.AccountPage;
import com.tutorialsninja.qa.pages.HeaderOptionsPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.LoginPage;

public class LogoutTest extends Base {

	public WebDriver driver;
	public LoginPage loginPage;
	AccountPage accountPage;
	HeaderOptionsPage headerOptionsPage;
	AccountLogoutPage accountLogoutPage;
	HeaderOptionsPage headerPageOption;
	HomePage homePage;

	public LogoutTest() {

		super();

	}

	@BeforeMethod
	public void setUp() {

		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browser"));
		HomePage homePage = new HomePage(driver);
		homePage.clickOnMyAccount();
		// homePage.selectLoginOption();
	}

	@AfterMethod
	public void tearDown() {

		driver.quit();

	}

	@Test(priority = 1)
	public void verifyLoggingOutUsingMyAccountDropMenu() throws InterruptedException {

		loginPage = new LoginPage(driver);
		accountPage = new AccountPage(driver);
		homePage = new HomePage(driver);
		homePage.selectLoginOption();
		loginPage.enterUserName(prop.getProperty("validEmail"));
		loginPage.enterPassword(prop.getProperty("validPassword"));
		loginPage.clickLoginButton();
		Assert.assertTrue(accountPage.verifyEditYourAccountInfoLabel());
		Assert.assertEquals(getPageURL(driver), dataProp.getProperty("accountPageURL"));

		headerOptionsPage = new HeaderOptionsPage(driver);
		headerOptionsPage.selectMyAccountDropMenu();
		headerOptionsPage.selectLogOutOption();

		AccountLogoutPage accountLogoutPage = new AccountLogoutPage(driver);
		Assert.assertEquals(accountLogoutPage.retrieveAccountLogOutHeadingText(),
				dataProp.getProperty("accountLogoutHeading"), "Account Logout message is not displayed");
	}

	@Test(priority = 2)
	public void verifyLoggingOutUsingRightColumnOption() {

		loginPage = new LoginPage(driver);
		homePage = new HomePage(driver);
		homePage.selectLoginOption();
		loginPage.enterUserName(prop.getProperty("validEmail"));
		loginPage.enterPassword(prop.getProperty("validPassword"));
		loginPage.clickLoginButton();

		accountPage = new AccountPage(driver);

		Assert.assertTrue(accountPage.verifyEditYourAccountInfoLabel());

		accountPage.clickOnRightColumnLogoutOption();

		accountLogoutPage = new AccountLogoutPage(driver);

		Assert.assertEquals(accountLogoutPage.retrieveAccountLogOutHeadingText(),
				dataProp.getProperty("accountLogoutHeading"), "Account logout confirmation mesg not displayed");

		Assert.assertEquals(getPageURL(driver), dataProp.getProperty("accountLogoutPageURL"));

		accountLogoutPage.clickOnAccontLogoutPageContinueButton();

		Assert.assertEquals(getPageURL(driver), dataProp.getProperty("headerOptionPageURL"));

	}

	@Test(priority = 3)
	public void verifyLoggingOutAndBrowsingBack() throws InterruptedException {

		loginPage = new LoginPage(driver);
		homePage = new HomePage(driver);
		homePage.selectLoginOption();
		loginPage.enterUserName(prop.getProperty("validEmail"));
		loginPage.enterPassword(prop.getProperty("validPassword"));
		loginPage.clickLoginButton();

		accountPage = new AccountPage(driver);
		Assert.assertTrue(accountPage.verifyEditYourAccountInfoLabel());

		driver.navigate().back();

		driver.navigate().refresh();

	}

	@Test(priority = 4)
	public void verifyNoLogoutOptionIsDisplyedBeforeLogin() {

		headerPageOption = new HeaderOptionsPage(driver);
		homePage = new HomePage(driver);
		homePage.selectLoginOption();

		Assert.assertFalse(headerPageOption.isLogoutOptionAvailable());

	}

	@Test(priority = 5)
	public void verifyThereIsNoRightColumnLogoutOptionBeforeLogin() throws InterruptedException {

		homePage = new HomePage(driver);
		Thread.sleep(5000);
		homePage.clickRegisterOption();

		accountPage = new AccountPage(driver);

		Assert.assertFalse(accountPage.isRightColumnLogoutOptionAvailable());

	}

	@Test(priority = 6)
	public void verifyLoginAfterLogout() {

		homePage = new HomePage(driver);
		homePage.selectLoginOption();
		loginPage = new LoginPage(driver);
		loginPage.enterUserName(prop.getProperty("validEmail"));
		loginPage.enterPassword(prop.getProperty("validPassword"));
		loginPage.clickLoginButton();

		accountPage = new AccountPage(driver);
		Assert.assertTrue(accountPage.verifyEditYourAccountInfoLabel());

		headerOptionsPage = new HeaderOptionsPage(driver);

		headerOptionsPage.selectMyAccountDropMenu();
		headerOptionsPage.selectLogOutOption();

		homePage.selectLoginOption();
		loginPage.enterUserName(prop.getProperty("validEmail"));
		loginPage.enterPassword(prop.getProperty("validPassword"));
		loginPage.clickLoginButton();
		Assert.assertEquals(getPageURL(driver), dataProp.getProperty("accountPageURL"));

	}

	@Test(priority = 7)
	public void verifyBreadCrumbTitleHeadingAndURLOfAccountLogoutPage() {

		homePage = new HomePage(driver);
		homePage.selectLoginOption();
		loginPage = new LoginPage(driver);
		loginPage.enterUserName(prop.getProperty("validEmail"));
		loginPage.enterPassword(prop.getProperty("validPassword"));
		loginPage.clickLoginButton();

		accountPage = new AccountPage(driver);
		Assert.assertTrue(accountPage.verifyEditYourAccountInfoLabel());

		headerOptionsPage = new HeaderOptionsPage(driver);

		headerOptionsPage.selectMyAccountDropMenu();
		headerOptionsPage.selectLogOutOption();

		accountLogoutPage = new AccountLogoutPage(driver);
		Assert.assertTrue(accountLogoutPage.verifyIsAccountLogoutBreadCrumbDisplayed());

		Assert.assertEquals(accountLogoutPage.retrieveAccountLogoutMessageText(),
				dataProp.getProperty("accountLogoutMessage"), "Account Logout text message is not displayed");

		Assert.assertEquals(getPageURL(driver), dataProp.getProperty("accountLogoutPageURL"));

	}
	
	@Test (priority = 8)
	public void verifyAccountLogoutFunctionality() {
		
		homePage = new HomePage(driver);
		homePage.selectLoginOption();
		loginPage = new LoginPage(driver);
		loginPage.enterUserName(prop.getProperty("validEmail"));
		loginPage.enterPassword(prop.getProperty("validPassword"));
		loginPage.clickLoginButton();
		
		accountPage = new AccountPage(driver);
		Assert.assertTrue(accountPage.verifyEditYourAccountInfoLabel());
		
		headerOptionsPage = new HeaderOptionsPage(driver);
		
		headerOptionsPage.selectMyAccountDropMenu();
		headerOptionsPage.selectLogOutOption();
		
		AccountLogoutPage accountLogout = new AccountLogoutPage(driver);
		Assert.assertEquals(accountLogout.retrieveAccountLogoutMessageText(), dataProp.getProperty("accountLogoutMessage"),"Account logout mesg not displayed");
		
	}
}
