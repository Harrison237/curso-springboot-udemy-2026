package com.harrison.curso.springboot.app.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.harrison.curso.springboot.app.entities.User;

public interface UserRepository extends CrudRepository<User, Long> {
    boolean existsByUsername(String username);

    Optional<User> findByUsername(String username);
}
