package nl.infosupport.javaminor.blok1.week4.spring.springlabs.others;

public abstract class DynamicHitter {

  public void hit() {
    getHitCounter().increment();
  }

  public int getHits() {
    return getHitCounter().getHits();
  }

  public abstract HitCounter getHitCounter();

}
