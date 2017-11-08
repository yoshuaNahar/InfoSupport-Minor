package com.example.demo4;



import org.springframework.amqp.core.DirectExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Demo4SenderConfig {

	//Create a springbean of type DirectExchange and name direct
    @Bean
    public DirectExchange direct() {
        return new DirectExchange("tut.direct");
    }

    @Bean
    public Demo4Sender sender() {
        return new Demo4Sender();
    }
}