package com.example.demo3;

import org.springframework.amqp.core.AnonymousQueue;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Demo3ReceiverConfig {

  //Create a SpringBean of type FanoutExchange with the name fanout
  @Bean
  public FanoutExchange fanoutExchange() {
    return new FanoutExchange("fanout");
  }

  //Create an 2 autoDeleteQueues
  @Bean
  public Queue autoDeleteQueue1() {
    return new AnonymousQueue();
  }

  @Bean
  public Queue autoDeleteQueue2() {
    return new AnonymousQueue();
  }

  //Create 2 bindings from the fanout exchange to the autoDeleteQueues
  @Bean
  public Binding binding1(FanoutExchange fanout, Queue autoDeleteQueue1) {
    return BindingBuilder.bind(autoDeleteQueue1).to(fanout);
  }

  @Bean
  public Binding binding2(FanoutExchange fanout, Queue autoDeleteQueue2) {
    return BindingBuilder.bind(autoDeleteQueue2).to(fanout);
  }

  @Bean
  public Demo3Receiver receiver() {
    return new Demo3Receiver();
  }

}
