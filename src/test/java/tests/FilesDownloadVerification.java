package tests;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import pages.LoadTestPage;
import templates.TestTemplate;

//NOTE (applicable to Eclipse): if you want to see the method declaration which is used in a different class
//just place the cursor on the method name and click F3 or Fn/ Ctrl F3 (depending on your laptop)
//if there is import missing just click Ctrl + Shift + "O" - do not insert the imports by hand!!!

public class FilesDownloadVerification extends TestTemplate {
	private LoadTestPage loadTestPage;

	// we use priority = some value to prioritize the order of test execution
	@Test(priority = 0)
	public void isUserLoggedIn() {
		loadTestPage = new LoadTestPage(getRemoteWebDriver());
		Reporter.log("Load testing page title: "
				+ getRemoteWebDriver().getTitle());
		Assert.assertTrue(getRemoteWebDriver().getTitle()
				.equals("Load Testing"));
	}

	// we use dependsOnMethods to structure our tests and to make sure that a
	// method
	// that had dependsOnMethods will be only executed if the method it depends
	// on did not fail,
	// otherwise the remaining tests that have dependencies will be skipped
	@Test(dependsOnMethods = { "isUserLoggedIn" }, priority = 1)
	public void isLoadTestTutorialDownloadable() {
		// here we get the "href" attribute of the load testing tutorial
		// download link which is the
		// link of the download file
		String loadTestTutorialLink = loadTestPage
				.getLoadTestingTutorialDownloadLink().getAttribute("href");
		// if the http response is 200 or 302 the method isFileDownloadable will
		// return true
		// otherwise it will return false
		Assert.assertTrue(loadTestPage.isFileDownloadable(loadTestTutorialLink));
	}

	@Test(priority = 2)
	public void isPerformanceTestTutorialDownloadable() {
		String performanceTestTutorialLink = loadTestPage
				.getPerformanceTestingTutorialDownloadLink().getAttribute(
						"href");
		// here we do the same and by design the test should fail as the file is
		// not found and 404 response is returned
		Assert.assertTrue(loadTestPage
				.isFileDownloadable(performanceTestTutorialLink));
	}
}
