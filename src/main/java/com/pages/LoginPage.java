package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	WebDriver driver;

	// create webelements objects by using page factory design pattern
	@FindBy(xpath = "//input[@id='input-email']")
	private WebElement emailField;

	@FindBy(xpath = "//input[@id='input-password']")
	private WebElement passwordField;

	@FindBy(xpath = "//input[@value='Login' and @type='submit']")
	private WebElement loginButton;

	@FindBy(xpath = "//div[contains(@class,'alert alert-danger alert-dismissible')]")
	private WebElement emailPasswordNotMatchingWarningMessage;

	// create constructors
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// create all objects actions
	public void emailField(String emailText) {
		emailField.sendKeys(emailText);
	}

	public void passwordField(String loginText) {
		passwordField.sendKeys(loginText);
	}

	public void loginButton() {
		loginButton.click();
	}

	public String emailPasswordNotMatchingWarningMessage() {
		String actualWarningMsgText = emailPasswordNotMatchingWarningMessage.getText();
		return actualWarningMsgText;
	}
}
