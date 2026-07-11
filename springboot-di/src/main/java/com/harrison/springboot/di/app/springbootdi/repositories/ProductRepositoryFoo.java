package com.harrison.springboot.di.app.springbootdi.repositories;

import com.harrison.springboot.di.app.springbootdi.models.Product;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

// Primary para definir esta implementación de ProductRepository como la que debe ser inyectada
//@Primary
@Repository("productFoo")
public class ProductRepositoryFoo implements ProductRepository {
    @Override
    public List<Product> findAll() {
        return Collections.singletonList(new Product(1L, "Monitor Asus 27", 600L));
    }

    @Override
    public Product findById(Long id) {
        return new Product(1L, "Monitor Asus 27", 600L);
    }
}
