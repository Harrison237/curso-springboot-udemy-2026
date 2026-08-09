package com.harrison.curso.springboot.app.entities;

import com.harrison.curso.springboot.app.validation.IsRequired;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product")
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Getter
    @IsRequired
    private String sku;

    @Getter
    @IsRequired(message = "{IsRequired.product.name}")
    @Size(min = 3, max = 20)
    private String name;

    @Getter
    @Min(value = 500, message = "{Min.product.price}")
    @NotNull(message = "{NotEmpty.product.price}")
    private Integer price;

    @Getter
    @IsRequired(/* message = "{NotEmpty.product.description}" */)
    private String description;
}
