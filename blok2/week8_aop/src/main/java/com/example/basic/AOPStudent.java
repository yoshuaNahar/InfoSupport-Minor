package com.example.basic;

import com.example.AOPAnnotation;
import org.springframework.stereotype.Component;

@Component("student")
public class AOPStudent {

  private String name = "Wim";

  private int age = 42;

  public AOPStudent() {
    super();
  }

  public AOPStudent(String name, int age) {
    super();
    this.name = name;
    this.age = age;
  }

  public String hello() {
    return "Hello student";
  }

  public String goodbye() {
    return "Goodbye student";
  }

  public void crash() throws Exception {
    throw new Exception();
  }

  @AOPAnnotation
  public void annotation() {
    System.out.println("Method with annotation");
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
