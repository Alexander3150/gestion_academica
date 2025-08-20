package com.example.gestion_academica.service;

import java.util.List;
import com.example.gestion_academica.dto.ProfesorDTO;
import com.example.gestion_academica.entities.Profesor;

public interface ProfesorService {
    // CRUD existentes (si ya los tienes)
    List<Profesor> findAll();
    Profesor findById(String id);
    Profesor create(Profesor profesor);
    Profesor update(String id, Profesor profesor);
    void delete(String id);

    // NUEVO: GET con DTOs + filtro q (nombre/apellido)
    List<ProfesorDTO> findAllDTO(String q, String apellido);
    ProfesorDTO findDTOById(String id);
}
