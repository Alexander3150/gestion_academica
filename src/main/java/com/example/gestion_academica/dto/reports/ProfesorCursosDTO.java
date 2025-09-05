package com.example.gestion_academica.dto.reports;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProfesorCursosDTO {
    private final String codProfesor;
    private final String nombreCompleto;
    private final Long totalCursos; // COUNT(DISTINCT ...)
}
