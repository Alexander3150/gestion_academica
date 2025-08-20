package com.example.gestion_academica.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.example.gestion_academica.dto.ProfesorDTO;
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

    // ------- GET con DTO + filtros --------
    // /api/profesores?q=ana   OR  /api/profesores?apellido=Perez
    @GetMapping
    public List<ProfesorDTO> list(
            @RequestParam(required = false) String q,
            @RequestParam(required = false) String apellido) {
        return service.findAllDTO(q, apellido);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfesorDTO> get(@PathVariable String id) {
        return ResponseEntity.ok(service.findDTOById(id));
    }

    // ------- Resto CRUD (si quieres mantenerlos como estaban) -------
    @PostMapping
    public ResponseEntity<Profesor> create(@Valid @RequestBody Profesor body) {
        return ResponseEntity.status(201).body(service.create(body));
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
