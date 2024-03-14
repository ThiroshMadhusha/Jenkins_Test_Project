package com.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class MyExtentReports {

	public static ExtentReports generateExtendReport() {
		
		ExtentReports extendReports = new ExtentReports();
		
		File extentReportFile = new File(System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html");
		
		//multiple type of reporter
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReportFile);

		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setReportName("Project Name Automation Results");
		sparkReporter.config().setDocumentTitle("PN Automation Report");
		sparkReporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");
		
		extendReports.attachReporter(sparkReporter);
		
		Properties configProp = new Properties();
		File configPropFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\config\\config.properties");
		
		try {
			FileInputStream fisConfigProp = new FileInputStream(configPropFile);
			configProp.load(fisConfigProp);
		}catch(Throwable e) {
			e.printStackTrace();
		}
		
		extendReports.setSystemInfo("Application URL", configProp.getProperty("url"));
		extendReports.setSystemInfo("Browser Name", configProp.getProperty("browser"));
		extendReports.setSystemInfo("Email", configProp.getProperty("validEmail"));
		extendReports.setSystemInfo("Password", configProp.getProperty("validPassword"));

		//system requirement
		extendReports.setSystemInfo("Operating System", System.getProperty("os.name"));
		extendReports.setSystemInfo("User Name", System.getProperty("user.name"));
		extendReports.setSystemInfo("Java Version", System.getProperty("java.version"));
		
		return extendReports;
	}
}
