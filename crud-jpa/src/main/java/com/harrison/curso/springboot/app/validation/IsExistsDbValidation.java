package com.harrison.curso.springboot.app.validation;

import org.springframework.stereotype.Component;

import com.harrison.curso.springboot.app.services.ProductService;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class IsExistsDbValidation implements ConstraintValidator<IsExistsDb, String> {

    private final ProductService service;

    public IsExistsDbValidation(ProductService service) {
        this.service = service;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return service == null || !service.existsBySku(value);
    }
}
