package com.example.demo5;

import org.springframework.amqp.core.AnonymousQueue;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Demo5ReceiverConfig {

  //Create a springBean of type TopicExchange and name topic

  @Bean
  public Demo5Receiver receiver() {
    return new Demo5Receiver();
  }

  @Bean
  public Queue autoDeleteQueue1() {
    return new AnonymousQueue();
  }

  @Bean
  public Queue autoDeleteQueue2() {
    return new AnonymousQueue();
  }

  //Create a binding between  Queue1 and the topicExchange for arbitrary type of animals and speed but with colour orange

  //Create a binding between  Queue1 and the exchange for arbitrary speed and colour but species rabbit

  //Create a binding between everything that is lazy, further no restrictions

}
