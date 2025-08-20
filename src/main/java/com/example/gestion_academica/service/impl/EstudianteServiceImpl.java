package com.example.gestion_academica.service.impl;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.gestion_academica.dto.EstudianteDTO;
import com.example.gestion_academica.entities.Estudiante;
import com.example.gestion_academica.repository.EstudianteRepository;
import com.example.gestion_academica.service.EstudianteService;
import com.example.gestion_academica.web.error.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class EstudianteServiceImpl implements EstudianteService {

    private final EstudianteRepository repo;

    // ------- CRUD existentes sobre entidad -------
    @Override public List<Estudiante> findAll() { return repo.findAll(); }

    @Override
    public Estudiante findById(String id) {
        return repo.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Estudiante no encontrado: " + id));
    }

    @Override
    public Estudiante create(Estudiante estudiante) {
        if (repo.existsById(estudiante.getCarnet()))
            throw new IllegalArgumentException("Ya existe un estudiante con carnet " + estudiante.getCarnet());
        return repo.save(estudiante);
    }

    @Override
    public Estudiante update(String id, Estudiante estudiante) {
        Estudiante existente = findById(id);
        existente.setNombre(estudiante.getNombre());
        existente.setApellido(estudiante.getApellido());
        existente.setFechaNacimiento(estudiante.getFechaNacimiento());
        return repo.save(existente);
    }

    @Override
    public void delete(String id) {
        repo.delete(findById(id));
    }

    // ------- NUEVO: DTOs + filtros -------
    @Override
    @Transactional(readOnly = true)
    public List<EstudianteDTO> findAllDTO(String nombre, String apellido) {
        List<Estudiante> data;
        boolean hasNombre = nombre != null && !nombre.isBlank();
        boolean hasApellido = apellido != null && !apellido.isBlank();

        if (hasNombre && hasApellido) {
            data = repo.findByNombreContainingIgnoreCaseAndApellidoContainingIgnoreCase(nombre, apellido);
        } else if (hasNombre) {
            data = repo.findByNombreContainingIgnoreCase(nombre);
        } else if (hasApellido) {
            data = repo.findByApellidoContainingIgnoreCase(apellido);
        } else {
            data = repo.findAll();
        }

        return data.stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public EstudianteDTO findDTOById(String id) {
        return toDTO(findById(id));
    }

    // ------- mapper privado -------
    private EstudianteDTO toDTO(Estudiante e) {
        int edad = 0;
        if (e.getFechaNacimiento() != null) {
            edad = Period.between(e.getFechaNacimiento(), LocalDate.now()).getYears();
        }
        return EstudianteDTO.builder()
                .carnet(e.getCarnet())
                .nombreCompleto(e.getNombre() + " " + e.getApellido())
                .edad(edad)
                .build();
    }
}
