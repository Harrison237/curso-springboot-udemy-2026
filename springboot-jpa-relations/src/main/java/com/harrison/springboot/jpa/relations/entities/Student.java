package com.harrison.springboot.jpa.relations.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "student")
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Getter
    private String name;

    @Getter
    private String lastname;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(
        name = "tbl_student_course", 
        joinColumns = @JoinColumn(name = "custom_student_id"), 
        inverseJoinColumns = @JoinColumn(name = "custom_course_id"),
        uniqueConstraints = @UniqueConstraint(columnNames = { "custom_student_id", "custom_course_id" }))
    @Getter
    private Set<Course> courses = new HashSet<>();

    public Student(String name, String lastname) {
        this.name = name;
        this.lastname = lastname;
    }

    @Override
    public String toString() {
        return "Student {id=" + id + ", name=" + name + ", lastname=" + lastname + ", courses=" + courses + "}";
    }

    
}
