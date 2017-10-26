package nl.infosupport.javaminor.blok1.week4.spring.springlabs.others;

public class Hitter {

  private HitCounter hitCounter;

  public void setHitCounter(HitCounter hitCounter) {
    this.hitCounter = hitCounter;
  }

  public void hit() {
    hitCounter.increment();
  }

  public int getHits() {
    return hitCounter.getHits();
  }

}
