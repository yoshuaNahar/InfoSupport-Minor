package nl.infosupport.javaminor.blok2.week9_concurrency.forkJoin;

import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class ForkJoinSpriteritusFred extends RecursiveTask<Long> {

  private static final long THRESHOLD = 10_000L;

  private long[] numbers;
  private long start;
  private long end;
  private int depth;

  public ForkJoinSpriteritusFred(long[] numbers, long start, long end, int depth) {
    this.numbers = numbers;
    this.start = start;
    this.end = end;
    this.depth = depth;
  }

  protected Long compute() {
    if ((end - start) < THRESHOLD) {
      long sum = 0;
      long index = start;
      while (index < end) {
        sum += numbers[(int) index++];

      }
      return sum;
    }

    ForkJoinTask<Long> leftTask = new ForkJoinSpriteritusFred(numbers, start, (start + end) / 2,
        depth + 1).fork();

    Long rightValue = new ForkJoinSpriteritusFred(numbers, (start + end) / 2, end, depth + 1)
        .compute();

    Long leftValue = leftTask.join();

    return rightValue + leftValue;
  }

}
