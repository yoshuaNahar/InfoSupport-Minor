package nl.infosupport.javaminor.week3.streams._01ajava8streams.part01_01;

public class Apple {

  private int weight;
  private String color;

  public Apple(int weight, String color) {
    this.weight = weight;
    this.color = color;
  }

  public Apple(int amount) {
  }

  public int getWeight() {
    return weight;
  }

  public void setWeight(int weight) {
    this.weight = weight;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  @Override
  public String toString() {
    return "Apple{" +
        "weight=" + weight +
        ", color='" + color + '\'' +
        '}';
  }

}
