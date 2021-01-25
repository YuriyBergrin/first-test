import config.ServerConfig;
import driverfactory.WebDriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class SampleTest {
	//неявные ожидание в классе driver factory
	private Logger logger = LogManager.getLogger(SampleTest.class);
	private ServerConfig cfg = ConfigFactory.create(ServerConfig.class);
	protected static WebDriver driver;
	private String webDriverBrowserName;
	private WebDriverWait wait;//явное ожидание

	@Before
	public void setUp() {
		webDriverBrowserName = System.getProperty("browser");//драйвер задается командой
		// например clean test -Dbrowser='ChRomE'
		//если просто clean test то по умолчанию будет firefox, указал в помнике
	}

	@Test//без опций с явным ожиданием
	public void webDriverFactoryTest() {
		driver = WebDriverFactory.create(webDriverBrowserName);
		driver.get("https://msk.tele2.ru/shop/number");
		driver.findElement(By.id("searchNumber")).sendKeys("97");
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("catalog-numbers")));
	}

	@Test//с опциями
	public void webDriverFactoryWithOptionsTest() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("start-maximized");
		//инициализируем драйвер с опциями
		driver = WebDriverFactory.create(webDriverBrowserName, options);
		driver.get("https://yandex.ru");
		Assert.assertEquals("Яндекс", driver.getTitle());
	}

	@Test
	public void testBootStrap() throws InterruptedException {

		ChromeOptions options = new ChromeOptions();
		options.addArguments("start-maximized");
		//инициализируем драйвер с опциями
		driver = WebDriverFactory.create(webDriverBrowserName, options);

		final String url = "https://ng-bootstrap.github.io/#/components/alert/examples ";
		final String ERROR_MESSAGE = "Message equal";
		String button = "//button[contains(text(),'Change message')]";
		String alertTextBefore;
		String alertTextAfter;

		driver.get(url);
		logger.info(String.format("Открыта станица %s", url));
		WebElement element = driver.findElement(By.xpath(button));
		logger.info("Кнопка найдена");

		alertTextBefore = getAlertText(element);
		logger.info(String.format("Alert %s", alertTextBefore));

		Thread.sleep(1500);

		alertTextAfter = getAlertText(element);
		logger.info(String.format("Alert %s", alertTextAfter));

		Assert.assertNotEquals(alertTextBefore, alertTextAfter, ERROR_MESSAGE);

	}

	private String getAlertText(WebElement element) {
		String alert = "//ngb-alert[contains(text(), 'Message successfully changed')]";
		element.click();
		WebElement alertBox = driver.findElement(By.xpath(alert));
		new WebDriverWait(driver, 4).until(visibilityOf(alertBox));
		return alertBox.getText();
	}


	@After
	public void setDown() {
		if (driver != null) {
			driver.quit();
			logger.info("Драйвер успешно закрыт");
		}
	}
}