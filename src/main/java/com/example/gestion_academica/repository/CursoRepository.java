package com.example.gestion_academica.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.gestion_academica.entities.Curso;

public interface CursoRepository extends JpaRepository<Curso, String> {

    // Como Curso no tiene "semestre", filtramos por nombre o por créditos
    List<Curso> findByNombreContainingIgnoreCase(String nombre);
    List<Curso> findByCreditos(Short creditos);
    List<Curso> findByNombreContainingIgnoreCaseAndCreditos(String nombre, Short creditos);
}
