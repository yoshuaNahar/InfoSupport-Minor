package com.example.demo4;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

public class Demo4Sender {

  private final String[] keys = {"orange", "black", "green"};

  @Autowired
  private RabbitTemplate template;

  @Autowired
  private DirectExchange direct;

  private int index;
  private int count;

  @Scheduled(fixedDelay = 1000, initialDelay = 500)
  public void send() {
    // Create and send to the direct exchange, use a routing key that switches between the
    // values available  in the arry keyx
  }

}
