package nl.yoboid.rdw.events.senders;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RdwCheckDeclinedSender {

  private Logger logger = LoggerFactory.getLogger(RdwCheckDeclinedSender.class);

  private final RabbitTemplate template;

  @Autowired
  public RdwCheckDeclinedSender(RabbitTemplate template) {
    this.template = template;
  }

  public void send(String id) {
    template.convertAndSend("yoboid", "rdw.check.declined", id);
    logger.debug("rdw.check.declined: {}", id);
  }

}
