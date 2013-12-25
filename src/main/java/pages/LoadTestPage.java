package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

//NOTE (applicable to Eclipse): if you want to see the method declaration which is used in a different class
//just place the cursor on the method name and click F3 or Fn/ Ctrl F3 (depending on your laptop)
//if there is import missing just click Ctrl + Shift + "O" - do not insert the imports by hand!!!

public class LoadTestPage extends PageBase {
	// LoadTestPage constructor that passes the WebDriver instance to
	// PageBase.class
	// and instantiates it's WebElements using PageFactory
	public LoadTestPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	// Below are WebElements located on the LoadTestPage and getters to access
	// the elements
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
