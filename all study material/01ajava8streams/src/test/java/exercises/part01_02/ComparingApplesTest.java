package exercises.part01_02;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class ComparingApplesTest {

  private List<Apple> apples;

  @Before
  public void setUp() {
    apples = Arrays.asList(new Apple("green", 120),
        new Apple("red", 80),
        new Apple("green", 200),
        new Apple("red", 140));
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
