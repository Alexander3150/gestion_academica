package com.example.gestion_academica.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ProfesorDTO {
    private String codProfesor;
    private String nombreCompleto;
    private String correo;
}
