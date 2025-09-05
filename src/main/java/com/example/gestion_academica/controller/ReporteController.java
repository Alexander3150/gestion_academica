package com.example.gestion_academica.controller;


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.example.gestion_academica.dto.reports.CursoNotaPromedioDTO;
import com.example.gestion_academica.dto.reports.InscritosPorCicloDTO;
import com.example.gestion_academica.dto.reports.ProfesorCursosDTO;
import com.example.gestion_academica.dto.reports.TopCursoPromedioDTO;
import com.example.gestion_academica.service.ReporteService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/reportes")
@RequiredArgsConstructor
@Validated
public class ReporteController {

    private final ReporteService service;

    // Reporte 1: número total de cursos que imparte cada profesor
    @GetMapping("/cursos-por-profesor")
    public ResponseEntity<List<ProfesorCursosDTO>> cursosPorProfesor() {
        return ResponseEntity.ok(service.cursosPorProfesor());
    }

    // Reporte 2: nota promedio por curso
    @GetMapping("/promedio-por-curso")
    public ResponseEntity<List<CursoNotaPromedioDTO>> promedioPorCurso() {
        return ResponseEntity.ok(service.promedioPorCurso());
    }

    // Reporte 3: cantidad de estudiantes inscritos por ciclo
    @GetMapping("/estudiantes-por-ciclo")
    public ResponseEntity<List<InscritosPorCicloDTO>> estudiantesPorCiclo() {
        return ResponseEntity.ok(service.estudiantesPorCiclo());
    }

    // Reporte 4: top N cursos con mayor nota promedio (N por query param, default 3)
    @GetMapping("/top-cursos-promedio")
    public ResponseEntity<List<TopCursoPromedioDTO>> topCursosPromedio(
            @RequestParam(name = "limit", required = false, defaultValue = "3") int limit) {
        return ResponseEntity.ok(service.topCursosPromedio(limit));
    }
}
