package nl.infosupport.javaminor.week9.threading.multiatomicity;

import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import nl.infosupport.javaminor.week9.threading.multiatomicity.broken.MultiValueAtomicityDemo;
import nl.infosupport.javaminor.week9.threading.multiatomicity.working.badsolution.BadSynchronizationDemo;
import nl.infosupport.javaminor.week9.threading.multiatomicity.working.solution.CorrectDemo;
import org.junit.Test;

public class MultiValueAtomicityDemoTest {

  @Test
  public void testBrokenConcurrency() {
    final Factorizer demo = new MultiValueAtomicityDemo();
    testConcurrent(demo);
  }

  @Test
  public void testBadSynchronization() {
    final Factorizer demo = new BadSynchronizationDemo();
    testConcurrent(demo);
  }

  @Test
  public void testCorrectSynchronization() {
    final Factorizer demo = new CorrectDemo();
    testConcurrent(demo);
  }

  private void testConcurrent(final Factorizer demo) {
    ExecutorService exec = Executors.newFixedThreadPool(100);
    //Executor exec = Executors.newSingleThreadExecutor();
    for (int i = 1; i <= 100000; i++) {
      final int nr = i;

      System.out.println("Nr: " + nr);
      Runnable task = () -> {
        Long[] result = demo.factor(nr);

        assertArrayEquals(calculate(nr), result);
      };

      exec.execute(task);
      exec.execute(task);
    }
  }

  private Long[] calculate(long number) {
    List<Long> factorsList = new ArrayList<Long>();

    for (long i = 2; i < number; i++) {
      if (number % i == 0) {
        factorsList.add(i);
      }
    }
    Long[] result = new Long[factorsList.size()];

    return factorsList.toArray(result);
  }

}
