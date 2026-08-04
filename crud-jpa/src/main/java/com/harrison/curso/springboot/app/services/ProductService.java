package com.harrison.curso.springboot.app.services;

import java.util.List;
import java.util.Optional;

import com.harrison.curso.springboot.app.entities.Product;

public interface ProductService {

    List<Product> findAll();

    Optional<Product> findById(Long id);

    Product save(Product product);

    Optional<Product> delete(Product product);
}
