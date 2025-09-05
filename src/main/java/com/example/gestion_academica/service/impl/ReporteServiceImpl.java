package com.example.gestion_academica.service.impl;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.gestion_academica.dto.reports.CursoNotaPromedioDTO;
import com.example.gestion_academica.dto.reports.InscritosPorCicloDTO;
import com.example.gestion_academica.dto.reports.ProfesorCursosDTO;
import com.example.gestion_academica.dto.reports.TopCursoPromedioDTO;
import com.example.gestion_academica.repository.InscripcionRepository;
import com.example.gestion_academica.service.ReporteService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReporteServiceImpl implements ReporteService {

    private final InscripcionRepository inscripcionRepo;

    @Override
    public List<ProfesorCursosDTO> cursosPorProfesor() {
        return inscripcionRepo.cursosPorProfesor();
    }

    @Override
    public List<CursoNotaPromedioDTO> promedioPorCurso() {
        return inscripcionRepo.promedioPorCurso();
    }

    @Override
    public List<InscritosPorCicloDTO> estudiantesPorCiclo() {
        return inscripcionRepo.estudiantesPorCiclo();
    }

    @Override
    public List<TopCursoPromedioDTO> topCursosPromedio(int limit) {
        int size = (limit > 0) ? limit : 3;
        return inscripcionRepo.topCursosPromedio(PageRequest.of(0, size));
    }
}
