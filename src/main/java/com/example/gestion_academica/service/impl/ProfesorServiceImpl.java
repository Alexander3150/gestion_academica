package com.example.gestion_academica.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.gestion_academica.dto.ProfesorDTO;
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

    // ------- CRUD existentes sobre entidad -------
    @Override public List<Profesor> findAll() { return repo.findAll(); }

    @Override
    public Profesor findById(String id) {
        return repo.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Profesor no encontrado: " + id));
    }

    @Override
    public Profesor create(Profesor profesor) {
        if (repo.existsById(profesor.getCodProfesor()))
            throw new IllegalArgumentException("Código ya existe: " + profesor.getCodProfesor());
        if (repo.existsByCorreoIgnoreCase(profesor.getCorreo()))
            throw new IllegalArgumentException("Correo ya en uso: " + profesor.getCorreo());
        return repo.save(profesor);
    }

    @Override
    public Profesor update(String id, Profesor profesor) {
        Profesor existente = findById(id);
        if (!existente.getCorreo().equalsIgnoreCase(profesor.getCorreo())
                && repo.existsByCorreoIgnoreCase(profesor.getCorreo()))
            throw new IllegalArgumentException("Correo ya en uso: " + profesor.getCorreo());
        existente.setNombre(profesor.getNombre());
        existente.setApellido(profesor.getApellido());
        existente.setCorreo(profesor.getCorreo());
        return repo.save(existente);
    }

    @Override
    public void delete(String id) {
        repo.delete(findById(id));
    }

    // ------- : DTOs + filtros -------
    @Override
    @Transactional(readOnly = true)
    public List<ProfesorDTO> findAllDTO(String q, String apellido) {
        List<Profesor> data;

        if (q != null && !q.isBlank()) {
            data = repo.findByNombreContainingIgnoreCaseOrApellidoContainingIgnoreCase(q, q);
        } else if (apellido != null && !apellido.isBlank()) {
            data = repo.findByApellidoContainingIgnoreCase(apellido);
        } else {
            data = repo.findAll();
        }

        return data.stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ProfesorDTO findDTOById(String id) {
        return toDTO(findById(id));
    }

    // ------- mapper privado -------
    private ProfesorDTO toDTO(Profesor p) {
        return ProfesorDTO.builder()
                .codProfesor(p.getCodProfesor())
                .nombreCompleto(p.getNombre() + " " + p.getApellido())
                .correo(p.getCorreo())
                .build();
    }
}
 