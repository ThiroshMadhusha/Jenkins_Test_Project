package com.base;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.utilities.Utilities;

public class Base {
	
	//create Properties as Global Leval
	public Properties configProp;
	public Properties dataProp;
	//create method
	public void loadPropertiesFile() {
		//maindata properties
		configProp = new Properties();
		File propFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\config\\config.properties");
		try {
			FileInputStream fils = new FileInputStream(propFile);
			configProp.load(fils);
		}catch(Throwable e) {
			e.printStackTrace();
		}
		
		//testdata properties
		dataProp = new Properties();
		File dataPropFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\testdata\\testdata.properties");
		try {
			FileInputStream testDataFils = new FileInputStream(dataPropFile);
			dataProp.load(testDataFils);
		}catch(Throwable e) {
			e.printStackTrace();
		}
	}

	WebDriver driver;
	//Inherite
	public WebDriver initializeBrowserAndOpenApplication(String browserName) {
		//equalsIgnoreCase mean we can add simple and capital both without any error
		if (browserName.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		} else {
			System.out.println("Error...!");
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGE_LOAD_TIME));
		driver.get(configProp.getProperty("URL"));
		
		return driver;
	}

}
