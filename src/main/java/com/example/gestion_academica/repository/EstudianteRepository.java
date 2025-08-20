package com.example.gestion_academica.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.gestion_academica.entities.Estudiante;

public interface EstudianteRepository extends JpaRepository<Estudiante, String> {
    // Filtros
    List<Estudiante> findByApellidoContainingIgnoreCase(String apellido);
    List<Estudiante> findByNombreContainingIgnoreCase(String nombre);
    List<Estudiante> findByNombreContainingIgnoreCaseAndApellidoContainingIgnoreCase(String nombre, String apellido);
}
