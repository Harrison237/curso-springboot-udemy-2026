package com.harrison.springboot.di.app.springbootdi.services;

import java.util.List;

import com.harrison.springboot.di.app.springbootdi.models.Product;
import com.harrison.springboot.di.app.springbootdi.repositories.ProductRepository;

public class ProductService {
    private ProductRepository repository = new ProductRepository();

    public List<Product> findAll() {
        return repository.findAll().stream().map(p -> {
            // Se agrega el IVA xd
            Double priceIva = p.getPrice() * 1.19d;
            // return new Product(p.getId(), p.getName(), priceIva.longValue());
            Product newProd = (Product) p.clone();
            newProd.setPrice(priceIva.longValue());
            return newProd;
        }).toList();
    }

    public Product findById(Long id) {
        return repository.findById(id);
    }
}
