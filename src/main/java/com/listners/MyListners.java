package com.listners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.utilities.MyExtentReports;
import com.utilities.Utilities;

//ITestListener pre defined class
//change pom.xml testNG scope
public class MyListners implements ITestListener {

	ExtentReports extentReport;
	ExtentTest extentTest;
	String testName;
	// click navbar Source --> override/implement methods --> Select anything

	// project start in high-level
	@Override
	public void onStart(ITestContext context) {
//		System.out.println("Execute of Project Test Started!");
		extentReport = MyExtentReports.generateExtendReport();
	}

	// start exeute test cases
	@Override
	public void onTestStart(ITestResult result) {
		testName = result.getName();
//		System.out.println(testName + " Started Executing...!");
		extentTest = extentReport.createTest(testName);
		extentTest.log(Status.INFO, testName + " Started Executing...!");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		testName = result.getName();
//		System.out.println(testName + " got Successfully Executed...!");
		extentTest.log(Status.PASS, testName + " got Successfully Executed..!");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		testName = result.getName();
//		System.out.println(testName + " got Failed...!");
//		System.out.println(result.getThrowable());

		// retrive the driver from Object format
		WebDriver driver = null;
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver")
					.get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}

		String destinationScreenshotPath = Utilities.captureScreenshot(driver, testName);
		// Attach the screenshot with extent reports
		extentTest.addScreenCaptureFromPath(destinationScreenshotPath);
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.FAIL, testName + " got Failed...!");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		testName = result.getName();
//		System.out.println(testName + " got Skipped...!");
//		System.out.println(result.getThrowable());
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.SKIP, testName + " got Skipped...!");
	}

	@Override
	public void onFinish(ITestContext context) {
//		System.out.println("Finished Executed!");
		extentReport.flush();
		//Open Extent Reports Automatically
		String pathOfExtentReport = System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html";
		File extentReport = new File(pathOfExtentReport);
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
