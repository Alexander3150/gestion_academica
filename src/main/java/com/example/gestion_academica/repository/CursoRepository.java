package com.example.gestion_academica.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gestion_academica.entities.Curso;


public interface CursoRepository extends JpaRepository<Curso, String> {
}
