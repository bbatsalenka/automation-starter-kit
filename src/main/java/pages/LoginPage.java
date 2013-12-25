package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.Utils;

//NOTE (applicable to Eclipse): if you want to see the method declaration which is used in a different class
//just place the cursor on the method name and click F3 or Fn/ Ctrl F3 (depending on your laptop)
//if there is import missing just click Ctrl + Shift + "O" - do not insert the imports by hand!!!

public class LoginPage extends PageBase {
	// LoginPage constructor that passes the WebDriver instance to
	// PageBase.class
	// and instantiates it's WebElements using PageFactory
	public LoginPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(name = "userid")
	private WebElement userNameInputField;

	@FindBy(name = "pswrd")
	private WebElement passwordInputField;

	@FindBy(xpath = "//input[@value='Login']")
	private WebElement loginButton;

	@FindBy(xpath = "//input[@value='Cancel']")
	private WebElement cancelButton;

	// makes sense to place the login process into a method as it's pretty big
	// and when
	// it's placed in a method the code gets more readable and can be reused
	public void login(String user) {
		// another useful thing is to place values like username and password
		// into a properties file
		// the Utils.getValueFromPropertiesFile() method will find the required
		// value in the file
		// and assign it to a variable
		String username = Utils.getValueFromPropertiesFile(user + "_login",
				"user.properties");
		String password = Utils.getValueFromPropertiesFile(user + "_password",
				"user.properties");
		userNameInputField.sendKeys(username);
		passwordInputField.sendKeys(password);
		loginButton.click();
	}
}
