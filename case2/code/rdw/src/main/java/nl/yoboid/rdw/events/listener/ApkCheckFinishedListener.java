package nl.yoboid.rdw.events.listener;

import nl.yoboid.rdw.interfaces.RdwCheckEventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ApkCheckFinishedListener {

  private Logger logger = LoggerFactory.getLogger(ApkCheckFinishedListener.class);

  private RdwCheckEventService service;

  @Autowired
  public ApkCheckFinishedListener(RdwCheckEventService service) {
    this.service = service;
  }

  @RabbitListener(bindings = @QueueBinding(
    value = @Queue,
    exchange = @Exchange(
      value = "yoboid",
      type = ExchangeTypes.TOPIC,
      durable = "true"),
    key = "apk.check.finished"))
  public void listen(String message) throws IOException {
    logger.debug("apk.check.finished: {}", message);
    service.checkResponseAndSend(message);
  }

}
