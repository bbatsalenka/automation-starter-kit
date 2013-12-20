package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoadTestPage extends PageBase {
	public LoadTestPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(linkText = "Performance testing definition")
	private WebElement performanceTestingLink;

	@FindBy(linkText = "Stress testing definition")
	private WebElement stressTestingLink;

	@FindBy(linkText = "Download load testing tutorial")
	private WebElement loadTestingTutorialDownloadLink;

	@FindBy(linkText = "Download performance testing tutorial")
	private WebElement performanceTestingTutorialDownloadLink;

	@FindBy(linkText = "Load testing tools")
	private WebElement loadToolsLink;

	public WebElement getLoadToolsLink() {
		return loadToolsLink;
	}

	public WebElement getPerformanceTestingLink() {
		return performanceTestingLink;
	}

	public WebElement getStressTestingLink() {
		return stressTestingLink;
	}

	public WebElement getLoadTestingTutorialDownloadLink() {
		return loadTestingTutorialDownloadLink;
	}

	public WebElement getPerformanceTestingTutorialDownloadLink() {
		return performanceTestingTutorialDownloadLink;
	}
}
