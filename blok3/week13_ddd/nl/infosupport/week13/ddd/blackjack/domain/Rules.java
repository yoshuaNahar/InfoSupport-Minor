package nl.infosupport.week13.ddd.blackjack.domain;

public class Rules {

  private static final int TARGET_WEIGHT = 21;

  public boolean doRuleCheck(Hand hand) {
    return passedTargetWeightCheck(hand);
  }

  private boolean passedTargetWeightCheck(Hand hand) {
    int handWeight = 0;

    for (Card card : hand.getCards()) {
      handWeight += card.getWeight();
    }

    return handWeight <= TARGET_WEIGHT;
  }

}
