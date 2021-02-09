package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PersonalAccountPage extends BasePage{
	public PersonalAccountPage(WebDriver driver) {
		super(driver);
	}

	/**
	 * Вебэлементы
	 */
	@FindBy(css = "[title=\"О себе\"]")
	private WebElement aboutMeTitle;

	/**
	 * Методы
	 */
	public void gotoAboutMe() {
		aboutMeTitle.click();
	}
}
