package com.example.gestion_academica.controller;

import java.util.List;

import org.springframework.http.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.example.gestion_academica.entities.Curso;
import com.example.gestion_academica.service.CursoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/cursos")
@RequiredArgsConstructor
@Validated
public class CursoController {

    private final CursoService service;

    @GetMapping
    public List<Curso> list() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> get(@PathVariable String id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<Curso> create(@Valid @RequestBody Curso body) {
        Curso c = service.create(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(c);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Curso> update(@PathVariable String id, @Valid @RequestBody Curso body) {
        return ResponseEntity.ok(service.update(id, body));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
