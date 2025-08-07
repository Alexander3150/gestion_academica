package com.example.gestion_academica.service.impl;


import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.gestion_academica.entities.Curso;
import com.example.gestion_academica.repository.CursoRepository;
import com.example.gestion_academica.service.CursoService;   // <--- ESTE IMPORT
import com.example.gestion_academica.web.error.ResourceNotFoundException; // o comenta esta línea si aún no creas la clase

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CursoServiceImpl implements CursoService {

    private final CursoRepository repo;

    @Override
    public List<Curso> findAll() {
        return repo.findAll();
    }

    @Override
    public Curso findById(String id) {
        return repo.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado: " + id));
            
    }

    @Override
    public Curso create(Curso curso) {
        if (repo.existsById(curso.getCodCurso())) {
            throw new IllegalArgumentException("Ya existe un curso con código " + curso.getCodCurso());
        }
        return repo.save(curso);
    }

    @Override
    public Curso update(String id, Curso curso) {
        Curso existente = findById(id);
        existente.setNombre(curso.getNombre());
        existente.setCreditos(curso.getCreditos());
        return repo.save(existente);
    }

    @Override
    public void delete(String id) {
        Curso existente = findById(id);
        repo.delete(existente);
    }
}
