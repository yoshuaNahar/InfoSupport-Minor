package nl.infosupport.week13.ddd.blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class GameFactory {
  private PlayerFactory playerFactory;
  private DealerFactory dealerFactory;

  public Game createGame() {
    this.playerFactory = new PlayerFactory();
    List<Player> players = new ArrayList<>();
    for (int i = 0; i < 2; i++) {
      players.add(playerFactory.createPlayer());
    }

    this.dealerFactory = dealerFactory;
    return new GameImpl(players, this.dealerFactory.createDealer(), new Rules());
  }

}
