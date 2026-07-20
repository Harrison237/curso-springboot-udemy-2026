package com.harrison.curso.springboot.app.aop.springboot.aop.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Order(1)
@Component
@Aspect
public class GreetingFooAspect {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Before("GreetingServicePointcuts.greetingFooLoggerPointCut()")
    public void loggerBefore(JoinPoint joinPoint) {
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());

        logger.info("Antes Foo: {}, Invocado con los parámetros: {}", method, args);
    }

    @After("GreetingServicePointcuts.greetingFooLoggerPointCut()")
    public void loggerAfter(JoinPoint joinPoint) {
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());

        logger.info("Después Foo: {}, Invocado con los parámetros: {}", method, args);
    }

}
