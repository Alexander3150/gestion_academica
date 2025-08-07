package com.example.gestion_academica.service;


import java.util.List;

import com.example.gestion_academica.entities.Estudiante;


public interface EstudianteService {
    List<Estudiante> findAll();
    Estudiante findById(String id);
    Estudiante create(Estudiante estudiante);
    Estudiante update(String id, Estudiante estudiante);
    void delete(String id);
}