package framework.tests;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import framework.base.BaseTest;
import framework.reporting.ReportManager;
import framework.utils.WaitUtils;
import frameworkpages.LoginPage;
import junit.framework.Assert;

public class LoginTest extends BaseTest {

	@Test
	public void validLogin_shouldNavigateToProductsPage() {

		LoginPage loginPage = new LoginPage(getDriver());

		ReportManager.getTest().log(Status.INFO, "Logging in with standard user");
		loginPage.login("standard_user", "secret_sauce");

		boolean urlChanged =  WaitUtils.waitForUrlContains("inventory.html");
		
		String currentUrl = driver.getCurrentUrl();
		
		ReportManager.getTest().log(Status.INFO, "Verifying navigation to the inventory page");
		
		Assert.assertTrue("Expected to navigate to inventory page, but was:" + currentUrl,
				urlChanged);
		
		Assert.fail();

	}

	@Test
	public void invalidLogin_shouldShowErrorMessage() {
		LoginPage loginPage = new LoginPage(getDriver());

		loginPage.login("locked_out_user", "wrong_password");

		Assert.assertTrue("Excepted error message for Invalid login is not displayed", loginPage.isErrorDisplayed());
	}

}
