package tests;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import pages.LoadTestPage;
import templates.TestTemplate;

public class FilesDownloadVerification extends TestTemplate {
	private LoadTestPage loadTestPage;

	@Test(priority = 0)
	public void isUserLoggedIn() {
		loadTestPage = new LoadTestPage(getRemoteWebDriver());
		Reporter.log("Load testing page title: "
				+ getRemoteWebDriver().getTitle());
		Assert.assertTrue(getRemoteWebDriver().getTitle()
				.equals("Load Testing"));
	}

	@Test(dependsOnMethods = { "isUserLoggedIn" }, priority = 1)
	public void isLoadTestTutorialDownloadable() {
		String loadTestTutorialLink = loadTestPage
				.getLoadTestingTutorialDownloadLink().getAttribute("href");
		Assert.assertTrue(loadTestPage.isFileDownloadable(loadTestTutorialLink));
	}

	@Test(priority = 2)
	public void isPerformanceTestTutorialDownloadable() {
		String performanceTestTutorialLink = loadTestPage
				.getPerformanceTestingTutorialDownloadLink().getAttribute(
						"href");
		Assert.assertTrue(loadTestPage
				.isFileDownloadable(performanceTestTutorialLink));
	}
}
