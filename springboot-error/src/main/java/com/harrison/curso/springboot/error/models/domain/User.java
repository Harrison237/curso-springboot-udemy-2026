package com.harrison.curso.springboot.error.models.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class User {

    @Getter
    private Long id;

    @Getter
    private String name;

    @Getter
    private String lastName;

    @Getter
    private Role role;

/*    public String getRoleName() {
        return this.role.getName();
    }*/
}
