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

public class TestTemplate {

	private DesiredCapabilities capability;
	private ThreadLocal<RemoteWebDriver> threadDriver = null;
	private static int broswerInitiationAttemptsCounter = 0;
	protected LoginPage loginPage;

	@BeforeSuite(alwaysRun = true)
	public void prepareSuiteForTesting() throws Exception {
		Utils.cleanDirectory("screenshots");
	}

	@Parameters({ "browser", "port", "ipAddress", "user", "environment" })
	@BeforeClass(alwaysRun = true)
	public synchronized void beforeTest(String browser, String port,
			String ipAddress, String user, String environment) throws Exception {
		threadDriver = new ThreadLocal<RemoteWebDriver>();
		capability = Utils.getBrowserInstance(browser);
		setDriver(capability, browser, ipAddress, port);
		getRemoteWebDriver().manage().timeouts()
				.implicitlyWait(35, TimeUnit.SECONDS);
		getRemoteWebDriver().manage().window().maximize();
		getRemoteWebDriver().get(Utils.getValueFromPropertiesFile(environment, "environment.properties"));
		loginPage = new LoginPage(getRemoteWebDriver());
		loginPage.login(user);
	}

	@AfterClass(alwaysRun = true)
	public synchronized void afterSuite() throws Exception {
		getRemoteWebDriver().quit();
	}

	public WebDriver getRemoteWebDriver() {
		return threadDriver.get();
	}

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
