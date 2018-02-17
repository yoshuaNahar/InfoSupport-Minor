package nl.infosupport.javaminor.blok2.week11.cucumber.implementation;

public class Checkout {

  int runningTotal = 0;

  public void add(int count, int price) {
    int addedPrice = count * price;
    runningTotal += addedPrice;
  }

  public int total() {
    return runningTotal;
  }

}
