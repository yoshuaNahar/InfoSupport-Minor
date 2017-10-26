package com.example.introduction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class IntroductionApplication implements CommandLineRunner {

  // Image describing AOP: http://i.stack.imgur.com/J7Hrh.png

  @Autowired
  private Car car;

  public static void main(String[] args) {
    // Used to make sure Tomcat is not started
    new SpringApplicationBuilder(IntroductionApplication.class).web(false).run(args);
  }

  @Override
  public void run(String... arg0) throws Exception {
    car.drive();

    Flyer flyer = (Flyer) car;
    flyer.fly();

    Shooter shooter = (Shooter) car;
    shooter.shoot();

    ((Shooter) car).shoot();
  }

}
