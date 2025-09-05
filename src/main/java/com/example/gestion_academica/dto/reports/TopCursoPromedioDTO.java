package com.example.gestion_academica.dto.reports;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TopCursoPromedioDTO {
    private final String codCurso;
    private final String nombreCurso;
    private final Double notaPromedio;
}
