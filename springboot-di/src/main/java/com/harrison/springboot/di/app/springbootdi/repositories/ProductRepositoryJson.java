package com.harrison.springboot.di.app.springbootdi.repositories;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.harrison.springboot.di.app.springbootdi.models.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ProductRepositoryJson implements ProductRepository {
    private final List<Product> list;

    // Esto no funciona en este repository ya que no está anotado como componente de Spring (la inyección se hace mediante el AppConfig)
//    @Value("classpath:json/products.json")
//    private Resource resource;

    public ProductRepositoryJson() {
        // Clase de Spring Boot que sirve para cargar recursos en el classpath (carpeta resources)
//        Resource resource = new ClassPathResource("json/products.json");
        this(null);
    }

    public ProductRepositoryJson(Resource resource) {
        if (resource == null)
            // Clase de Spring Boot que sirve para cargar recursos en el classpath (carpeta resources)
            resource = new ClassPathResource("json/products.json");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // El método readValue de objectMapper acepta la ruta al archivo (getFile()) o el stream de bytes (getInputStream())
            this.list = Arrays.asList(objectMapper.readValue(resource.getInputStream(), Product[].class));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Product> findAll() {
        return this.list;
    }

    @Override
    public Product findById(Long id) {
        return list.stream().filter(p ->
                p.getId().equals(id)
        ).findFirst().orElseThrow();
    }

    public List<Product> getList() {
        return list;
    }
}
