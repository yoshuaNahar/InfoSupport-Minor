package com.example.introduction;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AbilityIntroduction {

  @DeclareParents(value = "com.example.introduction.CarImpl", defaultImpl = com.example.introduction.FlyerImpl.class)
  public Flyer flyer;

  @DeclareParents(value = "com.example.introduction.CarImpl", defaultImpl = com.example.introduction.ShooterImpl.class)
  public Shooter shooter;

}
