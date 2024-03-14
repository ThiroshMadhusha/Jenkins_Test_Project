package com.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.base.Base;
import com.pages.AccountSuccessPage;
import com.pages.HomePage;
import com.pages.RegisterPage;
import com.utilities.Utilities;

public class RegisterTest extends Base {
	//At the global leval driver need to pass the listerners class it should be public
	//without public it cannot access to other class
	public WebDriver driver;

	@BeforeMethod
	public void beforeRegisterMethod() {
		// call the Loard Properties before intialize browser
		loadPropertiesFile();
		// inherite from base class
		driver = initializeBrowserAndOpenApplication(configProp.getProperty("browser"));
		HomePage homePage = new HomePage(driver);
		homePage.myAccountDropMenu();
		homePage.clickRegisterOptionDropdownMenu();

	}

	@Test(priority = 1)
	public void registerAccountWithAllMandatoryFieldsValidCredentials() {

		RegisterPage registerPage = new RegisterPage(driver);
		registerPage.firstNameField(dataProp.getProperty("firstName"));
		registerPage.lastNameField(dataProp.getProperty("lastName"));
		registerPage.emailAddressField(Utilities.generateEmail());
		registerPage.telephoneNoField(dataProp.getProperty("telephoneNumber"));
		registerPage.passwordField(configProp.getProperty("validPassword"));
		registerPage.confirmPasswordField(configProp.getProperty("validPassword"));
		registerPage.privacyPolicyCheckBox();
		registerPage.submitButton();
		AccountSuccessPage accSuccessPage = new AccountSuccessPage(driver);
		String actualSuccessHeading = accSuccessPage.actualSuccessHeadingDisplay();
		Assert.assertEquals(actualSuccessHeading, dataProp.getProperty("accountSuccessfullyCreatedHeading"),
				"Account Successful Page is Not Displayed. Account is Not Created!");
	}

	@Test(priority = 2)
	public void registerAccountWithAllFieldsValidCredentials() {

		RegisterPage registerPage = new RegisterPage(driver);
		registerPage.firstNameField(dataProp.getProperty("firstName"));
		registerPage.lastNameField(dataProp.getProperty("lastName"));
		registerPage.emailAddressField(Utilities.generateEmail());
		registerPage.telephoneNoField(dataProp.getProperty("telephoneNumber"));
		registerPage.passwordField(configProp.getProperty("validPassword"));
		registerPage.confirmPasswordField(configProp.getProperty("validPassword"));
		registerPage.newsletterSubscriptionRadioButton();
		registerPage.privacyPolicyCheckBox();
		registerPage.submitButton();
		AccountSuccessPage accSuccessPage = new AccountSuccessPage(driver);
		String actualSuccessHeading = accSuccessPage.actualSuccessHeadingDisplay();
		Assert.assertEquals(actualSuccessHeading, dataProp.getProperty("accountSuccessfullyCreatedHeading"),
				"Account Successful Page is Not Displayed. Account is Not Created!");
	}

	@Test(priority = 3)
	public void registerAccountWithExistingValidCredentials() {

		RegisterPage registerPage = new RegisterPage(driver);
		registerPage.firstNameField(dataProp.getProperty("firstName"));
		registerPage.lastNameField(dataProp.getProperty("lastName"));
		registerPage.emailAddressField(configProp.getProperty("validEmail"));
		registerPage.telephoneNoField(dataProp.getProperty("telephoneNumber"));
		registerPage.passwordField(configProp.getProperty("validPassword"));
		registerPage.confirmPasswordField(configProp.getProperty("validPassword"));
		registerPage.newsletterSubscriptionRadioButton();
		registerPage.privacyPolicyCheckBox();
		registerPage.submitButton();

		String actualWarningMessage = registerPage.existingEmailWarningMessageDisplay();
		Assert.assertTrue(actualWarningMessage.contains(dataProp.getProperty("existingEmailWarning")),
				"Dublicate emails Warning message is Not Displayed!");
	}

	@Test(priority = 4)
	public void registerAccountWithOutProvideAnyDetails() {

		RegisterPage registerPage = new RegisterPage(driver);
		registerPage.submitButton();

		String actualPrivacyPolicyWarningMessage = registerPage.privacyPolicyWarningMessageDisplay();
		Assert.assertTrue(actualPrivacyPolicyWarningMessage.contains(dataProp.getProperty("privacyPolicyWarning")));

		String actualFirstNameWarningMessage = registerPage.firstNameWarningMessageDisplay();
		Assert.assertEquals(actualFirstNameWarningMessage, dataProp.getProperty("firstNameWarning"));

		String actualLastNameWarningMessage = registerPage.lastNameWarningMessageDisplay();
		Assert.assertEquals(actualLastNameWarningMessage, dataProp.getProperty("lastNameWarning"));

		String actualEmailWarningMessage = registerPage.emailWarningMessageDisplay();
		Assert.assertEquals(actualEmailWarningMessage, dataProp.getProperty("emailWarning"));

		String actualTelephoneWarningMessage = registerPage.phoneNumberWarningMessageDisplay();
		Assert.assertEquals(actualTelephoneWarningMessage, dataProp.getProperty("telephoneNumberWarning"));

		String actualPasswordWarningMessage = registerPage.passwordWarningMessageDisplay();
		Assert.assertEquals(actualPasswordWarningMessage, dataProp.getProperty("passwordWarning"));

	}

	@AfterMethod
	public void afterRegisterMethod() {
		driver.quit();
	}

}
