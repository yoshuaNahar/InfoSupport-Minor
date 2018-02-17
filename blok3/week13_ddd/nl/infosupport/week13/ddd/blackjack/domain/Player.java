package nl.infosupport.week13.ddd.blackjack.domain;

public class Player {

  private Hand hand;
  private Stake stake;

  public Player(Hand hand) {
    this.hand = hand;
  }

  public void receiveCard(Card card) {
    hand.addToHand(card);
  }

  public Hand getHand() {
    return hand;
  }

}
