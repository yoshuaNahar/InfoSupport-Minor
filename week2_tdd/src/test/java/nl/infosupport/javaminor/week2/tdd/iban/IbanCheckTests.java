package nl.infosupport.javaminor.week2.tdd.iban;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class IbanCheckTests {

  private IbanChecker ibanChecker;

  @Before
  public void setUp() {
    ibanChecker = new IbanChecker();
  }

  @Test
  public void checkIbanWithTooManyCharacters() {
    //arrange
    String aIban = "NL14INGB00086654579";

    //action
    boolean result = ibanChecker.checkIbanDutch(aIban);

    //assert
    assertThat(result, is(false));
  }

  @Test
  public void CheckIbanOnDutchLandCode() {
    //arrange
    String aIban = "FR14INGB0008665457";

    //action
    boolean result = ibanChecker.checkIbanDutch(aIban);

    //assert
    assertThat(result, is(false));
  }

  @Test
  public void checkIbanChecksum() {
    // arrange
    String aIban = "PL61109010140000071219812874";

    //action
    boolean result = ibanChecker.verifyIbanChecksum(aIban);

    //assert
    assertThat(result, is(true));
  }

}
