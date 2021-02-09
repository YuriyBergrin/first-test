package custom_elements;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DropDown {
	private WebDriver driver;
	private By locator;

	public DropDown(WebDriver driver, By locator) {
		this.driver = driver;
		this.locator = locator;
	}

	public void chooseByOption(String option) {
		WebElement dropDownHeader = driver.findElement(locator);
		WebElement item = dropDownHeader.findElement(By.xpath("//..//button[@title=\"" + option + "\"]"));
		dropDownHeader.click();
		item.click();
	}
}
