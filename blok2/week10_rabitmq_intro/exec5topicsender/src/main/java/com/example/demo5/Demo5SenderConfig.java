package com.example.demo5;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Demo5SenderConfig {

  //Create a springBean of type TopicExchange and name topic

  @Bean
  public Demo5Sender sender() {
    return new Demo5Sender();
  }

}
