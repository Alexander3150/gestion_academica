package com.example.gestion_academica.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gestion_academica.entities.Estudiante;

public interface EstudianteRepository extends JpaRepository<Estudiante, String> {
}