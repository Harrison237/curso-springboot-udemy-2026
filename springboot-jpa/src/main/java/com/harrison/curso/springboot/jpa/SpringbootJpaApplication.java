package com.harrison.curso.springboot.jpa;

import com.harrison.curso.springboot.jpa.entities.Person;
import com.harrison.curso.springboot.jpa.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class SpringbootJpaApplication implements CommandLineRunner {

    @Autowired
    private PersonRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(SpringbootJpaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        create();
    }

    public void create() {
        Person person = new Person(null, "Lalo", "Thor", "Python");

        Person created = repository.save(person);
        System.out.println(created);
    }

    public void findOne() {
/*        Person person = null;
        Optional<Person> optionalPerson = repository.findById(8L);

        if (optionalPerson.isPresent()) person = optionalPerson.get();

        System.out.println(person);*/
        repository.findByNameEndingWith("Jo").ifPresent(System.out::println);
    }

    public void list() {
//        List<Person> persons = (List<Person>) repository.buscarByProgrammingLanguage("Python", "Pepe");
        List<Person> persons = repository.findByProgrammingLanguageAndName("Python", "Pepe");
        persons.forEach(System.out::println);

        List<Object[]> personsValues = repository.obtainPersonData();
        personsValues.forEach(p -> System.out.println(p[0] + " es experto en " + p[1]));
//        personsValues.forEach(System.out::println);
    }

}
