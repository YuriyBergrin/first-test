package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Вспомогательный класс - модальное окно для авторизации/регистрации
 */
public class LogInModalWindow extends BasePage {
	public LogInModalWindow(WebDriver driver) {
		super(driver);
	}

	/**
	 * Вебэлементы
	 */
	@FindBy(xpath = "//input[@name=\"email\" and @type=\"text\"]")
	private WebElement emailTextInput;

	@FindBy(css = "input[type=\"password\"]")
	private WebElement passwordTextInput;

	@FindBy(css = ".new-button")
	private WebElement submitButton;
	//Вебэлементы

	/**
	 * Залогиниться в модальном окне
	 */
	public void logIn() {
		logger.info("Авторизовываемся в модальном окне");
		wait.until(ExpectedConditions.visibilityOf(emailTextInput));
		emailTextInput.sendKeys(cfg.userEmail());
		passwordTextInput.sendKeys(cfg.userPassword());
		submitButton.click();
	}
}
