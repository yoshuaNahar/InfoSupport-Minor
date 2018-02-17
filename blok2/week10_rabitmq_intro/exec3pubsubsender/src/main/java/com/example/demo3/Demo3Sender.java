package com.example.demo3;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

public class Demo3Sender {

  int dots = 0;
  int count = 0;

  //Inject the FanoutExchange springbean
  @Autowired
  private FanoutExchange fanout;

  //Inject a dependency to the template that connects to Rabbitmq
  @Autowired
  private RabbitTemplate template;

  @Scheduled(fixedDelay = 1000, initialDelay = 500)
  public void send() {
    StringBuilder builder = new StringBuilder("Hello");
    if (dots++ == 3) {
      dots = 1;
    }
    for (int i = 0; i < dots; i++) {
      builder.append('.');
    }
    builder.append(Integer.toString(++count));
    String message = builder.toString();
    //Send message to the fanout exchange
    template.convertAndSend(fanout.getName(), "", message);
    System.out.println(" [x] Sent '" + message + "'");
  }

}
