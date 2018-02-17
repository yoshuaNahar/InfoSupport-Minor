package nl.yoboid.maintenance.events.senders;

import nl.yoboid.domain.entities.Vehicle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.json.Jackson2JsonObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class ApkCheckFinishedSender {

  private Logger logger = LoggerFactory.getLogger(ApkCheckFinishedSender.class);

  private final RabbitTemplate template;

  @Autowired
  public ApkCheckFinishedSender(RabbitTemplate template) {
    this.template = template;
  }

  public void send(Vehicle vehicle) {
    try {
      String json = new Jackson2JsonObjectMapper().toJson(vehicle);
      template.convertAndSend("yoboid", "apk.check.finished", json);
      logger.debug("apk.check.finished: {}", json);
    } catch (Exception exception) {
      logger.error(exception.getMessage());
      throw new IllegalArgumentException(exception);
    }
  }

}
