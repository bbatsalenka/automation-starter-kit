package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

//NOTE (applicable to Eclipse): if you want to see the method declaration which is used in a different class
//just place the cursor on the method name and click F3 or Fn/ Ctrl F3 (depending on your laptop)
//if there is import missing just click Ctrl + Shift + "O" - do not insert the imports by hand!!!

public class PerformanceTestPage extends PageBase {
	// PerformanceTestPage constructor that passes the WebDriver instance to
	// PageBase.class
	// and instantiates it's WebElements using PageFactory
	public PerformanceTestPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//img[@src='stress.jpg']")
	private WebElement stressTestingImage;

	public WebElement getStressTestingImage() {
		return stressTestingImage;
	}

}
