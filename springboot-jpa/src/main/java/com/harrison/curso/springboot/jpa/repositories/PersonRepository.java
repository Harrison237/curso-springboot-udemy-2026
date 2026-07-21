package com.harrison.curso.springboot.jpa.repositories;

import com.harrison.curso.springboot.jpa.entities.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> {
}
