package nl.infosupport.javaminor.blok1.week3.streams.tuesday.misc.exercise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import nl.infosupport.javaminor.blok1.week3.streams._01ajava8streams.part01_01.Apple;
import org.junit.Test;

public class MethodReferencesExercise {

  @Test
  public void useConstructorReferenceToCreateInstances() {
    List<Integer> weights = Arrays.asList(1, 2, 3, 4, 5);
    Function<Integer, Apple> f = Apple::new;
    List<Apple> apples = new ArrayList<>();
    weights.forEach(i -> apples.add(f.apply(i)));
  }

}
