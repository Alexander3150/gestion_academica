package com.example.gestion_academica.service;

import java.util.List;

import com.example.gestion_academica.dto.reports.CursoNotaPromedioDTO;
import com.example.gestion_academica.dto.reports.InscritosPorCicloDTO;
import com.example.gestion_academica.dto.reports.ProfesorCursosDTO;
import com.example.gestion_academica.dto.reports.TopCursoPromedioDTO;

public interface ReporteService {
    List<ProfesorCursosDTO> cursosPorProfesor();
    List<CursoNotaPromedioDTO> promedioPorCurso();
    List<InscritosPorCicloDTO> estudiantesPorCiclo();
    List<TopCursoPromedioDTO> topCursosPromedio(int limit);
}
