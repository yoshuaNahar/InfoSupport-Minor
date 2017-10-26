package nl.infosupport.javaminor.blok1.week3.tdd._02_matchers;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

//@RunWith(MockitoJUnitRunner.class)
public class CursistRegistratieManagerTest {

	private CursistRegistratieManager registratieManager;

	@Before
	public void before() throws Throwable {
		registratieManager = new CursistRegistratieManager();
	}

	@Test
	public void cursistOntvangtNaRegistratieEenActivatieCode() throws Throwable {
		Cursist cursist = createCursist("TestUser", "mail");

		String activatieCode = registratieManager.registreer(
				cursist.getGebruikersnaam(), cursist.getEmail());

		assertThat(activatieCode, is(notNullValue()));
	}

	@Test
	public void cursistKanZichNaRegistrerenLatenActiveren() throws Throwable {
		Cursist cursist = createCursist("TestUser", "mail");

		String activatieCode = registratieManager.registreer(
				cursist.getGebruikersnaam(), cursist.getEmail());
		registratieManager.activeer(cursist.getGebruikersnaam(), activatieCode);
	}

	@Test(expected = OngeldigeCursistActivatieException.class)
	public void managerGeeftFoutBijActiverenVanOnbekendeUser() throws Throwable {
		String onbekendeUser = "unknownUser";
		registratieManager.activeer(onbekendeUser, "Activatiecode");
	}

	@Test(expected = OngeldigeCursistRegistratie.class)
	public void managerGeeftFoutBijRegistratieVanReedsBestaandeGebruiker()
			throws Throwable {
		Cursist cursist = createCursist("BestaandeUser", "n.v.t.");

		registratieManager.registreer(cursist.getGebruikersnaam(),
				cursist.getEmail());
		registratieManager.registreer(cursist.getGebruikersnaam(),
				cursist.getEmail());
	}

	@Test(expected = OngeldigeCursistActivatieException.class)
	public void cursistKanEenmaalGeactiveerdWorden() throws Throwable {
		Cursist cursist = createCursist("NieuweUser", "n.v.t.");

		String activatieCode = registratieManager.registreer(
				cursist.getGebruikersnaam(), cursist.getEmail());
		registratieManager.activeer(cursist.getGebruikersnaam(), activatieCode);
		registratieManager.activeer(cursist.getGebruikersnaam(), activatieCode);
	}

	private Cursist createCursist(String naam, String email) {
		Cursist cursist = new Cursist();
		cursist.setEmail(email);
		cursist.setGebruikersnaam(naam);
		return cursist;
	}

}
