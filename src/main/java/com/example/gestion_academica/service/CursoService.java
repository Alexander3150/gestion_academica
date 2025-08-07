package com.example.gestion_academica.service;

import java.util.List;
import com.example.gestion_academica.entities.Curso;

public interface CursoService {
    List<Curso> findAll();
    Curso findById(String id);
    Curso create(Curso curso);
    Curso update(String id, Curso curso);
    void delete(String id);
}
