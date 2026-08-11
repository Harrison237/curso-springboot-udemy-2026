package com.harrison.curso.springboot.backend.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.harrison.curso.springboot.backend.entities.Product;

@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:4200"})
@RepositoryRestResource(path = "products")
public interface ProductRepository extends CrudRepository<Product, Long> {

}
