package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class PerformanceTestPage extends PageBase {

	public PerformanceTestPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

}
