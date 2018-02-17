package nl.infosupprt.javaminor.blok2.week9_concurrency.async;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class AsynchronousApplication implements CommandLineRunner {

  @Autowired
  private SleepService sleepService;

  public static void main(String[] args) {
    new SpringApplicationBuilder(AsynchronousApplication.class).web(false).run(args);
  }

  public void run(String... strings) throws InterruptedException {
    long startTime = System.currentTimeMillis();
    sleepService.sleep();
    sleepService.sleep();
    long endTime = System.currentTimeMillis();
    long time = endTime - startTime;
    System.out.println("Elapsed time: " + time);
  }

}
