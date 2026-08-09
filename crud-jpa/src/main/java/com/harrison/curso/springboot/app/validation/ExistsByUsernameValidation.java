package com.harrison.curso.springboot.app.validation;

import org.springframework.stereotype.Component;

import com.harrison.curso.springboot.app.services.UserService;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class ExistsByUsernameValidation implements ConstraintValidator<ExistsByUsername, String> {

    private final UserService service;

    public ExistsByUsernameValidation(UserService service) {
        this.service = service;
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        return service == null || !service.existsByUsername(username);
    }

}
