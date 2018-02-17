package nl.infosupport.week13.ddd.blackjack.domain;

import java.util.List;
import java.util.Random;

public class Deck {

  private List<Card> cards;

  public Deck(List<Card> cards) {
    this.cards = cards;
  }

  public Card giveCard() {
    Random random = new Random();
    int number = random.nextInt(cards.size()); // random number between 0 and cards.size()

    return cards.get(number);
  }

}
