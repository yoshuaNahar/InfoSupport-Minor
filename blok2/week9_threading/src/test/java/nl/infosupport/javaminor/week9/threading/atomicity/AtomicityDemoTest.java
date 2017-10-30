package nl.infosupport.javaminor.week9.threading.atomicity;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import nl.infosupport.javaminor.week9.threading.atomicity.intro.AtomicityDemo;
import nl.infosupport.javaminor.week9.threading.atomicity.working.solution1.SynchronizedDemo;
import nl.infosupport.javaminor.week9.threading.atomicity.working.solution2.AtomicLongDemo;
import org.junit.Test;

public class AtomicityDemoTest {

  private static final int NUMBER_OF_TASKS_EXECUTED = 100000;

  @Test
  public void testHitBrokenConcurrent() throws Exception {
    final Hitter demo = new AtomicityDemo();

    runConcurrent(demo);
  }

  @Test
  public void testHitSolution1Concurrent() throws Exception {
    final Hitter demo = new SynchronizedDemo();

    runConcurrent(demo);
  }

  @Test
  public void testHitSolution2Concurrent() throws Exception {

    final Hitter demo = new AtomicLongDemo();

    runConcurrent(demo);
  }

  private void runConcurrent(final Hitter demo)
      throws InterruptedException, BrokenBarrierException {

    final CountDownLatch latch = new CountDownLatch(NUMBER_OF_TASKS_EXECUTED);
    Executor exec = Executors.newFixedThreadPool(7);

    Runnable task = new Runnable() {
      public void run() {
        demo.hit();
        latch.countDown();
      }
    };

    long time = System.currentTimeMillis();
    for (int i = 0; i < NUMBER_OF_TASKS_EXECUTED; i++) {
      exec.execute(task);
    }

    latch.await();
    long duration = System.currentTimeMillis() - time;
    assertEquals(NUMBER_OF_TASKS_EXECUTED, demo.getHits());
    System.out.println("Time running: " + duration + " ms");
  }

}
