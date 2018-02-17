package nl.infosupport.week13.ddd.blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class DeckFactory {

  public Deck createDeck() {
    List<Card> cards = new ArrayList<>();

    for (Card.Rank rank : Card.Rank.values()) {
      for (Card.Suite suite : Card.Suite.values()) {
        Card card = new Card(rank, suite, 0);
        cards.add(card);
      }
    }

    Deck deck = new Deck(cards);

    return deck;
  }

}
