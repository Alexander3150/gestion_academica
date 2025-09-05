package com.example.gestion_academica.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.gestion_academica.dto.reports.CursoNotaPromedioDTO;
import com.example.gestion_academica.dto.reports.InscritosPorCicloDTO;
import com.example.gestion_academica.dto.reports.ProfesorCursosDTO;
import com.example.gestion_academica.dto.reports.TopCursoPromedioDTO;
import com.example.gestion_academica.entities.Inscripcion;

public interface InscripcionRepository extends JpaRepository<Inscripcion, Long> {

    // R1: número total de cursos que imparte cada profesor (DISTINCT por curso)
    @Query("""
        SELECT NEW com.example.gestion_academica.dto.reports.ProfesorCursosDTO(
            p.codProfesor,
            CONCAT(p.nombre, ' ', p.apellido),
            COUNT(DISTINCT a.curso.codCurso)
        )
        FROM Inscripcion i
        JOIN i.asignacion a
        JOIN a.profesor p
        GROUP BY p.codProfesor, p.nombre, p.apellido
        ORDER BY COUNT(DISTINCT a.curso.codCurso) DESC
    """)
    List<ProfesorCursosDTO> cursosPorProfesor();

    // R2: nota promedio por curso
    @Query("""
        SELECT NEW com.example.gestion_academica.dto.reports.CursoNotaPromedioDTO(
            c.codCurso,
            c.nombre,
            AVG(i.notaFinal)
        )
        FROM Inscripcion i
        JOIN i.asignacion a
        JOIN a.curso c
        GROUP BY c.codCurso, c.nombre
        ORDER BY c.codCurso
    """)
    List<CursoNotaPromedioDTO> promedioPorCurso();

    // R3: estudiantes inscritos por ciclo
    @Query("""
        SELECT NEW com.example.gestion_academica.dto.reports.InscritosPorCicloDTO(
            a.periodo.ciclo,
            COUNT(DISTINCT i.estudiante.carnet)
        )
        FROM Inscripcion i
        JOIN i.asignacion a
        GROUP BY a.periodo.ciclo
        ORDER BY a.periodo.ciclo
    """)
    List<InscritosPorCicloDTO> estudiantesPorCiclo();

    // R4: Top N cursos por promedio de nota (limit con Pageable)
    @Query("""
        SELECT NEW com.example.gestion_academica.dto.reports.TopCursoPromedioDTO(
            c.codCurso,
            c.nombre,
            AVG(i.notaFinal)
        )
        FROM Inscripcion i
        JOIN i.asignacion a
        JOIN a.curso c
        GROUP BY c.codCurso, c.nombre
        ORDER BY AVG(i.notaFinal) DESC
    """)
    List<TopCursoPromedioDTO> topCursosPromedio(Pageable pageable);
}
