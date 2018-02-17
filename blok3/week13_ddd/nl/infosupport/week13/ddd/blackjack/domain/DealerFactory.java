package nl.infosupport.week13.ddd.blackjack.domain;

public class DealerFactory {
  private DeckFactory deckFactory;

  public Dealer createDealer() {
    return new Dealer(deckFactory.createDeck());
  }

}
