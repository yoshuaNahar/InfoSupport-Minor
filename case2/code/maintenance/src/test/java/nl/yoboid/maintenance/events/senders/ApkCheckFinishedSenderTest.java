package nl.yoboid.maintenance.events.senders;

import nl.yoboid.domain.entities.Vehicle;
import nl.yoboid.maintenance.configurations.MaintenanceApplicationConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@Import(MaintenanceApplicationConfig.class)
@RunWith(SpringRunner.class)
public class ApkCheckFinishedSenderTest {

  @Mock
  private RabbitTemplate template;

  @Test
  public void SendValidVehicleExpectNoExceptionThrown(){
    ApkCheckFinishedSender sender = new ApkCheckFinishedSender(template);
    sender.send(new Vehicle());
    verify(template, times(1)).convertAndSend(any(String.class), any(String.class), any(String.class));
  }
}
