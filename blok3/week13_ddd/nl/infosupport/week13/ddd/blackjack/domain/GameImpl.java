package nl.infosupport.week13.ddd.blackjack.domain;

import java.util.List;

public class GameImpl implements Game{

  private List<Player> players;
  private Dealer dealer;

  private Rules rules;

  public GameImpl(List<Player> players, Dealer dealer,
      Rules rules) {
    this.players = players;
    this.dealer = dealer;
    this.rules = rules;
  }

  // 0. param = specific player
  // 1. Get new Card
  // 2. Check rules
  public void hit() {
    Card card = dealer.giveCard();
    Player player = players.get(0);
    player.receiveCard(card);

    boolean playerSurvived = checkHand(player.getHand());

    if (!playerSurvived) {
    }
  }

  private boolean checkHand(Hand hand) {
    return rules.doRuleCheck(hand);
  }

}
