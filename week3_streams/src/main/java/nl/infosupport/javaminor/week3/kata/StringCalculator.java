package nl.infosupport.javaminor.week3.kata;

import java.util.Arrays;

public class StringCalculator {

  public int add(String numbers) {
    if (numbers.equals("") || numbers.equals("0")) {
      return 0;
    }

    return Arrays.stream(numbers.split(",|\n"))
        .peek(value -> {
          if (value.isEmpty()) {
            throw new IllegalArgumentException("both delimiters after each other not allowed");
          }
        })
        .mapToInt(Integer::parseInt)
        .peek(value -> {
          if (value < 0) {
            throw new IllegalArgumentException("negatives not allowed");
          }
        })
        .sum();
  }

}
