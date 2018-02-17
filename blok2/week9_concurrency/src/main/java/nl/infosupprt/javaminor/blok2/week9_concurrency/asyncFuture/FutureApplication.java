package nl.infosupprt.javaminor.blok2.week9_concurrency.asyncFuture;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class FutureApplication implements CommandLineRunner {

  @Autowired
  private SleepService sleepService;

  public static void main(String[] args) {
    new SpringApplicationBuilder(FutureApplication.class).web(false).run(args);
  }

  public void run(String... strings)
      throws InterruptedException, ExecutionException, TimeoutException {
    long startTime = System.currentTimeMillis();
    Future<String> result1 = sleepService.sleep();
    Future<String> result2 = sleepService.sleep();

    System.out.println("Result 1: " + result1.get(5000L, TimeUnit.MILLISECONDS));
    System.out.println("Result 2: " + result2.get(5000L, TimeUnit.MILLISECONDS));

    long endTime = System.currentTimeMillis();
    long time = endTime - startTime;
    System.out.println("Elapsed time: " + time);
  }

}
