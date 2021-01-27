import config.ServerConfig;
import driverfactory.WebDriverFactory;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.remote.BrowserType.CHROME;

public class SampleTest {
	private Logger logger = LogManager.getLogger(SampleTest.class);
	private ServerConfig cfg = ConfigFactory.create(ServerConfig.class);
	private static WebDriver driver;
	private Actions actions;
	private WebDriverWait wait;
	private JavascriptExecutor executor;
	//локаторы
	private By electronics = By.xpath("//span[contains(text(),\"Электроника\")]");
	private By smartphones = By.xpath("//a[contains(text(),\"Смартфоны\")]");
	private By samsung = By.xpath("//span[text() = \"Samsung\"]");
	private By xiaomi = By.xpath("//span[text() = \"Xiaomi\"]");
	private By sortByPrice = By.cssSelector("[data-autotest-id=\"dprice\"]");
	private By firstSamsung = By.xpath("//span[contains(text(),\"Samsung\")]/../../../../.." +
			"//div[contains(@aria-label,\"сравнению\")]");
	private By accept = By.xpath("//*[text()=\"Понятно\"]/..");
	private By added = By.xpath("//*[contains(text(),\"добавлен к сравнению\")]");
	private By firstXiaomi = By.xpath("//span[contains(text(),\"Xiaomi\")]/../../../../.." +
			"//div[contains(@aria-label,\"сравнению\")]");
	private By compare = By.xpath("//span[text() = \"Сравнить\"]/..");
	private By elementInList = By.xpath("//*[@class = \"LwwocgVx0q _2VGDFjE-Ev\"]");


	@Before
	public void setUp() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("start-maximized");
		driver = WebDriverFactory.create(CHROME, options);
		actions = new Actions(driver);
		wait = new WebDriverWait(driver, 5);
		executor = (JavascriptExecutor)driver;
	}

	@Test
	public void yandexMarketTest() {
		driver.get(cfg.urlYandex());
		driver.findElement(electronics).click();
		driver.findElement(smartphones).click();
		driver.findElement(smartphones).click();
		driver.findElement(samsung).click();
		driver.findElement(xiaomi).click();
		driver.findElement(sortByPrice).click();
		driver.findElement(accept).click();
//		добавляем samsung в сравнение
		moveToElement(firstSamsung);
		wait.until(ExpectedConditions.stalenessOf(driver.findElement(firstSamsung)));
		driver.findElement(firstSamsung).click();
		Assert.assertTrue(driver.findElement(added).getText().contains("Samsung"));
//		добавляем xiaomi в сравнение
		moveToElement(firstXiaomi);
		driver.findElement(firstXiaomi).click();
		Assert.assertTrue(driver.findElement(added).getText().contains("Xiaomi"));
//		переход в сравнение
		executor.executeScript("arguments[0].click();", driver.findElement(compare));//обычный клик работает с ошибкой
//		element click intercepted
		Assert.assertEquals(2, driver.findElements(elementInList).size());
	}

	@After
	public void setDown() {
		if (driver != null) {
			driver.quit();
			logger.info("Драйвер успешно закрыт");
		}
	}

	private void moveToElement(By by) {
		actions.moveToElement(driver.findElement(by)).build().perform();
	}
}