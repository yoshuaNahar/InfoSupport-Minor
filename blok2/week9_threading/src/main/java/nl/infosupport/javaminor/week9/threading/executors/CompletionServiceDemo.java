package nl.infosupport.javaminor.week9.threading.executors;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class CompletionServiceDemo {

  public static void main(String[] args) throws Exception {
    Executor exec = Executors.newFixedThreadPool(20);
    CompletionService<Integer> completionService =
        new ExecutorCompletionService<Integer>(exec);
    long startTime = getTimeNN();
    for (int i = 1; i < 100; i++) {
      final int number = i;

      Callable<Integer> task = () -> {
        float sleep = Math.round(Math.random() * 10);

        int sleep2 = (int) sleep;
        System.out.println("slaaptijd is  " + sleep2);
        TimeUnit.SECONDS.sleep(sleep2);
        return number * number;
      };

      completionService.submit(task);
    }

    for (int i = 1; i < 100; i++) {
      Future<Integer> f = completionService.take();
      Integer result = f.get();
      System.out.println("Result " + i + ": " + result);
    }

    long endTime = getTimeNN();
    System.out.println("klaar! in " + (endTime - startTime) / 1000_000_000 + "s");

    System.exit(0);
  }

  private static long getTimeNN() {
    return System.nanoTime();
  }

}
    