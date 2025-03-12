package com.teachmeskills.market_32.app;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExecutionTimeAspect {

    @Around("@annotation(com.teachmeskills.market_32.annotation.ExecutionTime)")
    public Object executionTimeAspect(ProceedingJoinPoint joinPoint) throws Throwable {

        long startTime = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long excecutionTime = System.currentTimeMillis() - startTime;

        if (excecutionTime > 1000){
            System.out.println( joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName() + " executed in " + excecutionTime + " ms");
        }

        return proceed;
    }

}
