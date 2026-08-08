package com.harrison.curso.springboot.app.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.harrison.curso.springboot.app.entities.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {

    Optional<Role> findByName(String name);
}
