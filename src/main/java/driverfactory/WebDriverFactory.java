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
	private static WebDriver driver;
	private static Logger logger = LogManager.getLogger(WebDriverFactory.class);

	/**
	 * инициализация драйвера без опций
	 * @param webDriverName - имя драйвер
	 * @return
	 */
	public static WebDriver create(String webDriverName) {
		webDriverName = webDriverName.toUpperCase().replaceAll("'", "");
		logger.info("BROWSER IS " + webDriverName);
		logger.info("RETURN NEW " + Browsers.valueOf(webDriverName));
		switch (webDriverName) {
			case "CHROME":
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				break;
			case "FIREFOX":
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				break;
			default:
				logger.info("Unknown browser name");
				driver = null;
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		logger.info("Return " + webDriverName + " driver without options");
		return driver;
	}

	/**
	 * инициализация драйвера chrome с опциями
	 * @param webDriverName - имя драйвер
	 * @param options - опции
	 * @return
	 */
	public static WebDriver create(String webDriverName, ChromeOptions options) {
		logger.info("Return chrome with options");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		return driver;
	}

	/**
	 * инициализация драйвера firefox с опциями
	 * @param webDriverName - имя драйвер
	 * @param options - опции
	 * @return
	 */
	public static WebDriver create(String webDriverName, FirefoxOptions options) {
		logger.info("Return firefox with options");
		WebDriverManager.firefoxdriver().setup();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver = new FirefoxDriver(options);
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		return driver;
	}
}
