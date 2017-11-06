package com.example.demo1;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Demo1ConsumerConfig {

//   Configure a Queue("hello");
  @Bean
  public Queue hello() {
    return new Queue("hello");
  }

  // Configure a @Bean Demo1Receiver
  @Bean
  public Demo1Receiver receiver() {
    return new Demo1Receiver();
  }

}
