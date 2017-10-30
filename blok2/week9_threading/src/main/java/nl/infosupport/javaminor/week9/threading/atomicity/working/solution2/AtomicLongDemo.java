package nl.infosupport.javaminor.week9.threading.atomicity.working.solution2;

import java.util.concurrent.atomic.AtomicLong;
import nl.infosupport.javaminor.week9.threading.atomicity.Hitter;

public class AtomicLongDemo implements Hitter {

  private AtomicLong hits = new AtomicLong(0);

  public void hit() {
    hits.getAndIncrement();
  }

  public long getHits() {
    return hits.get();
  }

}
