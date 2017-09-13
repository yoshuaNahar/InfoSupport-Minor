package nl.infosupport.javaminor.week2.tdd.generics;

public class BoxAdv<T extends Number> {

  private T t;

  public BoxAdv(T t) {
    this.t = t;
  }

  public T getValue() {
    return t;
  }

}
