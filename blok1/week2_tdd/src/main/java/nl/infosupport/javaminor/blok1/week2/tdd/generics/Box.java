package nl.infosupport.javaminor.blok1.week2.tdd.generics;

public class Box<T> {

  private T t;

  public Box(T t) {
    this.t = t;
  }

  public T getValue() {
    return t;
  }

}
