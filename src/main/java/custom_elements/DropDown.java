package custom_elements;

import org.openqa.selenium.By;
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
		dropDownHeader.click();//кликаем, должен выпасть список
//		try {
		waitUntilElementToBeClickable(driver.findElement(By.xpath("//button[@title=\"" + option + "\"]")));
		driver.findElement(By.xpath("//button[@title=\"" + option + "\"]")).click();//кликаем по элементу списка
		waitUntilElementInvisibly(driver.findElement(By.xpath("//button[@title=\"" + option + "\"]")));
//		} catch (Exception e) {//если словили исключение, пробуем еще раз
//			pause();//ждемс(
//			dropDownHeader.click();
//			waitUntilElementToBeClickable(driver.findElement(By.xpath("//button[@title=\"" + option + "\"]")));
//			driver.findElement(By.xpath("//button[@title=\"" + option + "\"]")).click();
//		}
	}

	/**
	 * Пауза, не лучшее решение =)
	 */
	private void pause() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void waitUntilElementToBeClickable(WebElement element) {
		new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(element));
	}

	private void waitUntilElementInvisibly(WebElement element) {
		new WebDriverWait(driver, 5).until(ExpectedConditions.invisibilityOf(element));
	}
}
