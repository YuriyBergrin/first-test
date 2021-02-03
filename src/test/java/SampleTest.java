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

public class SampleTest extends BaseTest{

	/**
	 * Локаторы
	 */
	private final By electronics = By.xpath("//span[contains(text(),\"Электроника\")]");
	private final By smartphones = By.xpath("//a[contains(text(),\"Смартфоны\")]");
	private final By samsung = By.xpath("//span[text() = \"Samsung\"]");
	private final By xiaomi = By.xpath("//span[text() = \"Xiaomi\"]");
	private final By sortByPrice = By.cssSelector("[data-autotest-id=\"dprice\"]");
	private final By firstSamsung = By.xpath("//span[contains(text(),\"Samsung\")]/../../../../.." +
			"//div[contains(@aria-label,\"сравнению\")]");
	private final By added = By.xpath("//*[contains(text(),\"добавлен к сравнению\")]");
	private final By firstXiaomi = By.xpath("//span[contains(text(),\"Xiaomi\")]/../../../../.." +
			"//div[contains(@aria-label,\"сравнению\")]");
	private final By compare = By.xpath("//span[text() = \"Сравнить\"]/..");
	private final By elementInList = By.xpath("//*[@class = \"LwwocgVx0q _2VGDFjE-Ev\"]");

	@Test
	public void yandexMarketTest() {
		logger.info("Start test");
		driver.get(cfg.urlYandex());
		clickToElement(electronics);
		clickToElement(smartphones);
		clickToElement(smartphones);
		clickToElement(samsung);
		clickToElement(xiaomi);
		clickToElement(sortByPrice);
//		добавляем samsung в сравнение
		moveToElement(firstSamsung);
		wait.until(ExpectedConditions.stalenessOf(driver.findElement(firstSamsung)));
		clickToElement(firstSamsung);
		Assert.assertTrue(driver.findElement(added).getText().contains("Samsung"));
//		добавляем xiaomi в сравнение
		moveToElement(firstXiaomi);
		clickToElement(firstXiaomi);
		Assert.assertTrue(driver.findElement(added).getText().contains("Xiaomi"));
//		переход в сравнение
		executor.executeScript("arguments[0].click();", driver.findElement(compare));//обычный клик работает с ошибкой
//		element click intercepted, нужно подобрать ожидание
		Assert.assertEquals(2, driver.findElements(elementInList).size());
	}
}