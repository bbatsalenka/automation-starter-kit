package pages;

import java.net.HttpURLConnection;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

// each page extends this class that has some common methods and WebElements
public abstract class PageBase {

	protected WebDriver driver;
	protected static final int DEFAULT_WAIT_4_ELEMENT = 25;
	protected static final int DEFAULT_WAIT_4_PAGE = 30;

	// PageBase constructor that initiates WebElement
	public PageBase(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// it makes sense to place this element in PageBase.class as it appears on
	// all pages that extend PageBase.class
	@FindBy(linkText = "Terms of Use")
	private WebElement termsOfUseLink;

	@FindBy(linkText = "Logout")
	private WebElement logoutLink;

	@FindBy(linkText = "Back to load testing")
	private WebElement backToLoadTestingLink;

	public WebElement getTermsOfUseLink() {
		return termsOfUseLink;
	}

	public WebElement getLogoutLink() {
		return logoutLink;
	}

	public WebElement getBackToLoadTestingLink() {
		return backToLoadTestingLink;
	}

	public boolean isFileDownloadable(String link) {
		int code = 0;
		Reporter.log("Link: " + link);
		try {
			URL url = new URL(link);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setRequestMethod("GET");
			code = connection.getResponseCode();
			Reporter.log("Code: " + code);
		} catch (Exception e) {
			Reporter.log(e.toString());
			return false;
		}
		if (link.contains("pager") || code == 403) {
			return true;
		}
		return code == 200 || code == 302;
	}

	public WebDriver getWebDriver() {
		return driver;
	}
}
