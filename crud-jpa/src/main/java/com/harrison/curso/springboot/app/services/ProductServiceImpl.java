package com.harrison.curso.springboot.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.harrison.curso.springboot.app.entities.Product;
import com.harrison.curso.springboot.app.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    public ProductServiceImpl(@Autowired ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return (List<Product>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Product> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Product save(Product product) {
        return repository.save(product);
    }

    @Override
    @Transactional
    public Optional<Product> update(Long id, Product product) {
        return repository.findById(id).map(foundProduct -> {
            Product toUpdate = new Product(id, product.getName(), product.getPrice(), product.getDescription());
            return Optional.of(repository.save(toUpdate));
        }).orElse(Optional.empty());
    }

    @Override
    @Transactional
    public Optional<Product> delete(Product product) {
        if (product.getId() == null)
            return Optional.empty();

        Optional<Product> toDelete = repository.findById(product.getId());

        toDelete.ifPresent(p -> repository.delete(p));

        return toDelete;
    }

    @Override
    @Transactional
    public Optional<Product> delete(Long id) {
        Optional<Product> toDelete = repository.findById(id);

        toDelete.ifPresent(repository::delete);

        return toDelete;
    }
}
