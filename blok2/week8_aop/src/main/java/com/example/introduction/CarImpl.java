package com.example.introduction;

import org.springframework.stereotype.Component;

@Component
public class CarImpl implements Car {

  @Override
  public void drive() {
    System.out.println("Driving...");
  }

}
