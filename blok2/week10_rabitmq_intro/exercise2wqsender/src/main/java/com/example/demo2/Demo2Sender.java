package com.example.demo2;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

public class Demo2Sender {

  @Autowired
  private RabbitTemplate template;

  @Autowired
  private Queue queue;

  @Scheduled(fixedDelay = 1000, initialDelay = 500)
  public void send() {
    template.convertAndSend(queue.getName(), "message");
    //Create a variable lengt message where each character represents a fixed amount of work
    //template.convertAndSend(queue.getName(), message);
  }

}
