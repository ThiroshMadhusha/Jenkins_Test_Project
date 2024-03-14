package com.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.base.Base;
import com.pages.SearchPage;

public class SearchTest extends Base {
	//At the global leval driver need to pass the listerners class it should be public
	//without public it cannot access to other class
	public WebDriver driver;

	@BeforeMethod
	public void beforeSearchmethod() {
		// call the Loard Properties before intialize browser
		loadPropertiesFile();
		// inherite from base class
		driver = initializeBrowserAndOpenApplication(configProp.getProperty("browser"));
	}

	@Test(priority = 1)
	public void verifySearchWithValidProduct() {
		SearchPage searchPage = new SearchPage(driver);
		searchPage.searchBoxText(dataProp.getProperty("validProduct"));
		searchPage.clickSearchButton();
		Assert.assertTrue(searchPage.successMessageDisplay(), dataProp.getProperty("successMessage"));
	}

	@Test
	public void verifySearchWithInvalidProduct() {
		SearchPage searchPage = new SearchPage(driver);
		searchPage.searchBoxText(dataProp.getProperty("invalidProduct"));
		searchPage.clickSearchButton();
		String actualSearchMessage = searchPage.errorWarningMessageDisplay();
		Assert.assertEquals(actualSearchMessage, dataProp.getProperty("errorMessage"),
				"No Products Displayed in Search Page!");
	}

	@Test
	public void verifySearchWithOutAnyProduct() {
		SearchPage searchPage = new SearchPage(driver);
		searchPage.searchBoxText("");
		searchPage.clickSearchButton();
		String actualSearchMessage = searchPage.errorWarningMessageDisplay();
		Assert.assertEquals(actualSearchMessage, dataProp.getProperty("errorMessage"),
				"No Products Displayed in Search Page!");
	}

	@AfterMethod
	public void afterSearchMethos() {
		driver.quit();
	}

}
