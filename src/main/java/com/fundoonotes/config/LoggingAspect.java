package com.fundoonotes.config;

import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* com.fundoonotes.service..*(..))")
    public void logBefore() {
        System.out.println("Service method executing...");
    }
}