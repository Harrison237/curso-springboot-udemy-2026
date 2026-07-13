package com.harrison.curso.springboot.di.factura.springboot.difactura.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Getter
    private String name;

    @Getter
    private Integer price;
}
