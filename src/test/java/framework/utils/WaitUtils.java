package framework.utils;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import framework.base.DriverManager;

public class WaitUtils {

	private static long DEFAULT_WAIT = 10;
//	private static WebDriver driver = DriverManager.getdriver();
	
	
	public static WebElement waitForVisible(By locator) {
		WebDriver driver = DriverManager.getDriver();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	public static WebElement waitforClickable(By locator) {
		WebDriver driver = DriverManager.getDriver();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT));
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}
	
	public static boolean waitForUrlContains(String keyword) {
		WebDriver driver = DriverManager.getDriver();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT));
		return wait.until(ExpectedConditions.urlContains(keyword));
	}
}
