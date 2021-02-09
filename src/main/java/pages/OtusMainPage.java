package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OtusMainPage extends BasePage {
	public OtusMainPage(WebDriver driver) {
		super(driver);
		url = cfg.urlOtus();//задаем url данной страницы
	}

	/**
	 * Вебэлементы
	 */
	@FindBy(css = ".header2-menu__item-text__username")
	private WebElement userNameLabel;

	@FindBy(css = "[title=\"Личный кабинет\"]")
	private WebElement personalAccountOption;

	/**
	 * Открыть страницу и получить экземпляр страницы
	 *
	 * @return экземпляр страницы
	 */
	@Override
	public OtusMainPage open() {
		super.open();
		return this;
	}

	/**
	 * авторизация
	 * @return экземпляр текущей страницы
	 */
	@Override
	public OtusMainPage auth() {
		super.auth();
		return this;
	}

	/**
	 * переход в личный кабинет
	 * @return экземпляр текущей страницы
	 */
	public OtusMainPage gotoPersonalAccount() {
		logger.info("Переходим в личный кабинет");
		moveToElement(userNameLabel);
		personalAccountOption.click();
		return this;
	}
}
