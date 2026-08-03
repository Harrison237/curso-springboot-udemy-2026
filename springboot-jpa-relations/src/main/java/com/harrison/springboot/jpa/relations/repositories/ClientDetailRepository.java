package com.harrison.springboot.jpa.relations.repositories;

import org.springframework.data.repository.CrudRepository;

import com.harrison.springboot.jpa.relations.entities.ClientDetail;

public interface ClientDetailRepository extends CrudRepository<ClientDetail, Long> {

}
