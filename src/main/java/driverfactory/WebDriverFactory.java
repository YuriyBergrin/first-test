package driverfactory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.concurrent.TimeUnit;

public class WebDriverFactory {
	private static final Logger logger = LogManager.getLogger(WebDriverFactory.class);
	private static final int IMPLICIT_TIMEOUT_TIME = 5;

	/**
	 * инициализация драйвера без опций
	 *
	 * @param webDriverName - имя драйвер
	 * @return - драйвер
	 */
	public static WebDriver create(String webDriverName) {
		webDriverName = webDriverName.toUpperCase().replaceAll("'", "");
		logger.info(String.format("BROWSER IS %s", webDriverName));

		Browsers browser = Browsers.valueOf(webDriverName);

		WebDriver driver;

		switch (browser) {
			case CHROME:
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				break;
			case FIREFOX:
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				break;
			default:
				logger.info("Unknown browser name");
				driver = null;
		}
		driver.manage().timeouts().implicitlyWait(IMPLICIT_TIMEOUT_TIME, TimeUnit.SECONDS);
		logger.info(String.format("Return %s driver without options", webDriverName));
		return driver;
	}

	/**
	 * инициализация драйвера chrome с опциями
	 *
	 * @param webDriverName - имя драйвера
	 * @param options       - опции
	 * @return - драйвер
	 */
	public static WebDriver create(String webDriverName, ChromeOptions options) {
		logger.info(String.format("Return %s with options", webDriverName));
		WebDriver driver;
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(IMPLICIT_TIMEOUT_TIME, TimeUnit.SECONDS);
		return driver;
	}

	/**
	 * инициализация драйвера firefox с опциями
	 *
	 * @param webDriverName - имя драйвера
	 * @param options       - опции
	 * @return - драйвер
	 */
	public static WebDriver create(String webDriverName, FirefoxOptions options) {
		logger.info(String.format("Return %s with options", webDriverName));
		WebDriver driver;
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver(options);
		driver.manage().timeouts().implicitlyWait(IMPLICIT_TIMEOUT_TIME, TimeUnit.SECONDS);
		return driver;
	}
}
