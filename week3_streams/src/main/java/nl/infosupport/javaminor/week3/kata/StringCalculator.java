package nl.infosupport.javaminor.week3.kata;

import java.util.Arrays;

public class StringCalculator {

  public int add(String numbers) {
    if (numbers.equals("") || numbers.equals("0")) {
      return 0;
    }

    boolean delimiterExists = numbers.matches("//.?\n.+"); // //{1 or 0 extra}\n{1 or more}
    String delimiter = "";
    if (delimiterExists) {
      delimiter = "|" + numbers.substring(2, 3);
      numbers = numbers.substring(4);
    }

    return Arrays.stream(numbers.split(",|\n" + delimiter))
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
