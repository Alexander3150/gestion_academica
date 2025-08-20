package com.example.gestion_academica.controller;


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.example.gestion_academica.dto.CursoDTO;
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

    // GET con DTO + filtros: /api/cursos?nombre=datos&creditos=4
    @GetMapping
    public List<CursoDTO> list(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) Short creditos) {
        return service.findAllDTO(nombre, creditos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CursoDTO> get(@PathVariable String id) {
        return ResponseEntity.ok(service.findDTOById(id));
    }

    // CRUD restante (opcional, como ya lo tenías)
    @PostMapping
    public ResponseEntity<Curso> create(@Valid @RequestBody Curso body) {
        return ResponseEntity.status(201).body(service.create(body));
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
