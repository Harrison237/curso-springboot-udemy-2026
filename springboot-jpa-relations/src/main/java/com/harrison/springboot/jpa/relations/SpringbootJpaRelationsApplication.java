package com.harrison.springboot.jpa.relations;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.harrison.springboot.jpa.relations.entities.Address;
import com.harrison.springboot.jpa.relations.entities.Client;
import com.harrison.springboot.jpa.relations.entities.ClientDetail;
import com.harrison.springboot.jpa.relations.entities.Course;
import com.harrison.springboot.jpa.relations.entities.Invoice;
import com.harrison.springboot.jpa.relations.entities.Student;
import com.harrison.springboot.jpa.relations.repositories.ClientDetailRepository;
import com.harrison.springboot.jpa.relations.repositories.ClientRepository;
import com.harrison.springboot.jpa.relations.repositories.CourseRepository;
import com.harrison.springboot.jpa.relations.repositories.InvoiceRepository;
import com.harrison.springboot.jpa.relations.repositories.StudentRepository;

@SpringBootApplication
public class SpringbootJpaRelationsApplication implements CommandLineRunner {

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private InvoiceRepository invoiceRepository;

	@Autowired
	private ClientDetailRepository clientDetailRepository;

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private CourseRepository courseRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaRelationsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		manyToManyBidirectionalFindRemove();
	}

	@Transactional
	public void manyToManyBidirectionalFindRemove() {
		Student student1 = studentRepository.findOneWithCoursesById(1L).get();
		Student student2 = studentRepository.findOneWithCoursesById(2L).get();

		Course course1 = new Course("Curso de Java Master", "Harrison");
		Course course2 = new Course("Curso de Spring Boot", "Harrison");

		student1.addCourse(course1);
		student1.addCourse(course2);
		student2.addCourse(course2);

		studentRepository.saveAll(List.of(student1, student2));

		System.out.println(student1);
		System.out.println(student2);

		studentRepository.findOneWithCoursesById(student1.getId()).ifPresentOrElse(studentDb -> {
			courseRepository.findOneWithStudentsById(course1.getId()).ifPresentOrElse(courseDb -> {
				studentDb.removeCourse(courseDb);
				studentRepository.save(studentDb);
			}, () -> System.out.println("Course not found"));

			System.out.println(studentDb);
		}, () -> System.out.println("Student not found"));
	}

	@Transactional
	public void manyToManyBidirectionalFind() {
		Student student1 = studentRepository.findOneWithCoursesById(1L).get();
		Student student2 = studentRepository.findOneWithCoursesById(2L).get();

		Course course1 = new Course("Curso de Java Master", "Harrison");
		Course course2 = new Course("Curso de Spring Boot", "Harrison");

		student1.addCourse(course1);
		student1.addCourse(course2);
		student2.addCourse(course2);

		studentRepository.saveAll(List.of(student1, student2));

		System.out.println(student1);
		System.out.println(student2);
	}

	@Transactional
	public void manyToManyBidirectionalRemove() {
		Student student1 = new Student("Jano", "Pura");
		Student student2 = new Student("Erba", "Doe");

		Course course1 = new Course("Curso de Java Master", "Harrison");
		Course course2 = new Course("Curso de Spring Boot", "Harrison");

		student1.addCourse(course1);
		student1.addCourse(course2);
		student2.addCourse(course2);

		studentRepository.saveAll(List.of(student1, student2));

		System.out.println(student1);
		System.out.println(student2);

		Optional<Student> studentOptionalDb = studentRepository.findOneWithCoursesById(student1.getId());
		if (studentOptionalDb.isPresent()) {
			Student studentDb = studentOptionalDb.get();
			Optional<Course> courseOptionalDb = courseRepository.findOneWithStudentsById(course2.getId());

			if (courseOptionalDb.isPresent()) {
				Course courseDb = courseOptionalDb.get();
				studentDb.removeCourse(courseDb);

				studentRepository.save(studentDb);
			}
			System.out.println(studentDb);
		}
	}

	@Transactional
	public void manyToManyBidirectional() {
		Student student1 = new Student("Jano", "Pura");
		Student student2 = new Student("Erba", "Doe");

		Course course1 = new Course("Curso de Java Master", "Harrison");
		Course course2 = new Course("Curso de Spring Boot", "Harrison");

		student1.addCourse(course1);
		student1.addCourse(course2);
		student2.addCourse(course2);

		studentRepository.saveAll(List.of(student1, student2));

		System.out.println(student1);
		System.out.println(student2);
	}

	@Transactional
	public void manyToManyRemove() {
		Student student1 = new Student("Jano", "Pura");
		Student student2 = new Student("Erba", "Doe");

		Course course1 = new Course("Curso de Java Master", "Harrison");
		Course course2 = new Course("Curso de Spring Boot", "Harrison");

		student1.getCourses().addAll(Set.of(course1, course2));
		student2.getCourses().addAll(Set.of(course2));

		studentRepository.saveAll(List.of(student1, student2));

		System.out.println(student1);
		System.out.println(student2);

		Optional<Student> studentOptionalDb = studentRepository.findOneWithCoursesById(student1.getId());
		if (studentOptionalDb.isPresent()) {
			Student studentDb = studentOptionalDb.get();
			Optional<Course> courseOptionalDb = courseRepository.findById(course2.getId());

			if (courseOptionalDb.isPresent()) {
				Course courseDb = courseOptionalDb.get();
				studentDb.getCourses().remove(courseDb);

				studentRepository.save(studentDb);
			}
			System.out.println(studentDb);
		}
	}

	@Transactional
	public void manyToManyRemoveFind() {
		Optional<Student> optionalStudent1 = studentRepository.findOneWithCoursesById(1L);
		Optional<Student> optionalStudent2 = studentRepository.findOneWithCoursesById(2L);

		Student student1 = optionalStudent1.get();
		Student student2 = optionalStudent2.get();

		Course course1 = courseRepository.findById(1L).get();
		Course course2 = courseRepository.findById(2L).get();

		student1.getCourses().addAll(Set.of(course1, course2));
		student2.getCourses().addAll(Set.of(course2));

		studentRepository.saveAll(List.of(student1, student2));

		System.out.println(student1);
		System.out.println(student2);

		Optional<Student> studentOptionalDb = studentRepository.findOneWithCoursesById(1L);
		if (studentOptionalDb.isPresent()) {
			Student studentDb = studentOptionalDb.get();
			Optional<Course> courseOptionalDb = courseRepository.findById(2L);

			if (courseOptionalDb.isPresent()) {
				Course courseDb = courseOptionalDb.get();
				studentDb.getCourses().remove(courseDb);

				studentRepository.save(studentDb);
			}
			System.out.println(studentDb);
		}
	}

	@Transactional
	public void manyToManyFind() {
		Optional<Student> optionalStudent1 = studentRepository.findOneWithCoursesById(1L);
		Optional<Student> optionalStudent2 = studentRepository.findOneWithCoursesById(2L);

		Student student1 = optionalStudent1.get();
		Student student2 = optionalStudent2.get();

		Course course1 = courseRepository.findById(1L).get();
		Course course2 = courseRepository.findById(2L).get();

		student1.getCourses().addAll(Set.of(course1, course2));
		student2.getCourses().addAll(Set.of(course2));

		studentRepository.saveAll(List.of(student1, student2));

		System.out.println(student1);
		System.out.println(student2);
	}

	@Transactional
	public void manyToMany() {
		Student student1 = new Student("Jano", "Pura");
		Student student2 = new Student("Erba", "Doe");

		Course course1 = new Course("Curso de Java Master", "Harrison");
		Course course2 = new Course("Curso de Spring Boot", "Harrison");

		student1.getCourses().addAll(Set.of(course1, course2));
		student2.getCourses().addAll(Set.of(course2));

		studentRepository.saveAll(List.of(student1, student2));

		System.out.println(student1);
		System.out.println(student2);
	}

	@Transactional
	public void oneToOneBidirectionalFindById() {
		Optional<Client> optionalClient = clientRepository.findOneWithAddressesAndInvoicesAndDetail(2L);

		optionalClient.ifPresent(client -> {
			ClientDetail clientDetail = new ClientDetail(true, 5000, client);
			clientDetailRepository.save(clientDetail);
			client = new Client(client.getId(), client.getName(), client.getLastname(), client.getAddresses(), client.getInvoices(), clientDetail);

			System.out.println(client);
		});
	}

	@Transactional
	public void oneToOneBidirectional() {
		ClientDetail clientDetail = new ClientDetail(true, 5000);
		Client client = new Client("Erba", "Pura", clientDetail);
		clientRepository.save(client);
		System.out.println(client);
	}

	@Transactional
	public void oneToOneFindById() {
		ClientDetail clientDetail = new ClientDetail(true, 5000);
		clientDetailRepository.save(clientDetail);

		Optional<Client> optionalClient = clientRepository.findOneWithAddressesAndInvoicesAndDetail(2L);

		optionalClient.ifPresent(client -> {
			clientRepository.save(client);
			System.out.println(client);
		});
	}

	@Transactional
	public void oneToOne() {
		ClientDetail clientDetail = new ClientDetail(true, 5000);
		clientDetailRepository.save(clientDetail);

		Client client = new Client("Erba", "Pura", clientDetail);
		clientRepository.save(client);
		System.out.println(client);
	}

	@Transactional
	public void removeInvoiceBidirectional() {
		
		Client client = new Client("Fran", "Moras");

		client
			.addInvoice(new Invoice("Compras de la casa", 5000L, client))
			.addInvoice(new Invoice("Compras de oficina", 8000L, client));
	
		clientRepository.save(client);
	
		System.out.println(client);

		Optional<Client> optionalClientDb = clientRepository.findOneWithAddressesAndInvoices(client.getId());

		optionalClientDb.ifPresent(clientDb -> {
			Invoice invoice3 = new Invoice(1L, "Compras de la casa", 5000L, null);
			Optional<Invoice> optionalInvoice = Optional.of(invoice3);
			optionalInvoice.ifPresent(invoice -> {
				clientDb.getInvoices().remove(invoice);
				clientRepository.save(clientDb);
				System.out.println(clientDb);
			});
		});
	}

	@Transactional
	public void removeInvoiceBidirectionalFindById() {
		Optional<Client> optionalClient = clientRepository.findOneWithAddressesAndInvoices(1L);

		optionalClient.ifPresent(client -> {
			client
				.addInvoice(new Invoice("Compras de la casa", 5000L, client))
				.addInvoice(new Invoice("Compras de oficina", 8000L, client));
	
			clientRepository.save(client);
	
			System.out.println(client);
		});

		Optional<Client> optionalClientDb = clientRepository.findOneWithAddressesAndInvoices(1L);

		optionalClientDb.ifPresent(client -> {
/* 			Optional<Invoice> optionalInvoice = invoiceRepository.findById(2L);
			optionalInvoice.ifPresent(invoice -> {
				client.getInvoices().remove(invoice);
				clientRepository.save(client);
				System.out.println(client);
			}); */

			// Eliminación manual mediante comparación de campos en lugar de buscar directo en la base de datos.
			Invoice invoice3 = new Invoice(1L, "Compras de la casa", 5000L, null);
			Optional<Invoice> optionalInvoice = Optional.of(invoice3);
			optionalInvoice.ifPresent(invoice -> {
				client.getInvoices().remove(invoice);
				clientRepository.save(client);
				System.out.println(client);
			});
		});
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
