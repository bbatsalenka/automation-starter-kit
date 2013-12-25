package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

//NOTE (applicable to Eclipse): if you want to see the method declaration which is used in a different class
//just place the cursor on the method name and click F3 or Fn/ Ctrl F3 (depending on your laptop)
//if there is import missing just click Ctrl + Shift + "O" - do not insert the imports by hand!!!

public class Utils {

	// below are hardWait methods - which is a basic Thread.sleep() but just
	// wrapped into methods
	public static void hardWaitMilliSeconds(int milliSeconds) {
		try {
			TimeUnit.MILLISECONDS.sleep(milliSeconds);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void hardWaitSeconds(int seconds) {
		try {
			TimeUnit.SECONDS.sleep(seconds);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void hardWaitMinutes(int minutes) {
		try {
			TimeUnit.MINUTES.sleep(minutes);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// this method is being used in the ScreenShotOnFailure.class
	public static String createDateForFileName() {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH-mm-ss");
		return dateFormat.format(date);
	}

	// method to get a certain value from a certain .properties file
	public static String getValueFromPropertiesFile(String value,
			String fileName) {
		Properties prop = new Properties();
		String selectedItem = null;

		try {
			FileInputStream fileInputStream = new FileInputStream(fileName);
			prop.load(fileInputStream);
			selectedItem = prop.getProperty(value);
			fileInputStream.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return selectedItem;
	}

	// method to select the needed browser
	public static DesiredCapabilities getBrowserInstance(String browserName) {
		switch (browserName) {
		case "firefox": {
			DesiredCapabilities capability = DesiredCapabilities.firefox();
			FirefoxProfile firefoxProfile = new FirefoxProfile();
			capability.setCapability(FirefoxDriver.PROFILE, firefoxProfile);
			return capability;
		}
		case "ie": {
			System.setProperty("webdriver.ie.driver", "C:\\IEDriverServer.exe");
			DesiredCapabilities capability = DesiredCapabilities
					.internetExplorer();
			return capability;
		}
		case "chrome": {
			DesiredCapabilities capability = DesiredCapabilities.chrome();
			System.setProperty("webdriver.chrome.driver",
					"c:\\chromedriver.exe");
			return capability;
		}
		default: {
			DesiredCapabilities capability = DesiredCapabilities.firefox();
			return capability;
		}
		}
	}

	// method to clean a certain directory
	public static void cleanDirectory(String directoryName) throws Exception {
		File directory = new File(directoryName);
		if (!directory.exists()) {
			directory.mkdir();
		}
		FileUtils.cleanDirectory(directory);
	}

}
