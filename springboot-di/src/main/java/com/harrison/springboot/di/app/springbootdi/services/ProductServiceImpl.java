package com.harrison.springboot.di.app.springbootdi.services;

import java.util.List;

import com.harrison.springboot.di.app.springbootdi.models.Product;
import com.harrison.springboot.di.app.springbootdi.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

//    @Autowired
//    @Qualifier("productList")

//    @Autowired
    private final Environment environment;
    private final ProductRepository repository;

    //    @Value("${tax.value}")
    private final double tax;

//    @Autowired
//    public void setRepository(ProductRepository repository) {
//        this.repository = repository;
//    }

    // Cuando se inyecta por el constructor no hace falta utilizar el @Autowired ya que se hace de forma automática
    // @Qualifier tiene prioridad por encima de @Primary
    public ProductServiceImpl(
            @Qualifier("productRepositoryJson") ProductRepository repository,
            Environment environment
    ) {
        this.repository = repository;
        this.environment = environment;
        this.tax = this.environment.getProperty("tax.value", Double.class, 1d);
    }

    @Override
    public List<Product> findAll() {
        return repository.findAll().stream().map(p -> {
            // Se agrega el IVA xd
            double priceIva = p.getPrice() * tax;

            // return new Product(p.getId(), p.getName(), priceIva.longValue());

//            p.setPrice((long) priceIva);
//            return p;

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
