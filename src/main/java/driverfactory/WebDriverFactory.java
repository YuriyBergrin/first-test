package driverfactory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class WebDriverFactory {
	private static WebDriver driver;
	private static Logger logger = LogManager.getLogger(WebDriverFactory.class);

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
//		driver.manage().timeouts()
		return driver;
	}

	public static WebDriver create(String webDriverName, ChromeOptions options) {
		WebDriverManager.chromedriver().setup();
		return driver = new ChromeDriver(options);
	}

	public static WebDriver create(String webDriverName, FirefoxOptions options) {
		WebDriverManager.firefoxdriver().setup();
		return driver = new FirefoxDriver(options);
	}
}
