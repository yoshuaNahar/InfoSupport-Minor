package nl.infosupport.javaminor.blok1.week3.kata._2;

import java.util.Arrays;

public class StringCalculator {

  public int add(String number) {
    if (number == null || number.trim().isEmpty()) {
      return 0;
    }

    return Arrays.stream(number.split(",|\n"))
        .mapToInt(Integer::parseInt)
        .sum();
  }

}
