package frameworkpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

	private final WebDriver driver;

	// locators
	private final By txtUsername = By.id("user-name");
	private final By txtPassword = By.id("password");
	private final By btnLogin = By.id("login-button");
	private final By lblErrorMessage = By.cssSelector("[data-test='error']");

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	public void login(String userName, String Password) {

		driver.findElement(txtUsername).sendKeys(userName);
		driver.findElement(txtPassword).sendKeys(Password);

		driver.findElement(btnLogin).click();
	}

	public boolean isErrorDisplayed() {
		return !driver.findElements(lblErrorMessage).isEmpty();
	}
}
