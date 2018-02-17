package nl.infosupport.week13.ddd.blackjack.domain;

public class PlayerFactory {

    public Player createPlayer() {
      Player player = new Player(new Hand());
      return player;
    }
}
