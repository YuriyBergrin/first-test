import config.ServerConfig;
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

import java.util.concurrent.TimeUnit;


public class SampleTest {
	private Logger logger = LogManager.getLogger(SampleTest.class);
	private ServerConfig cfg = ConfigFactory.create(ServerConfig.class);
	protected static WebDriver driver;

	@Before
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		logger.info("Драйвер поднят");
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	}

	@Test
	public void checkTitleTest() {
		openPage();
		checkTitle("Онлайн‑курсы для профессионалов, дистанционное обучение современным профессиям");
	}

	@Test
	public void runMaximizeSize() {
		driver.manage().window().maximize();
		openPage();
		logger.info(driver.manage().window().getSize());
	}

	@Test
	public void run800x600Size() {
		openPage();
		driver.manage().window().setSize(new Dimension(800, 600));
		logger.info(driver.manage().window().getPosition());
	}

	@Test
	public void run800x600SizeAndMove() {
		openPage();
		driver.manage().window().setSize(new Dimension(800, 600));
		logger.info(driver.manage().window().getPosition());
		Point point = driver.manage().window().getPosition();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		point.x = point.x + 100;
		point.y = point.y + 100;
		driver.manage().window().setPosition(point);
		logger.info(driver.manage().window().getPosition());
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		point.x = point.x - 100;
		point.y = point.y - 100;
		driver.manage().window().setPosition(point);
		logger.info(driver.manage().window().getPosition());
	}

	@Test
	public void Cookie() {
		openPage();
		driver.manage().addCookie(new Cookie("Otus1", "Value1"));
		driver.manage().addCookie(new Cookie("Otus2", "Value2"));
		Cookie cookie = new Cookie("Otus3","Value3");
		driver.manage().addCookie(cookie);
		driver.manage().addCookie(new Cookie("Otus4", "Value4"));

		logger.info(driver.manage().getCookies());
		logger.info(driver.manage().getCookieNamed("Otus1"));
		driver.manage().deleteCookieNamed("Otus2");
		driver.manage().deleteCookie(cookie);
		driver.manage().deleteAllCookies();
		logger.info(driver.manage().getCookies().size());

	}

	@After
	public void setDown() {
		if (driver != null) {
//			driver.quit();
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