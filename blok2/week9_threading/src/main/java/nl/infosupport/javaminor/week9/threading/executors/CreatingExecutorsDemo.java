package nl.infosupport.javaminor.week9.threading.executors;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CreatingExecutorsDemo {

  public static void main(String[] args) {
    Executor exec = Executors.newFixedThreadPool(20);

    Runnable task = () -> {
      try {
        TimeUnit.SECONDS.sleep(5);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println("I did something!");
    };

    exec.execute(task);
    System.out.println("I'm still alive");
  }

}
