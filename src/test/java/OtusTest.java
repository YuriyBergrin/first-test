import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class OtusTest extends BaseTest {
	@Test
	public void test() {
		otusMainPage
				.open()
				.auth()
				.gotoPersonalAccount();
		personalAccountPage.gotoAboutMe();
		aboutMePage
				.deleteContact()
				.setFirstName("Иван")
				.setLastName("Иванов")
				.setLatinFirstName("Ivan")
				.setLatinLastName("Ivanov")
				.setBlogName("Ванька")
				.setDateOfBirth("01.02.1991")
				.setCountry("Россия")
				.setCity("Москва")
				.setEnglishLevel("Средний (Intermediate)")
				.setRelocateRadioButton(true)
				.setWorkSchedule("Гибкий график")
				.setPhone("123456789")
				.setNewContact(1, "Facebook", "123456789")
				.setNewContact(2, "Viber", "@Test")
				.setNewContact(3, "OK", "0987654321")
				.saveChanges()
				.clearCookies();
		//почистили куки - вышли, идем проверять поля
		otusMainPage
				.open()
				.auth()
				.gotoPersonalAccount();
		personalAccountPage.gotoAboutMe();
		assertEquals("Имя не соотвествует", "Иван", aboutMePage.getFirstName());
		assertEquals("Иванов", aboutMePage.getLastName());
		assertEquals("Ivan", aboutMePage.getLatinFirstName());
		assertEquals("Ivanov", aboutMePage.getLatinLastName());
		assertEquals("Ванька", aboutMePage.getBlogName());
		assertEquals("01.02.1991", aboutMePage.getDateOfBirth());
		assertEquals("Россия", aboutMePage.getCountry());
		assertEquals("Москва", aboutMePage.getCity());
		assertEquals("Средний (Intermediate)", aboutMePage.getEnglishLevel());
		assertTrue(aboutMePage.getReadyRelocateValue());
		assertTrue(aboutMePage.getWorkSchedule("Гибкий график"));
		assertEquals("+1 234 567-89", aboutMePage.getMainPhone());
		assertTrue(aboutMePage.checkContact(1, "Facebook", "123456789"));
		assertTrue(aboutMePage.checkContact(2, "OK", "0987654321"));
		assertTrue(aboutMePage.checkContact(3, "Viber", "@Test"));
	}
}
