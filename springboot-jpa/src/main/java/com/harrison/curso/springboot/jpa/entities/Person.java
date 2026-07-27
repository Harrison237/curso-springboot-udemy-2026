package com.harrison.curso.springboot.jpa.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "person")
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    public Person(String name, String lastname) {
        this.name = name;
        this.lastname = lastname;
    }

    public Person(Long id, String name, String lastname, String programmingLanguage) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.programmingLanguage = programmingLanguage;
    }

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

    @Embedded
    @Getter
    private Audit audit = new Audit();

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", programmingLanguage='" + programmingLanguage + '\'' +
                ", createdAt=" + audit.getCreatedAt() +
                ", updatedAt=" + audit.getUpdatedAt() +
                '}';
    }
}
