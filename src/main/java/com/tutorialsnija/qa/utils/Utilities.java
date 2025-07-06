package com.tutorialsnija.qa.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

public class Utilities {

	public static final int IMPLICITE_WAIT_TIME = 80;
	public static final int PAGE_LOAD_TIME = 80;

	public static String generateEmailWithTimeStamp() {

		Date date = new Date();
		String timeStamp = date.toString().replace(" ", "_").replace(":", "_");
		return "NinjaQA" + timeStamp + "@gmail.com";

	}

	public static Object[][] getTestDataFromExcel(String sheetName) {

		XSSFWorkbook workbook = null;

		File excelFile = new File(System.getProperty("user.dir")
				+ "\\src\\main\\java\\com\\tutorialsninja\\qa\\testdata\\TutorialsNinjaTestData.xlsx");
		try {

			FileInputStream fisExcel = new FileInputStream(excelFile);
			workbook = new XSSFWorkbook(fisExcel);

		} catch (Exception e) {

			e.printStackTrace();
		}

		XSSFSheet sheet = workbook.getSheet(sheetName);

		int rows = sheet.getLastRowNum();
		int cols = sheet.getRow(0).getLastCellNum();

		Object data[][] = new Object[rows][cols];

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

				default:
					break;

				}

			}
		}

		return data;
	}

	public static String captureScreenshot(WebDriver driver, String testName) {

		File srcScreenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String destinationScreenshotPath = System.getProperty("user.dir") + "\\Screenshot\\" + testName + ".png";
		try {
			FileHandler.copy(srcScreenshot, new File(destinationScreenshotPath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return destinationScreenshotPath;

	}

	public static WebDriver takeScreenshot(WebDriver driver, String pathToBeCopied) {

		File srcScreenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		// String destinationScreenshotPath =
		// System.getProperty("user.dir")+"\\Screenshot\\"+testName+".png";
		try {
			FileHandler.copy(srcScreenshot, new File(System.getProperty("user.dir") + pathToBeCopied));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return driver;

	}

	public static boolean compareTwoScreenshot(String actualImagePath, String expectedImagePath) {

		BufferedImage actualBImg = null;
		BufferedImage expectedBImg = null;

		try {

			actualBImg = ImageIO.read(new File(actualImagePath));
			expectedBImg = ImageIO.read(new File(expectedImagePath));

		} catch (IOException e) {

			e.printStackTrace();

		}

		ImageDiffer imgDiffer = new ImageDiffer();
		ImageDiff imgDifference = imgDiffer.makeDiff(expectedBImg, actualBImg);

		return imgDifference.hasDiff();
	}

}
