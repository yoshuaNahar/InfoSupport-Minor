package nl.infosupport.javaminor.blok1.week3.tdd._03_mocking;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CursistRegistratieManagerTest {

  private CursistRegistratieManager registratieManager;
  @Mock
  private CursistDao cursistDaoMock;

  @Before
  public void before() {
    registratieManager = new CursistRegistratieManager(cursistDaoMock);
  }

  @Test
  public void cursistOntvangtNaRegistratieEenActivatieCode() throws Throwable {
    when(cursistDaoMock.getCursist("testuser")).thenReturn(null);

    String registratieCode = registratieManager.registreer("testuser",
        "test@mail.com");

    assertThat(registratieCode, is(notNullValue()));
  }

  @Test
  public void cursistKanZichNaRegistrerenLatenActiveren() throws Throwable {
    String gebruikersnaam = "defaultCursist";
    String activatieCode = registratieManager.registreer(gebruikersnaam,
        "defaultEmail");

    when(cursistDaoMock.getCursist(gebruikersnaam))
        .thenReturn(new Cursist(gebruikersnaam, "defaultEmail"));

    registratieManager.activeer(gebruikersnaam, activatieCode);
  }

  @Test(expected = OngeldigeCursistActivatieException.class)
  public void managerGeeftFoutBijActiverenVanOnbekendeUser() throws Throwable {
    String onbekendeUser = "unknownUser";

    registratieManager.activeer(onbekendeUser, "Activatiecode");
  }

  @Test(expected = OngeldigeCursistRegistratie.class)
  public void managerGeeftFoutBijRegistratieVanReedsBestaandeGebruiker()
      throws Throwable {
    String bestaandeUser = "bestaandeUser";

    when(cursistDaoMock.getCursist(bestaandeUser)).thenReturn(new Cursist(bestaandeUser, ""));

    registratieManager.registreer(bestaandeUser, "user1@mail.com");
    registratieManager.registreer(bestaandeUser, "user2@mail.com");
  }

  @Test(expected = OngeldigeCursistActivatieException.class)
  public void cursistKanEenmaalGeactiveerdWorden() throws Throwable {
    String nieuweUser = "nieuwUser";

    when(cursistDaoMock.getCursist(nieuweUser)).thenReturn(null);

    String activatieCode = registratieManager.registreer(nieuweUser,
        "user1@mail.com");

    when(cursistDaoMock.getCursist(nieuweUser)).thenReturn(new Cursist(nieuweUser, ""));

    registratieManager.activeer(nieuweUser, activatieCode);
    registratieManager.activeer(nieuweUser, activatieCode);
  }

}
