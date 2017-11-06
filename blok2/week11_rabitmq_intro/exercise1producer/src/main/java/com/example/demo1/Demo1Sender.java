package com.example.demo1;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

public class Demo1Sender {

  // Inject a rabbitTemplate
  @Autowired
  private RabbitTemplate template;

  // Inject the Queue
  @Autowired
  private Queue queue;

  @Scheduled(fixedDelay = 1000, initialDelay = 500)
  public void send() {
    //create and send a message to the queue
    String message = "Hello World!";
    template.convertAndSend(queue.getName(), message);
    System.out.println(" [x] Sent '" + message + "'");
  }

}
