package nl.yoboid.rdw.events.listener;

import nl.yoboid.rdw.configurations.RdwApplicationConfig;
import nl.yoboid.rdw.interfaces.RdwCheckEventService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@Import(RdwApplicationConfig.class)
@RunWith(SpringRunner.class)
public class ApkCheckFinishedListenerTest {

  @Mock
  private RdwCheckEventService service;

  @Test
  public void listenToMessageExpectNoExceptionThrown() throws IOException {
    ApkCheckFinishedListener listener = new ApkCheckFinishedListener(service);
    String message = "asd";
    listener.listen(message);
    verify(service, times(1)).checkResponseAndSend(message);
  }
}
