package com.harrison.curso.springboot.error.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@AllArgsConstructor
public class Error {

    @Getter
    private String message;

    @Getter
    private String error;

    @Getter
    private int status;

    @Getter
    private Date date;
}
