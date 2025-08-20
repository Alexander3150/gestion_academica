package com.example.gestion_academica.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.gestion_academica.entities.Profesor;

public interface ProfesorRepository extends JpaRepository<Profesor, String> {
    boolean existsByCorreoIgnoreCase(String correo);

    // Filtros
    List<Profesor> findByNombreContainingIgnoreCaseOrApellidoContainingIgnoreCase(String nombre, String apellido);
    List<Profesor> findByApellidoContainingIgnoreCase(String apellido);
}
