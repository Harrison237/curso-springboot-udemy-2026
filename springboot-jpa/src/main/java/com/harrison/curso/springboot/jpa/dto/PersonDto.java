package com.harrison.curso.springboot.jpa.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class PersonDto {

    @Getter
    private String name;
    @Getter
    private String lastname;

    @Override
    public String toString() {
        return "PersonDto{" +
                "name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }
}
