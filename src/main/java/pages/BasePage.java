package pages;

import config.ServerConfig;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	protected final Logger logger = LogManager.getLogger(BasePage.class);
	protected final ServerConfig cfg = ConfigFactory.create(ServerConfig.class);
	protected WebDriver driver;
	protected Actions actions;
	protected WebDriverWait wait;
	protected JavascriptExecutor executor;
	protected final static int EXPLICIT_WAIT_TIME_OUT_TIME = 5;
	protected String url;

	/**
	 * Вебэлементы
	 */
	@FindBy(css = "button[data-modal-id=\"new-log-reg\"]")
	private WebElement logRegButton;
	//Вебэлементы

	public BasePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
		actions = new Actions(driver);
		wait = new WebDriverWait(driver, EXPLICIT_WAIT_TIME_OUT_TIME);
		executor = (JavascriptExecutor) driver;
	}

	/**
	 * метод позволяет навести курсор на элемент
	 *
	 * @param element - web элемент
	 */
	protected void moveToElement(WebElement element) {
		logger.info(String.format("Наводим курсор на элемент %s", element));
		actions.moveToElement(element).build().perform();
	}

	/**
	 * Открыть страницу
	 *
	 * @return экземпляр класса страницы
	 */
	public BasePage open() {
		logger.info(String.format("Переходим на %s", url));
		driver.get(url);
		return this;
	}

	/**
	 * Авторизоваться
	 */
	public BasePage auth() {
		logger.info("Кликаем вход/регистрация и переходим к модальному окну");
		wait.until(ExpectedConditions.elementToBeClickable(logRegButton)).click();
		try {
			new LogInModalWindow(driver).logIn();
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(logRegButton)).click();
			new LogInModalWindow(driver).logIn();//todo стал тупить сайт
		}
		return this;
	}

	/**
	 * почистить cookies
	 */
	public void clearCookies() {
		logger.info("Чистим куки");
		driver.manage().deleteAllCookies();
	}

	/**
	 * пауза
	 */
	protected void pause(int mls) {
		try {
			Thread.sleep(mls);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
