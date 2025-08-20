package com.example.gestion_academica.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.gestion_academica.dto.CursoDTO;
import com.example.gestion_academica.entities.Curso;
import com.example.gestion_academica.repository.CursoRepository;
import com.example.gestion_academica.service.CursoService;
import com.example.gestion_academica.web.error.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CursoServiceImpl implements CursoService {

    private final CursoRepository repo;

    // ------- CRUD existentes sobre entidad -------
    @Override public List<Curso> findAll() { return repo.findAll(); }

    @Override
    public Curso findById(String id) {
        return repo.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Curso no encontrado: " + id));
    }

    @Override
    public Curso create(Curso curso) {
        if (repo.existsById(curso.getCodCurso()))
            throw new IllegalArgumentException("Ya existe un curso con código " + curso.getCodCurso());
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
        repo.delete(findById(id));
    }

    // ------- NUEVO: DTOs + filtros -------
    @Override
    @Transactional(readOnly = true)
    public List<CursoDTO> findAllDTO(String nombre, Short creditos) {
        List<Curso> data;
        boolean hasNombre = nombre != null && !nombre.isBlank();
        boolean hasCred = creditos != null;

        if (hasNombre && hasCred) {
            data = repo.findByNombreContainingIgnoreCaseAndCreditos(nombre, creditos);
        } else if (hasNombre) {
            data = repo.findByNombreContainingIgnoreCase(nombre);
        } else if (hasCred) {
            data = repo.findByCreditos(creditos);
        } else {
            data = repo.findAll();
        }

        return data.stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public CursoDTO findDTOById(String id) {
        return toDTO(findById(id));
    }

    // ------- mapper privado -------
    private CursoDTO toDTO(Curso c) {
        return CursoDTO.builder()
                .codCurso(c.getCodCurso())
                .nombre(c.getNombre())
                .creditos(c.getCreditos())
                .build();
    }
}
