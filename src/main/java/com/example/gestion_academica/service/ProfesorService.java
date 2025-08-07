package com.example.gestion_academica.service;


import java.util.List;

import com.example.gestion_academica.entities.Profesor;

public interface ProfesorService {
    List<Profesor> findAll();
    Profesor findById(String id);
    Profesor create(Profesor profesor);
    Profesor update(String id, Profesor profesor);
    void delete(String id);
}