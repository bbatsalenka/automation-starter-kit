package tests;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import templates.TestTemplate;

public class LoginTest extends TestTemplate {

	@Test
	public void isUserLoggedIn() {
		Reporter.log("Load testing page title: "
				+ getRemoteWebDriver().getTitle());
		Assert.assertTrue(getRemoteWebDriver().getTitle()
				.equals("Load Testing"));
	}

}
