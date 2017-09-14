package nl.infosupport.javaminor.week2.tdd.iban;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class IbanChecker {

  private static Map<String, String> LOOKUP_TABLE = new HashMap<>();

  static {
    LOOKUP_TABLE.put("A", "10");
    LOOKUP_TABLE.put("B", "11");
    LOOKUP_TABLE.put("C", "12");
    LOOKUP_TABLE.put("D", "13");
    LOOKUP_TABLE.put("E", "14");
    LOOKUP_TABLE.put("F", "15");
    LOOKUP_TABLE.put("G", "16");
    LOOKUP_TABLE.put("H", "17");
    LOOKUP_TABLE.put("I", "18");
    LOOKUP_TABLE.put("J", "19");
    LOOKUP_TABLE.put("K", "20");
    LOOKUP_TABLE.put("L", "21");
    LOOKUP_TABLE.put("M", "22");
    LOOKUP_TABLE.put("N", "23");
    LOOKUP_TABLE.put("O", "24");
    LOOKUP_TABLE.put("P", "25");
    LOOKUP_TABLE.put("Q", "26");
    LOOKUP_TABLE.put("R", "27");
    LOOKUP_TABLE.put("S", "28");
    LOOKUP_TABLE.put("T", "29");
    LOOKUP_TABLE.put("U", "30");
    LOOKUP_TABLE.put("V", "31");
    LOOKUP_TABLE.put("W", "32");
    LOOKUP_TABLE.put("X", "33");
    LOOKUP_TABLE.put("Y", "34");
    LOOKUP_TABLE.put("Z", "35");
  }

  public boolean checkIbanDutch(String ibanCode) {
    if (ibanCode.length() > 18) {
      return false;
    } else if (!ibanCode.startsWith("NL")) {
      return false;
    }

    return true;
  }

  public boolean verifyIbanChecksum(String iban) {
    // place first digits on the end
    String firstFourCharacters = iban.substring(0, 4);
    String leftovers = iban.substring(4);

    //combine them again
    String combinedString = leftovers + firstFourCharacters;
    StringBuilder sb = new StringBuilder(combinedString);
    String resultString = "";

    //Rebuild string with only digits
    for (int i = 0; i < combinedString.length(); i++) {
      if (!Character.isDigit(sb.charAt(i))) {
        resultString = resultString + LOOKUP_TABLE.get((String.valueOf(sb.charAt(i))));
      } else {
        resultString = resultString + sb.charAt(i);
      }
    }

    BigInteger modulo = new BigInteger(resultString);
    BigInteger leftOver = modulo.mod(new BigInteger("97"));

    return leftOver.intValue() == 1;
  }

}
