package nl.infosupport.javaminor.week9.threading.atomicity.intro;

import nl.infosupport.javaminor.week9.threading.atomicity.Hitter;

public class AtomicityDemo implements Hitter {

  private long hits = 0;

  public void hit() {
    hits++;
  }

  public long getHits() {
    return hits;
  }

}
