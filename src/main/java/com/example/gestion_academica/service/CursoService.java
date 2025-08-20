package com.example.gestion_academica.service;

import java.util.List;
import com.example.gestion_academica.dto.CursoDTO;
import com.example.gestion_academica.entities.Curso;

public interface CursoService {
    // CRUD existentes
    List<Curso> findAll();
    Curso findById(String id);
    Curso create(Curso curso);
    Curso update(String id, Curso curso);
    void delete(String id);

    // NUEVO: GET con DTOs + filtros (nombre, creditos)
    List<CursoDTO> findAllDTO(String nombre, Short creditos);
    CursoDTO findDTOById(String id);
}
