package com.teachmeskills.market_32.app;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Aspect
@Component
public class LogAspect {

    @Before("@annotation(com.teachmeskills.market_32.annotation.LoggerAnnotation)")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println(new Date() + " INFO IN:  " + joinPoint.getSignature().getName() );
    }

    @After("@annotation(com.teachmeskills.market_32.annotation.LoggerAnnotation)")
    public void logAfter(JoinPoint joinPoint) {
        System.out.println(new Date() + " INFO OUT: " + joinPoint.getSignature().getName());
    }

    @AfterThrowing(value = "@annotation(com.teachmeskills.market_32.annotation.LoggerAnnotation)", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint,  Exception e) {
        System.out.println(new Date() + " ERROR:  " + joinPoint.getSignature().getName() + " -> " + e.getMessage());
    }

    @AfterReturning(value = "@annotation(com.teachmeskills.market_32.annotation.LoggerAnnotation)", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint,  Object result) {
        System.out.println(new Date() + " INFO RESULT: " + joinPoint.getSignature().getName() + " -> " + result);
    }

}
