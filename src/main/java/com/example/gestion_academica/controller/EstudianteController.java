package com.example.gestion_academica.controller;


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.example.gestion_academica.dto.EstudianteDTO;
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

    // GET con DTO + filtros: /api/estudiantes?apellido=Perez  ó  ?nombre=an
    @GetMapping
    public List<EstudianteDTO> list(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String apellido) {
        return service.findAllDTO(nombre, apellido);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstudianteDTO> get(@PathVariable String id) {
        return ResponseEntity.ok(service.findDTOById(id));
    }

    // CRUD restante (opcional, como ya lo tenías)
    @PostMapping
    public ResponseEntity<Estudiante> create(@Valid @RequestBody Estudiante body) {
        return ResponseEntity.status(201).body(service.create(body));
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
