package nl.infosupport.javaminor.blok1.week3.tdd._03_mocking;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class CursistMatcher extends TypeSafeMatcher<Cursist> {

  private Cursist cursist;

  private CursistMatcher(Cursist cursist) {
    this.cursist = cursist;
  }

  public void describeTo(Description description) {
    description.appendText("Verwacht cursist met naam: ")
        .appendValue(cursist.getGebruikersnaam()).appendText(" met email: ")
        .appendValue(cursist.getEmail());
  }

  @Override
  protected boolean matchesSafely(Cursist item) {
    if (!item.getGebruikersnaam().equals(cursist.getGebruikersnaam())) {
      return false;
    } else if (!item.getEmail().equals(cursist.getEmail())) {
      return false;
    }
    return true;
  }

  public static CursistMatcher isCursist(Cursist cursist) {
    return new CursistMatcher(cursist);
  }

}
