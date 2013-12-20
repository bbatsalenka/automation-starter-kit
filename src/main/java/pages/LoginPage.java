package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.Utils;

public class LoginPage extends PageBase {
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

	public void login(String user) {
		String username = Utils.getValueFromPropertiesFile(user + "_login",
				"user.properties");
		String password = Utils.getValueFromPropertiesFile(user + "_password",
				"user.properties");
		userNameInputField.sendKeys(username);
		passwordInputField.sendKeys(password);
		loginButton.click();
	}
}
