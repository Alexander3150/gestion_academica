package com.example.gestion_academica.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class EstudianteDTO {
    private String carnet;
    private String nombreCompleto;
    private int edad;       // Exponemos edad en vez de fecha completa
}
