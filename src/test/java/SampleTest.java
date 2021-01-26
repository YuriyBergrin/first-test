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
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.remote.BrowserType.CHROME;

public class SampleTest {
	private Logger logger = LogManager.getLogger(SampleTest.class);
	private ServerConfig cfg = ConfigFactory.create(ServerConfig.class);
	protected static WebDriver driver;

	@Before
	public void setUp() {
		ChromeOptions options = new ChromeOptions();
//		options.addArguments()
		driver = WebDriverFactory.create(CHROME);
	}


	@Test
	public void yandexMarketTest() {
		driver.get(cfg.urlYandex());
	}

	@After
	public void setDown() {
		if (driver != null) {
			driver.quit();
			logger.info("Драйвер успешно закрыт");
		}
	}
}