package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.LoadTestPage;
import pages.PerformanceTestPage;
import templates.TestTemplate;

//NOTE (applicable to Eclipse): if you want to see the method declaration which is used in a different class
//just place the cursor on the method name and click F3 or Fn/ Ctrl F3 (depending on your laptop)
//if there is import missing just click Ctrl + Shift + "O" - do not insert the imports by hand!!!

public class VerifyPagesLoadFine extends TestTemplate {
	private LoadTestPage loadTestPage;
	private PerformanceTestPage performanceTestPage;

	// the purpose of the methods in this class is to
	// make sure that each page loads fine
	// it's done by verifying the page title
	// one of the tests will fail on purpose

	// this test will pass
	@Test
	public void veifyLoadTestPageLoadsFine() {
		loadTestPage = new LoadTestPage(getRemoteWebDriver());
		String loadTestPageTitle = "Load Testing";
		Assert.assertTrue(getRemoteWebDriver().getTitle().equals(
				loadTestPageTitle));
		loadTestPage.getPerformanceTestingLink().click();
	}

	// this test will pass
	@Test(dependsOnMethods = { "veifyLoadTestPageLoadsFine" })
	public void verifyPerformancePageLoadsFine() {
		performanceTestPage = new PerformanceTestPage(getRemoteWebDriver());
		String performanceTestPageTitle = "Performance Testing";
		Assert.assertTrue(getRemoteWebDriver().getTitle().equals(
				performanceTestPageTitle));
		performanceTestPage.getBackToLoadTestingLink().click();
	}

	// this test fails on purpose to show how WebDriver will identify and report
	// a test failure
	@Test(dependsOnMethods = { "verifyPerformancePageLoadsFine" })
	public void verifyLoadTestingToolsPageLoadsFine() {
		String loadTestingToolsPageTitle = "Load tools";
		loadTestPage.getLoadToolsLink().click();
		performanceTestPage = new PerformanceTestPage(getRemoteWebDriver());
		Assert.assertTrue(getRemoteWebDriver().getTitle().equals(
				loadTestingToolsPageTitle));
	}
}
