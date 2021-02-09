package pages;

import custom_elements.DropDown;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AboutMePage extends BasePage {
	public AboutMePage(WebDriver driver) {
		super(driver);
	}

	/**
	 * Вебэлементы
	 */
	@FindBy(name = "fname")
	private WebElement firstNameTextInput;
	@FindBy(name = "fname_latin")
	private WebElement latinFirstNameTextInput;
	@FindBy(name = "lname")
	private WebElement lastNameTextInput;
	@FindBy(name = "lname_latin")
	private WebElement latinLastNameTextInput;
	@FindBy(name = "blog_name")
	private WebElement blogNameTextInput;
	@FindBy(name = "date_of_birth")
	private WebElement dateOfBirthTextInput;
	@FindBy(xpath = "//span[@class=\"radio__label\" and text()=\"Да\"]")
	private WebElement readyToRelocateRadioButton;
	@FindBy(xpath = "//span[@class=\"radio__label\" and text()=\"Нет\"]")
	private WebElement unReadyToRelocateRadioButton;
	@FindBy(css = ".js-change-phone")
	private WebElement changePhoneButton;
	@FindBy(xpath = "//*[@class=\"login-modal__container\"]//input[@type=\"text\" and @name=\"phone\"]")
	private WebElement phoneTextInput;
	@FindBy(xpath = "//*[@class=\"login-modal__container\"]//button[@type=\"submit\"]")
	private WebElement submitPhoneButton;
	@FindBy(css = ".dod-phone-modal [class*=\"close\"]")
	private WebElement closeModalWindowButton;
	@FindBy(xpath = "//button[text()=\"Добавить\"]")
	private WebElement addContactButton;

	/**
	 * Методы
	 */
	//заполнение текстовых полей
	public AboutMePage setFirstName(String text) {
		logger.info(String.format("Добавляем имя %s", text));
		firstNameTextInput.clear();
		firstNameTextInput.sendKeys(text);
		return this;
	}

	public AboutMePage setLatinFirstName(String text) {
		logger.info(String.format("Добавляем имя на латинице %s", text));
		latinFirstNameTextInput.clear();
		latinFirstNameTextInput.sendKeys(text);
		return this;
	}

	public AboutMePage setLastName(String text) {
		logger.info(String.format("Добавляем фамилию %s", text));
		lastNameTextInput.clear();
		lastNameTextInput.sendKeys(text);
		return this;
	}

	public AboutMePage setLatinLastName(String text) {
		logger.info(String.format("Добавляем фамлию на латинице %s", text));
		latinLastNameTextInput.clear();
		latinLastNameTextInput.sendKeys(text);
		return this;
	}

	public AboutMePage setBlogName(String text) {
		logger.info(String.format("Добавляем блог имя %s", text));
		blogNameTextInput.clear();
		blogNameTextInput.sendKeys(text);
		return this;
	}

	public AboutMePage setDateOfBirth(String text) {
		logger.info(String.format("Добавляем дату рождения %s", text));
		dateOfBirthTextInput.clear();
		dateOfBirthTextInput.sendKeys(text);
		return this;
	}

	//заполнение дропдаунов
	public AboutMePage setCountry(String country) {
		logger.info(String.format("Выбираем страну %s", country));
		DropDown countryDropDown = new DropDown(driver, By.xpath("//*[@name=\"country\"]/.."));
		countryDropDown.chooseByOption(country);
		return this;
	}

	public AboutMePage setCity(String city) {
		logger.info(String.format("Выбираем город %s", city));
		DropDown cityDropDown = new DropDown(driver, By.xpath("//*[@name=\"city\"]/.."));
		cityDropDown.chooseByOption(city);
		return this;
	}

	public AboutMePage setEnglishLevel(String level) {
		logger.info(String.format("Выбираем уровень языка %s", level));
		DropDown levelDropDown = new DropDown(driver, By.xpath("//*[@name=\"english_level\"]/.."));
		levelDropDown.chooseByOption(level);
		return this;
	}

	//радио баттон
	public AboutMePage setRelocateRadioButton(boolean value) {
		logger.info(String.format("Выбираем радио баттон релокация %s", value));
		if (value) {
			readyToRelocateRadioButton.click();
		} else {
			unReadyToRelocateRadioButton.click();
		}
		return this;
	}

	//чекбоксы
	public AboutMePage setWorkSchedule(String value) {
		logger.info(String.format("Выбираем график %s", value));
		driver.findElement(
				By.xpath("//span[contains(@class,\"work-schedule\") and contains(text(),\"" + value + "\")]")).click();
		return this;
	}

	//телефон
	public AboutMePage setPhone(String phone) {
		logger.info(String.format("Заполняем телефон %s", phone));
		changePhoneButton.click();
		phoneTextInput.clear();
		phoneTextInput.sendKeys(phone);
		submitPhoneButton.click();
		closeModalWindowButton.click();
		return this;
	}

	/**
	 *
	 * @param position - какой по счету контакт заполнить
	 * @param type - тип из выпадающего списка
	 * @param value - текст которым заполняется поле контакт
	 * @return - экземпляр текущей страницы
	 */
	public AboutMePage setNewContact(int position, String type, String value) {
		logger.info(String.format("Добавляем новый контакт %s -  %s -  %s", position, type, value));
		//по умолчанию уже есть пустое поле для контакта, поэтому для первого не надо нажимать "Добавить"
		if (position != 0) {
			addContactButton.click();
		}
		//выбираем тип контакта
		logger.info(String.format("Выбираем тип %s", type));
		DropDown countryDropDown = new DropDown(driver, By.xpath("//*[@name=\"contact-" + position + "-service\"]/.."));
		countryDropDown.chooseByOption(type);
		//заполняем поле контакта
		logger.info(String.format("Заполняем поле контакта %s", value));
		driver.findElement(By.name("contact-" + position + "-value")).sendKeys(value);
		return this;
	}
}
