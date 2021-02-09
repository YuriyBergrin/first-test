import driverfactory.WebDriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.AboutMePage;
import pages.OtusMainPage;
import pages.PersonalAccountPage;

import static org.openqa.selenium.remote.BrowserType.CHROME;

public class BaseTest {
	protected final Logger logger = LogManager.getLogger(BaseTest.class);
	protected WebDriver driver;

	/**
	 * Страницы используемые в тестах
	 */
	protected OtusMainPage otusMainPage;
	protected PersonalAccountPage personalAccountPage;
	protected AboutMePage aboutMePage;

	@Before
	public void setUp() {
		logger.info("Set up test");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("start-maximized");
		driver = WebDriverFactory.create(CHROME, options);
		initPages();
	}

	@After
	public void setDown() {
		logger.info("Finish test");
		if (driver != null) {
//			driver.quit();
			logger.info("Драйвер успешно закрыт");
		}
	}

	/**
	 * инициализация страниц, которые используются в тестах
	 */
	private void initPages() {
		otusMainPage = new OtusMainPage(driver);
		personalAccountPage = new PersonalAccountPage(driver);
		aboutMePage = new AboutMePage(driver);
	}

	protected void deleteCookies() {
		driver.manage().deleteAllCookies();
	}
}
