package nl.infosupport.javaminor.week3.tdd._02_matchers;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class CursistMatcher extends TypeSafeMatcher<Cursist> {

  private Cursist expectedCursist;

  public CursistMatcher(Cursist expectedCursist) {
    this.expectedCursist = expectedCursist;
  }

  @Factory
  public static Matcher<Cursist> cursist(Cursist expectedCursist) {
    return new CursistMatcher(expectedCursist);
  }

  @Override
  protected boolean matchesSafely(Cursist currentCursist) {
    return currentCursist.getEmail().equals(expectedCursist.getEmail())
        && currentCursist.getGebruikersnaam().equals(expectedCursist.getGebruikersnaam());
  }

  @Override
  public void describeTo(Description description) {
    description.appendText("a cursist with email: ")
        .appendValue(expectedCursist.getEmail())
        .appendText(" and with gebruikersnaam: ")
        .appendValue(expectedCursist.getGebruikersnaam());
  }

  @Override
  public void describeMismatchSafely(Cursist actualCursist, Description mismatchDescription) {
    mismatchDescription.appendText("was a cursist with email: ")
        .appendValue(actualCursist.getEmail())
        .appendText(" and with gebruikersnaam: ")
        .appendValue(actualCursist.getGebruikersnaam());
//    super.describeMismatch(item, mismatchDescription);
  }

}
