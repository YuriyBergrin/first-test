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
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;


public class SampleTest {
	private Logger logger = LogManager.getLogger(SampleTest.class);
	private ServerConfig cfg = ConfigFactory.create(ServerConfig.class);
	protected static WebDriver driver;
	String webDriverBrowserName;

	@Before
	public void setUp() {
		webDriverBrowserName = System.getProperty("browser");
	}

	@Test
	public void webDriverFactoryTest() {
		driver = WebDriverFactory.create(webDriverBrowserName);
		openPage();
	}

	@Test
	public void webDriverFactoryWithOptionsTest() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("start-maximized");
		driver = WebDriverFactory.create(webDriverBrowserName, options);
		driver.get("https://yandex.ru");
	}

	@After
	public void setDown() {
		if (driver != null) {
			driver.quit();
			logger.info("Драйвер успешно закрыт");
		}
	}

	private void openPage() {
		driver.get(cfg.url());
		logger.info("Открыта страница " + cfg.url());
	}
}