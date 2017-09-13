package nl.infosupport.javaminor.week2.tdd.iban;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class IbanCheckTests {

  private IbanChecker ibanChecker;

  @Before
  public void SetUp() {
    ibanChecker = new IbanChecker();
  }

  @Test
  public void CheckIbanWithTooManyCharacters() {
    //arrange
    String aIban = "NL14INGB00086654579";

    //action
    boolean result = ibanChecker.checkIbanDutch(aIban);

    //assert
    Assert.assertTrue(!result);
  }

  @Test
  public void CheckIbanOnDutchLandCode() {
    //arrange
    String aIban = "FR14INGB0008665457";

    //action
    boolean result = ibanChecker.checkIbanDutch(aIban);

    //assert
    Assert.assertTrue(!result);
  }

  @Test
  public void checkIbanChecksum() {
    // arrange
    String aIban = "PL61109010140000071219812874";

    //action
    boolean result = ibanChecker.verifyIbanChecksum(aIban);

    //assert
    Assert.assertTrue(result);
  }

}
