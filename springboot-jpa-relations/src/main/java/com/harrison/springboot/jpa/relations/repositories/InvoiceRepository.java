package com.harrison.springboot.jpa.relations.repositories;

import org.springframework.data.repository.CrudRepository;

import com.harrison.springboot.jpa.relations.entities.Invoice;

public interface InvoiceRepository extends CrudRepository<Invoice, Long> {

}
