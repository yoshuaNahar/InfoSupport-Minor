package nl.yoboid.rdw.events.senders;

import nl.yoboid.rdw.configurations.RdwApplicationConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@Import(RdwApplicationConfig.class)
@RunWith(SpringRunner.class)
public class RdwCheckDeclinedSenderTest {
  @Mock
  private RabbitTemplate template;

  @Test
  public void listenToMessageExpectTemplateConvertAndSendInvokedOnceWithThreeStrings() throws IOException {
    RdwCheckDeclinedSender sender = new RdwCheckDeclinedSender(template);
    String id = "1";
    sender.send(id);

    verify(template, times(1)).convertAndSend(any(String.class), any(String.class), eq(id));
  }
}
