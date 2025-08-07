package com.example.gestion_academica.service.impl;


import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    public List<Estudiante> findAll() {
        return repo.findAll();
    }

    @Override
    public Estudiante findById(String id) {
        return repo.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Estudiante no encontrado: " + id));
    }

    @Override
    public Estudiante create(Estudiante estudiante) {
        if (repo.existsById(estudiante.getCarnet())) {
            throw new IllegalArgumentException("Ya existe un estudiante con carnet " + estudiante.getCarnet());
        }
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
        Estudiante existente = findById(id);
        repo.delete(existente);
    }
}
