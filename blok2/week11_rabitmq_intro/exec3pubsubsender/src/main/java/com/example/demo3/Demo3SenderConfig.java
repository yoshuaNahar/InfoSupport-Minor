package com.example.demo3;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Demo3SenderConfig {

  //Create a springbean of type FanoutExchange with name fanout
  @Bean
  public FanoutExchange fanoutExchange() {
    return new FanoutExchange("fanout");
  }

  //Create a springbean of type Demo3Sender
  @Bean
  public Demo3Sender demo3Sender() {
    return new Demo3Sender();
  }

}
