package nl.infosupprt.javaminor.blok2.week9_concurrency.asyncFuture;

import java.util.concurrent.Future;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

@Component
public class SleepService {

  @Async
  public Future<String> sleep() throws InterruptedException {
    Thread.sleep(3000L);
    return new AsyncResult<>("OK");
  }

}
