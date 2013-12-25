package templates;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import pages.LoginPage;
import utils.Utils;

//NOTE (applicable to Eclipse): if you want to see the method declaration which is used in a different class
//just place the cursor on the method name and click F3 or Fn/ Ctrl F3 (depending on your laptop)
//if there is import missing just click Ctrl + Shift + "O" - do not insert the imports by hand!!!

public class TestTemplate {

	private DesiredCapabilities capability;
	private ThreadLocal<RemoteWebDriver> threadDriver = null;
	private static int broswerInitiationAttemptsCounter = 0;
	protected LoginPage loginPage;

	@BeforeSuite(alwaysRun = true)
	// clean out the "screenshots" folder before testing
	public void prepareSuiteForTesting() throws Exception {
		Utils.cleanDirectory("screenshots");
	}

	// pass the test parameters to @BeforeClass()
	@Parameters({ "browser", "port", "ipAddress", "user", "environment" })
	@BeforeClass(alwaysRun = true)
	public synchronized void beforeTest(String browser, String port,
			String ipAddress, String user, String environment) throws Exception {
		// WebDriver is set as ThreadLocal<RemoteWebDriver>() to protect it
		// during
		// parallel tests run
		threadDriver = new ThreadLocal<RemoteWebDriver>();
		// here we select which browser we want to use
		capability = Utils.getBrowserInstance(browser);
		// here we set the RemoteWebDriver to be able to use it in Selenium Grid
		setDriver(capability, browser, ipAddress, port);
		// here we set the timeout using implicit wait for an element to be
		// found
		getRemoteWebDriver().manage().timeouts()
				.implicitlyWait(35, TimeUnit.SECONDS);
		// here we maximize the window
		getRemoteWebDriver().manage().window().maximize();
		// here we get the testing environment url from .properties file
		getRemoteWebDriver().get(
				Utils.getValueFromPropertiesFile(environment,
						"environment.properties"));
		// instantiate the loginPage and it's WebElements
		loginPage = new LoginPage(getRemoteWebDriver());
		// perform login
		loginPage.login(user);
	}

	@AfterClass(alwaysRun = true)
	public synchronized void afterSuite() throws Exception {
		getRemoteWebDriver().quit();
	}

	public WebDriver getRemoteWebDriver() {
		return threadDriver.get();
	}

	// method for setting the RemoteWebDriver
	public void setDriver(DesiredCapabilities capability, String browser,
			String ipAddress, String port) {
		try {
			threadDriver.set(new RemoteWebDriver(new URL("http://" + ipAddress
					+ ":" + port + "/wd/hub"), capability));
		} catch (Exception e) {
			if (broswerInitiationAttemptsCounter < 10) {
				broswerInitiationAttemptsCounter++;
				Reporter.log("===Browser initiation failed===");
				Utils.hardWaitSeconds(2);
				setDriver(capability, browser, ipAddress, port);
			} else {
				Reporter.log("=== Unable to bind to locking port 7054 after 10 attempts ===");
				throw new WebDriverException();
			}
		}
	}

}
