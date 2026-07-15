package com.harrison.curso.springboot.error.controllers;

import com.harrison.curso.springboot.error.exceptions.UserNotFoundException;
import com.harrison.curso.springboot.error.models.domain.User;
import com.harrison.curso.springboot.error.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/app")
public class AppController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String index() {
        int value = 100 / 0;
        //int value = Integer.parseInt("hola");
        System.out.println(value);

        return "ok 200";
    }

    @GetMapping("/show/{id}")
    public User show(@PathVariable(name = "id") Long id) {
/*        Optional<User> optionalUser = userService.findById(id);

        if (optionalUser.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(optionalUser.get());*/

        User user = userService.findById(id).orElseThrow(() -> new UserNotFoundException("Error: El usuario no existe"));
        System.out.println(user.getLastName());

        return user;
    }
}
