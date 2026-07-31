package com.harrison.springboot.jpa.relations.entities;

import java.util.ArrayList;
import java.util.List;

import com.harrison.springboot.jpa.relations.repositories.Address;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "client")
@NoArgsConstructor
@AllArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Getter
    private String name;

    @Getter
    private String lastname;

    // @JoinColumn(name = "client_id")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "tbl_client_to_address", 
        joinColumns = @JoinColumn(name = "custom_client_id"), 
        inverseJoinColumns = @JoinColumn(name = "custom_address_id"), 
        uniqueConstraints = @UniqueConstraint(columnNames = {"custom_address_id" }
    ))
    @Getter
    private List<Address> addresses;

    @Override
    public String toString() {
        return "{id=" + id + ", name=" + name + ", lastname=" + lastname + ", addresses=" + addresses + "}";
    }

    public Client(String name, String lastname) {
        this.addresses = new ArrayList<>();
        this.name = name;
        this.lastname = lastname;
    }

    public Client(String name, String lastname, List<Address> addresses) {
        this.name = name;
        this.lastname = lastname;
        this.addresses = addresses;
    }
}
