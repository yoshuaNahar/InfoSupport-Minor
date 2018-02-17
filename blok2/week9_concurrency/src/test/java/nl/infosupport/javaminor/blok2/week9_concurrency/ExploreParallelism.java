package nl.infosupport.javaminor.blok2.week9_concurrency;

import java.util.stream.LongStream;
import java.util.stream.Stream;
import org.junit.Test;

public class ExploreParallelism {

  @Test // 74 ms
  public void sequentialSumAverageTime() {
    long n = 10_000_000L;
    long sum = 0;
    long minduration = Long.MAX_VALUE;
    for (int run = 0; run < 10; run++) {
      long startTime = System.nanoTime();
      sum = sequentialSum(n);
      long finishTime = System.nanoTime();

      long duration = (finishTime - startTime) / 1_000_000;
      if (duration < minduration) {
        minduration = duration;
      }
    }
    System.out.println("Som = " + sum + " in avg. time = " + minduration + "ms");
  }

  @Test //13ms
  public void primitiveSequentialSumAverageTime() {
    long n = 10_000_000L;
    long sum = 0;
    long minduration = Long.MAX_VALUE;
    for (int run = 0; run < 10; run++) {
      long startTime = System.nanoTime();
      sum = primitiveSequentialSum(n);
      long finishTime = System.nanoTime();

      long duration = (finishTime - startTime) / 1_000_000;
      if (duration < minduration) {
        minduration = duration;
      }
    }
    System.out.println("Som = " + sum + " in avg. time = " + minduration + "ms");
  }

  @Test //112 ms
  public void parallelSumAverageTime() {
    long n = 10_000_000L;
    long sum = 0;
    long minduration = Long.MAX_VALUE;
    for (int run = 0; run < 10; run++) {
      long startTime = System.nanoTime();
      sum = parallelSum(n);
      long finishTime = System.nanoTime();

      long duration = (finishTime - startTime) / 1_000_000;
      if (duration < minduration) {
        minduration = duration;
      }
    }
    System.out.println("Som = " + sum + " in avg. time = " + minduration + "ms");
  }

  @Test //1ms
  public void primitiveParallelSumAverageTime() {
    long n = 10_000_000L;
    long sum = 0;
    long minduration = Long.MAX_VALUE;
    for (int run = 0; run < 10; run++) {
      long startTime = System.nanoTime();
      sum = primitiveRangeClosedParallelSum(n);
      long finishTime = System.nanoTime();

      long duration = (finishTime - startTime) / 1_000_000;
      if (duration < minduration) {
        minduration = duration;
      }
    }
    System.out.println("Som = " + sum + " in avg. time = " + minduration + "ms");
  }

  private long sequentialSum(long n) {
    return Stream.iterate(0L, i -> i + 1L)
        .limit(n)
        .reduce(Long::sum)
        .get();
  }

  private long primitiveSequentialSum(long n) {
    return LongStream.iterate(0L, i -> i + 1L)
        .limit(n)
        .sum();
  }

  private long parallelSum(long n) {
    return Stream.iterate(0L, i -> i + 1L)
        .limit(n)
        .parallel()
        .reduce(Long::sum)
        .get();
  }

  private long primitiveRangeClosedParallelSum(long n) {
    return LongStream.rangeClosed(0, n)
        .parallel()
        .sum();
  }

}
