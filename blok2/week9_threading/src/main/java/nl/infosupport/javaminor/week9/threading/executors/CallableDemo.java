package nl.infosupport.javaminor.week9.threading.executors;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class CallableDemo {

  public static void main(String[] args) throws Exception {
    ExecutorService exec = Executors.newFixedThreadPool(10);

    Callable<Integer> task = () -> {
      //Calculate something
      TimeUnit.SECONDS.sleep(10);
      return 5;
    };

    Future<Integer> future = exec.submit(task);
    System.out.println("After submit");

    //Do other tasks
    Integer result = future.get();
    System.out.println("Result is " + result);
    System.out.println("After get");
    exec.shutdown();
  }

}
