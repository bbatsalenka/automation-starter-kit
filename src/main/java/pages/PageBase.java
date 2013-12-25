package pages;

import java.net.HttpURLConnection;
import java.net.URL;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

//NOTE (applicable to Eclipse): if you want to see the method declaration which is used in a different class
//just place the cursor on the method name and click F3 or Fn/ Ctrl F3 (depending on your laptop)
//if there is import missing just click Ctrl + Shift + "O" - do not insert the imports by hand!!!

// each page extends this class that has some common methods and WebElements
public abstract class PageBase {

	protected WebDriver driver;

	// PageBase constructor that initiates WebElement, it gets the WebDriver
	// instance from other pages
	// when the ones are instantiated
	public PageBase(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// it makes sense to place this element in PageBase.class as it appears on
	// all pages that extend PageBase.class
	@FindBy(linkText = "Terms of Use")
	private WebElement termsOfUseLink;

	// this element is located on most of the pages, even though it's not
	// located on LoginPage
	// it still makes sense to place it here so that all the pages could inherit
	// it
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

	// this method is useful when you have multiple links on a page and instead
	// of click each link
	// and verifying the page title or what's written on the page - you can just
	// send an http request
	// to the link and see what the response is
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
		return code == 200 || code == 302;
	}

	public WebDriver getWebDriver() {
		return driver;
	}
}
