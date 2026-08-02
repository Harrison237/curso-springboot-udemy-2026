package com.harrison.springboot.jpa.relations.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "invoice")
@AllArgsConstructor
@NoArgsConstructor
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Getter
    private String description;

    @Getter
    private Long total;

    @JoinColumn(name = "client_id", referencedColumnName = "id")
    @ManyToOne
    private Client client;

    @Override
    public String toString() {
        return "{id=" + id + ", description=" + description + ", total=" + total + "}";
    }

    public Invoice(String description, Long total) {
        this.description = description;
        this.total = total;
    }

    public Invoice(String description, Long total, Client client) {
        this.description = description;
        this.total = total;
        this.client = client;
    }
    
}
