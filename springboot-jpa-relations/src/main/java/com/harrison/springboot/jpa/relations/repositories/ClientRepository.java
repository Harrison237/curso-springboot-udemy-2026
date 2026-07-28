package com.harrison.springboot.jpa.relations.repositories;

import org.springframework.data.repository.CrudRepository;

import com.harrison.springboot.jpa.relations.entities.Client;

public interface ClientRepository extends CrudRepository<Client, Long> {

}
