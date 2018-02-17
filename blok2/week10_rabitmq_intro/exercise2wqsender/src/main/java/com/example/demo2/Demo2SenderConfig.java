package com.example.demo2;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Demo2SenderConfig {

  @Bean
  public Queue hello() {
    return new Queue("hello");
  }

  //Create a spring bean of type Demo2Sender
  @Bean
  public Demo2Sender sender() {
    return new Demo2Sender();
  }

}
