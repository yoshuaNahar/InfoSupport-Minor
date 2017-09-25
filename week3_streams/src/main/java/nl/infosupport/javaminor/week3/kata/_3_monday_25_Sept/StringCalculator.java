package nl.infosupport.javaminor.week3.kata._3_monday_25_Sept;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {

  public int add(String number) {
    if (number == null) {
      throw new IllegalArgumentException("null not allowed");
    }

    if (number.trim().isEmpty()) {
      return 0;
    }

    // //;\n1;2
    System.out.println(number.matches("(//.+\n.+)"));

    return Arrays.stream(number.split("(,|\n)"))
        .mapToInt(Integer::parseInt)
        .sum();
  }

  public static void main(String[] args) {
    String word = "an int is 32 bits a long is 64";

    List<String> keys = Arrays.asList("int", "float", "if", "else", "double");

    Optional<String> possibleMatch = keys.stream()
        .filter(word::contains)
        .findAny();

    if (possibleMatch.isPresent()) {
      System.out.println(possibleMatch.get());
    }

    System.out.println(" ---- ");

    String key2 = "(int|long|if|else|double)"; // is a regex

    Pattern pattern = Pattern.compile(key2);
    Matcher matcher = pattern.matcher(word);
    while(matcher.find()) {
      System.out.println(matcher.group());
    }
    System.out.println(matcher.matches());
  }

}
