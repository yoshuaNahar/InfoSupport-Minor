package nl.infosupport.javaminor.week3.streams.tuesday.misc;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;
import nl.infosupport.javaminor.week3.streams._01ajava8streams.part01_01.Apple;
import org.junit.Test;

public class ExploreStreamApi {

  private List<Apple> stock = Arrays.asList(
      new Apple(100, "Red"),
      new Apple(150, "Green"),
      new Apple(80, "Green"),
      new Apple(200, "Red"));

  @Test
  public void introductionToStreams() {
    Stream<Apple> appleStream = stock.stream();
    Predicate<? super Apple> predicate = apple -> apple.getColor().equalsIgnoreCase("Red");
    Stream<Apple> applesFilteredOnColorStream = appleStream.filter(predicate);
    Function<? super Apple, String> mapper = Apple::getColor;
    Stream<String> appleColors = applesFilteredOnColorStream.map(mapper);
    Consumer<String> consumer = System.out::println;
    appleColors.forEach(consumer);
  }

}
