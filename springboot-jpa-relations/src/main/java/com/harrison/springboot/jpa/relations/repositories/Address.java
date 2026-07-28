package com.harrison.springboot.jpa.relations.repositories;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "address")
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Getter
    private String street;

    @Getter
    private Integer number;

    public Address(String street, Integer number) {
        this.street = street;
        this.number = number;
    }

    @Override
    public String toString() {
        return "{id=" + id + ", street=" + street + ", number=" + number + "}";
    }

    
}
