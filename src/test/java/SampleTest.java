import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

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
		logger.info("Проверяем что samsung добавлен в сравнение");
		Assert.assertTrue(driver.findElement(added).getText().contains("Samsung"));
//		добавляем xiaomi в сравнение
		moveToElement(firstXiaomi);
		clickToElement(firstXiaomi);
		logger.info("Проверяем что Xiaomi добавлен в сравнение");
		Assert.assertTrue(driver.findElement(added).getText().contains("Xiaomi"));
//		переход в сравнение
		executor.executeScript("arguments[0].click();", driver.findElement(compare));//обычный клик работает с ошибкой
//		element click intercepted, нужно подобрать ожидание
		logger.info("Проверяем что в сравнение 2 товара");
		Assert.assertEquals(2, driver.findElements(elementInList).size());
	}
}