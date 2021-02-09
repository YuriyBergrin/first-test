import org.junit.Test;

public class OtusTest extends BaseTest {
	@Test
	public void test() {
		otusMainPage
				.open()
				.auth()
				.gotoPersonalAccount();
		personalAccountPage.gotoAboutMe();
		aboutMePage
				.setFirstName("Иван")
				.setLastName("Иванов")
				.setLatinFirstName("Ivan")
				.setLatinLastName("Ivanov")
				.setBlogName("Ванька")
				.setDateOfBirth("01.02.1991")
				.setCountry("Россия")
				.setCity("Азов")
				.setEnglishLevel("Средний (Intermediate)")
				.setRelocateRadioButton(true)
				.setWorkSchedule("Гибкий график")
				.setPhone("123456789")
				.setNewContact(1,"Facebook", "123456789");
//				.setNewContact(1,"Viber", "@Test");
//				.setNewContact(2,"Viber", "0987654321");
	}
}
