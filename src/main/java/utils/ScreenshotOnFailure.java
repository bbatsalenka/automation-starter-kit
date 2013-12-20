package utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.testng.IClass;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import templates.TestTemplate;

public class ScreenshotOnFailure extends TestListenerAdapter {

	@Override
	public void onTestFailure(ITestResult iTestResult) {
		ITestNGMethod method = iTestResult.getMethod();
		String methodName = method.getMethodName();
		IClass iClassObject = iTestResult.getTestClass();
		String className = iClassObject.getRealClass().toString();
		System.out.println(iTestResult.getInstance().toString());
		Object currentClass = iTestResult.getInstance();
		WebDriver webDriver = ((TestTemplate) currentClass)
				.getRemoteWebDriver();
		File screenshot = ((TakesScreenshot) new Augmenter().augment(webDriver))
				.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(
					screenshot,
					new File("screenshots\\method " + methodName + " from "
							+ className + " failed "
							+ Utils.createDateForFileName() + ".png"));
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
