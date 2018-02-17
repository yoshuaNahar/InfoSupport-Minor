package com.example.demo4;

import org.springframework.amqp.core.AnonymousQueue;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Demo4ReceiverConfig {

  //Create a springbean of type DirectExchange with name direct
  @Bean
  public DirectExchange direct() {
    return new DirectExchange("tut.direct");
  }

  //Create 2 springbeans that automatically delete themselves
  @Bean
  public Queue autoDeleteQueue1() {
    return new AnonymousQueue();
  }

  @Bean
  public Queue autoDeleteQueue2() {
    return new AnonymousQueue();
  }

  //Create 2 binding springbeans that bind to the first autoDeleteQueue when the routingkey orange or black is used.
  @Bean //Create a couple of bindings to colours
  public Binding binding1a(DirectExchange direct, Queue autoDeleteQueue1) {
    return BindingBuilder.bind(autoDeleteQueue1).to(direct).with("orange");
  }

  @Bean //Create a couple of bindings to colours
  public Binding binding1b(DirectExchange direct, Queue autoDeleteQueue2) {
    return BindingBuilder.bind(autoDeleteQueue2).to(direct).with("green");
  }

    //Create 2 other bindings as springbeans that bind to the second autoDeleteQueue when the routingkey is green or black.

    @Bean
    public Demo4Receiver receiver () {
      return new Demo4Receiver();
    }

  }
