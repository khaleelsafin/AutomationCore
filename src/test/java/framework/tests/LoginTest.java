package framework.tests;

import org.testng.annotations.Test;

import framework.base.BaseTest;
import framework.utils.WaitUtils;
import frameworkpages.LoginPage;
import junit.framework.Assert;

public class LoginTest extends BaseTest {

	@Test
	public void validLogin_shouldNavigateToProductsPage() {

		LoginPage loginPage = new LoginPage(getDriver());

		loginPage.login("standard_user", "secret_sauce");

		String currentUrl = driver.getCurrentUrl();
		
		boolean urlChanged =  WaitUtils.waitForUrlContains("inventory.html");
		Assert.assertTrue("Expected to navigate to inventory page, but was:" + currentUrl,
				urlChanged);

	}

	@Test
	public void invalidLogin_shouldShowErrorMessage() {
		LoginPage loginPage = new LoginPage(getDriver());

		loginPage.login("locked_out_user", "wrong_password");

		Assert.assertTrue("Excepted error message for Invalid login is not displayed", loginPage.isErrorDisplayed());
	}

}
