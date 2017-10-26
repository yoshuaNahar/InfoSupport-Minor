package nl.infosupport.javaminor.blok1.week2.tdd.generics;

import org.junit.Test;

public class ExploreTypeErasure {

  @Test
  public void testBoxT() {
    Box<Double> box = new Box<>(1.0);
    double value = box.getValue();
  }

  @Test
  public void testBoxTextendsNumber() {
    BoxAdv<Double> box = new BoxAdv<>(2.0);
    double value = box.getValue();
  }
}
