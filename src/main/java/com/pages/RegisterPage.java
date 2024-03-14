package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {

	WebDriver driver;

	// create webelements objects by using page factory design pattern
	@FindBy(id = "input-firstname")
	private WebElement firstNameField;

	@FindBy(id = "input-lastname")
	private WebElement lastNameField;

	@FindBy(id = "input-email")
	private WebElement emailAddressField;

	@FindBy(id = "input-telephone")
	private WebElement telephoneNoField;

	@FindBy(id = "input-password")
	private WebElement passwordField;

	@FindBy(id = "input-confirm")
	private WebElement confirmPasswordField;

	@FindBy(xpath = "//input[@type='checkbox' and @name='agree']")
	private WebElement privacyPolicyCheckBox;

	@FindBy(xpath = "//input[@type='submit' and @value='Continue']")
	private WebElement submitButton;

	@FindBy(xpath = "//input[@value='1' and @name='newsletter']")
	private WebElement newsletterSubscriptionRadioButton;

	@FindBy(xpath = "//div[contains(text(),'Warning: E-Mail Address is already registered!')]")
	private WebElement existingEmailWarningMessageDisplay;

	@FindBy(xpath = "//div[contains(text(),'Warning: You must agree to the Privacy Policy!')]")
	private WebElement privacyPolicyWarningMessageDisplay;

	@FindBy(xpath = "//div[contains(text(),'First Name must be between 1 and 32 characters!')]")
	private WebElement firstNameWarningMessageDisplay;

	@FindBy(xpath = "//div[contains(text(),'Last Name must be between 1 and 32 characters!')]")
	private WebElement lastNameWarningMessageDisplay;

	@FindBy(xpath = "//div[contains(text(),'E-Mail Address does not appear to be valid!')]")
	private WebElement emailWarningMessageDisplay;

	@FindBy(xpath = "//div[contains(text(),'Telephone must be between 3 and 32 characters!')]")
	private WebElement phoneNumberWarningMessageDisplay;

	@FindBy(xpath = "//div[contains(text(),'Password must be between 4 and 20 characters!')]")
	private WebElement passwordWarningMessageDisplay;

	// create constructors
	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// actions
	public void firstNameField(String firstNameText) {
		firstNameField.sendKeys(firstNameText);
	}

	public void lastNameField(String lastNameText) {
		lastNameField.sendKeys(lastNameText);
	}

	public void emailAddressField(String emailAddressText) {
		emailAddressField.sendKeys(emailAddressText);
	}

	public void telephoneNoField(String telephoneNoText) {
		telephoneNoField.sendKeys(telephoneNoText);
	}

	public void passwordField(String passwordText) {
		passwordField.sendKeys(passwordText);
	}

	public void confirmPasswordField(String confirmPasswordText) {
		confirmPasswordField.sendKeys(confirmPasswordText);
	}

	public void newsletterSubscriptionRadioButton() {
		newsletterSubscriptionRadioButton.click();
	}

	public void privacyPolicyCheckBox() {
		privacyPolicyCheckBox.click();
	}

	public void submitButton() {
		submitButton.click();
	}

	public String existingEmailWarningMessageDisplay() {
		String actualWarningMessage = existingEmailWarningMessageDisplay.getText();
		return actualWarningMessage;
	}

	public String privacyPolicyWarningMessageDisplay() {
		String actualPrivacyPolicyWarningMessage = privacyPolicyWarningMessageDisplay.getText();
		return actualPrivacyPolicyWarningMessage;
	}

	public String firstNameWarningMessageDisplay() {
		String actualFirstNameWarningMessage = firstNameWarningMessageDisplay.getText();
		return actualFirstNameWarningMessage;
	}

	public String lastNameWarningMessageDisplay() {
		String actualLastNameWarningMessage = lastNameWarningMessageDisplay.getText();
		return actualLastNameWarningMessage;
	}

	public String emailWarningMessageDisplay() {
		String actualEmailWarningMessage = emailWarningMessageDisplay.getText();
		return actualEmailWarningMessage;
	}

	public String phoneNumberWarningMessageDisplay() {
		String actualTelephoneWarningMessage = phoneNumberWarningMessageDisplay.getText();
		return actualTelephoneWarningMessage;
	}

	public String passwordWarningMessageDisplay() {
		String actualPasswordWarningMessage = passwordWarningMessageDisplay.getText();
		return actualPasswordWarningMessage;
	}
}
