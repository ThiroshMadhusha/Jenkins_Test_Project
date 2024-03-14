package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {

	WebDriver driver;

	@FindBy(name = "search")
	private WebElement searchBoxText;

	@FindBy(xpath = "//button[@class='btn btn-default btn-lg']")
	private WebElement clickSearchButton;

	@FindBy(linkText = "iPhone")
	private WebElement successMessageDisplay;

	@FindBy(xpath = "//p[contains(text(),'There is no product that matches the search criteria.')]")
	private WebElement errorWarningMessageDisplay;

	public SearchPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void searchBoxText(String searchText) {
		searchBoxText.sendKeys(searchText);
	}

	public void clickSearchButton() {
		clickSearchButton.click();
	}

	public boolean successMessageDisplay() {
		boolean actualSuccessMessage = successMessageDisplay.isDisplayed();
		return actualSuccessMessage;
	}

	public String errorWarningMessageDisplay() {
		String warningMessage = errorWarningMessageDisplay.getText();
		return warningMessage;
	}

}
