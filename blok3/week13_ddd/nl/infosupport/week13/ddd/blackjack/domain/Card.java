package nl.infosupport.week13.ddd.blackjack.domain;

public class Card {

  private Rank rank;
  private Suite suite;
  private int weight;

  public Card(Rank rank, Suite suite, int weight) {
    this.rank = rank;
    this.suite = suite;
    this.weight = weight;
  }

  public Rank getRank() {
    return rank;
  }

  public void setRank(Rank rank) {
    this.rank = rank;
  }

  public Suite getSuite() {
    return suite;
  }

  public void setSuite(Suite suite) {
    this.suite = suite;
  }

  public int getWeight() {
    return weight;
  }

  public void setWeight(int weight) {
    this.weight = weight;
  }

  enum Rank {
    TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10), JACK(10), QUEEN(10), KING(10), ACE(10);

    private int weight;

    private Rank(int weight) {
      this.weight = weight;
    }

    public int getWeight() {
      return weight;
    }
  }

  enum Suite {
    HEARTS, SPADES, DIAMONDS, CLUBS
  }

}
