package com.example.demo1;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RabbitAmqpApplication {

  public static void main(String[] args) throws Exception {
    SpringApplication.run(RabbitAmqpApplication.class, args);
  }

  @Bean
  public CommandLineRunner tutorial() {
    return new RabbitAmqpRunner();
  }

}
