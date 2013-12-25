package pages;

import org.openqa.selenium.WebDriver;

//NOTE (applicable to Eclipse): if you want to see the method declaration which is used in a different class
//just place the cursor on the method name and click F3 or Fn/ Ctrl F3 (depending on your laptop)
//if there is import missing just click Ctrl + Shift + "O" - do not insert the imports by hand!!!

public class LoadTestingToolsPage extends PageBase {
	// It's a dummy page as in the tests it's used to show how WebDriver will
	// identify a 404 error when the page is not found
	public LoadTestingToolsPage(WebDriver driver) {
		super(driver);
	}
}
