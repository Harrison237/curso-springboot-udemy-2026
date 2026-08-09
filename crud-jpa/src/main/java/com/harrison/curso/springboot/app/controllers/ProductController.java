package com.harrison.curso.springboot.app.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.harrison.curso.springboot.app.entities.Product;
import com.harrison.curso.springboot.app.entities.Role;
import com.harrison.curso.springboot.app.services.ProductService;

import jakarta.validation.Valid;
import tools.jackson.databind.json.JsonMapper;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService service;

    // private final ProductValidation validation;

    ProductController(
            ProductService service/*
                                   * ,
                                   * ProductValidation validation
                                   */) {
        this.service = service;
        // this.validation = validation;
    }

    @PreAuthorize("hasAnyRole('" + Role.ADMIN + "', '" + Role.USER + "')")
    @GetMapping
    public List<Product> list() {
        return service.findAll();
    }

    @PreAuthorize("hasAnyRole('" + Role.ADMIN + "', '" + Role.USER + "')")
    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable Long id) {
        Optional<Product> optionalProduct = service.findById(id);

        if (optionalProduct.isPresent())
            return ResponseEntity.ok(optionalProduct.orElseThrow());

        return ResponseEntity.notFound().build();
    }

    @PreAuthorize("hasRole('" + Role.ADMIN + "')")
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Product product, BindingResult result) {
        // validation.validate(product, result);
        if (result.hasFieldErrors())
            return validation(result);

        Product productSaved = null;

        try {
            productSaved = service.save(product);
        } catch (IllegalArgumentException e) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(JsonMapper.builder().build().writeValueAsString(
                            Map.of("error", e.getMessage(), "message", "Error al crear el producto")));
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(productSaved);
    }

    @PreAuthorize("hasRole('" + Role.ADMIN + "')")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Product product, BindingResult result,
            @PathVariable Long id) {
        // validation.validate(product, result);
        if (result.hasFieldErrors())
            return validation(result);
        Optional<Product> optionalProduct = service.update(id, product);

        return optionalProduct.map(p -> ResponseEntity.status(HttpStatus.OK).body(p))
                .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasRole('" + Role.ADMIN + "')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Product> optionalProduct = service.delete(id);

        if (optionalProduct.isPresent())
            return ResponseEntity.ok(optionalProduct.orElseThrow());

        return ResponseEntity.notFound().build();
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach(
                err -> errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage()));

        return ResponseEntity.badRequest().body(errors);
    }
}
