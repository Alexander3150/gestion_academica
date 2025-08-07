package com.example.gestion_academica.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.gestion_academica.entities.Profesor;
import com.example.gestion_academica.repository.ProfesorRepository;
import com.example.gestion_academica.service.ProfesorService;
import com.example.gestion_academica.web.error.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ProfesorServiceImpl implements ProfesorService {

    private final ProfesorRepository repo;

    @Override
    public List<Profesor> findAll() {
        return repo.findAll();
    }

    @Override
    public Profesor findById(String id) {
        return repo.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Profesor no encontrado: " + id));
    }

    @Override
    public Profesor create(Profesor profesor) {
        if (repo.existsById(profesor.getCodProfesor())) {
            throw new IllegalArgumentException("Ya existe un profesor con código " + profesor.getCodProfesor());
        }
        if (repo.existsByCorreoIgnoreCase(profesor.getCorreo())) {
            throw new IllegalArgumentException("El correo ya está en uso: " + profesor.getCorreo());
        }
        return repo.save(profesor);
    }

    @Override
    public Profesor update(String id, Profesor profesor) {
        Profesor existente = findById(id);

        if (!existente.getCorreo().equalsIgnoreCase(profesor.getCorreo())
                && repo.existsByCorreoIgnoreCase(profesor.getCorreo())) {
            throw new IllegalArgumentException("El correo ya está en uso: " + profesor.getCorreo());
        }

        existente.setNombre(profesor.getNombre());
        existente.setApellido(profesor.getApellido());
        existente.setCorreo(profesor.getCorreo());
        return repo.save(existente);
    }

    @Override
    public void delete(String id) {
        Profesor existente = findById(id);
        repo.delete(existente);
    }
}