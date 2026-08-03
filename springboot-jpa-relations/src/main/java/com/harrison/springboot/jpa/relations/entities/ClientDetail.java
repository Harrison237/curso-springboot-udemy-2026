package com.harrison.springboot.jpa.relations.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "client_detail")
@AllArgsConstructor
@NoArgsConstructor
public class ClientDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Getter
    private boolean premium;

    @Getter
    private Integer points;

    @OneToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    @Getter
    private Client client;

    public ClientDetail(boolean premium, Integer points) {
        this.premium = premium;
        this.points = points;
    }

    public ClientDetail(boolean premium, Integer points, Client client) {
        this.premium = premium;
        this.points = points;
        this.client = client;
    }

    @Override
    public String toString() {
        return "ClientDetail {id=" + id + ", premium=" + premium + ", points=" + points + "}";
    }
}
