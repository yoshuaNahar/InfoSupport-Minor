package nl.infosupport.javaminor.week2.tdd.generics;

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

// BoxAdv becomes, because that is the information that is given
//class BoxAdv {
//  private Number t;
//  ...
//}
