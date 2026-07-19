package com.harrison.curso.springboot.app.aop.springboot.aop.services;

public interface GreetingService {
    String sayHello(String person, String phrase);
    String sayHelloError(String person, String phrase) throws Exception;
}
