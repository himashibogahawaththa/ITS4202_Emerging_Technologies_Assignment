package com.actitime.qa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;


import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.usermodel.Cell;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;


import com.actitime.qa.base.TestBase;
import org.apache.commons.io.FileUtils;

public class TestUtil extends TestBase {

	public static final long PAGE_LOAD_TIMEOUT = 40;
	public static final long IMPLICIT_WAIT = 40;

	private static final Map<String, Long> BROWSER_TIMEOUT_MAP = new HashMap<>();
	static {
		BROWSER_TIMEOUT_MAP.put("chrome", 50L);
		BROWSER_TIMEOUT_MAP.put("firefox", 60L);
		BROWSER_TIMEOUT_MAP.put("safari", 70L);
	}

	public static long getPageLoadTimeout(WebDriver driver) {
		String browserName = ((RemoteWebDriver) driver).getCapabilities().getBrowserName().toLowerCase();
		return BROWSER_TIMEOUT_MAP.getOrDefault(browserName, PAGE_LOAD_TIMEOUT);
	}

	public static long getImplicitWait(WebDriver driver) {
		String browserName = ((RemoteWebDriver) driver).getCapabilities().getBrowserName().toLowerCase();
		return BROWSER_TIMEOUT_MAP.getOrDefault(browserName, IMPLICIT_WAIT);
	}

	public void switchToFrame(String frameName) {
		driver.switchTo().frame(frameName);
	}

	public static Object[][] getTestData(String sheetName) {
		String testDataPath = "C:\\Users\\gavee\\OneDrive\\Desktop\\ITS4202_Emerging_Technologies_Assignment\\src\\main\\java\\com\\actitime\\qa\\testdata\\actitimeTestData.xlsx";
		FileInputStream file = null;
		try {
			file = new FileInputStream(testDataPath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			Workbook book = WorkbookFactory.create(file);
			Sheet sheet = book.getSheet(sheetName);
			Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];

			for (int i = 0; i < sheet.getLastRowNum(); i++) {
				for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
					data[i][k] = sheet.getRow(i + 1).getCell(k).getStringCellValue().trim();

				}
			}
			return data;
		} catch (IOException | InvalidFormatException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void takeScreenshotAtEndOfTest() {
		try {
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String currentDir = System.getProperty("user.dir");
			FileUtils.copyFile(scrFile,
					new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
