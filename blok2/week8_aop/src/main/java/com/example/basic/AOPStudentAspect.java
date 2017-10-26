package com.example.basic;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AOPStudentAspect {

  @Before("execution(public String hello())")
  public void before(JoinPoint joinpoint) {
    System.out.println(joinpoint.getStaticPart().toString());
    System.out.println("Begin");
  }

  @Before("execution(* com.example.*.get*())")
  public void getGetterAdvice() {
    System.out.println("Getter is called");
  }

  @After("execution(public String goodbye())")
  public void after() {
    System.out.println("End");
  }

  @AfterThrowing("execution(* *.crash(..))")
  public void throwing() {
    System.out.println("Exception was thrown");
  }

  @Around("@annotation(com.example.AOPAnnotation)")
  public void around(ProceedingJoinPoint proceedingJoinPoint) {
    System.out.println("Start around advice");
    try {
      proceedingJoinPoint.proceed();
    } catch (Throwable e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    System.out.println("End around advice");
  }

}