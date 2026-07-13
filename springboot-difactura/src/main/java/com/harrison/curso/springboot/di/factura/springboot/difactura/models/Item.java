package com.harrison.curso.springboot.di.factura.springboot.difactura.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class Item {

    @Getter
    private Product product;

    @Getter
    private Integer quantity;

    public int getTotal() {
        return this.product.getPrice() * this.quantity;
    }
}
