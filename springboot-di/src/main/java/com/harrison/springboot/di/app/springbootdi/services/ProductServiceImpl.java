package com.harrison.springboot.di.app.springbootdi.services;

import java.util.List;

import com.harrison.springboot.di.app.springbootdi.models.Product;
import com.harrison.springboot.di.app.springbootdi.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repository;

    @Override
    public List<Product> findAll() {
        return repository.findAll().stream().map(p -> {
            // Se agrega el IVA xd
            double priceIva = p.getPrice() * 1.19d;
            // return new Product(p.getId(), p.getName(), priceIva.longValue());
            Product newProd = (Product) p.clone();
            newProd.setPrice((long) priceIva);
            return newProd;
        }).toList();
    }

    @Override
    public Product findById(Long id) {
        return repository.findById(id);
    }
}
