package com.harrison.springboot.di.app.springbootdi.services;

import java.util.List;

import com.harrison.springboot.di.app.springbootdi.models.Product;
import com.harrison.springboot.di.app.springbootdi.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    //@Autowired
    //@Qualifier("productList")
    private ProductRepository repository;

    //@Autowired
    //public void setRepository(ProductRepository repository) {
    //    this.repository = repository;
    //}

    // Cuando se inyecta por el constructor no hace falta utilizar el @Autowired ya que se hace de forma automática
    // @Qualifier tiene prioridad por encima de @Primary
    public ProductServiceImpl(@Qualifier("productList") ProductRepository repository) {
        this.repository = repository;
    }

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
