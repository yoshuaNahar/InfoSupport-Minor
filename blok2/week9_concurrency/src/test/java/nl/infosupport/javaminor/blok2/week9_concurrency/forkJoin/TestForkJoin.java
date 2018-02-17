package nl.infosupport.javaminor.blok2.week9_concurrency.forkJoin;

import java.util.concurrent.ForkJoinPool;
import java.util.stream.LongStream;
import org.junit.Test;

public class TestForkJoin {

  @Test
  public void testForkJoin() {
    ForkJoinPool fjPool = new ForkJoinPool();
    long[] numbers = LongStream.range(0, 10_000_000L).toArray();
    Long sum = fjPool
        .invoke(new ForkJoinSpriteritusFred(numbers, 0, 10_000_000L, 0));
    System.out.println(sum);
  }

}
