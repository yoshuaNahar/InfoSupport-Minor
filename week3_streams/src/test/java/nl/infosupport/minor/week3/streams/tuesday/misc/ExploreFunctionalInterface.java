package nl.infosupport.minor.week3.streams.tuesday.misc;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import nl.infosupport.minor.week3.streams._01ajava8streams.part01_01.Apple;
import org.junit.Test;

public class ExploreFunctionalInterface {

  private List<Apple> stock = Arrays.asList(
      new Apple(100, "Red"),
      new Apple(150, "Green"),
      new Apple(80, "Green"),
      new Apple(200, "Red"));

  @Test
  public void filterAppleOnTheColorRed() {
    List<Apple> resultAfterFiltering =
        filterStockOfApples(stock, apple -> "Red".equalsIgnoreCase(apple.getColor()));

    assertThat(resultAfterFiltering.size(), is(2));
  }

  @Test
  public void filterAppleOnWeight() {
    List<Apple> resultAfterFiltering =
        filterStockOfApples(stock, apple -> apple.getWeight() > 100);

    assertThat(resultAfterFiltering.size(), is(2));
  }

  @Test
  public void filterAppleOnWeightWithPredicate() {
    List<Apple> resultAfterFiltering =
        filterStockOfApples(stock, apple -> apple.getWeight() > 100);

    assertThat(resultAfterFiltering.size(), is(2));
  }

  private List<Apple> filterStockOfApples(List<Apple> stock, Predicate<Apple> predicate) {
    List<Apple> resultAfterFiltering = new ArrayList<>();

    for (Apple apple : stock) {
      if (predicate.test(apple)) {
        resultAfterFiltering.add(apple);
      }
    }

    return resultAfterFiltering;
  }

}
