package framework.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

	protected WebDriver driver;
	protected Properties config;

	@BeforeMethod
	public void setUp() throws FileNotFoundException {
		loadConfig();

		String browser = config.getProperty("browser", "chrome");
		driver = DriverManager.createDriver(browser);

		driver.manage().window().maximize();

		String baseUrl = config.getProperty("base.url");

		if (baseUrl != null && !baseUrl.isEmpty()) {
			driver.get(baseUrl);
		} else {
			throw new IllegalArgumentException("baseUrl is null or empty");
		}
	}

	@AfterMethod
	public void tearDown() {
		DriverManager.quitDriver();
	}

	private void loadConfig() throws FileNotFoundException {

		if (config != null)
			return;

		config = new Properties();
//		try (FileInputStream file = new FileInputStream("config.properties")) {
			try (FileInputStream file = new FileInputStream("src/test/java/resources/config.properties")) {

			if (file == null) {
				throw new RuntimeException("config.properties file not found in class path");
			}

			config.load(file);

		} catch (Exception ex) {
			throw new RuntimeException("Failed to load config.properties", ex);
		}
	}
	
	
	public WebDriver getDriver() {
		return driver;
	}

}
