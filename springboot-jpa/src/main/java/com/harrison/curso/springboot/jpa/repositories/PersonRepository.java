package com.harrison.curso.springboot.jpa.repositories;

import com.harrison.curso.springboot.jpa.dto.PersonDto;
import com.harrison.curso.springboot.jpa.entities.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends CrudRepository<Person, Long> {

    @Query("select p from Person p where p.id IN(?1)")
    List<Person> getPersonsByIds(List<Long> ids);

    @Query("select p from Person p where p.id=(select MAX(p.id) from Person p)")
    Optional<Person> getLastPersonRegistered();

    @Query("select p.name, LENGTH(p.name) from Person p where LENGTH(p.name) = (select MIN(LENGTH(p.name)) from Person p)")
    List<Object[]> getShorterNameAndLength();

    @Query("select MIN(p.id), MAX(p.id), SUM(p.id), AVG(LENGTH(p.name)), COUNT(p.id) from Person p")
    Object getAggregationFunctionSummary();

    @Query("select MAX(LENGTH(p.name)) from Person p")
    Integer getMaxLengthName();

    @Query("select MIN(LENGTH(p.name)) from Person p")
    Integer getMinLengthName();

    @Query("select p.name, LENGTH(p.name) from Person p")
    List<Object[]> getPersonNameAndLength();

    @Query("select COUNT(p) from Person p")
    Long getTotalPersonCount();

    @Query("select MIN(p.id) from Person p")
    Long getMinId();

    @Query("select MAX(p.id) from Person p")
    Long getMaxId();

    List<Person> findAllByOrderByNameAscLastnameDesc();

    @Query("select p from Person p ORDER BY p.name, p.lastname ASC")
    List<Person> getAllOrdered();

    List<Person> findByIdBetweenOrderByIdDesc(Long id1, Long id2);

    List<Person> findByNameBetweenOrderByNameDescLastnameDesc(String name1, String name2);

    @Query("select p from Person p where p.id BETWEEN ?1 and ?2 ORDER BY p.id DESC")
    List<Person> findPersonsBetweenIdOrdered(Long id1, Long id2);

    @Query("select p from Person p where p.name BETWEEN ?1 and ?2 ORDER BY p.name ASC, p.lastname DESC")
    List<Person> findPersonsBetweenNameOrdered(String c1, String c2);

    @Query("select p.id, UPPER(p.name), LOWER(p.lastname), UPPER(p.programmingLanguage) from Person p")
    List<Object[]> findAllPersonDataListDifferentCases();

    @Query("select UPPER(p.name || ' ' || p.lastname) from Person p")
    List<String> findAllFullNameConcatUpper();

    @Query("select LOWER(CONCAT(p.name, ' ', p.lastname)) from Person p")
    List<String> findAllFullNameConcatLower();

    //    @Query("select CONCAT(p.name, ' ', p.lastname) from Person p")
    @Query("select p.name || ' ' || p.lastname from Person p")
    List<String> findAllFullNameConcat();

    @Query("select p.name from Person p")
    List<String> findAllNames();

    @Query("select DISTINCT(p.name) from Person p")
    List<String> findAllNamesDistinct();

    @Query("select DISTINCT(p.programmingLanguage) from Person p")
    List<String> findAllProgrammingLanguagesDistinct();

    @Query("select COUNT(DISTINCT(p.programmingLanguage)) from Person p")
    Long countAllProgrammingLanguages();

    @Query("select new com.harrison.curso.springboot.jpa.dto.PersonDto(p.name, p.lastname) from Person p")
    List<PersonDto> findAllPersonDto();

    @Query("select new Person(p.name, p.lastname) from Person p")
    List<Person> findAllObjectPersonPersonalized();

    @Query("select p.name from Person p where p.id=?1")
    String getNameById(Long id);

    @Query("select p.id from Person p where p.id=?1")
    Long getIdById(Long id);

    @Query("select CONCAT(p.name, ' ', p.lastname) as fullname from Person p where p.id=?1")
    String getFullNameById(Long id);

    @Query("select p from Person p where p.id=?1")
    Optional<Person> findOne(Long id);

    @Query("select p from Person p where p.name=?1")
    Optional<Person> findOneName(String name);

    @Query("select p from Person p where p.name like %?1")
    Optional<Person> findOneLikeName(String name);

    Optional<Person> findByNameEndingWith(String name);

    List<Person> findByProgrammingLanguage(String programmingLanguage);

    @Query("select p from Person p where p.programmingLanguage=?1 and p.name=?2")
    List<Person> searchByProgrammingLanguage(String programmingLanguage, String name);

    List<Person> findByProgrammingLanguageAndName(String programmingLanguage, String name);

    @Query("select p, p.programmingLanguage from Person p")
    List<Object[]> findAllMixPerson();

    @Query("select p.id, p.name, p.lastname, p.programmingLanguage from Person p")
    List<Object[]> obtainPersonDataList();

    @Query("select p.id, p.name, p.lastname, p.programmingLanguage from Person p where p.id=?1")
    Optional<Object> obtainPersonDataById(Long id);

    @Query("select p.name, p.programmingLanguage from Person p")
    List<Object[]> obtainPersonData();

    @Query("select p.name, p.programmingLanguage from Person p where p.name=?1")
    List<Object[]> obtainPersonData(String name);

    @Query("select p.name, p.programmingLanguage from Person p where p.programmingLanguage=?1 and p.name=?2")
    List<Object[]> obtainPersonData(String programmingLanguage, String name);

    @Query("select p.name, p.programmingLanguage from Person p where p.programmingLanguage=?1")
    List<Object[]> obtainPersonDataByProgrammingLanguage(String programmingLanguage);
}
