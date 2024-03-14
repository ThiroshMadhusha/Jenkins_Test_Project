package com.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.base.Base;
import com.pages.AccountPage;
import com.pages.HomePage;
import com.pages.LoginPage;
import com.utilities.Utilities;

public class LoginTest extends Base {
	//At the global leval driver need to pass the listerners class it should be public
	//without public it cannot access to other class
	public WebDriver driver;

	@BeforeMethod
	public void beforeLoginMethod() {
		// call the Loard Properties before intialize browser
		loadPropertiesFile();
		// inherite from base class
		driver = initializeBrowserAndOpenApplication(configProp.getProperty("browser"));
		// inherite from locators homepage
		HomePage homePage = new HomePage(driver);
		// Remove driver.findElement line and assign hardcoded vlues
		homePage.myAccountDropMenu();
		homePage.clickLoginOptionDropdownMenu();

	}

	@Test(priority = 1, dataProvider = "supplierValidCredentials")
	public void verifyLoginWithValidCredentials(String email, String password) {

		LoginPage loginPage = new LoginPage(driver);
		loginPage.emailField(email);
		loginPage.passwordField(password);
		loginPage.loginButton();

		AccountPage accountPage = new AccountPage(driver);
		Assert.assertTrue(accountPage.getDisplayStatusOfSuccessfulMessage(),
				"Edit your account information Option is Not Displayed!");
	}

	@DataProvider(name = "supplierValidCredentials")
	public Object[][] supplyTestData() {
		Object[][] data = Utilities.getTestDataFromExcell("Login");
		return data;
	}

	@Test(priority = 2)
	public void verifyloginWithInvalidCredentials() {

		LoginPage loginPage = new LoginPage(driver);
		loginPage.emailField(Utilities.generateEmail());
		loginPage.passwordField(dataProp.getProperty("invalidPassword"));
		loginPage.loginButton();
		String actualWarningMsgText = loginPage.emailPasswordNotMatchingWarningMessage();
		String expectedWarningMsgText = dataProp.getProperty("invalidEmailWarningMessage");
		Assert.assertTrue(actualWarningMsgText.contains(expectedWarningMsgText),
				"Expected Warning Message is Not Displayed!");
	}

	@Test(priority = 3)
	public void verifyloginWithInvalidEmailAndValidPassword() {

		LoginPage loginPage = new LoginPage(driver);
		loginPage.emailField(Utilities.generateEmail());
		loginPage.passwordField(configProp.getProperty("validPassword"));
		loginPage.loginButton();
		String actualWarningMsgText = loginPage.emailPasswordNotMatchingWarningMessage();
		String expectedWarningMsgText = dataProp.getProperty("invalidEmailWarningMessage");
		Assert.assertTrue(actualWarningMsgText.contains(expectedWarningMsgText),
				"Expected Warning Message is Not Displayed!");
	}

	@Test(priority = 4)
	public void verifyloginWithValidEmailAndInValidPassword() {

		LoginPage loginPage = new LoginPage(driver);
		loginPage.emailField(configProp.getProperty("validEmail"));
		loginPage.passwordField(Utilities.generateData());
		loginPage.loginButton();
		String actualWarningMsgText = loginPage.emailPasswordNotMatchingWarningMessage();
		String expectedWarningMsgText = dataProp.getProperty("invalidEmailWarningMessage");
		Assert.assertTrue(actualWarningMsgText.contains(expectedWarningMsgText),
				"Expected Warning Message is Not Displayed!");
	}

	@Test(priority = 5)
	public void verifyloginWithoutProvidingCredencials() {

		LoginPage loginPage = new LoginPage(driver);
		loginPage.emailField("");
		loginPage.passwordField("");
		loginPage.loginButton();
		String actualWarningMsgText = loginPage.emailPasswordNotMatchingWarningMessage();
		String expectedWarningMsgText = dataProp.getProperty("invalidEmailWarningMessage");
		Assert.assertTrue(actualWarningMsgText.contains(expectedWarningMsgText),
				"Expected Warning Message is Not Displayed!");
	}

	/**
	 * Create These as Global Level It help to close each and every browser results
	 * are passed or failed both times
	 */
	@AfterMethod
	public void afterLoginMethod() {
		driver.quit();
	}

}
