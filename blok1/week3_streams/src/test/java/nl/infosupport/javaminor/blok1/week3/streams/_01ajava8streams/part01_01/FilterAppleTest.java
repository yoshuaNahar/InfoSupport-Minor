package nl.infosupport.javaminor.blok1.week3.streams._01ajava8streams.part01_01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;
import org.junit.Before;
import org.junit.Test;

public class FilterAppleTest {

  private List<Apple> apples;

  private static List<Apple> filterApples(Predicate<Apple> filter, List<Apple> stock) {
    List<Apple> filteredApples = new ArrayList<>();

    stock.stream()
        .filter(filter)
        .forEach(filteredApples::add);

    return filteredApples;
  }

  @Before
  public void setUp() {
    apples = Arrays.asList(new Apple(120, "green"),
        new Apple(80, "red"),
        new Apple(200, "green"),
        new Apple(140, "red"));
  }

  @Test
  public void filterAllGreenApplesWithAnonymousClass() {
    Predicate<Apple> greenAppleFiltered = null;

    Stream<Apple> applesToFilter = apples.stream();
    Predicate<Apple> greenApplePredicate = apple -> apple.getColor().equalsIgnoreCase("green");
//    greenAppleFiltered = applesToFilter.filter(greenApplePredicate);

    //TODO assign an instance of an anonymous class to the reference variable greenApplefilter
    //TODO the filter should filter all green apples

    List<Apple> filteredApples = filterApples(greenAppleFiltered, apples);

    filteredApples.forEach((apple) -> System.out.println(apple));
  }


  @Test
  public void filterAllApplesAbove100GramsWithAnonymousClass() {
    Predicate<Apple> weightFilter = null;

    //TODO assign an instance of an anonymous class to the reference variable weightFilter
    //TODO the filter should filter all  apples above 100 grams

    List<Apple> filteredApples = filterApples(weightFilter, apples);

    filteredApples.forEach((apple) -> System.out.println(apple));
  }

  @Test
  public void filterAllGreenApplesAbove100GramsWithAnonymousClass() {
    Predicate<Apple> greenAppleWeightFilter = null;

    //TODO assign an instance of an anonymous class to the reference variable greenAppleWeightfilter
    //TODO the filter should filter all  green apples above 100 grams

    List<Apple> filteredApples = filterApples(greenAppleWeightFilter, apples);

    filteredApples.forEach((apple) -> System.out.println(apple));
  }

  //TODO Copy the above 3 unittests to here and change the testnames into AnonymousFunction instead
  //TODO of AnonymousClass
  //TODO remove the boiler plate code from the anonymous classes in such a way that a anonymous function remains

}
