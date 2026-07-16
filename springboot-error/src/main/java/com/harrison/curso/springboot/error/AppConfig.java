package com.harrison.curso.springboot.error;

import com.harrison.curso.springboot.error.models.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class AppConfig {
    @Bean
    List<User> usersList() {
        return Arrays.asList(
                new User(1L, "Pepe", "Gonzalez", null),
                new User(2L, "Andres", "Mena", null),
                new User(3L, "María", "Perez", null),
                new User(4L, "Josefa", "Ramirez", null),
                new User(5L, "Ale", "Gutierrez", null)
        );
    }
}
