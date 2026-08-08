package com.harrison.curso.springboot.app.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_table")
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Column(unique = true)
    @NotBlank
    @Size(min = 4, max = 12)
    @Getter
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank
    @Getter
    private String password;

    @Getter
    private Boolean enabled;

    @ManyToMany
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"), uniqueConstraints = {
            @UniqueConstraint(columnNames = { "user_id, role_id" }) })
    @Getter
    private List<Role> roles;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Transient
    @Getter
    private Boolean admin;

    public User(Long id, @NotBlank @Size(min = 4, max = 12) String username, @NotBlank String password, Boolean enabled,
            List<Role> roles, Boolean admin) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.enabled = enabled != null ? enabled : true;
        this.roles = roles;
        this.admin = admin != null ? admin : false;
    }

}
