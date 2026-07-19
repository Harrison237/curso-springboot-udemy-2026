package com.harrison.curso.springboot.app.aop.springboot.aop.services;

import org.springframework.stereotype.Service;

@Service
public class GreetingServiceImpl implements GreetingService {
    @Override
    public String sayHello(String person, String phrase) {
        String greeting = phrase + " " + person;
        System.out.println(greeting);
        return greeting;
    }

    @Override
    public String sayHelloError(String person, String phrase) throws Exception {
        throw new RuntimeException("excepción");
    }
}
