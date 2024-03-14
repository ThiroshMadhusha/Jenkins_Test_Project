package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	WebDriver driver;
	// Page Factory Design Pattern
	// All webElements are Objects
	@FindBy(xpath = "//i[@class='fa fa-user' or following-sibling::span[text()='My Account']]")
	private WebElement myAccountDropMenu;

	@FindBy(linkText = "Login")
	private WebElement clickLoginOptionDropdownMenu;

	@FindBy(linkText = "Register")
	private WebElement clickRegisterOptionDropdownMenu;

	// create constructor
	public HomePage(WebDriver driver) {
		this.driver = driver;

		// page factory design pattern
		// when the constructor is called, it automatically initialised
		PageFactory.initElements(driver, this);
	}

	// actions
	public void myAccountDropMenu() {
		myAccountDropMenu.click();
	}

	public void clickLoginOptionDropdownMenu() {
		clickLoginOptionDropdownMenu.click();
	}

	public void clickRegisterOptionDropdownMenu() {
		clickRegisterOptionDropdownMenu.click();
	}

}
