package nl.infosupport.javaminor.blok1.week3.tdd._02_matchers;

import static nl.infosupport.javaminor.blok1.week3.tdd._02_matchers.CursistMatcher.cursist;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class CursistDaoTest {

  @Test
  public void cursistWordtGeregistreerdInDao() {
    Cursist expectedCursist = createCursist("Joris", "joris@infosupport.com");

    CursistDao dao = CursistDao.getInstance();
    dao.save(expectedCursist);
    Cursist result = dao.getCursist(expectedCursist.getGebruikersnaam());
    assertThat(result, is(cursist(expectedCursist)));
    // zorg ervoor dat de volgende regel het resultaat valideert
    // assertThat(result, is(CursistMatcher.isCursist(expectedCursist)));
  }

  @Test
  public void cursistNietGeregistreerdInDao() {
    Cursist expectedCursist = createCursist("Joris", "joris@infosupport.com");

    CursistDao dao = CursistDao.getInstance();
    dao.save(expectedCursist);
    Cursist result = dao.getCursist(expectedCursist.getGebruikersnaam());

    Cursist differentCursist = createCursist("Testnaam", "joris@infosupport.com");

    assertThat(differentCursist, is(not(cursist(result))));
  }

  private Cursist createCursist(String naam, String email) {
    Cursist cursist = new Cursist();
    cursist.setEmail(email);
    cursist.setGebruikersnaam(naam);
    return cursist;
  }

}
