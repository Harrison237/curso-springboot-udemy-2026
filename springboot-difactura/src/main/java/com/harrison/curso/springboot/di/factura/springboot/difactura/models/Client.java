package com.harrison.curso.springboot.di.factura.springboot.difactura.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@NoArgsConstructor
@AllArgsConstructor
@Component
@RequestScope
public class Client {

    @Getter
    @Value("${client.name}")
    private String name;

    @Getter
    @Value("${client.lastname}")
    private String lastname;
}
