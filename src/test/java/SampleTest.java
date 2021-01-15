import config.ServerConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class SampleTest {
	private Logger logger = LogManager.getLogger(SampleTest.class);
	private ServerConfig cfg = ConfigFactory.create(ServerConfig.class);
	protected static WebDriver driver;

	@Before
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		logger.info("Драйвер поднят");
	}

	@Test
	public void checkTitleTest() {
		openPage();
		checkTitle("Онлайн‑курсы для профессионалов, дистанционное обучение современным профессиям");
	}

	@After
	public void setDown() {
		if (driver != null) {
			driver.quit();
			logger.info("Драйвер успешно закрыт");
		}
	}

	/**
	 * проверка заголовка
	 * @param title - ожидаемый заголовок
	 */
	private void checkTitle(String title) {
		logger.info("Проверяем заголовок страницы");
		Assert.assertEquals(driver.getTitle(), title);
	}

	private void openPage() {
		driver.get(cfg.url());
		logger.info("Открыта страница " + cfg.url());
	}
}