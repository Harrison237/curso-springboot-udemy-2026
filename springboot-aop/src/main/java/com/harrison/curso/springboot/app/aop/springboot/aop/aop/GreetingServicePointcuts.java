package com.harrison.curso.springboot.app.aop.springboot.aop.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class GreetingServicePointcuts {
    @Pointcut("execution(* com.harrison.curso.springboot.app.aop.springboot.aop.services.*.*(..))")
    public void greetingLoggerPointCut() {
    }

    @Pointcut("execution(* com.harrison.curso.springboot.app.aop.springboot.aop.services.GreetingService.*(..))")
    public void greetingFooLoggerPointCut() {
    }
}
