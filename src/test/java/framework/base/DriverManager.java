package framework.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverManager {

	private static WebDriver driver;

	private DriverManager() {

	}

	public static WebDriver getdriver() {
		if (driver == null) {
			throw new IllegalStateException("Driver is not initialized. Call createDriver() first.");
		}

		return driver;
	}

	public static WebDriver createDriver(String browser) {
		if (driver != null) {
			return driver;
		}

		if (browser == null) {
			browser = "chrome";
		}

		browser = browser.toLowerCase();
		switch (browser) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		case "edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;
		default:
			throw new IllegalArgumentException("Unsupported browser:" + browser);
		}

		return driver;
	}

	public static void quitDriver() {
		if (driver != null)
			driver.quit();

		driver = null;
	}

}
