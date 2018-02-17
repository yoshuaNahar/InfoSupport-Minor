package com.example.demo3;

import org.springframework.amqp.rabbit.annotation.RabbitListener;

public class Demo3Receiver {

  //Make of this method a RabbitListener that listens to a queue with a randomly generated name
  //Hint use SPEL"#{....}")
  @RabbitListener(queues = "#{autoDeleteQueue1.name}")
  public void receive1(String in) throws InterruptedException {
    receive(in, 1);
  }

  //Make of this method a RabbitListener that listens to a queue with a randomly generated name
  //Hint use SPEL"#{....}")
  @RabbitListener(queues = "#{autoDeleteQueue2.name}")
  public void receive2(String in) throws InterruptedException {
    receive(in, 2);
  }

  public void receive(String in, int receiver) throws InterruptedException {
    doWork(in);
  }

  private void doWork(String in) throws InterruptedException {
    //Do some job where you also print the contents of the message
    System.out.println(in);
    Thread.sleep(100);
  }

}
