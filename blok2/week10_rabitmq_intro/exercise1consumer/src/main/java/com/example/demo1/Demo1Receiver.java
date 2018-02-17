package com.example.demo1;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

// Make Demo1Receiver a  RabbitListener to the queue "hello"
@RabbitListener(queues = "hello")
public class Demo1Receiver {

  // Create a RabbitHandler that handles the message
  @RabbitHandler
  public void receive(String in) {
    System.out.println(" [x] Received '" + in + "'");
  }

}
