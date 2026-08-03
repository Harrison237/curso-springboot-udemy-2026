package com.harrison.springboot.jpa.relations.repositories;

import org.springframework.data.repository.CrudRepository;

import com.harrison.springboot.jpa.relations.entities.Student;

public interface StudentRepository extends CrudRepository<Student, Long> {

}
