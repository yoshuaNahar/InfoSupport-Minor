package com.example.basic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class AOPApplication implements CommandLineRunner {

  // Image describing AOP: http://i.stack.imgur.com/J7Hrh.png

  @Autowired
  private AOPStudent student;

  public static void main(String[] args) {
    // Used to make sure Tomcat is not started
    new SpringApplicationBuilder(AOPApplication.class).web(false).run(args);
  }

  @Override
  public void run(String... arg0) throws Exception {
    System.out.println(student.hello());
    System.out.println(student.getAge());
    System.out.println(student.getName());
    System.out.println(student.goodbye());

    student.annotation();
    try {
      student.crash();
    } catch (Exception e) {

    }
  }
}
