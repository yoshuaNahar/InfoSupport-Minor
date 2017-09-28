package nl.infosupport.javaminor.week3.kata._4_thursday_28_sept;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {

  public int add(String number) {
    if (number == null) {
      throw new IllegalArgumentException("null not allowed");
    }

    // (multiple digits)(1 or more non digit delimiters)
    Pattern pattern = Pattern.compile("([0-9]+)([^0-9]+)");
    Matcher matcher = pattern.matcher(number);

    int sum = 0;

    while (matcher.find()) {
      // System.out.print(matcher.group());
      System.out.println(matcher.group(1));
      if (matcher.group(2).length() > 1) {
        throw new IllegalArgumentException("2 delimiters after each other not allowed");
      }
      System.out.println(matcher.group(2));

      sum += Integer.parseInt(matcher.group(1));
    }

    return sum;
  }

}
