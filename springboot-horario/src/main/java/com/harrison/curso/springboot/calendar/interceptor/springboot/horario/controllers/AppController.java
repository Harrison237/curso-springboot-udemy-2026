package com.harrison.curso.springboot.calendar.interceptor.springboot.horario.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;

@RestController
public class AppController {

    @GetMapping("/foo")
    public ResponseEntity<?> foo(HttpServletRequest request) {
        Map<String, Object> data = new HashMap<>();
        data.put("title", "Bienvenidos al sistema de atención!");
        data.put("time", LocalDateTime.now(ZoneId.systemDefault()));
        data.put("message", request.getAttribute("message"));

        return ResponseEntity.ok(data);
    }
}
