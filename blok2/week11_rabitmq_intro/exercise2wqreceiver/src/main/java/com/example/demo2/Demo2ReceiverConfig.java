package com.example.demo2;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Demo2ReceiverConfig {

  @Bean
  public Queue hello() {
    return new Queue("hello");
  }

  //Create 2 SpringBeans of Type Demo2Receiver
  @Bean
  public Demo2Receiver receiver1() {
    return new Demo2Receiver(1);
  }

  @Bean
  public Demo2Receiver receiver2() {
    return new Demo2Receiver(2);
  }

}
