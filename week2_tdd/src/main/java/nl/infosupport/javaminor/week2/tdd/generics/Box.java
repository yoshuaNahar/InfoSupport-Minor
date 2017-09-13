package nl.infosupport.javaminor.week2.tdd.generics;

public class Box<T> {

  private T t;

  public Box(T t) {
    this.t = t;
  }

  public T getValue() {
    return t;
  }

}
