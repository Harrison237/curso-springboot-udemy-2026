package com.harrison.curso.springboot.error.services;

import com.harrison.curso.springboot.error.models.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();
    Optional<User> findById(Long id);
}
