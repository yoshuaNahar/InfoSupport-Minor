package com.example.demo2;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.util.StopWatch;

//Make this class a RabbitListener to the queue "hello"
@RabbitListener(queues = "hello")
public class Demo2Receiver {

  private final int instance;

  public Demo2Receiver(int i) {
    this.instance = i;
  }

  //This method should be called when a message arrives on the queue
  public void receive(String in) throws InterruptedException {
    StopWatch watch = new StopWatch();
    watch.start();
    doWork(in);
    watch.stop();
    System.out.println(watch.getTotalTimeSeconds());
    //heavy lifting in doWork()
    //Measure time and print duration to console
  }

  private void doWork(String in) throws InterruptedException {
    for (char c : in.toCharArray()) {
      Thread.sleep(100);
    }
    //Work hard for every  character in the payload
    //Sleep a second for every character send representing the hardwork
  }

}
