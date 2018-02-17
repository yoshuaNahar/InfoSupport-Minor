package com.example.demo4;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.util.StopWatch;

public class Demo4Receiver {

  @RabbitListener(queues = "#{autoDeleteQueue1.name}")
  public void receive1(String in) {
    receive(in, 1);
  }

  @RabbitListener(queues = "#{autoDeleteQueue2.name}")
  public void receive2(String in) {
    receive(in, 2);
  }

  public void receive(String in, int receiver) {
    StopWatch watch = new StopWatch();
    watch.start();
    System.out.println("instance " + receiver + " [x] Received '" + in + "'");
    doWork(in);
    watch.stop();
    System.out.println("instance " + receiver + " [x] Done in " +
        watch.getTotalTimeSeconds() + "s");
  }

  private void doWork(String in) {
    //Do some work
  }

}
