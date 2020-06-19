package main.java.commonFunctions;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import test.java.seleniumgluecode.TestInitialize;

public class CommonUIFunctions {

	// To wait for element visible by xpath
	public static void waitWithXpath(String xpath) {
		try {
			WebDriverWait wait = new WebDriverWait(TestInitialize.driver, 30);
			wait.until(ExpectedConditions.or(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))));
			System.out.println("Element is visible now" + xpath);
		} catch (Exception e) {
			System.out.println("Element is not visible." + xpath);
		}
	}

	// To wait for element visible by id
	public static void waitWithid(String id) {
		try {
			WebDriverWait wait = new WebDriverWait(TestInitialize.driver, 30);
			wait.until(ExpectedConditions.or(ExpectedConditions.visibilityOfElementLocated(By.id(id))));
			System.out.println("Element is visible now" + id);
		} catch (Exception e) {
			System.out.println("Element is not visible." + id);
		}
	}
}
