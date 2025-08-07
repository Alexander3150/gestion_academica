package com.example.gestion_academica.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gestion_academica.entities.Profesor;


public interface ProfesorRepository extends JpaRepository<Profesor, String> {
    boolean existsByCorreoIgnoreCase(String correo);
}