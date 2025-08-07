package com.example.gestion_academica.controller;

import java.util.List;

import org.springframework.http.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.example.gestion_academica.entities.Profesor;
import com.example.gestion_academica.service.ProfesorService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/profesores")
@RequiredArgsConstructor
@Validated
public class ProfesorController {

    private final ProfesorService service;

    @GetMapping
    public List<Profesor> list() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Profesor> get(@PathVariable String id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<Profesor> create(@Valid @RequestBody Profesor body) {
        Profesor p = service.create(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(p);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Profesor> update(@PathVariable String id, @Valid @RequestBody Profesor body) {
        return ResponseEntity.ok(service.update(id, body));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
