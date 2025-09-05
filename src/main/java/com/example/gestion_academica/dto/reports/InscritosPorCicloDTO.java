package com.example.gestion_academica.dto.reports;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class InscritosPorCicloDTO {
    private final Short ciclo;           
    private final Long totalEstudiantes;
}
