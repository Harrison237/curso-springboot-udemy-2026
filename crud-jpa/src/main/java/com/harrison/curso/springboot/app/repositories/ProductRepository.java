package com.harrison.curso.springboot.app.repositories;

import org.springframework.data.repository.CrudRepository;

import com.harrison.curso.springboot.app.entities.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {
    boolean existsBySku(String sku);
}
