package com.harrison.curso.springboot.jpa.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class Audit {

    public Audit(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Column(name = "created_at")
    @Getter
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @Getter
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        System.out.println("Evento del ciclo de vida del objeto Entity: 'Pre-Persist'");
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        System.out.println("Evento del ciclo de vida del objeto Entity: 'Pre-Update'");
        this.updatedAt = LocalDateTime.now();
    }
}
