package com.example.demo1;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Create  a Java Based configuration class
@Configuration
public class Demo1ProducerConfig {

  // Config a Bean of type Queue with name "hello"
  @Bean
  public Queue hello() {
    return new Queue("hello");
  }

  // Config a Bean Demo1Sender
  @Bean
  public Demo1Sender sender() {
    return new Demo1Sender();
  }

}
