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

import java.util.concurrent.TimeUnit;

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
		wait = new WebDriverWait(driver,10);
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
	public void lesson6() {
		driver = WebDriverFactory.create("FIREFOX");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://otus.ru");
		driver.findElement(By.xpath("//button[@data-modal-id]")).click();
		driver.findElement(By.xpath("(//input[@placeholder = \"Электронная почта\"])[3]")).sendKeys("fasekel644@pashter.com");
		driver.findElement(By.cssSelector("input[type = \"password\"]")).sendKeys("qwerty123456789");
		driver.findElement(By.xpath("(//button[@type = \"submit\"])[3]")).click();
		driver.findElement(By.cssSelector("div[class*=\"username\"]")).click();
		driver.findElement(By.cssSelector("a[title = \"Личный кабинет\"]")).click();
		driver.findElement(By.cssSelector("a[title = \"О себе\"]")).click();
		driver.findElement(By.cssSelector("button[name = \"agreement\"]")).click();
		driver.findElement(By.id("id_fname")).clear();
		driver.findElement(By.id("id_fname")).sendKeys("Ivan");
		driver.findElement(By.id("id_lname")).clear();
		driver.findElement(By.id("id_lname")).sendKeys("Ivan");
		driver.findElement(By.cssSelector("buttton[title = \"Сохранить и продолжить\"]")).click();
	}

	@After
	public void setDown() {
		if (driver != null) {
//			driver.quit();
			logger.info("Драйвер успешно закрыт");
		}
	}
}