package com.example.gestion_academica.controller;

import java.util.List;

import org.springframework.http.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.example.gestion_academica.entities.Estudiante;
import com.example.gestion_academica.service.EstudianteService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/estudiantes")
@RequiredArgsConstructor
@Validated
public class EstudianteController {

    private final EstudianteService service;

    @GetMapping
    public List<Estudiante> list() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estudiante> get(@PathVariable String id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<Estudiante> create(@Valid @RequestBody Estudiante body) {
        Estudiante e = service.create(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(e);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Estudiante> update(@PathVariable String id, @Valid @RequestBody Estudiante body) {
        return ResponseEntity.ok(service.update(id, body));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
