package com.harrison.curso.springboot.app.config;

import org.hibernate.cfg.ValidationSettings;
import org.springframework.boot.hibernate.autoconfigure.HibernatePropertiesCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * Vincula el ValidatorFactory de Spring con Hibernate para que las
 * validaciones a nivel de entidad (JPA Bean Validation) puedan usar
 * validadores con dependencias inyectadas por Spring.
 *
 * Sin esta configuración, Hibernate usa su DefaultConstraintValidatorFactory,
 * que sólo sabe instanciar validadores con constructor sin argumentos.
 */
@Configuration
public class HibernateValidationConfig {

    @Bean
    HibernatePropertiesCustomizer hibernateValidatorCustomizer(
            LocalValidatorFactoryBean validatorFactory) {
        return hibernateProperties -> hibernateProperties.put(
                ValidationSettings.JAKARTA_VALIDATION_FACTORY,
                validatorFactory);
    }
}
