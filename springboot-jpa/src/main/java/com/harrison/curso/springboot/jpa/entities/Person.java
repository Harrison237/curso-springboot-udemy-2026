package com.harrison.curso.springboot.jpa.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "person")
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Getter
    private String name;

    @Getter
    private String lastname;

    @Column(name = "programming_language")
    @Getter
    private String programmingLanguage;

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", programmingLanguage='" + programmingLanguage + '\'' +
                '}';
    }
}
