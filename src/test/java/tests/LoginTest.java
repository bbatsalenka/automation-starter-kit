package tests;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import templates.TestTemplate;

//NOTE (applicable to Eclipse): if you want to see the method declaration which is used in a different class
//just place the cursor on the method name and click F3 or Fn/ Ctrl F3 (depending on your laptop)
//if there is import missing just click Ctrl + Shift + "O" - do not insert the imports by hand!!!

public class LoginTest extends TestTemplate {

	// the simplest login test
	// as you remember the login is done in the TestTemplate
	// so here we make sure user is logged in by verifying the title of
	// LoadTestingPage as
	// / it is displayed after successful login
	@Test
	public void isUserLoggedIn() {
		// we user Reporter.log() to add some information to our test report
		// generated at the end of tests
		// execution
		Reporter.log("Load testing page title: "
				+ getRemoteWebDriver().getTitle());
		// we user Assert.assertTrue() testNG method to assert a statement
		// if it fails - the test will be displayed as failed
		Assert.assertTrue(getRemoteWebDriver().getTitle()
				.equals("Load Testing"));
	}

}
