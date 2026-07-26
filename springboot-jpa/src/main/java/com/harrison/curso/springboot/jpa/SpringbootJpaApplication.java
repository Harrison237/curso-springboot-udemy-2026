package com.harrison.curso.springboot.jpa;

import com.harrison.curso.springboot.jpa.dto.PersonDto;
import com.harrison.curso.springboot.jpa.entities.Person;
import com.harrison.curso.springboot.jpa.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@SpringBootApplication
public class SpringbootJpaApplication implements CommandLineRunner {

    @Autowired
    private PersonRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(SpringbootJpaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        this.personalizedQueriesBetween();
    }

    @Transactional(readOnly = true)
    public void personalizedQueriesBetween() {
        System.out.println("=============================== Consultas por rangos ===============================");
        List<Person> persons = repository.findByIdBetweenOrderByIdDesc(2L, 5L);
        persons.forEach(System.out::println);

        persons = repository.findByNameBetweenOrderByNameDescLastnameDesc("J", "Q");
        persons.forEach(System.out::println);

        persons = repository.findPersonsBetweenIdOrdered(2L, 5L);
        persons.forEach(System.out::println);

        persons = repository.findPersonsBetweenNameOrdered("J", "Q");
        persons.forEach(System.out::println);

        persons = repository.getAllOrdered();
        persons.forEach(System.out::println);

        persons = repository.findAllByOrderByNameAscLastnameDesc();
        persons.forEach(System.out::println);

    }

    @Transactional(readOnly = true)
    public void personalizedQueriesConcatUpperAndLowerCase() {
        System.out.println("=============================== Consulta nombres y apellidos de personas ===============================");
        List<String> names = repository.findAllFullNameConcat();
        names.forEach(System.out::println);

        System.out.println("=============================== Consulta nombres y apellidos de personas en mayúsculas ===============================");
        List<String> upperNames = repository.findAllFullNameConcatUpper();
        upperNames.forEach(System.out::println);

        System.out.println("=============================== Consulta nombres y apellidos de personas en minúsculas ===============================");
        List<String> lowerNames = repository.findAllFullNameConcatLower();
        lowerNames.forEach(System.out::println);

        System.out.println("=============================== Consulta datos de persona en mayúsculas y minúsculas ===============================");
        List<Object[]> personReg = repository.findAllPersonDataListDifferentCases();
        personReg.forEach(reg ->
                System.out.println("id=" + reg[0] + ", nombre=" + reg[1] + ", apellido=" + reg[2] + ", lenguaje=" + reg[3]));
    }

    @Transactional(readOnly = true)
    public void personalizedQueriesDistinct() {
        System.out.println("=============================== Consultar únicamente los nombres en el objeto persona ===============================");
        List<String> personsName = repository.findAllNames();
        personsName.forEach(System.out::println);

        System.out.println("=============================== Consultas mediante DISTINCT ===============================");

        System.out.println("=============================== Consultar únicamente los nombres ===============================");
        List<String> names = repository.findAllNamesDistinct();
        names.forEach(System.out::println);

        System.out.println("=============================== Consultar únicamente los lenguajes de programación ===============================");
        List<String> programmingLanguages = repository.findAllProgrammingLanguagesDistinct();
        programmingLanguages.forEach(System.out::println);

        System.out.println("=============================== Cantidad de lenguajes de programación distintos ===============================");
        Long programmingLanguagesCount = repository.countAllProgrammingLanguages();
        System.out.println("Total de lenguajes de programación: " + programmingLanguagesCount);
    }

    @Transactional(readOnly = true)
    public void personalizedQueries2() {
        System.out.println("=============================== Consulta persona con campo agregado lenguaje de programación ===============================");
        List<Object[]> personsRegs = repository.findAllMixPerson();

        personsRegs.forEach(reg ->
                System.out.println("programmingLanguage=" + reg[1] + ", person=" + reg[0]));

        System.out.println("=============================== Consulta de nombre y apellido mediante una instancia personalizada de Person ===============================");
        List<Person> persons = repository.findAllObjectPersonPersonalized();
        persons.forEach(System.out::println);

        System.out.println("=============================== Consulta de nombre y apellido mediante una instancia de PersonDto ===============================");
        List<PersonDto> personsDto = repository.findAllPersonDto();
        personsDto.forEach(System.out::println);
    }

    @Transactional(readOnly = true)
    public void personalizedQueries() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=============================== Consulta únicamente el nombre por el ID de la persona ===============================");
        System.out.println("Ingrese el ID para ver el nombre de la persona:");
        Long id = scanner.nextLong();
        scanner.close();

        System.out.println("=============================== Mostrando solo el nombre ===============================");
        String name = repository.getNameById(id);
        System.out.println(name);

        System.out.println("=============================== Mostrando solo el id ===============================");
        Long idFound = repository.getIdById(id);
        System.out.println(idFound);

        System.out.println("=============================== Mostrando solo el nombre completo con concat ===============================");
        String fullName = repository.getFullNameById(id);
        System.out.println(fullName);

        System.out.println("=============================== Consulta de campos personalizados por ID ===============================");
        Optional<Object> optionalReg = repository.obtainPersonDataById(id);
        optionalReg.ifPresent(personRegRaw -> {
            Object[] personReg = (Object[]) personRegRaw;
            System.out.println("id=" + personReg[0] + ", nombre=" + personReg[1] + ", apellido=" + personReg[2] + ", lenguaje=" + personReg[3]);
        });

        System.out.println("=============================== Consulta lista de campos personalizados por ID ===============================");
        List<Object[]> regs = repository.obtainPersonDataList();
        regs.forEach(reg -> System.out.println("id=" + reg[0] + ", nombre=" + reg[1] + ", apellido=" + reg[2] + ", lenguaje=" + reg[3]));
    }

    @Transactional
    public void delete() {
        repository.findAll().forEach(System.out::println);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el ID de la persona a eliminar:");
        Long id = scanner.nextLong();

        repository.deleteById(id);

        repository.findAll().forEach(System.out::println);
        scanner.close();
    }

    // Eliminación de persona mediante búsqueda manual y eliminación por entidad Person
    @Transactional
    public void delete2() {
        repository.findAll().forEach(System.out::println);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el ID de la persona a eliminar:");
        Long id = scanner.nextLong();

        Optional<Person> optionalFound = repository.findById(id);
        optionalFound.ifPresentOrElse(repository::delete,
                () -> System.out.println("Lo sentimos, no existe la persona con el id '" + id + "'!"));

        repository.findAll().forEach(System.out::println);
        scanner.close();
    }

    @Transactional
    public void update() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el ID de la persona:");
        Long id = scanner.nextLong();

        Optional<Person> optionalFound = repository.findById(id);
//        optionalFound.ifPresent(person -> {
        if (optionalFound.isPresent()) {
            Person person = optionalFound.get();
            System.out.println(person);

            System.out.println("Ingrese el lenguaje de programación:");
            String programmationLanguage = scanner.next();

            Person updated = repository.save(new Person(person.getId(), person.getName(), person.getLastname(), programmationLanguage));
            System.out.println(updated);
        } else {
            System.out.println("El usuario con el id '" + id + "' no existe.");
        }
//        });

        scanner.close();
    }

    @Transactional
    public void create() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el nombre:");
        String name = scanner.next();
        System.out.println("Ingrese el apellido:");
        String lastname = scanner.next();
        System.out.println("Ingrese el lenguaje de programación:");
        String programmingLanguage = scanner.next();
        scanner.close();

        Person person = new Person(null, name, lastname, programmingLanguage);

        Person created = repository.save(person);
        System.out.println(created);

        repository.findById(created.getId()).ifPresent(System.out::println);
    }

    @Transactional(readOnly = true)
    public void findOne() {
/*        Person person = null;
        Optional<Person> optionalPerson = repository.findById(8L);

        if (optionalPerson.isPresent()) person = optionalPerson.get();

        System.out.println(person);*/
        repository.findByNameEndingWith("Jo").ifPresent(System.out::println);
    }

    @Transactional(readOnly = true)
    public void list() {
//        List<Person> persons = (List<Person>) repository.buscarByProgrammingLanguage("Python", "Pepe");
        List<Person> persons = repository.findByProgrammingLanguageAndName("Python", "Pepe");
        persons.forEach(System.out::println);

        List<Object[]> personsValues = repository.obtainPersonData();
        personsValues.forEach(p -> System.out.println(p[0] + " es experto en " + p[1]));
//        personsValues.forEach(System.out::println);
    }

}
