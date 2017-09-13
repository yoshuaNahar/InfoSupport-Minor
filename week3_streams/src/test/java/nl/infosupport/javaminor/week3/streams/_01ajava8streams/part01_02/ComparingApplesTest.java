package nl.infosupport.javaminor.week3.streams._01ajava8streams.part01_02;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import nl.infosupport.javaminor.week3.streams._01ajava8streams.part01_01.Apple;
import org.junit.Before;
import org.junit.Test;

public class ComparingApplesTest {

  private List<Apple> apples;

  @Before
  public void setUp() {
    apples = Arrays.asList(new Apple(120, "green"),
        new Apple(80, "red"),
        new Apple(200, "green"),
        new Apple(140, "red"));
  }

  @Test
  public void sortApplesOnColourWithAnonymousClass() {
    Comparator<Apple> colourComparator = null;

    //TODO implement the comparator with an anonymous class

    apples.sort(colourComparator);

    apples.forEach(apple -> System.out.println(apple));
  }

  @Test
  public void sortApplesOnWeightWithAnonymousClass() {
    Comparator<Apple> weightComparator = null;

    //TODO implement the comparator with an anonymous class

    apples.sort(weightComparator);

    apples.forEach(apple -> System.out.println(apple));
  }

  @Test
  public void sortApplesOnColourAndWeightWithAnonymousClass() {
    Comparator<Apple> colourAndWeightComparator = null;

    //TODO implement the comparator with an anonymous class

    apples.sort(colourAndWeightComparator);

    apples.forEach(apple -> System.out.println(apple));
  }

  //TODO Copy the above 3 unittests to here and change the testnames into AnonymousFunction instead
  //TODO of AnonymousClass
  //TODO remove the boiler plate code from the anonymous classes in such a way that a anonymous function remains

}
