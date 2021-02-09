package pages;

import custom_elements.DropDown;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AboutMePage extends BasePage{
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

	/**
	 * Методы
	 */
	//заполнение тектсовых полей
	public AboutMePage setLatinFirstName(String text) {
		firstNameTextInput.clear();
		firstNameTextInput.sendKeys(text);
		return this;
	}
	public AboutMePage setFirstName(String text) {
		latinFirstNameTextInput.clear();
		latinFirstNameTextInput.sendKeys(text);
		return this;
	}
	public AboutMePage setLastName(String text) {
		lastNameTextInput.clear();
		lastNameTextInput.sendKeys(text);
		return this;
	}
	public AboutMePage setLatinLastName(String text) {
		latinLastNameTextInput.clear();
		latinLastNameTextInput.sendKeys(text);
		return this;
	}
	public AboutMePage setBlogName(String text) {
		blogNameTextInput.clear();
		blogNameTextInput.sendKeys(text);
		return this;
	}
	public AboutMePage setDateOfBirth(String text) {
		dateOfBirthTextInput.clear();
		dateOfBirthTextInput.sendKeys(text);
		return this;
	}

	//заполнение дропдаунов
	public AboutMePage setCountry(String country) {
		DropDown countryDropDown = new DropDown(driver, By.xpath("//*[@name=\"country\"]/.."));
		countryDropDown.chooseByOption(country);
		return this;
	}

	public AboutMePage setCity(String city) {
		DropDown countryDropDown = new DropDown(driver, By.xpath("//*[@name=\"city\"]/.."));
		countryDropDown.chooseByOption(city);
		return this;
	}

	public AboutMePage setEnglishLevel(String level) {
		DropDown countryDropDown = new DropDown(driver, By.xpath("//*[@name=\"english_level\"]/.."));
		countryDropDown.chooseByOption(level);
		return this;
	}
}
