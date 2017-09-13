package nl.infosupport.javaminor.week3.streams.wednesday.misc;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import nl.infosupport.javaminor.week3.streams._01ajava8streams.part01_01.Apple;
import org.junit.Test;

public class ExploreStreamsFurther {

  private List<Apple> stock = Arrays.asList(
      new Apple(100, "Red"),
      new Apple(150, "Green"),
      new Apple(80, "Green"),
      new Apple(200, "Red"));

  @Test
  public void vindParenApplesDie300GramWegen() {
    Stream<Apple> fromAppleSource1 = stock.stream();
    Stream<Apple> sd = stock.stream();

    fromAppleSource1
        .flatMap(apple1 -> sd.map(apple2 -> new Apple[] {apple1, apple2}))
        .filter(applePaar -> applePaar[0].getWeight() + applePaar[1].getWeight() == 300)
        .forEach(apples -> System.out.println(apples[0] + " " + apples[1]));
  }

}
