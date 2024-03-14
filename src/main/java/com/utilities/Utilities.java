package com.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Utilities {

	public static String generateEmail() {

		Date date = new Date();
		String emailGenerate = date.toString().replace(":", "").replace(" ", "");
		return emailGenerate + "@gmail.com";
	}

	public static String generateData() {

		Date date = new Date();
		String dataGenerate = date.toString().replace(":", "").replace(" ", "");
		return dataGenerate;

	}

	// final mean we cannot change values
	public static final int IMPLICIT_WAIT_TIME = 10;
	public static final int PAGE_LOAD_TIME = 5;

	/**
	 * Get Test Data from Excell
	 * 
	 * @param sheetName
	 * @return
	 */
	@SuppressWarnings("incomplete-switch")
	public static Object[][] getTestDataFromExcell(String sheetName) {

		File excellFile = new File(
				System.getProperty("user.dir") + "\\src\\main\\java\\com\\testdata\\loginTestData.xlsx");
		XSSFWorkbook workbook = null;
		try {
			FileInputStream fisExcel = new FileInputStream(excellFile);
			workbook = new XSSFWorkbook(fisExcel);
		} catch (Throwable e) {
			e.printStackTrace();
		}

		XSSFSheet sheet = workbook.getSheet(sheetName);

		int rows = sheet.getLastRowNum();
		int cols = sheet.getRow(0).getLastCellNum();

		Object[][] data = new Object[rows][cols];

		for (int i = 0; i < rows; i++) {
			XSSFRow row = sheet.getRow(i + 1);
			for (int j = 0; j < cols; j++) {
				XSSFCell cell = row.getCell(j);
				CellType cellType = cell.getCellType();

				switch (cellType) {

				case STRING:
					data[i][j] = cell.getStringCellValue();
					break;
				case NUMERIC:
					data[i][j] = Integer.toString((int) cell.getNumericCellValue());
					break;
				case BOOLEAN:
					data[i][j] = cell.getBooleanCellValue();
					break;
				}
			}
		}
		return data;
	}

	/**
	 * Capture the Screenshots
	 */
	public static String captureScreenshot(WebDriver driver, String testName) {

		// Take Screenshot Code
		File srcScreenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String destinationScreenshotPath = System.getProperty("user.dir") + "\\Screenshots\\" + testName + ".png";
		try {
			FileHandler.copy(srcScreenshot, new File(destinationScreenshotPath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return destinationScreenshotPath;
	}

}
