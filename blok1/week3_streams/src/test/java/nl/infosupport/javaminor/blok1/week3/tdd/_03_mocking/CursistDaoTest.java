package nl.infosupport.javaminor.blok1.week3.tdd._03_mocking;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class CursistDaoTest {

  @Test
  public void cursistWordtGeregistreerdInDao() {
    Cursist expectedCursist = createCursist("Joris", "joris@infosupport.com");

    CursistDao dao = CursistDao.getInstance();
    dao.save(expectedCursist);
    Cursist result = dao.getCursist(expectedCursist.getGebruikersnaam());

    assertThat(result, is(CursistMatcher.isCursist(expectedCursist)));
  }

  private Cursist createCursist(String naam, String email) {
    Cursist cursist = new Cursist(naam, email);

    return cursist;
  }

}
