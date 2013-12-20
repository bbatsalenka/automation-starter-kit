package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.LoadTestPage;
import pages.PerformanceTestPage;
import templates.TestTemplate;

public class VerifyPagesLoadFine extends TestTemplate {
	private LoadTestPage loadTestPage;
	private PerformanceTestPage performanceTestPage;

	@Test
	public void veifyLoadTestPageLoadsFine() {
		loadTestPage = new LoadTestPage(getRemoteWebDriver());
		String loadTestPageTitle = "Load Testing";
		Assert.assertTrue(getRemoteWebDriver().getTitle().equals(
				loadTestPageTitle));
		loadTestPage.getPerformanceTestingLink().click();
	}

	@Test(dependsOnMethods = { "veifyLoadTestPageLoadsFine" })
	public void verifyPerformancePageLoadsFine() {
		performanceTestPage = new PerformanceTestPage(getRemoteWebDriver());
		String performanceTestPageTitle = "Performance Testing";
		Assert.assertTrue(getRemoteWebDriver().getTitle().equals(
				performanceTestPageTitle));
		performanceTestPage.getBackToLoadTestingLink().click();
	}

	@Test(dependsOnMethods = { "verifyPerformancePageLoadsFine" })
	public void verifyLoadTestingToolsPageLoadsFine() {
		String loadTestingToolsPageTitle = "Load tools";
		loadTestPage.getLoadToolsLink().click();
		performanceTestPage = new PerformanceTestPage(getRemoteWebDriver());
		Assert.assertTrue(getRemoteWebDriver().getTitle().equals(
				loadTestingToolsPageTitle));
	}
}
