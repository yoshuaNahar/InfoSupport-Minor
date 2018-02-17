package nl.infosupport.week13.ddd.blackjack.domain;

import java.util.List;

public class Hand {

  private List<Card> cards;

  public Hand() {
  }

  public List<Card> getCards() {
    return cards;
  }

  public void addToHand(Card card) {
    cards.add(card);
  }

}
