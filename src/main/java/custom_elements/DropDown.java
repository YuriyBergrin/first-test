package custom_elements;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Класс для дропдаунов сайта отус
 * строится с помощью драйвера и локатора, по клику на который выпадет список
 */
public class DropDown {
	private WebDriver driver;
	private By locator;

	public DropDown(WebDriver driver, By locator) {
		this.driver = driver;
		this.locator = locator;
	}

	/**
	 * Выбор элемента из выпадающего списка
	 *
	 * @param option - текст по которому выбирается элемент дропдауна
	 */
	public void chooseByOption(String option) {
		WebElement dropDownHeader = driver.findElement(locator);//элемент из которого выпадет список
		waitUntilElementToBeClickable(dropDownHeader);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", dropDownHeader);
		try {
			executor.executeScript("arguments[0].click();", dropDownHeader);
		} catch (Exception e) {

		}
		executor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//button[@title=\"" + option + "\"]")));
		waitUntilElementInvisibly(driver.findElement(By.xpath("//button[@title=\"" + option + "\"]")));
	}

	private void waitUntilElementToBeClickable(WebElement element) {
		new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(element));
	}

	private void waitUntilElementInvisibly(WebElement element) {
		new WebDriverWait(driver, 5).until(ExpectedConditions.invisibilityOf(element));
	}
}
