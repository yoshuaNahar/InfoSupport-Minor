package nl.infosupport.javaminor.week9.threading.atomicity.working.solution1;

import nl.infosupport.javaminor.week9.threading.atomicity.Hitter;

public class SynchronizedDemo implements Hitter {

  private long hits = 0;

  public synchronized void hit() {

    hits++;
  }

  public synchronized long getHits() {
    return hits;
  }

}
