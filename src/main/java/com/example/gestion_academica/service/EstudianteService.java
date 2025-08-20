package com.example.gestion_academica.service;

import java.util.List;
import com.example.gestion_academica.dto.EstudianteDTO;
import com.example.gestion_academica.entities.Estudiante;

public interface EstudianteService {
    // CRUD existentes
    List<Estudiante> findAll();
    Estudiante findById(String id);
    Estudiante create(Estudiante estudiante);
    Estudiante update(String id, Estudiante estudiante);
    void delete(String id);

    // NUEVO: GET con DTOs + filtros (nombre, apellido)
    List<EstudianteDTO> findAllDTO(String nombre, String apellido);
    EstudianteDTO findDTOById(String id);
}
