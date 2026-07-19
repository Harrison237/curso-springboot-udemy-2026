package com.harrison.curso.springboot.app.aop.springboot.aop.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class GreetingAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Before("execution(* com.harrison.curso.springboot.app.aop.springboot.aop.services.GreetingService.*(..))")
    public void loggerBefore(JoinPoint joinPoint) {
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());

        logger.info("Antes: {}, Argumentos: {}", method, args);
    }

    @After("execution(* com.harrison.curso.springboot.app.aop.springboot.aop.services.GreetingService.*(..))")
    public void loggerAfter(JoinPoint joinPoint) {
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());

        logger.info("Después: {}, Argumentos: {}", method, args);
    }

    @AfterReturning("execution(* com.harrison.curso.springboot.app.aop.springboot.aop.services.GreetingService.*(..))")
    public void loggerAfterReturning(JoinPoint joinPoint) {
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());

        logger.info("Después de retornar: {}, Argumentos: {}", method, args);
    }

    @AfterThrowing("execution(* com.harrison.curso.springboot.app.aop.springboot.aop.services.GreetingService.*(..))")
    public void loggerAfterThrowing(JoinPoint joinPoint) {
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());

        logger.info("Después de lanzar la excepción: {}, Argumentos: {}", method, args);
    }
}
