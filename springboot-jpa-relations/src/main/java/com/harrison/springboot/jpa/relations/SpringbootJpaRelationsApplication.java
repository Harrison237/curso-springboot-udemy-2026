package com.harrison.springboot.jpa.relations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.harrison.springboot.jpa.relations.entities.Client;
import com.harrison.springboot.jpa.relations.entities.Invoice;
import com.harrison.springboot.jpa.relations.repositories.Address;
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
		oneToManyBidirectionalFindById();
	}

	@Transactional
	public void oneToManyBidirectionalFindById() {
		Optional<Client> optionalClient = clientRepository.findOneWithAddressesAndInvoices(1L);

		optionalClient.ifPresent(client -> {
			client
				.addInvoice(new Invoice("Compras de la casa", 5000L, client))
				.addInvoice(new Invoice("Compras de oficina", 8000L, client));
	
			clientRepository.save(client);
	
			System.out.println(client);
		});
	}

	@Transactional
	public void oneToManyBidirectional() {
		Client client = new Client("Fran", "Moras");

		client
			.addInvoice(new Invoice("Compras de la casa", 5000L, client))
			.addInvoice(new Invoice("Compras de oficina", 8000L, client));

		clientRepository.save(client);

		System.out.println(client);
	}

	@Transactional
	public void removeAddressFindById() {
		Optional<Client> optionalClient = clientRepository.findById(2L);
		optionalClient.ifPresent(client -> {

			Address address1 = new Address("El verjel", 1234);
			Address address2 = new Address("Vasco de Gama", 9875);

			
			Client toCreate = new Client(client.getName(), client.getLastname(), Set.of(address1, address2));

			clientRepository.save(toCreate);

			System.out.println(toCreate);

			Optional<Client> optionalClient2 = clientRepository.findOneWithAddresses(2L);
			optionalClient2.ifPresent(c -> {
				c.getAddresses().remove(address2);
				clientRepository.save(c);
				System.out.println(c);
			});
		});
	}

	@Transactional
	public void removeAddress() {
		Client client = new Client("Fran", "Moras");

		Address address1 = new Address("El verjel", 1234);
		Address address2 = new Address("Vasco de Gama", 9875);

		client.getAddresses().add(address1);
		client.getAddresses().add(address2);

		clientRepository.save(client);

		System.out.println(client);

		Optional<Client> optionalClient = clientRepository.findById(3L);
		optionalClient.ifPresent(c -> {
			c.getAddresses().remove(address1);
			clientRepository.save(c);
			System.out.println(c);
		});
	}

	@Transactional
	public void oneToManyFindById() {
		Optional<Client> optionalClient = clientRepository.findById(2L);
		optionalClient.ifPresent(client -> {

			Address address1 = new Address("El verjel", 1234);
			Address address2 = new Address("Vasco de Gama", 9875);

			Client toCreate = new Client(client.getName(), client.getLastname(), Set.of(address1, address2));

			clientRepository.save(toCreate);

			System.out.println(toCreate);
		});
	}

	@Transactional
	public void oneToMany() {
		Client client = new Client("Fran", "Moras");

		Address address1 = new Address("El verjel", 1234);
		Address address2 = new Address("Vasco de Gama", 9875);

		client.getAddresses().add(address1);
		client.getAddresses().add(address2);

		clientRepository.save(client);

		System.out.println(client);
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
