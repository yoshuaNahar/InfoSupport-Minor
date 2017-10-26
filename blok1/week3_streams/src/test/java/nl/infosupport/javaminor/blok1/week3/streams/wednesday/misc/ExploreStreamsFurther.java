package nl.infosupport.javaminor.blok1.week3.streams.wednesday.misc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import nl.infosupport.javaminor.blok1.week3.streams._01ajava8streams.part01_01.Apple;
import org.junit.Test;

public class ExploreStreamsFurther {

  private List<Apple> stock = Arrays.asList(
      new Apple(100, "Red"),
      new Apple(150, "Green"),
      new Apple(80, "Green"),
      new Apple(200, "Red"));

  @Test
  public void vindParenApplesDie300GramWegen() {
    // Creating a new stream of stock to operate on
    stock.stream()
        .flatMap(apple1 -> stock.stream().map(apple2 -> new Apple[] {apple1, apple2}))
        .filter(applePaar -> applePaar[0].getWeight() + applePaar[1].getWeight() == 300)
        .forEach(apples -> System.out.println(apples[0] + " " + apples[1]));
  }

  @Test
  public void vindWillekeurigeApplePaarDie300GramWeegt() {
    // Creating a new stream of stock to operate on
    Optional<Apple[]> optionalApplePaar = stock.stream()
        .flatMap(apple1 -> stock.stream().map(apple2 -> new Apple[] {apple1, apple2}))
        .filter(applePaar -> applePaar[0].getWeight() + applePaar[1].getWeight() == 300)
        .findAny();

    optionalApplePaar.ifPresent(apples -> System.out.println(apples[0] + " " + apples[1]));
  }

  @Test
  public void watIsHetTotaalGewichtVanMijnAppelVoorraad() {
    int totaalGewicht = stock.stream()
        .map(Apple::getWeight)
        .reduce(0, (gewicht1, gewicht2) -> gewicht1 + gewicht2);

    System.out.println(totaalGewicht);
  }

}
