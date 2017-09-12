package nl.infosupport.minor.week3.streams.tuesday.misc.exercise;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.junit.Test;

public class FunctionInterfaceExercise {

  private static void processFile(Function<BufferedReader, String> function) throws IOException {
    try (BufferedReader in = new BufferedReader(new FileReader("src/main/resources/_01ajava8streams/data.txt"))) {
      System.out.println(function.apply(in));
    }
  }

  @Test
  public void processFileShouldPrintFirstLine() throws IOException {
    processFile(in -> in
        .lines()
        .findFirst()
        .orElse(null));
  }

  // You can't operate on a stream twice!
  @Test
  public void processFileShouldPrintLastLine() throws IOException {
    processFile(in -> {
      List<String> lines = in.lines().collect(Collectors.toList());
      return lines.get(lines.size() - 1);
    });
  }

  @Test
  public void processFileShouldPrintLongestLine() throws IOException {
    processFile(in -> {
      List<String> lines = in.lines().collect(Collectors.toList());
      return lines.stream()
          .max(Comparator.comparingInt(String::length))
          .orElse(null);
    });
  }

  // Use the method below this one. You choose which one is clearer.
  @Test
  public void processFileShouldPrintLongestWord() throws IOException {
    processFile(in -> {
      List<String> lines = in.lines().collect(Collectors.toList());
      String longestWord = ""; // can't use lambda, because should be effectively final
      for (String line : lines) {
        for (String word : line.split(" ")) {
          if (longestWord.length() < word.length()) {
            longestWord = word;
          }
        }
      }
      return longestWord;
    });
  }

  @Test
  public void processFileShouldPrintLongestWordFullFunctional() throws IOException {
    processFile(in -> in
        .lines()
        .flatMap(line -> Arrays.stream(line.split(" ")))
        .max(Comparator.comparingInt(String::length))
        .orElse(null));
  }

}
