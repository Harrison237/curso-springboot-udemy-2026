package com.harrison.springboot.jpa.relations;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.harrison.springboot.jpa.relations.entities.Client;
import com.harrison.springboot.jpa.relations.entities.Invoice;
import com.harrison.springboot.jpa.relations.repositories.ClientRepository;
import com.harrison.springboot.jpa.relations.repositories.InvoiceRepository;

@SpringBootApplication
public class SpringbootJpaRelationsApplication implements CommandLineRunner {

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private InvoiceRepository invoiceRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaRelationsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		manyToOneFindByIdClient();
	}

	@Transactional
	public void manyToOne() {
		Client client = new Client("Jhon", "Doe");
		clientRepository.save(client);

		Invoice invoice = new Invoice("Compras de oficina", 2000L, client);

		Invoice invoiceDB = invoiceRepository.save(invoice);
		System.out.println(invoiceDB);
	}

	@Transactional
	public void manyToOneFindByIdClient() {
		Optional<Client> optionalClient = clientRepository.findById(1L);

		if (optionalClient.isPresent()) {
			Client client = optionalClient.orElseThrow();

			Invoice invoice = new Invoice("Compras de oficina", 2000L, client);

			Invoice invoiceDB = invoiceRepository.save(invoice);
			System.out.println(invoiceDB);
		}
	}

}
