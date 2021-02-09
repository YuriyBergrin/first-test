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
				.setDateOfBirth("01.02.1984")
				.setCountry("Россия")
				.setCity("Москва")
				.setEnglishLevel("Средний (Intermediate)");
	}
}
