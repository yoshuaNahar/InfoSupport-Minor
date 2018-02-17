package nl.infosupport.week13.ddd.blackjack.domain;

public class Dealer {

  private Deck deck;

  public Dealer(Deck deck) {
    this.deck = deck;
  }

  public Card giveCard() {
    return deck.giveCard();
  }

}
