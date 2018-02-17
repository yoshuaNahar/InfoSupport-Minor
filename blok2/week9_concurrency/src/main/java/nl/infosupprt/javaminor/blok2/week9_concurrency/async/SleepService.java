package nl.infosupprt.javaminor.blok2.week9_concurrency.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class SleepService {

  @Async
  public void sleep() throws InterruptedException {
    Thread.sleep(3000L);
  }

}
